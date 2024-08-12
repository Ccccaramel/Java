package com.ding.office.webSocket;

import com.alibaba.fastjson2.JSON;
import com.ding.office.constant.DictionaryCode;
import com.ding.office.info.ChatInfo;
import com.ding.office.info.UserInfo;
import com.ding.office.service.ChineseChessService;
import com.ding.office.service.ChineseChessSnapshotService;
import com.ding.office.service.LoginService;
import com.ding.office.service.UserService;
import com.ding.office.thread.ChineseChessRunnable;
import com.ding.office.utils.CloneUtil;
import com.ding.office.utils.MathUtils;
import com.ding.office.utils.MessageUtils;
import com.ding.office.utils.TimeUtils;
import com.ding.office.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@ServerEndpoint(value = "/linkChineseChess/{token}")
@Component
public class ChineseChessEndpoint {

    private static final Map<Integer,Session> onlineUsers = new ConcurrentHashMap<>();  // 所有在线玩家
    private static final CopyOnWriteArrayList<ChineseChessRoomVo> onlineRoomList = new CopyOnWriteArrayList<>();  // 房间列表

    private static LoginService loginService;
    private static ChineseChessService chineseChessService;
    private static ChineseChessSnapshotService chineseChessSnapshotService;
    private static UserService userService;

    private UserInfo user;
    private ChatInfo chatInfo;

    /**
     * 建立 WebSocket 连接后调用,将用户信息保存
     *   1.将 Session 保存
     *   2.广播消息
     *   3.如果该用户已经在房间内,需要将相关数据传递给他
     * @param session webSocket 会话
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value="token") String token){
        log.info("onOpen:{}",token);
        user= getUserInfo(token);  // 根据 token 获取用户信息
        if(user==null){  // token 失效
            try {
                session.getBasicRemote().sendText(MessageUtils.getMessage(false,9,"登录状态失效!请重新登录!",null));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        onlineUsers.put(user.getId(),session);  // 将新上线的用户 session 保存

        // 广播消息
//        chatInfo = new ChatInfo();
//        chatInfo.setContent(user.getName()+"上线了");
//        chatInfo.setCreateTimeStr(TimeUtils.getNow());
//        broadcastAllUsers(true);
//        broadcastAllUsers(2);

        refreshRoomList();

        HashMap<String,Object> skillResult=new HashMap<>();
        skillResult.put("skillList", ChineseChessRoomVo.CHINESE_CHESS_SKILL_LIST);
        String skillData = JSON.toJSONString(skillResult);
        try {
            session.getBasicRemote().sendText(MessageUtils.getMessage(true,17,"发送技能信息!",skillData));  // ws 连接成功后立即将技能信息发送过去
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 当用户关闭浏览器,退出账号或其它原因导致 ws 断开,前端会删除房间ID,导致再次进入无法获取复原之前的信息
        onlineRoomList.forEach(e->{
            if( Objects.equals(e.getRoomOwner(),user.getId()) || Objects.equals(e.getRival(),user.getId()) ){  // 用户建立 ws 连接,但发现早已在某一对局内,须立即将拉入对剧中并恢复棋盘
                try {
                    HashMap<String,Object> result=new HashMap<>();
                    result.put("roomId", e.getId());
                    String data = JSON.toJSONString(result);
                    session.getBasicRemote().sendText(MessageUtils.getMessage(true,16,"欢迎回来!请继续对战吧!",data));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * 通知所有在线用户刷新房间列表信息
      */
    private void broadcastAllUsers(Integer instruct) {
        Set<Map.Entry<Integer, Session>> entrySet = onlineUsers.entrySet();
        for (Map.Entry<Integer, Session> stringSessionEntry : entrySet) {
            Session session = stringSessionEntry.getValue();
            try {
                String message = MessageUtils.getAllRoomInfo(true,instruct,"向所有在线用户发送房间列表信息",getOnlineRoomListInfo());
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private CopyOnWriteArrayList<ChineseChessRoomVo> getOnlineRoomListInfo() {  // 获取所有房间的部分信息
        CopyOnWriteArrayList<ChineseChessRoomVo> list=new CopyOnWriteArrayList<>();
        for (ChineseChessRoomVo chineseChessRoomVo :onlineRoomList) {
            ChineseChessRoomVo chessVo = new ChineseChessRoomVo();
            chessVo.setId(chineseChessRoomVo.getId());
            chessVo.setStatus(chineseChessRoomVo.getStatus());
            chessVo.setNeedPassword(!chineseChessRoomVo.getPassword().isBlank());
            if(chineseChessRoomVo.getRival()!=null){
                chessVo.setMsg("该房间已指定用户ID为【"+ chineseChessRoomVo.getRival()+"】为对手");
            }
            chessVo.setName(chineseChessRoomVo.getName());
            chessVo.setNote(chineseChessRoomVo.getNote());
            list.add(chessVo);
        }
        return list;
    }

    /**
     * 浏览器发送到服务端时调用
     * @param message 客户端发送过来的数据
     */
    @OnMessage
    public void onMessage(String message){
        log.info("onMessage - 收到信息: {}",message);
        ChineseChessRoomVo chineseChessRoomVo = JSON.parseObject(message, ChineseChessRoomVo.class);

        switch (chineseChessRoomVo.getInstruct()){
            case 1:
                createRoom(chineseChessRoomVo);
                break;
            case 2:
                refreshRoomList();
                break;
            case 3:
                joinRoom(chineseChessRoomVo);
                break;
            case 4:
                exitRoom(chineseChessRoomVo);
                break;
            case 7:
                getRoomInfo(chineseChessRoomVo);
                break;
            case 8:
                getPlayerInfo(chineseChessRoomVo);
                break;
            case 10:
                changeSkill(chineseChessRoomVo);
                break;
            case 11:
                ready(chineseChessRoomVo);
                break;
            case 12:
                cancelReady(chineseChessRoomVo);
                break;
            case 14:
//                selectNumber(chineseChessRoomVo);  // 不生效
                break;
            case 15:
                action(chineseChessRoomVo);
                break;
            case 18:
                submission(chineseChessRoomVo);
                break;
            case 20:
                stalemate(chineseChessRoomVo);
                break;
            default:
                break;
        }
    }

    /**
     * 投降,本局结束,当前用户败,对方胜
     * @param chineseChessRoomVo
     */
    private void submission(ChineseChessRoomVo chineseChessRoomVo) {
        for (ChineseChessRoomVo vo : onlineRoomList) {
            if (vo.getId().equals(chineseChessRoomVo.getId())) {  // 找到对应房间
                endBattle(vo,vo.isActorIsRoomOwner()?vo.getRival():vo.getRoomOwner(),vo.isActorIsRoomOwner(),"败方投降","当前玩家投降,结束对战!",DictionaryCode.CHINESE_CHESS_WINNER_WAY_2,"战败!","对方已投降!获胜!");  // 结束对战
            }
        }
    }

    /**
     * 和棋
     */
    private void stalemate(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo : onlineRoomList) {
            if (vo.getId().equals(chineseChessRoomVo.getId())) {  // 找到对应房间
                boolean isRoomOwner=Objects.equals(user.getId(),vo.getRoomOwner());  // 当前用户是否为房主
                if(isRoomOwner){  // 房主同意了和棋
                    vo.setRoomOwnerStalemate(true);
                    if(vo.isRivalStalemate()){  // 对手也同意了和棋
                        endBattle(vo,null,isRoomOwner,"和棋","双方和棋,结束对战!",DictionaryCode.CHINESE_CHESS_WINNER_WAY_1,"和棋,对战结束!","对方同意和棋,对战结束!");  // 结束对战
                    }
                    else{  // 对手还未同意和棋,通知对手,房主想和棋
                        try {
                            onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,20,"对方发起和棋请求!",null));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else{  // 对手同意了和棋
                    vo.setRivalStalemate(true);
                    if(vo.isRoomOwnerStalemate()){  // 房主也同意了和棋
                        endBattle(vo,null, false,"和棋","双方和棋,结束对战!",DictionaryCode.CHINESE_CHESS_WINNER_WAY_1,"和棋,对战结束!","对方同意和棋,对战结束!");  // 结束对战
                    }
                    else{  // 房主还未同意和棋,通知房主,对手想和棋
                        try {
                            onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,20,"对方发起和棋请求!",null));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }


            }
        }
    }

    /**
     * 结束对战,保存数据,回显数据以及相关信息
     * @param vo 房间所有信息
     * @param winner 获胜玩家ID
     * @param isRoomOwner 执行该方法的用户是否为房主
     * @param note 对战结果备注
     * @param info 打印日志
     * @param victoryMethods 战胜方式
     * @param oneselfMsg 发送给当前用户的文本信息
     * @param opponentMsg 发送给对手的文本信息
     */
    public static void endBattle(ChineseChessRoomVo vo,Integer winner,boolean isRoomOwner,String note,String info,Integer victoryMethods,String oneselfMsg,String opponentMsg){
        log.info(info);
        log.info("房间ID: " + vo.getId());
        ChineseChessVo chineseChessVo = new ChineseChessVo();
        chineseChessVo.setId(vo.getRoomId());
        chineseChessVo.setWinner(winner);
        chineseChessVo.setType(victoryMethods);
        chineseChessVo.setNote(note);
        chineseChessService.settlement(chineseChessVo);  // 对局结果更新

        vo.setStatus(2);
        vo.setRoomOwnerSkill(0);
        vo.setRivalSkill(0);
        vo.setRoomOwnerIsReady(false);
        vo.setRivalIsReady(false);
        vo.setRoomOwnerStalemate(false);
        vo.setRivalStalemate(false);
        vo.initRound();

        // 返回棋盘信息给当前用户
        HashMap<String, Object> result = new HashMap<>();
        result.put("isActor", false);  // 双方都不允许控制棋子
        result.put("status", 2);  // 状态重置

        // 发送给自己
        boolean win = winner != null && winner.equals(isRoomOwner ? vo.getRoomOwner() : vo.getRival());  // 当前用户是不是赢家
        result.put("soundType", win?3:4);  // 音效
        result.put("isRoomOwner", isRoomOwner);  // 当前用户是否为房主
        result.put("oneself", isRoomOwner?vo.getRed():image(vo.getBlack()));  // 自己
        result.put("opponent", isRoomOwner?vo.getBlack():image(vo.getRed()));  // 对手
        result.put("tip", isRoomOwner? getSealTip(vo):imageTip(getSealTip(vo)));  // 对战已结束,仅显示封印tip,如果是黑方(对手)则需要将tip镜像
        try {
            // 发送给自己
            onlineUsers.get(isRoomOwner?vo.getRoomOwner():vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(win,18,oneselfMsg,JSON.toJSONString(result)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        win = winner != null && winner.equals(isRoomOwner ? vo.getRival():vo.getRoomOwner() );  // 对手是不是赢家
        // 发送给对方
        result.put("soundType", win ?3:4);  // 音效
        result.put("isRoomOwner", !isRoomOwner);
        result.put("oneself", isRoomOwner?image(vo.getBlack()):vo.getRed());  // 自己
        result.put("opponent", isRoomOwner?image(vo.getRed()):vo.getBlack());  // 对手
        result.put("tip", isRoomOwner? getSealTip(vo):imageTip(getSealTip(vo)));  // 对战已结束,仅显示封印tip,如果是黑方(对手)则需要将tip镜像
        try {
            // 发送给对方
            onlineUsers.get(isRoomOwner?vo.getRival():vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(win,18,opponentMsg,JSON.toJSONString(result)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建房间
     * @param chineseChessRoomVo
     */
    private void createRoom(ChineseChessRoomVo chineseChessRoomVo){
        if(chineseChessRoomVo.getRival()!=null&&user.getId().equals(chineseChessRoomVo.getRival())){  // 指定的对手是自己
            try {
                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,1,"对手不可以指定为自己!",null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            chineseChessRoomVo.setId(String.valueOf(MathUtils.createNo(6)));  // 随机生成一个6位数作为id
            chineseChessRoomVo.setRoomOwner(user.getId());
            chineseChessRoomVo.setStatus(1);
            chineseChessRoomVo.setRecentActivityTime(LocalDateTime.now());  // 创建房间,最开始的活动时间
            chineseChessRoomVo.setNeedPassword(!chineseChessRoomVo.getPassword().isBlank());
            onlineRoomList.add(chineseChessRoomVo);
            broadcastAllUsers(2);  // 更新在线房间列表
            HashMap<String,Object> result=new HashMap<>();
            result.put("roomId", chineseChessRoomVo.getId());
            result.put("roomName", chineseChessRoomVo.getName());
            String data = JSON.toJSONString(result);
            try {
                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,1,"房间创建成功!",data));  // 告诉自己房间的 id
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新当前用户的房间列表
     */
    private void refreshRoomList(){
        try {
            onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getAllRoomInfo(true,2,"向当前用户发送所有房间列表信息",getOnlineRoomListInfo()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加入房间
     * @param chineseChessRoomVo
     */
    private void joinRoom(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo:onlineRoomList) {
            if(vo.getRoomOwner().equals(user.getId())){  // 自己进入自己创建的房间
                try {
                    onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,3,"不可以加入自己的房间!",null));
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(Objects.equals(vo.getId(), chineseChessRoomVo.getId())){  // 存在此房间
                if(vo.getStatus()!=1){  // 已在对战中,直接拒绝
                    try {
                        onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,3,"已在对战中!不可加入!",null));
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(vo.getRival()!=null ){  // 房间指定了对手
                    if(vo.getRival().equals(user.getId())){  // 指定的对手就是当前用户
                        cleanTip(vo,false);
                        initChineseChess(vo);
                        vo.setStatus(2);
                        HashMap<String,Object> result=new HashMap<>();
                        result.put("roomId", vo.getId());
                        result.put("roomName", vo.getName());
                        String data = JSON.toJSONString(result);
                        try {
                            onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,3,"指定对手进入房间成功!",data));
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        broadcastAllUsers(2);
                    }
                    else{  // 指定的对手不是当前用户
                        try {
                            onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,3,"进入房间失败!你不是该房间指定的对手!",null));
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{  // 没有指定对手,则判断密码
                    if(vo.isNeedPassword()){  // 需要密码
                        if(Objects.equals(vo.getPassword(), chineseChessRoomVo.getPassword())){  // 需要密码且密码一致
                            cleanTip(vo,false);
                            initChineseChess(vo);


                            vo.setRival(user.getId());
                            /**
                             * 为什么不在创建房间时重置双方的技能和准备状态?
                             * 当一局对局结束后,对手离开了房间,并未立即重置双方的状态信息和棋盘信息,目的是为了让还留在房间内的用户可以继续查看棋盘
                             * 当新对手加入且双方都已准备后再重置相关信息
                             */
                            vo.setRoomOwnerSkill(0);
                            vo.setRivalSkill(0);
                            vo.setRoomOwnerIsReady(false);
                            vo.setRivalIsReady(false);
                            vo.setRoomOwnerStalemate(false);
                            vo.setRivalStalemate(false);

                            HashMap<String,Object> result=new HashMap<>();
                            result.put("roomId", vo.getId());
                            result.put("roomName", vo.getName());
                            String data = JSON.toJSONString(result);
                            try {
                                onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,6,"对手已进入房间!",null));  // 通知房主对手已进入房间
                                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,3,"暗号正确,进入房间成功!",data));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            broadcastAllUsers(2);
                            return;
                        }
                        else{
                            try {
                                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,3,"进入房间失败!暗号错误!",null));
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{  // 等待对手状态,没有指定对手,没有密码,直接进入
                        cleanTip(vo,false);
                        vo.setRival(user.getId());

                        vo.setRoomOwnerSkill(0);
                        vo.setRivalSkill(0);
                        vo.setRoomOwnerIsReady(false);
                        vo.setRivalIsReady(false);
                        vo.setRoomOwnerStalemate(false);
                        vo.setRivalStalemate(false);

                        initChineseChess(vo);

                        HashMap<String,Object> result=new HashMap<>();
                        result.put("roomId", vo.getId());
                        result.put("roomName", vo.getName());
                        String data = JSON.toJSONString(result);
                        try {
                            onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,6,"对手已进入房间!",null));  // 通知房主对手已进入房间
                            onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,3,"进入房间成功!",data));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        broadcastAllUsers(2);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 主动退出房间,仅双方都不是【已准备】条件下
     * @param chineseChessRoomVo
     */
    private void exitRoom(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo:onlineRoomList) {
            if(vo.getId().equals(chineseChessRoomVo.getId())){
                if(vo.getRoomOwner().equals(user.getId())){  // 房主要退出
                    try {
                        onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,4,"你已退出房间!",null));  // 通知自己(房主)
                        if(vo.getRival()!=null){  // 对手还在房间里
                            onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,4,"房主已退出房间!",null));  // 通知对手,并退出
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onlineRoomList.remove(vo);  // 移除房间
                }
                else{  // 对手退出,但房间还在,且房主依旧在房间内,房间状态置为1,即等待对手进入
                    vo.setRival(null);
                    vo.setStatus(1);
                    vo.setSkill(0);
                    vo.setRoomOwnerSkill(0);
                    vo.setRivalSkill(0);
                    vo.setRivalIsReady(false);
                    vo.setRoomOwnerIsReady(false);
                    vo.setRoomOwnerStalemate(false);
                    vo.setRivalStalemate(false);
                    vo.initRound();
                    try {
                        onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,5,"对手已退出房间!",null));  // 通知房主
                        onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,4,"你已退出房间!",null));  // 通知自己,并退出
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                broadcastAllUsers(2);
                return;
            }
        }
    }

    /**
     * 根据房间 id 获取房间基本信息
     */
    private void getRoomInfo(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo:onlineRoomList) {
            if(vo.getId().equals(chineseChessRoomVo.getId())) {
                HashMap<String,Object> result=new HashMap<>();
                result.put("roomId", vo.getId());
                result.put("roomName", vo.getName());
                result.put("roomOwner", vo.getRoomOwner());
                result.put("rival", vo.getRival());
                result.put("status",vo.getStatus());
                boolean isRoomOwner = vo.getRoomOwner().equals(user.getId());
                result.put("isRoomOwner", isRoomOwner);
                result.put("selfSkill", isRoomOwner?vo.getRoomOwnerSkill():vo.getRivalSkill());
                result.put("rivalSkill", isRoomOwner?vo.getRivalSkill():vo.getRoomOwnerSkill());
                result.put("selfIsReady", isRoomOwner?vo.isRoomOwnerIsReady():vo.isRivalIsReady());
                result.put("rivalIsReady", isRoomOwner?vo.isRivalIsReady():vo.isRoomOwnerIsReady());
                result.put("isStalemate", isRoomOwner?vo.isRoomOwnerStalemate():vo.isRivalStalemate());
                String data = JSON.toJSONString(result);
                try {
                    onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,7,"数据已重新加载!将双方技能,是否准备,房间基本信息发送给当前用户",data));
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,7,"未找到房间数据!",null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取棋盘信息
     * @param chineseChessRoomVo
     */
    private void getPlayerInfo(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo:onlineRoomList) {
            if (vo.getId().equals(chineseChessRoomVo.getId())) {
                HashMap<String, Object> result = new HashMap<>();
                result.put("selfInfo", userService.findBriefInfoById(user.getId()));
                result.put("selfBattle", chineseChessService.getBattleInfo(user.getId()));
                result.put("status", vo.getStatus());
                result.put("round", vo.getRound());
                result.put("selfSkill", user.getId().equals(vo.getRoomOwner())?vo.getRoomOwnerSkill():vo.getRivalSkill());  // 技能信息
                if(vo.getRival() != null){  // 双方已到场
                    if(user.getId().equals(vo.getRoomOwner())){  // 当前用户是房主
                        result.put("isRoomOwner", true);
                        result.put("rivalInfo", userService.findBriefInfoById(vo.getRival()));
                        result.put("rivalBattle", chineseChessService.getBattleInfo(vo.getRival()));
                        result.put("rivalSkill", vo.getRivalSkill());
                        result.put("oneself", vo.getRed());
                        result.put("selfIsReady",vo.isRoomOwnerIsReady());
                        result.put("opponent", vo.getBlack());
                        result.put("rivalIsReady",vo.isRivalIsReady());
                        result.put("rivalIsReady",vo.isRivalIsReady());
                        result.put("isStalemate", vo.isRoomOwnerStalemate());
                        if(Arrays.asList(3,4).contains(vo.getStatus())){
                            result.put("isActor", vo.isActorIsRoomOwner());
                            result.put("tip", vo.isActorIsRoomOwner()? vo.getChineseChessBaseTip():getSealTip(vo));  // 获取tip,如果当前回合自己是行动方,则获取所有tip,如果不是则只给【封印】tip
                        }
                    }
                    else{  // 当前用户不是房主
                        result.put("isRoomOwner", false);
                        result.put("rivalInfo", userService.findBriefInfoById(vo.getRoomOwner()));
                        result.put("rivalBattle", chineseChessService.getBattleInfo(vo.getRoomOwner()));
                        result.put("rivalSkill", vo.getRoomOwnerSkill());
                        result.put("oneself", this.image(vo.getBlack()));
                        result.put("selfIsReady",vo.isRivalIsReady());
                        result.put("opponent", this.image(vo.getRed()));
                        result.put("rivalIsReady",vo.isRoomOwnerIsReady());
                        result.put("isStalemate", vo.isRivalStalemate());
                        if(Arrays.asList(3,4).contains(vo.getStatus())) {
                            result.put("isActor", !vo.isActorIsRoomOwner());
                            result.put("tip", vo.isActorIsRoomOwner()? this.imageTip(getSealTip(vo)):this.imageTip(vo.getChineseChessBaseTip()));  // 获取tip,如果当前回合自己是行动方,则获取所有tip,如果不是则只给【封印】tip
                        }
                    }
                }

                String data = JSON.toJSONString(result);
                try {
                    onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,8,"获取双方棋子和tip信息",data));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将【封印】tip从tips中分离出来,只获取【封印】tip
     * @param vo
     * @return
     */
    private static ArrayList<ChineseChessBaseTipVo> getSealTip(ChineseChessRoomVo vo) {
        ArrayList<ChineseChessBaseTipVo> tips=new ArrayList<>();
        for (ChineseChessBaseTipVo chineseChessBaseTipVo : vo.getChineseChessBaseTip()) {
            if(chineseChessBaseTipVo.getType()==2){
                tips.add(chineseChessBaseTipVo);
            }
        }
        return tips;
    }

    /**
     * 变更技能
     * @param chineseChessRoomVo
     */
    private void changeSkill(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo : onlineRoomList) {
            if (vo.getId().equals(chineseChessRoomVo.getId())) {
                HashMap<String, Object> result = new HashMap<>();
                result.put("rivalSkill", chineseChessRoomVo.getSkill());  // 对手的技能
                Integer rivalId;
                if(vo.getRoomOwner().equals(user.getId())){  // 当前用户就是房主,修改房主的技能,并通知对手
                    vo.setRoomOwnerSkill(chineseChessRoomVo.getSkill());
                    rivalId = vo.getRival();
                }
                else{  // 当前用户不是房主,修改技能,通知房主
                    vo.setRivalSkill(chineseChessRoomVo.getSkill());
                    rivalId = vo.getRoomOwner();
                }
                String data = JSON.toJSONString(result);
                try {
                    onlineUsers.get(rivalId).getBasicRemote().sendText(MessageUtils.getMessage(false,10,"对手变更了技能,通知当前用户变更显示",data));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 当前用户已准备完毕
     * @param chineseChessRoomVo
     */
    private void ready(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo : onlineRoomList) {
            if (vo.getId().equals(chineseChessRoomVo.getId())) {
                Integer recipient;
                boolean next = false;
                if(vo.getRoomOwner().equals(user.getId())){  // 当前用户就是房主,被通知的是对手
                    recipient = vo.getRival();
                    vo.setRoomOwnerIsReady(true);
                    vo.setRoomOwnerValue(chineseChessRoomVo.getFingerGuessValue());  // 选择的正反面
                    if(vo.isRivalIsReady()){  // 感觉需要加个锁
                        next=true;  // 对方也准备好了
                    }
                }
                else{  // 当前用户不是房主,被通知的是房主
                    recipient = vo.getRoomOwner();
                    vo.setRivalIsReady(true);
                    vo.setRivalValue(chineseChessRoomVo.getFingerGuessValue());  // 选择的正反面
                    if(vo.isRoomOwnerIsReady()){
                        next=true;  // 对方也准备好了
                    }
                }
                try {
                    onlineUsers.get(recipient).getBasicRemote().sendText(MessageUtils.getMessage(true,11,"对手准备好了",null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(next){  // 双方都准备好了
                    try {
                        // 对战基本信息保存
                        ChineseChessVo chineseChessVo = new ChineseChessVo();
                        chineseChessVo.setName(vo.getName());
                        chineseChessVo.setRoomOwner(vo.getRoomOwner());
                        chineseChessVo.setRoomOwnerSkill(vo.getRoomOwnerSkill());
                        chineseChessVo.setRival(vo.getRival());
                        chineseChessVo.setRivalSkill(vo.getRivalSkill());
                        chineseChessVo.setNote(vo.getNote());
                        chineseChessService.add(chineseChessVo);
                        vo.setRoomId(chineseChessVo.getId());  // 保存数据库中的房间号
                        vo.initRound();
                        vo.increaseRound();
                        vo.initCountdown();

                        initChineseChess(vo);
                        cleanTip(vo,false);
                        vo.setStatus(3);  // 对战开始标记
                        vo.setRecentActivityTime(LocalDateTime.now());  // 准备完毕开始对战,更新活动时间
                        HashMap<String, Object> result = new HashMap<>();  // 存储响应数据
                        result.put("round", vo.getRound());  // 当前回合
                        result.put("status",vo.getStatus());  // 房间状态
                        if(Objects.equals(vo.getRoomOwnerValue(), vo.getRivalValue())){  // 如果双方选择的一样,则房主后手
                            vo.setActorIsRoomOwner(false);
                        }
                        else{  // 如果双方选择的不一样,则房主先手
                            vo.setActorIsRoomOwner(true);
                        }
                        if(vo.isActorIsRoomOwner()){  // 房主先手
                            // 推送给房主的数据
                            result.put("isActor", true);
                            result.put("isRoomOwner", true);
                            result.put("oneself", vo.getRed());  // 自己
                            result.put("opponent", vo.getBlack());  // 对手
//                            result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                            result.put("selfSkill", vo.getRoomOwnerSkill());
//                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                            result.put("rivalSkill", vo.getRivalSkill());
                            String data = JSON.toJSONString(result);
                            onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是先手!",data));
                            // 推送给对手的数据
                            result.put("isActor", false);
                            result.put("isRoomOwner", false);
                            result.put("oneself", this.image(vo.getBlack()));  // 自己
                            result.put("opponent", this.image(vo.getRed()));  // 对手
//                            result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                            result.put("selfSkill", vo.getRivalSkill());
//                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                            result.put("rivalSkill", vo.getRoomOwnerSkill());
                            data = JSON.toJSONString(result);
                            onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是后手!",data));
                        }
                        else{  // 房主后手
                            // 推送给房主的数据
                            result.put("isActor", false);
                            result.put("isRoomOwner", true);
                            result.put("oneself", vo.getRed());  // 自己
                            result.put("opponent", vo.getBlack());  // 对手
//                            result.put("selfSkillName",ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                            result.put("selfSkill",vo.getRoomOwnerSkill());
//                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                            result.put("rivalSkill", vo.getRivalSkill());
                            String data = JSON.toJSONString(result);
                            onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是后手!",data));
                            // 推送给对手的数据
                            result.put("isActor", true);
                            result.put("isRoomOwner", false);
                            result.put("oneself", this.image(vo.getBlack()));  // 自己
                            result.put("opponent", this.image(vo.getRed()));  // 对手
//                            result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                            result.put("selfSkill", vo.getRivalSkill());
//                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                            result.put("rivalSkill", vo.getRoomOwnerSkill());
                            data = JSON.toJSONString(result);
                            onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是先手!",data));
                        }
                        ChineseChessRunnable chineseChessRunnable = new ChineseChessRunnable("中国象棋-"+vo.getRoomId());
                        chineseChessRunnable.start(vo);
//                        onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,13,"开始决定先后手",null));
//                        onlineUsers.get(recipient).getBasicRemote().sendText(MessageUtils.getMessage(true,13,"开始决定先后手",null));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 取消准备
     * @param chineseChessRoomVo
     */
    private void cancelReady(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo : onlineRoomList) {
            if (vo.getId().equals(chineseChessRoomVo.getId())) {
                Integer recipient;
                if(vo.getRoomOwner().equals(user.getId())){  // 当前用户就是房主,被通知的是对手
                    recipient = vo.getRival();
                    vo.setRoomOwnerIsReady(false);
                }
                else{  // 当前用户不是房主,被通知的是房主
                    recipient = vo.getRoomOwner();
                    vo.setRivalIsReady(false);
                }
                try {
                    onlineUsers.get(recipient).getBasicRemote().sendText(MessageUtils.getMessage(true,12,"对手取消了准备",null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 在先后手判断中,用户选择了数字
     * @param chineseChessRoomVo
     */
//    private void selectNumber(ChineseChessRoomVo chineseChessRoomVo){  // 不生效
//        for (ChineseChessRoomVo vo : onlineRoomList) {
//            if (vo.getId().equals(chineseChessRoomVo.getId())) {
//                if(vo.getRoomOwner().equals(user.getId())){  // 当前用户就是房主
//                    vo.setRoomOwnerValue(chineseChessRoomVo.getFingerGuessValue());
//                }
//                else{  // 当前用户不是房主
//                    vo.setRivalValue(chineseChessRoomVo.getFingerGuessValue());
//                }
//                // 当双方都选择了数字,则立即判断并通知
//                try {
//                    if(vo.getRoomOwnerValue()!=null && vo.getRivalValue()!=null){
//                        this.initChineseChess(vo);
//                        vo.setStatus(3);  // 对战开始
//                        HashMap<String, Object> result = new HashMap<>();
//                        result.put("round", 1);  // 当前回合
//                        result.put("oneself", vo.getRed());  // 自己
//                        result.put("opponent", vo.getBlack());  // 对手
//                        if((vo.getRoomOwnerValue()==-1&&vo.getRivalValue()!=-1) && (vo.getRoomOwnerValue()!=-1&&vo.getRivalValue()!=-1&&Objects.equals(vo.getRoomOwnerValue(), vo.getRivalValue()))){
//                            vo.setActorIsRoomOwner(false);
//                        }
//                        else{
//                            vo.setActorIsRoomOwner(true);
//                        }
//                        if(vo.isActorIsRoomOwner()){  // 先手是房主
//                            result.put("isActor", true);
//                            result.put("isRoomOwner", true);
//                            result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
//                            result.put("selfSkill", vo.getRoomOwnerSkill());
//                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
//                            result.put("rivalSkill", vo.getRivalSkill());
//                            String data = JSON.toJSONString(result);
//                            onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是先手!",data));
//                            result.put("isActor", false);
//                            result.put("isRoomOwner", false);
//                            result.put("oneself", this.image(vo.getBlack()));  // 自己
//                            result.put("opponent", this.image(vo.getRed()));  // 对手
//                            result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
//                            result.put("selfSkill", vo.getRivalSkill());
//                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
//                            result.put("rivalSkill", vo.getRoomOwnerSkill());
//                            data = JSON.toJSONString(result);
//                            onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是后手!",data));
//                        }
//                        else{  // 先手是对手
//                            result.put("isActor", false);
//                            result.put("isRoomOwner", true);
//                            result.put("selfSkillName",ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
//                            result.put("selfSkill",vo.getRoomOwnerSkill());
//                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
//                            result.put("rivalSkill", vo.getRivalSkill());
//                            String data = JSON.toJSONString(result);
//                            onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是后手!",data));
//                            result.put("isActor", true);
//                            result.put("isRoomOwner", false);
//                            result.put("oneself", this.image(vo.getBlack()));  // 自己
//                            result.put("opponent", this.image(vo.getRed()));  // 对手
//                            result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
//                            result.put("selfSkill", vo.getRivalSkill());
//                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
//                            result.put("rivalSkill", vo.getRoomOwnerSkill());
//                            data = JSON.toJSONString(result);
//                            onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是先手!",data));
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    /**
     * 当前行动方点击了棋盘上的某一个位置
     * Step1
     *  1.找到对应房间
     *  2.确定当前用户身份,是房主还是挑战者
     *  3.将双方棋子整合至同一棋盘上
     *  4.判断是否选中了无效处,若是则清空 tip
     *  5.获取本次点击的位置的信息
     * Step2
     *  1.本次选中了空白处
     *    a.上次选中自己棋子,通过 tip 判断棋子是否可以移动至本次位置
     *      1>可以,上次选中的棋子变更位置,清空 tip 并判断是否绝杀
     *      2>不可以,清空 tip ,后新增本次选中的位置 tip
     *    b.上次选中空白处或无效处或对方棋子,清空 tip ,后新增本次选中的位置 tip
     *  2.本次选中了自己的棋子
     *    a.清空 tip ,后新增本次选中的位置 tip 以及可移动的位置 tip
     *  3.本次选中了一枚对方的棋子
     *    a.上次选中非自己棋子,先清空 tip ,再新增本次选中的位置 tip
     *    b.上次选中自己棋子,通过 tip 判断棋子是否可以移动至本次位置
     *      1>可以,上次选中的棋子变更位置,本次选中的对方棋子移除,清空 tip 并判断是否绝杀
     *      2>不可以,先清空 tip ,再新增本次选中的位置 tip
     * @param chineseChessRoomVo
     */
    private void action(ChineseChessRoomVo chineseChessRoomVo){
        for (ChineseChessRoomVo vo : onlineRoomList) {
            if (vo.getId().equals(chineseChessRoomVo.getId())) {  // 找到对应房间
                // 返回棋盘信息给当前用户
                HashMap<String, Object> result = new HashMap<>();
                log.info("房间ID: "+vo.getId());
                // Step1
                Integer chessPlayer = vo.isActorIsRoomOwner()?1:2;  // 当前用户标识 1:红方(房主) 2:黑方(挑战者)
                boolean isRoomOwner=vo.getRoomOwner().equals(user.getId());
                log.info("行动方是否为房主: "+isRoomOwner);
                Integer skill=isRoomOwner?vo.getRoomOwnerSkill():vo.getRivalSkill();  // 获取当前用户的技能标识
                log.info("行动方技能: "+skill);
                log.info("行动方点击位置: "+chineseChessRoomVo.getSelectedChessX()+","+chineseChessRoomVo.getSelectedChessY());
                // 将上次选中的位置信息赋值给"上一次"
                vo.setPreviousChessOwner(vo.getSelectedChessOwner());
                vo.setPreviousChess(vo.getSelectedChess());
                vo.setPreviousChessX(vo.getSelectedChessX());
                vo.setPreviousChessY(vo.getSelectedChessY());
                vo.setSelectedChess(0);

                if(chessPlayer==2){  // 如果是黑方(对手)需要将其坐标转换成镜像
                    chineseChessRoomVo.setSelectedChessX(8-chineseChessRoomVo.getSelectedChessX());
                    chineseChessRoomVo.setSelectedChessY(9-chineseChessRoomVo.getSelectedChessY());
                }

                vo.setSelectedChessX(chineseChessRoomVo.getSelectedChessX());
                vo.setSelectedChessY(chineseChessRoomVo.getSelectedChessY());
                vo.setSelectedChessOwner(0);

                this.integrate(vo);  // 生成整体棋局,将双方棋子整合至同一棋盘上
                for (ChineseChessBaseVo baseVo : vo.getWhole()) {  // 根据坐标找到棋子
                    for (ChineseChessBaseLocationVo locationVo : baseVo.getLocation()) {
                        if(locationVo.getX().equals(chineseChessRoomVo.getSelectedChessX())&&locationVo.getY().equals(chineseChessRoomVo.getSelectedChessY())){
                            vo.setSelectedChess(baseVo.getPiece());
                            vo.setSelectedChessOwner(chessPlayer);
                            break;
                        }
                    }
                }

                result.put("soundType",-1);
                boolean updateOpponent = false;  // 是否更新对手的棋局
                if(chineseChessRoomVo.getSelectedChessX()==-1||chineseChessRoomVo.getSelectedChessY()==-1){  // 越界了,本次选中了无效处

//                    vo.setChineseChessBaseTip(null);  // 清除所有提示
                    cleanTip(vo,true);
                    vo.setPreviousChessOwner(-1);  // 置空"上次选择的棋子"的拥有者
                    vo.setPreviousChess(0);  // 置空"上次选择的棋子"的类型
                }
                else{  // 未越界,本次点击了棋盘某一点
                    int[] res = this.getChessType(vo,chineseChessRoomVo.getSelectedChessX(),chineseChessRoomVo.getSelectedChessY());  // 获取本次点击的位置的信息
                    // Step2
                    if(res[0]==0){  // 本次选中了空白处
                        if(Objects.equals(vo.getPreviousChessOwner(), chessPlayer)&&inTips(vo,chineseChessRoomVo.getSelectedChessX(),chineseChessRoomVo.getSelectedChessY())){  // 上次点击的棋子的拥有者就是当前用户,且当前位置为上次点击的棋子的可移动位置
                            result.put("soundType",1);
                            this.moveChess(vo,chessPlayer,0);  // 移动棋子
                            updateOpponent=true;
                        }
                        else{  // 上次点击的棋子的拥有者不是当前用户
//                            vo.setChineseChessBaseTip(null);  // 清除所有提示
                            cleanTip(vo,true);
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chineseChessRoomVo.getSelectedChessX(),chineseChessRoomVo.getSelectedChessY()));  // 将本次点击的空白位置添加高亮
                        }
                    }
                    else if(res[0]==chessPlayer){  // 本次选中了自己的棋子
                        if(isSealState(vo.getChineseChessBaseTip(),vo.getSelectedChessX(),vo.getSelectedChessY())){  // 选中的棋子处于被【封印】状态
                            cleanTip(vo,true);
                            try {
                                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,0,"该棋子处于【封印】状态!",null));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else if(skill==7&&activateDebuff(vo)){  // 因【身先士卒】影响,所有【兵/卒】被吃【帅/将】将无法移动
                            cleanTip(vo,true);
                            try {
                                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,0,"所有【兵/卒】被吃,【帅/将】将无法移动!",null));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else{  // 获取该棋子的所有可移动位置并放入 tip
//                        vo.setChineseChessBaseTip(null);  // 清除所有提示
                            cleanTip(vo,true);
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(0,chineseChessRoomVo.getSelectedChessX(),chineseChessRoomVo.getSelectedChessY()));  // 将本次点击的自己的棋子添加高亮
                            this.accessibility(vo,skill);
                        }
                    }
                    else {  // 本次选中了对方的棋子
                        boolean flag = false;// 判断本次命中的棋子的坐标是否存在 tip 中
                        for (ChineseChessBaseTipVo chineseChessBaseTipVo:vo.getChineseChessBaseTip()) {
                            if(chineseChessBaseTipVo.getType()==1&&vo.getSelectedChessX().equals(chineseChessBaseTipVo.getX())&&vo.getSelectedChessY().equals(chineseChessBaseTipVo.getY())){
                                flag=true;
                                break;
                            }
                        }
                        if(this.isSelfChess(vo,vo.getPreviousChessX(),vo.getPreviousChessY())&&flag){  // 上一次选中的是自己的棋子,并且当前选中的对方棋子在 tip 中
                            result.put("soundType",2);
                            this.moveChess(vo,chessPlayer,1);  // 移动棋子
                            updateOpponent=true;
                        }
                        else{  // 上一次选中的是自己的棋子,但当前选中的对方棋子不在 tip 中,则清空 tip
//                            vo.setChineseChessBaseTip(null);  // 清除所有提示
                            cleanTip(vo,true);
                        }
                    }

                }


                result.put("code",0);  // 状态码,与 msg 有关, 0:无,1:红方将军,2:黑方将军,3:红方被绝杀,4:黑方被绝杀 5:对战结束结果通知
                result.put("msg","暂无提示信息");
                if(updateOpponent){  // 更新对手棋局,则意味着当前玩家已移动了棋子,结束了自己的回合
                    result.put("isActor", false);
                    /**
                     * 保存双方的每一步
                     */
                    ChineseChessSnapshotVo chineseChessSnapshotVo = new ChineseChessSnapshotVo();
                    chineseChessSnapshotVo.setChineseChess(vo.getRoomId());
                    chineseChessSnapshotVo.setRound(vo.getRound());
                    chineseChessSnapshotVo.setActor(chessPlayer);  // 当前用户标识 1:红方(房主) 2:黑方(挑战者)
                    chineseChessSnapshotVo.setChess(vo.getPreviousChess());
                    chineseChessSnapshotVo.setOldLocationX(vo.getPreviousChessX());
                    chineseChessSnapshotVo.setOldLocationY(vo.getPreviousChessY());
                    chineseChessSnapshotVo.setNewLocationX(vo.getSelectedChessX());
                    chineseChessSnapshotVo.setNewLocationY(vo.getSelectedChessY());
                    chineseChessSnapshotService.add(chineseChessSnapshotVo);
                    vo.setRecentActivityTime(LocalDateTime.now());  // 当前玩家行动结束进入下一回合,更新活动时间
                    vo.increaseRound();
                    vo.initCountdown();

                    this.updateSealTip(vo);  // 当有玩家的技能为【一车十子寒】时,需为相关棋子加上【封印】标记
                    vo.setActorIsRoomOwner(!vo.isActorIsRoomOwner());  // 变更服务器存储的控制权
                }
                else{
                    result.put("isActor", true);
                }
                result.put("round", vo.getRound());  // 当前回合
                // 发送给当前玩家
                result.put("isRoomOwner", isRoomOwner);
                result.put("status", vo.getStatus());
                result.put("oneself", isRoomOwner?vo.getRed():this.image(vo.getBlack()));  // 自己
                result.put("opponent", isRoomOwner?vo.getBlack():this.image(vo.getRed()));  // 对手
                result.put("tip", isRoomOwner? vo.getChineseChessBaseTip():this.imageTip(vo.getChineseChessBaseTip()));  // tip,如果是黑方(对手)则需要将tip镜像
                String data = JSON.toJSONString(result);
                try {
                    onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,15,"向当前玩家发送数据,包含双方棋盘数据!",data));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                result.put("isActor", updateOpponent);  // 允许对手控制棋子
                result.put("isRoomOwner", !isRoomOwner);
                result.put("status", vo.getStatus());
                result.put("oneself", isRoomOwner?this.image(vo.getBlack()):vo.getRed());  // 自己棋局
                result.put("opponent", isRoomOwner?this.image(vo.getRed()):vo.getBlack());  // 对手棋局
                result.put("tip", isRoomOwner? this.imageTip(vo.getChineseChessBaseTip()):vo.getChineseChessBaseTip());  // tip,如果是黑方(对手)则需要将tip镜像
                data = JSON.toJSONString(result);
                try {
                    onlineUsers.get(user.getId().equals(vo.getRoomOwner())?vo.getRival():vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,15,"向对手发送数据,包含双方棋盘数据!",data));
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                if(updateOpponent){  // 更新对手棋局并授予控制权,当当前用户移动了棋子时触发
//                    result.put("isActor", true);  // 允许对手控制棋子
//                    result.put("isRoomOwner", !isRoomOwner);
//                    result.put("status", vo.getStatus());
//                    result.put("oneself", isRoomOwner?this.image(vo.getBlack()):vo.getRed());  // 自己棋局
//                    result.put("opponent", isRoomOwner?this.image(vo.getRed()):vo.getBlack());  // 对手棋局
//                    result.put("tip", isRoomOwner? this.imageTip(vo.getChineseChessBaseTip()):vo.getChineseChessBaseTip());  // tip,如果是黑方(对手)则需要将tip镜像
//                    vo.setActorIsRoomOwner(!vo.isActorIsRoomOwner());  // 变更服务器存储的控制权
//                    data = JSON.toJSONString(result);
//                    try {
//                        onlineUsers.get(isRoomOwner?vo.getRival():vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,15,"向对手发送数据,包含双方棋盘数据!",data));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }

            }
        }
    }

    /**
     * 判断是否因【身先士卒】影响,所有【兵/卒】被吃【帅/将】将无法移动
     * @param vo
     * @return
     */
    private boolean activateDebuff(ChineseChessRoomVo vo) {
        boolean isRoomOwner=vo.getRoomOwner().equals(user.getId());
        // 判断选中的棋子是否是【帅/将】
        for (ChineseChessBaseVo chineseChessBaseVo :(isRoomOwner? vo.getRed():vo.getBlack())) {
            if(chineseChessBaseVo.getPiece()==7){  // 获取【帅/将】信息
                ChineseChessBaseLocationVo chineseChessBaseLocationVo = chineseChessBaseVo.getLocation().get(0);
                if(!Objects.equals(chineseChessBaseLocationVo.getX(), vo.getSelectedChessX()) || !Objects.equals(chineseChessBaseLocationVo.getY(), vo.getSelectedChessY())){  // 选中的棋子不是【帅/将】
                    return false;
                }
            }
        }

        for (ChineseChessBaseVo chineseChessBaseVo :(isRoomOwner? vo.getRed():vo.getBlack())) {
            if(chineseChessBaseVo.getPiece()==1){
                if(chineseChessBaseVo.getLocation().size()==0){  // 所有【兵/卒】被吃
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断选中的棋子是否为【封印】状态
     * @param chineseChessBaseTip
     * @param selectedChessX
     * @param selectedChessY
     * @return
     */
    private boolean isSealState(ArrayList<ChineseChessBaseTipVo> chineseChessBaseTip, Integer selectedChessX, Integer selectedChessY) {
        for (ChineseChessBaseTipVo chineseChessBaseTipVo : chineseChessBaseTip) {
            if(chineseChessBaseTipVo.getType()==2&&chineseChessBaseTipVo.getX()==selectedChessX&&chineseChessBaseTipVo.getY()==selectedChessY){
                return true;
            }
        }
        return false;
    }

    /**
     * 清除 tip
     * @param vo
     * @param reserveSealTip 是否保留【封印】标记
     */
    private void cleanTip(ChineseChessRoomVo vo,boolean reserveSealTip){
        if(reserveSealTip){
            // 移除旧【封印】标记
            ArrayList<ChineseChessBaseTipVo> tips = vo.getChineseChessBaseTip();
            for (int i = (tips.size()-1); i >=0; i--) {
                if(tips.get(i).getType()!=2){
                    tips.remove(i);
                }
            }
        }
        else{
            vo.setChineseChessBaseTip(null);  // 清除所有提示
        }

    }

    /**
     * 先清空【封印】标记,再根据当前棋子数据生成新的【封印】标记
     * @param vo
     */
    private void updateSealTip(ChineseChessRoomVo vo) {
        // 移除旧【封印】标记
        ArrayList<ChineseChessBaseTipVo> tips = vo.getChineseChessBaseTip();
        for (int i = (tips.size()-1); i >=0; i--) {
            if(tips.get(i).getType().equals(2)){
                tips.remove(i);
            }
        }
        // 添加新【封印】标记
        if(vo.getRoomOwnerSkill()==3){  // 房主的技能是【一车十子寒】
            for (ChineseChessBaseVo chineseChessBaseVo : vo.getRed()) {
                if(chineseChessBaseVo.getPiece().equals(3)){  // 找到【车】
                    for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                        Integer x = chineseChessBaseLocationVo.getX();
                        Integer y = chineseChessBaseLocationVo.getY();
                        // 遍历对手棋子
                        for (ChineseChessBaseVo chessBaseVo : vo.getBlack()) {
                            if(chessBaseVo.getPiece()!=7){  // 【帅/将】不可被【禁止】
                                for (ChineseChessBaseLocationVo chessBaseLocationVo : chessBaseVo.getLocation()) {
                                    addVehicleSealTip(vo,chessBaseLocationVo,x,y);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(vo.getRivalSkill()==3){  // 对手的技能是【一车十子寒】
            for (ChineseChessBaseVo chineseChessBaseVo : vo.getBlack()) {
                if(chineseChessBaseVo.getPiece().equals(3)){  // 找到【车】
                    for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                        Integer x = chineseChessBaseLocationVo.getX();
                        Integer y = chineseChessBaseLocationVo.getY();
                        // 遍历对手棋子
                        for (ChineseChessBaseVo chessBaseVo : vo.getRed()) {
                            if(chessBaseVo.getPiece()!=7){  // 【帅/将】不可被【禁止】
                                for (ChineseChessBaseLocationVo chessBaseLocationVo : chessBaseVo.getLocation()) {
                                    addVehicleSealTip(vo,chessBaseLocationVo,x,y);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    /**
     * 为【车】周围的敌方棋子添加【封印】标记
     * @param vo
     * @param x
     * @param y
     */
    private void addVehicleSealTip(ChineseChessRoomVo vo,ChineseChessBaseLocationVo chessBaseLocationVo,Integer x,Integer y){
        if(x.equals(chessBaseLocationVo.getX()+1) && y.equals(chessBaseLocationVo.getY()+1)){  // 右上
            addSealTip(vo,chessBaseLocationVo.getX(),chessBaseLocationVo.getY());
        }
        if(x.equals(chessBaseLocationVo.getX()) && y.equals(chessBaseLocationVo.getY()+1)){  // 上
            addSealTip(vo,chessBaseLocationVo.getX(),chessBaseLocationVo.getY());
        }
        if(x.equals(chessBaseLocationVo.getX()-1) && y.equals(chessBaseLocationVo.getY()+1)){  // 左上
            addSealTip(vo,chessBaseLocationVo.getX(),chessBaseLocationVo.getY());
        }
        if(x.equals(chessBaseLocationVo.getX()+1) && y.equals(chessBaseLocationVo.getY())){  // 左
            addSealTip(vo,chessBaseLocationVo.getX(),chessBaseLocationVo.getY());
        }
        if(x.equals(chessBaseLocationVo.getX()-1) && y.equals(chessBaseLocationVo.getY())){  // 右
            addSealTip(vo,chessBaseLocationVo.getX(),chessBaseLocationVo.getY());
        }
        if(x.equals(chessBaseLocationVo.getX()+1) && y.equals(chessBaseLocationVo.getY()-1)){  // 右下
            addSealTip(vo,chessBaseLocationVo.getX(),chessBaseLocationVo.getY());
        }
        if(x.equals(chessBaseLocationVo.getX()) && y.equals(chessBaseLocationVo.getY()-1)){  // 下
            addSealTip(vo,chessBaseLocationVo.getX(),chessBaseLocationVo.getY());
        }
        if(x.equals(chessBaseLocationVo.getX()-1) && y.equals(chessBaseLocationVo.getY()-1)){  // 左下
            addSealTip(vo,chessBaseLocationVo.getX(),chessBaseLocationVo.getY());
        }
    }

    /**
     * 将指定位置的棋子标记为【封印】
     * @param vo
     * @param x
     * @param y
     */
    private void addSealTip(ChineseChessRoomVo vo,Integer x,Integer y){
        boolean exist=false;
        // 判断是否已存在,若存在直接跳过
        for (ChineseChessBaseTipVo chineseChessBaseTipVo : vo.getChineseChessBaseTip()) {
            if(chineseChessBaseTipVo.getType()==2&&chineseChessBaseTipVo.getX().equals(x)&&chineseChessBaseTipVo.getY().equals(y)){
                exist=true;
                break;
            }
        }
        if(!exist){
            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(2,x,y));
        }

    }


    /**
     * 判断当前用户选中的位置,是否存在tip中(前提该用户上次点击了自己的棋子)
     * @param vo
     * @param selectedChessX
     * @param selectedChessY
     * @return
     */
    private boolean inTips(ChineseChessRoomVo vo, Integer selectedChessX, Integer selectedChessY) {
        AtomicBoolean res= new AtomicBoolean(false);
        vo.getChineseChessBaseTip().forEach(e->{
            if(Objects.equals(1,e.getType())&& Objects.equals(selectedChessX, e.getX()) && Objects.equals(selectedChessY, e.getY())){
                res.set(true);
            }
        });
        return res.get();
    }

    /**
     * 移动棋子
     * @param vo
     * @param chessPlayer 当前用户身份,1:房主(红方),2:对手(黑方)
     * @param type 0:移至空白处 1:吃掉对方棋子 2:互换位置
     */
    private void moveChess(ChineseChessRoomVo vo,Integer chessPlayer,Integer type) {
        for (ChineseChessBaseTipVo chineseChessBaseTipVo : vo.getChineseChessBaseTip()) {  // 判断选中的位置是否在 tip 中
            if(chineseChessBaseTipVo.getX().equals(vo.getSelectedChessX()) && chineseChessBaseTipVo.getY().equals(vo.getSelectedChessY())){
                if(type==0){  // 移至空白处
                    for (ChineseChessBaseVo chineseChessBaseVo : chessPlayer==1?vo.getRed():vo.getBlack()) {  // 判断所属并遍历棋子
                        if(chineseChessBaseVo.getPiece().equals(vo.getPreviousChess())){  // 找到该棋子
                            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {  // 遍历该棋子所有的位置
                                if(chineseChessBaseLocationVo.getX().equals(vo.getPreviousChessX())&&chineseChessBaseLocationVo.getY().equals(vo.getPreviousChessY())){  // 找到该位置
                                    chineseChessBaseLocationVo.setX(vo.getSelectedChessX());  // 移动至新位置
                                    chineseChessBaseLocationVo.setY(vo.getSelectedChessY());
//                                    vo.setChineseChessBaseTip(null);  // 清空 tip
                                    cleanTip(vo,false);
                                }
                            }
                        }
                    }
                }
                else if(type==1){  // 移至对方棋子上,吃掉
                    for (ChineseChessBaseVo chineseChessBaseVo : chessPlayer==1?vo.getRed():vo.getBlack()) {  // 判断所属并遍历棋子
                        if(chineseChessBaseVo.getPiece().equals(vo.getPreviousChess())){  // 找到上次选中的棋子
                            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {  // 遍历该棋子所有的位置
                                if(chineseChessBaseLocationVo.getX().equals(vo.getPreviousChessX())&&chineseChessBaseLocationVo.getY().equals(vo.getPreviousChessY())){  // 找到该位置
                                    chineseChessBaseLocationVo.setX(vo.getSelectedChessX());  // 移动至新位置
                                    chineseChessBaseLocationVo.setY(vo.getSelectedChessY());
//                                    vo.setChineseChessBaseTip(null);  // 清空 tip
                                    cleanTip(vo,false);
                                }
                            }
                        }
                    }
                    for (ChineseChessBaseVo chineseChessBaseVo : chessPlayer==1?vo.getBlack():vo.getRed()) {  // 遍历对方棋子,并移除棋子
                        if(chineseChessBaseVo.getPiece().equals(vo.getSelectedChess())){  // 找到当前命中的棋子,并移除该位置上的棋子
                            if(vo.getSelectedChess().equals(7)){  // 吃掉对方的【帅/将】,对战结束并立即结算
                                endBattle(vo,
                                        vo.isActorIsRoomOwner()?vo.getRoomOwner():vo.getRival(),
                                        vo.isActorIsRoomOwner(),
                                        "吃掉【帅/将】对战胜利",
                                        "对手【帅/将】被吃",
                                        DictionaryCode.CHINESE_CHESS_WINNER_WAY_0,
                                        "对战胜利!",
                                        "战败!");
                            }
                            chineseChessBaseVo.getLocation().removeIf(chineseChessBaseLocationVo -> chineseChessBaseLocationVo.getX().equals(vo.getSelectedChessX()) && chineseChessBaseLocationVo.getY().equals(vo.getSelectedChessY()));
                        }
                    }
                }
                else if(type==3){  // 互换位置,特殊情况,当当前用户拥有【身先士卒】的技能时,将【将/帅】与选定的【車】互换
                    ArrayList<ChineseChessBaseVo> baseVos = chessPlayer == 1 ? vo.getRed() : vo.getBlack();  // 当前玩家的所有棋子

                    // 获取【車】的棋子信息
                    if(baseVos.get(2).getLocation().size()==0){  // 由于是按照一定顺序 put 所以可知依次是【兵/卒】【炮/砲】【车】【马】【相/象】【士/仕】【帅/将】
                        log.info("已经没有【車】可交换位置");
                    }
                    // 找到与选中的位置相同的【車】，直接移除
                    baseVos.get(2).getLocation().removeIf(chineseChessBaseLocationVo -> chineseChessBaseLocationVo.getX().equals(vo.getSelectedChessX()) && chineseChessBaseLocationVo.getY().equals(vo.getSelectedChessY()));

                    // 获取【帅/将】的棋子信息
                    ChineseChessBaseVo baseVo = baseVos.get(6);  // 由于是按照一定顺序 put 所以可知依次是【兵/卒】【炮/砲】【车】【马】【相/象】【士/仕】【帅/将】
                    ChineseChessBaseLocationVo locationVo = baseVo.getLocation().get(0);  // 【帅/将】就一个,本质将一直存在
                    locationVo.setX(vo.getSelectedChessX());  // 移动至选择的位置
                    locationVo.setY(vo.getSelectedChessY());
                }
            }
        }
    }

    /**
     * 获取本次点击的位置的信息
     * @param vo
     * @return 长度为2的素组
     *      第1个值表示拥有者, 0:空位置 1:红方 2:黑方
     *      第2个值表示棋子类型,1:兵/卒 2:炮/砲 3:车 4:马 5:相/象 6:士/仕 7:帅/将
     *      第三个值表示是不是自己的棋子,0:不是,1:是
     */
    private int[] getChessType(ChineseChessRoomVo vo,Integer x,Integer y) {
        int[] res= {0,0,0};
        for (ChineseChessBaseVo chineseChessBaseVo : vo.getWhole()) {
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                if(chineseChessBaseLocationVo.getX().equals(x) && chineseChessBaseLocationVo.getY().equals(y)){
                    res[0]=chineseChessBaseVo.isRoomOwner()?1:2;
                    res[1]=chineseChessBaseVo.getPiece();
                    if(vo.getRoomOwner().equals(user.getId()) && chineseChessBaseVo.isRoomOwner()){  // 当前用户是房主,且当前棋子也是房主的
                        res[2]=1;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 根据的棋子类型和位置以及技能,获取所有可移动的位置,并存入 tip
     * @param vo
     * @return
     */
    private void accessibility(ChineseChessRoomVo vo,Integer skill) {
        int tableX = 9,tableY=10;
        int offsetX=0,offsetY=0;
        Integer chessX = vo.getSelectedChessX();
        Integer chessY = vo.getSelectedChessY();
        Integer shift=vo.isActorIsRoomOwner()?1:-1;  // 中心对称,针对对手视角的位移
        switch (vo.getSelectedChess()){  // 1:兵/卒 2:炮/砲 3:车 4:马 5:相/象 6:士/仕 7:帅/将
            case 1:  // 兵/卒
                boolean LoggedIn=vo.isActorIsRoomOwner()?(chessY<5):(chessY>4);  // 是否已过河
                if (skill==1) {  // 技能开启
                    offsetX=vo.isActorIsRoomOwner()? -1:1;  // 非房主的视角与房主的视角是呈中心对称的
//                    if (chessX>0) {  // 左
                        if ( this.isValidLocation(chessX+(1*offsetX),chessY)  // 目标位置有效
                                && !this.isSelfChess(vo,chessX+(1*offsetX),chessY)) {  // 目标位置不是自己的棋子才能加入
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(1*offsetX),chessY));
                        }
//                    }
                    offsetX=vo.isActorIsRoomOwner()? 1:-1;
//                    if ((tableX - 1 - chessX) > 0) {  // 右
                        if (this.isValidLocation(chessX+(1*offsetX),chessY)  // 目标位置有效
                                && !this.isSelfChess(vo,chessX+(1*offsetX),chessY)) {
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(1*offsetX),chessY));
                        }
//                    }
                    offsetY=vo.isActorIsRoomOwner()? -1:1;
                    if ( this.isValidLocation(chessX,chessY+(1*offsetY))  // 目标位置有效
                            &&!this.isSelfChess(vo,chessX,chessY+(1*offsetY))) {  // 上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY+(1*offsetY)));
                    }
                    offsetY=vo.isActorIsRoomOwner()? 1:-1;
                    if (this.isValidLocation(chessX,chessY+(1*offsetY))  // 目标位置有效
                            && !this.isSelfChess(vo,chessX,chessY+(1*offsetY))) {  // 下
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY+(1*offsetY)));
                    }
                }
                else {  // 技能未开启
                    offsetX=vo.isActorIsRoomOwner()? -1:1;
                    if (this.isValidLocation(chessX+(1*offsetX),chessY)  // 目标位置有效
                            &&LoggedIn
                            &&!this.isSelfChess(vo,chessX+offsetX,chessY)) {  // 左
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+offsetX,chessY));
                    }
                    offsetX=vo.isActorIsRoomOwner()? 1:-1;
                    if (this.isValidLocation(chessX+(1*offsetX),chessY)  // 目标位置有效
                            &&LoggedIn
                            &&!this.isSelfChess(vo,chessX+offsetX,chessY)) {  // 右
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+offsetX,chessY));
                    }
                    offsetY=vo.isActorIsRoomOwner()? -1:1;
                    if (chessY>0&& !this.isSelfChess(vo,chessX,chessY+offsetY)) {  // 上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY+offsetY));
                    }
                }
                break;
            case 2:  // 炮/砲
                boolean flag = true;  // 越过一枚棋子
                offsetX=vo.isActorIsRoomOwner()? -1:1;
                for (int i = chessX+offsetX; tableX>i&&i>=0;i+=offsetX){  // 左
                    if (this.isNullChess(vo.getWhole(),i,chessY)&&flag) {  // 指定位置是空的,且当前位置与【炮/砲】之间不存在棋子
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                    }
                    else {
                        if (flag) {  // 遇到一枚棋子,标记并跳过
                            flag = false;
                        }
                        else {  // 越过之后
                            if (skill==2&&this.isNullChess(vo.getWhole(),i,chessY)) {  // 技能开启,并遇到空位置,高亮,并继续循环
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                            }
                            if (!this.isNullChess(vo.getWhole(),i,chessY)&&!this.isSelfChess(vo,i,chessY)) {  // 遇到对手的棋子,高亮,并 break
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                                break;
                            }
                            if (this.isSelfChess(vo,i,chessY)) { // 遇到自己的棋子,直接 break
                                break;
                            }
                        }
                    }
                }
                flag = true;  // 重置
                offsetX=vo.isActorIsRoomOwner()? 1:-1;
                for (int i = chessX+offsetX; tableX>i&&i>=0;i+=offsetX){  // 右
                    if (this.isNullChess(vo.getWhole(),i,chessY)&&flag) {
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                    }
                    else {
                        if (flag) {
                            flag = false;
                        }
                        else {
                            if (skill==2&&this.isNullChess(vo.getWhole(),i,chessY)) {  // 技能开启
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                            }
                            if (!this.isNullChess(vo.getWhole(),i,chessY)&&!this.isSelfChess(vo,i,chessY)) {
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                                break;
                            }
                            if (this.isSelfChess(vo,i,chessY)) {
                                break;
                            }
                        }
                    }
                }
                flag = true;
                offsetY=vo.isActorIsRoomOwner()? -1:1;
                for (int i = chessY+offsetY; tableY>i&&i>=0;i+=offsetY){  // 上
                    if (this.isNullChess(vo.getWhole(),chessX,i)&&flag) {
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                    }
                    else {
                        if (flag) {
                            flag = false;
                        }
                        else {
                            if (skill==2&&this.isNullChess(vo.getWhole(),chessX,i)) {  // 技能开启
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                            }
                            if (!this.isNullChess(vo.getWhole(),chessX,i)&&!this.isSelfChess(vo,chessX,i)) {
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                                break;
                            }
                            if (this.isSelfChess(vo,chessX,i)) {
                                break;
                            }
                        }
                    }
                }
                flag = true;
                offsetY=vo.isActorIsRoomOwner()? 1:-1;
                for (int i = chessY+offsetY; tableY>i && i>=0;i+=offsetY){  // 下
                    if (this.isNullChess(vo.getWhole(),chessX,i)&&flag) {
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                    }
                    else {
                        if (flag) {
                            flag = false;
                        }
                        else {
                            if (skill==2&&this.isNullChess(vo.getWhole(),chessX,i)) {  // 技能开启
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                            }
                            if (!this.isNullChess(vo.getWhole(),chessX,i)&&!this.isSelfChess(vo,chessX,i)) {
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                                break;
                            }
                            if (this.isSelfChess(vo,chessX,i)) {
                                break;
                            }
                        }
                    }
                }
                break;
            case 3:  // 车
                offsetX=vo.isActorIsRoomOwner()? -1:1;
                for (int i = chessX+offsetX; tableX>i&&i>=0;i+=offsetX) {  // 左
                    if(this.isNullChess(vo.getWhole(),i,chessY)){  // 是一个空位置
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                    }
                    else if(!this.isSelfChess(vo,i,chessY)){  // 是自己的棋子
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                        break;
                    }
                    else{
                        break;
                    }
                }
                offsetX=vo.isActorIsRoomOwner()? 1:-1;
                for (int i = chessX+offsetX; tableX>i&&i>=0;i+=offsetX) {  // 右
                    if(this.isNullChess(vo.getWhole(),i,chessY)){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                    }
                    else if(!this.isSelfChess(vo,i,chessY)){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                        break;
                    }
                    else{
                        break;
                    }
                }
                offsetY=vo.isActorIsRoomOwner()? -1:1;
                for (int i = chessY+offsetY; tableY>i&&i>=0;i+=offsetY) {  // 上
                    if(this.isNullChess(vo.getWhole(),chessX,i)){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                    }
                    else if(!this.isSelfChess(vo,chessX,i)){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                        break;
                    }
                    else{
                        break;
                    }
                }
                offsetY=vo.isActorIsRoomOwner()? 1:-1;
                for (int i = chessY+offsetY; tableY>i&&i>=0;i+=offsetY) {  // 下
                    if(this.isNullChess(vo.getWhole(),chessX,i)){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                    }
                    else if(!this.isSelfChess(vo,chessX,i)){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                        break;
                    }
                    else{
                        break;
                    }
                }
                break;
            case 4:  // 马
                // 上左
                if((this.isNullChess(vo.getWhole(),chessX,chessY+(-1*shift))||skill==4)  // 不撇脚或拥有【金戈铁骑】技能
                            && this.isValidLocation(chessX+(-1*shift),chessY+(-2*shift))  // 目标位置是否有效未越界
                        && (this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY+(-2*shift))  // 目标位置为空
                        || !this.isSelfChess(vo,chessX+(-1*shift),chessY+(-2*shift)))){  // 目标位置不是自己的棋子,即对手棋子
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(-1*shift),chessY+(-2*shift)));
                }
                // 上右
                if((this.isNullChess(vo.getWhole(),chessX,chessY+(-1*shift))||skill==4)  // 不撇脚或拥有【金戈铁骑】技能
                        && this.isValidLocation(chessX+(1*shift),chessY+(-2*shift))  // 目标位置是否有效未越界
                        && (this.isNullChess(vo.getWhole(),chessX+(1*shift),chessY+(-2*shift))
                        || !this.isSelfChess(vo,chessX+(1*shift),chessY+(-2*shift)))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(1*shift),chessY+(-2*shift)));
                }
                // 右上
                if((this.isNullChess(vo.getWhole(),chessX+(1*shift),chessY)||skill==4)  // 不撇脚或拥有【金戈铁骑】技能
                        && this.isValidLocation(chessX+(2*shift),chessY+(-1*shift))  // 目标位置是否有效未越界
                        && (this.isNullChess(vo.getWhole(),chessX+(2*shift),chessY+(-1*shift))
                        || !this.isSelfChess(vo,chessX+(2*shift),chessY+(-1*shift)))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(2*shift),chessY+(-1*shift)));
                }
                // 右下
                if((this.isNullChess(vo.getWhole(),chessX+(1*shift),chessY)||skill==4)  // 不撇脚或拥有【金戈铁骑】技能
                        && this.isValidLocation(chessX+(2*shift),chessY+(1*shift))  // 目标位置是否有效未越界
                        && (this.isNullChess(vo.getWhole(),chessX+(2*shift),chessY+(1*shift))
                        || !this.isSelfChess(vo,chessX+(2*shift),chessY+(1*shift)))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(2*shift),chessY+(1*shift)));
                }
                // 下左
                if((this.isNullChess(vo.getWhole(),chessX,chessY+(1*shift))||skill==4)  // 不撇脚或拥有【金戈铁骑】技能
                        && this.isValidLocation(chessX+(-1*shift),chessY+(2*shift))  // 目标位置是否有效未越界
                        && (this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY+(2*shift))
                        || !this.isSelfChess(vo,chessX+(-1*shift),chessY+(2*shift)))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(-1*shift),chessY+(2*shift)));
                }
                // 下右
                if((this.isNullChess(vo.getWhole(),chessX,chessY+(1*shift))||skill==4)  // 不撇脚或拥有【金戈铁骑】技能
                        && this.isValidLocation(chessX+(1*shift),chessY+(2*shift))
                        && (this.isNullChess(vo.getWhole(),chessX+(1*shift),chessY+(2*shift))
                        || !this.isSelfChess(vo,chessX+(1*shift),chessY+(2*shift)))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(1*shift),chessY+(2*shift)));
                }
                // 左上
                if((this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY)||skill==4)  // 不撇脚或拥有【金戈铁骑】技能
                        && this.isValidLocation(chessX+(-2*shift),chessY+(-1*shift))  // 目标位置是否有效未越界
                        && (this.isNullChess(vo.getWhole(),chessX+(-2*shift),chessY+(-1*shift))
                        || !this.isSelfChess(vo,chessX+(-2*shift),chessY+(-1*shift)))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(-2*shift),chessY+(-1*shift)));
                }
                // 左下
                if((this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY)||skill==4)  // 不撇脚或拥有【金戈铁骑】技能
                        && this.isValidLocation(chessX+(-2*shift),chessY+(1*shift))  // 目标位置是否有效未越界
                        && (this.isNullChess(vo.getWhole(),chessX+(-2*shift),chessY+(1*shift))
                        || !this.isSelfChess(vo,chessX+(-2*shift),chessY+(1*shift)))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(-2*shift),chessY+(1*shift)));
                }
                break;
            case 5:  // 相/象
                shift=vo.isActorIsRoomOwner()?1:-1;
                // 左上
                if(this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY-1)
                        && chessX>=2 && chessY>=2 && (chessY-4)*shift>=2
                        &&(this.isNullChess(vo.getWhole(),chessX,chessY)
                        ||!this.isSelfChess(vo,chessX-2,chessY-2))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX-2,chessY-2));
                }
                // 左下
                if(this.isNullChess(vo.getWhole(),chessX-1,chessY+1)
                        && chessX>=2 && tableY>=chessY+2
                        &&(this.isNullChess(vo.getWhole(),chessX,chessY)
                        ||!this.isSelfChess(vo,chessX-2,chessY+2))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX-2,chessY+2));
                }
                // 右上
                if(this.isNullChess(vo.getWhole(),chessX+1,chessY-1)
                        && tableX>=chessX+2 && chessY>=2 && (chessY-4)*shift>=2
                        &&(this.isNullChess(vo.getWhole(),chessX+2,chessY-2)
                        ||!this.isSelfChess(vo,chessX+2,chessY-2))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+2,chessY-2));
                }
                // 右下
                if(this.isNullChess(vo.getWhole(),chessX+1,chessY+1)
                        && tableX>=chessX+2 && tableY>=chessY+2
                        &&(this.isNullChess(vo.getWhole(),chessX+2,chessY+2)
                        ||!this.isSelfChess(vo,chessX+2,chessY+2))){
                    vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+2,chessY+2));
                }
                break;
            case 6:  // 士/仕
                if(vo.isActorIsRoomOwner()){  // 红方(房主)
                    if(chessX>3&&chessY>tableY-3&&!this.isSelfChess(vo,chessX-1,chessY-1)){  // 左上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX-1,chessY-1));
                    }
                    if(chessX>3&&chessY<tableY-1&&!this.isSelfChess(vo,chessX-1,chessY+1)){  // 左下
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX-1,chessY+1));
                    }
                    if(chessX<5&&chessY>tableY-3&&!this.isSelfChess(vo,chessX+1,chessY-1)){  // 右上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+1,chessY-1));
                    }
                    if(chessX<5&&chessY<tableY-1&&!this.isSelfChess(vo,chessX+1,chessY+1)){  // 右下
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+1,chessY+1));
                    }
                }
                else{  // 黑方
                    if(chessX<5&&chessY<2&&!this.isSelfChess(vo,chessX+1,chessY+1)){  // 左上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+1,chessY+1));
                    }
                    if(chessX<5&&chessY>0&&!this.isSelfChess(vo,chessX+1,chessY-1)){  // 左下
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+1,chessY-1));
                    }
                    if(chessX>3&&chessY<2&&!this.isSelfChess(vo,chessX-1,chessY+1)){  // 右上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX-1,chessY+1));
                    }
                    if(chessX>3&&chessY>0&&!this.isSelfChess(vo,chessX-1,chessY-1)){  // 右下
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX-1,chessY-1));
                    }
                }
                break;
            case 7:  // 帅/将
                if (skill==7) {  // 技能开启
                    offsetX=vo.isActorIsRoomOwner()? -1:1;
                    for (int i = chessX+offsetX; tableX>i&&i>=0;i+=offsetX) {  // 左
                        if(this.isNullChess(vo.getWhole(),i,chessY)){  // 是一个空位置
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                        }
                        else if(!this.isSelfChess(vo,i,chessY)){  // 是自己的棋子
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                            break;
                        }
                        else{
                            break;
                        }
                    }
                    offsetX=vo.isActorIsRoomOwner()? 1:-1;
                    for (int i = chessX+offsetX; tableX>i&&i>=0;i+=offsetX) {  // 右
                        if(this.isNullChess(vo.getWhole(),i,chessY)){
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                        }
                        else if(!this.isSelfChess(vo,i,chessY)){
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,i,chessY));
                            break;
                        }
                        else{
                            break;
                        }
                    }
                    offsetY=vo.isActorIsRoomOwner()? -1:1;
                    for (int i = chessY+offsetY; tableY>i&&i>=0;i+=offsetY) {  // 上
                        if(this.isNullChess(vo.getWhole(),chessX,i)){
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                        }
                        else if(!this.isSelfChess(vo,chessX,i)){
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                            break;
                        }
                        else{
                            break;
                        }
                    }
                    offsetY=vo.isActorIsRoomOwner()? 1:-1;
                    for (int i = chessY+offsetY; tableY>i&&i>=0;i+=offsetY) {  // 下
                        if(this.isNullChess(vo.getWhole(),chessX,i)){
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                        }
                        else if(!this.isSelfChess(vo,chessX,i)){
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,i));
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }
                else if(vo.isActorIsRoomOwner()){  // 红方(房主)
                    if(chessY>tableY-3&&!this.isSelfChess(vo,chessX,chessY-1)){  // 上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY-1));
                    }
                    if(chessY<tableY-1&&!this.isSelfChess(vo,chessX,chessY+1)){  // 下
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY+1));
                    }
                    if(chessX>3&&!this.isSelfChess(vo,chessX-1,chessY)){  // 左
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX-1,chessY));
                    }
                    if(chessX<5&&!this.isSelfChess(vo,chessX+1,chessY)){  // 右
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+1,chessY));
                    }
                }
                else{  // 黑方
                    if(chessY<2&&!this.isSelfChess(vo,chessX,chessY+1)){  // 上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY+1));
                    }
                    if(chessY>0&&!this.isSelfChess(vo,chessX,chessY-1)){  // 下
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY-1));
                    }
                    if(chessX<5&&!this.isSelfChess(vo,chessX+1,chessY)){  // 左
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+1,chessY));
                    }
                    if(chessX>3&&!this.isSelfChess(vo,chessX-1,chessY)){  // 右
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX-1,chessY));
                    }
                }
                break;
            default:
                log.info("未知类型棋子");
                break;
        }

        // 根据技能移除不符合要求的 tip
        if(vo.isActorIsRoomOwner()){  // 房主是行动方
            if(vo.getRivalSkill()==4){  // 对手拥有【金戈铁骑】技能
                for (ChineseChessBaseVo chineseChessBaseVo : vo.getBlack()) {
                    if(chineseChessBaseVo.getPiece()==4){
                        for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                            if(chineseChessBaseLocationVo.getX()==1&&chineseChessBaseLocationVo.getY()==0){
                                removeTips(vo.getChineseChessBaseTip(),1,0);
                            }
                            if(chineseChessBaseLocationVo.getX()==7&&chineseChessBaseLocationVo.getY()==0){
                                removeTips(vo.getChineseChessBaseTip(),7,0);
                            }
                        }
                    }
                }
            }
            if(vo.getRivalSkill()==5){  // 对手拥有【蚕丛鸟道】技能
                for (ChineseChessBaseVo chineseChessBaseVo : vo.getBlack()) {
                    if(chineseChessBaseVo.getPiece()==5&&chineseChessBaseVo.getLocation().size()>0){
                        if(crossings(vo.getRed(),true)>=2){
                            ArrayList<ChineseChessBaseTipVo> tipList = vo.getChineseChessBaseTip();
                            for (int i = tipList.size()-1; i >0 ; i--) {
                                if(tipList.get(i).getY()<5&&vo.getSelectedChessY()>4){
                                    tipList.remove(i);  // 已经过河的棋子不低于2枚,将移除 tip 集中过河的 tip
                                }
                            }
                        }
                        break;
                    }
                }
            }
            if(vo.getRivalSkill()==6) {  // 对手拥有【固若金汤】技能
                // 获取【帅/将】的位置
                Integer x=0,y=0;
                for (ChineseChessBaseVo chineseChessBaseVo : vo.getBlack()) {
                    if(chineseChessBaseVo.getPiece()==7){
                        x=chineseChessBaseVo.getLocation().get(0).getX();
                        y=chineseChessBaseVo.getLocation().get(0).getY();
                    }
                }
                // 判断【士/仕】是否存在,存在则移除【帅/将】的对应位置的 tip
                for (ChineseChessBaseVo chineseChessBaseVo : vo.getBlack()) {
                    if(chineseChessBaseVo.getPiece()==6&&chineseChessBaseVo.getLocation().size()>0){
                        ArrayList<ChineseChessBaseTipVo> tipList = vo.getChineseChessBaseTip();
                        for (int i = tipList.size()-1; i >0 ; i--) {
                            if(tipList.get(i).getX()==x&&tipList.get(i).getY()==y){
                                tipList.remove(i);  // 已经过河的棋子不低于2枚,将移除 tip 集中过河的 tip
                            }
                        }
                        break;
                    }
                }
            }
        }
        else{  // 对手是行动方
            if(vo.getRoomOwnerSkill()==4){  // 房主拥有【金戈铁骑】技能
                for (ChineseChessBaseVo chineseChessBaseVo : vo.getRed()) {
                    if(chineseChessBaseVo.getPiece()==4){
                        for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                            if(chineseChessBaseLocationVo.getX()==1&&chineseChessBaseLocationVo.getY()==9){
                                removeTips(vo.getChineseChessBaseTip(),1,9);
                            }
                            if(chineseChessBaseLocationVo.getX()==7&&chineseChessBaseLocationVo.getY()==9){
                                removeTips(vo.getChineseChessBaseTip(),7,9);
                            }
                        }
                    }
                }
            }
            if(vo.getRoomOwnerSkill()==5){  // 房主拥有【蚕丛鸟道】技能
                for (ChineseChessBaseVo chineseChessBaseVo : vo.getRed()) {
                    if(chineseChessBaseVo.getPiece()==5&&chineseChessBaseVo.getLocation().size()>0){
                        if(crossings(vo.getBlack(),false)>=2){
                            ArrayList<ChineseChessBaseTipVo> tipList = vo.getChineseChessBaseTip();
                            for (int i = tipList.size()-1; i >0 ; i--) {
                                if(tipList.get(i).getY()>4&&vo.getSelectedChessY()<5){
                                    tipList.remove(i);  // 已经过河的棋子不低于2枚,将移除 tip 集中过河的 tip
                                }
                            }
                        }
                        break;
                    }
                }
            }
            if(vo.getRoomOwnerSkill()==6) {  // 房主拥有【固若金汤】技能
                // 获取【帅/将】的位置
                Integer x=0,y=0;
                for (ChineseChessBaseVo chineseChessBaseVo : vo.getRed()) {
                    if(chineseChessBaseVo.getPiece()==7){
                        x=chineseChessBaseVo.getLocation().get(0).getX();
                        y=chineseChessBaseVo.getLocation().get(0).getY();
                    }
                }
                // 判断【士/仕】是否存在,存在则移除【帅/将】的对应位置的 tip
                for (ChineseChessBaseVo chineseChessBaseVo : vo.getRed()) {
                    if(chineseChessBaseVo.getPiece()==6&&chineseChessBaseVo.getLocation().size()>0){
                        ArrayList<ChineseChessBaseTipVo> tipList = vo.getChineseChessBaseTip();
                        for (int i = tipList.size()-1; i >0 ; i--) {
                            if(tipList.get(i).getX()==x&&tipList.get(i).getY()==y){
                                tipList.remove(i);  // 已经过河的棋子不低于2枚,将移除 tip 集中过河的 tip
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 判断玩家已过河的棋子数量
     * @param chineseChessBaseVoList
     * @param isRed 玩家是否为红方(房主)
     * @return
     */
    private Integer crossings(ArrayList<ChineseChessBaseVo> chineseChessBaseVoList,boolean isRed) {
        Integer num=0;
        for (ChineseChessBaseVo chineseChessBaseVo : chineseChessBaseVoList) {
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                if(isRed){
                    if(chineseChessBaseLocationVo.getY()<5){
                        num++;
                    }
                }
                else{
                    if(chineseChessBaseLocationVo.getY()>4){
                        num++;
                    }
                }
            }
        }
        return num;
    }

    /**
     * 移除指定位置的 tip
     * @param chineseChessBaseTip
     * @param x
     * @param y
     */
    private void removeTips(ArrayList<ChineseChessBaseTipVo> chineseChessBaseTip,Integer x,Integer y){
        for (int i = 0; i < chineseChessBaseTip.size(); i++) {
            if(chineseChessBaseTip.get(i).getType()==1&&chineseChessBaseTip.get(i).getX()==x&&chineseChessBaseTip.get(i).getY()==y){
                chineseChessBaseTip.remove(i);
            }
        }
    }

    /**
     * 判断目标位置是否有效未越界
     * @param x
     * @param y
     * @return
     */
    private boolean isValidLocation(Integer x, Integer y) {
        if(x<0||x>8||y<0||y>9){
            return false;
        }
        return true;
    }


    /**
     * 判断指定位置是否不存在棋子,此外当目标位置为非法位置时返回 false
     * @param whole
     * @param x
     * @param y
     * @return
     */
    private boolean isNullChess(ArrayList<ChineseChessBaseVo> whole,Integer x, Integer y) {
        if(x<0||x>8||y<0||y>9){
            return false;
        }
        for (ChineseChessBaseVo chineseChessBaseVo : whole) {  // 遍历整个棋局
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                if(chineseChessBaseLocationVo.getX().equals(x) && chineseChessBaseLocationVo.getY().equals(y)){  // 指定位置存在棋子
                    return false;  // 返回拥有者标识
                }
            }
        }
        return true;
    }

    /**
     * 判断指定位置的棋子是否是自己的棋子
     * @return
     */
    private boolean isSelfChess(ChineseChessRoomVo vo,Integer x, Integer y){
        for (ChineseChessBaseVo chineseChessBaseVo : vo.isActorIsRoomOwner()?vo.getRed():vo.getBlack()) {
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                if(chineseChessBaseLocationVo.getX().equals(x) && chineseChessBaseLocationVo.getY().equals(y)){  // 找到对应位置的棋子
                    return true;  // 返回拥有者标识
                }
            }
        }
        return false;
    }

    /**
     * 判断指定位置的棋子是否是对手的棋子
     * @return
     */
    private boolean isRivalChess(ChineseChessRoomVo vo,Integer x, Integer y){
        for (ChineseChessBaseVo chineseChessBaseVo : vo.isActorIsRoomOwner()?vo.getBlack():vo.getRed()) {
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {
                if(chineseChessBaseLocationVo.getX().equals(x) && chineseChessBaseLocationVo.getY().equals(y)){  // 找到对应位置的棋子
                    return true;  // 返回拥有者标识
                }
            }
        }
        return false;
    }

    /**
     * 初始化棋盘信息以及相关状态和配置
     */
    private void initChineseChess(ChineseChessRoomVo vo) {
        vo.setStatus(2);
        vo.setPreviousChess(0);
        if(vo.getBlack()==null){
            vo.setBlack(new ArrayList<>());
        }
        else{
            vo.getBlack().clear();
        }
        vo.getBlack().add(this.ChineseChessFactory(false,1,0,3,2,3,4,3,6,3,8,3));  // 1-兵/卒
        vo.getBlack().add(this.ChineseChessFactory(false,2,1,2,7,2));  // 2-炮/砲
        vo.getBlack().add(this.ChineseChessFactory(false,3,0,0,8,0));  // 3-车
        vo.getBlack().add(this.ChineseChessFactory(false,4,1,0,7,0));  // 4-马
        vo.getBlack().add(this.ChineseChessFactory(false,5,2,0,6,0));  // 5-相/象
        vo.getBlack().add(this.ChineseChessFactory(false,6,3,0,5,0));  // 6-士/仕
        vo.getBlack().add(this.ChineseChessFactory(false,7,4,0));  // 7-帅/将

        if(vo.getRed()==null){
            vo.setRed(new ArrayList<>());
        }
        else{
            vo.getRed().clear();
        }
        vo.getRed().add(this.ChineseChessFactory(true,1,0,6,2,6,4,6,6,6,8,6));  // 1-兵/卒
        vo.getRed().add(this.ChineseChessFactory(true,2,1,7,7,7));  // 2-炮/砲
        vo.getRed().add(this.ChineseChessFactory(true,3,0,9,8,9));  // 3-车
        vo.getRed().add(this.ChineseChessFactory(true,4,1,9,7,9));  // 4-马
        vo.getRed().add(this.ChineseChessFactory(true,5,2,9,6,9));  // 5-相/象
        vo.getRed().add(this.ChineseChessFactory(true,6,3,9,5,9));  // 6-士/仕
        vo.getRed().add(this.ChineseChessFactory(true,7,4,9));  // 7-帅/将
    }

    /**
     * 镜像,发送给挑战者
     * 镜像:x'=8-x,y'=9-y
     * @param vo
     */
    private static ArrayList<ChineseChessBaseTipVo> imageTip(ArrayList<ChineseChessBaseTipVo> vo){
        ArrayList<ChineseChessBaseTipVo> res = new ArrayList<>();
        for (ChineseChessBaseTipVo chessBaseVo : vo) {
//            res.add(new ChineseChessBaseTipVo(chessBaseVo.getType(),8-chessBaseVo.getX(),9-chessBaseVo.getY()));
            ChineseChessBaseTipVo obj = CloneUtil.clone(chessBaseVo);
            obj.setX(8-obj.getX());
            obj.setY(9-obj.getY());
            res.add(obj);
        }
        return res;
    }

    /**
     * 镜像tip,发送给黑方(对手)
     * 镜像:x'=8-x,y'=9-y
     * @param vo
     */
    private static ArrayList<ChineseChessBaseVo> image(ArrayList<ChineseChessBaseVo> vo){
        ArrayList<ChineseChessBaseVo> result= new ArrayList<>();
        for (ChineseChessBaseVo chessBaseVo : vo) {
            ChineseChessBaseVo baseVo = new ChineseChessBaseVo();
            baseVo.setPiece(chessBaseVo.getPiece());
            baseVo.setLocation(new ArrayList<>());
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chessBaseVo.getLocation()) {
                ChineseChessBaseLocationVo v = new ChineseChessBaseLocationVo(8-chineseChessBaseLocationVo.getX(),9-chineseChessBaseLocationVo.getY());
                baseVo.getLocation().add(v);
            }
            result.add(baseVo);
        }
        return result;
    }

    /**
     * 生成指定类型棋子对象集,黑上红下,绝对位置
     * @param roomOwner 拥有者
     * @param piece 棋子类型
     * @param locations 所有该类型的棋子的位置
     * @return
     */
    private ChineseChessBaseVo ChineseChessFactory(boolean roomOwner,Integer piece,Integer ... locations){
        ChineseChessBaseVo chessBaseVo = new ChineseChessBaseVo();
        chessBaseVo.setRoomOwner(roomOwner);
        chessBaseVo.setPiece(piece);
        int i=0,x = 0;
        ArrayList<ChineseChessBaseLocationVo> location = new ArrayList<>();
        for(Integer y:locations){
            if(i%2==1){  // 遍历至y值
                location.add(new ChineseChessBaseLocationVo(x,y));
            }else{
                x=y;
            }
            i++;
        }
        chessBaseVo.setLocation(location);
        return chessBaseVo;
    }

    /**
     * 将双方的棋子整合在一起,用于判断
     */
    private void integrate(ChineseChessRoomVo vo){
        ArrayList<ChineseChessBaseVo> whole = new ArrayList<>();
        for (ChineseChessBaseVo chessBaseVo : vo.getRed()) {  // 红方
            ChineseChessBaseVo chineseChessBaseVo = new ChineseChessBaseVo();
            chineseChessBaseVo.setLocation(new ArrayList<>());
            chineseChessBaseVo.setRoomOwner(true);
            chineseChessBaseVo.setPiece(chessBaseVo.getPiece());
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chessBaseVo.getLocation()) {
                ChineseChessBaseLocationVo locationVo = new ChineseChessBaseLocationVo();
                locationVo.setX(chineseChessBaseLocationVo.getX());
                locationVo.setY(chineseChessBaseLocationVo.getY());
                chineseChessBaseVo.getLocation().add(locationVo);
            }
            whole.add(chineseChessBaseVo);
        }
        for (ChineseChessBaseVo chessBaseVo : vo.getBlack()) {  // 黑方
            ChineseChessBaseVo chineseChessBaseVo = new ChineseChessBaseVo();
            chineseChessBaseVo.setLocation(new ArrayList<>());
            chineseChessBaseVo.setRoomOwner(false);
            chineseChessBaseVo.setPiece(chessBaseVo.getPiece());
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chessBaseVo.getLocation()) {
                ChineseChessBaseLocationVo locationVo = new ChineseChessBaseLocationVo();
                locationVo.setX(chineseChessBaseLocationVo.getX());
                locationVo.setY(chineseChessBaseLocationVo.getY());
                chineseChessBaseVo.getLocation().add(locationVo);
            }
            whole.add(chineseChessBaseVo);
            vo.setWhole(whole);
        }
    }

    // 当前玩家已移动了棋子回合结束,分析棋盘,判断是否 将军/绝杀
    private Map<String,String> analysis(ChineseChessRoomVo vo){
        Map<String,String> res = new HashMap<>();
        res.put("type","0");  // 0:无,1:红方将军,2:黑方将军,3:红方绝杀黑方,4:黑方绝杀红方
//        res.put("msg","你输了");

        /**
         * 将军:当前行动方结束回合,其下一个回合有机会将对方 将/帅 吃掉
         * 绝杀:当前行动方结束回合,其下一个回合必将对方 将/帅 吃掉
         */

        /**
         * 获取 帅/将 的位置
         */
        Integer x=0,y= 0;
        String msg;
        Iterator<ChineseChessBaseVo> it;
        if(vo.isActorIsRoomOwner()){  // 当前红方(房主)在行动
            log.info("判断红方是否可以 将军 黑方");
            // 找到黑方的 帅/将 的位置
            it = vo.getBlack().iterator();
            while (it.hasNext()){
                ChineseChessBaseVo baseVo = it.next();
                if(baseVo.getPiece()==7){
                    x=baseVo.getLocation().get(0).getX();
                    y=baseVo.getLocation().get(0).getY();
                    break;
                }
            }
            ArrayList<HashMap<String, Integer>> map = this.getGeneralAllNext(vo, x, y);
//            if(this.lock(vo.getWhole(),x,y,vo.getRed().iterator())){
//                res.put("type","1");
//                res.put("msg","红方将军!");
//            }

            /**
             * 绝杀对手必须满足以下条件之一:
             * 1.将军对方后,对方的 帅/将 无法移动,且无法通过(阻挡/阻碍/吃)移动棋子改变将军的状态
             * 2.将军对方后,移动对方的 帅/将 但可移动的所有位置都已被"将军"
             */
            // 绝杀判断
            boolean flag=true;
            if("1".equals(res.get("type"))){
                for (HashMap<String, Integer> hashMap : map) {  // 遍历 帅/将 的所有下一步可行的位置
                    ArrayList<ChineseChessBaseVo> temp = new ArrayList<>(vo.getWhole());
                    for (ChineseChessBaseVo baseVo : temp) {  // 变更 帅/将 位置
                        if(7==baseVo.getPiece()){
                            ChineseChessBaseLocationVo locationVo = baseVo.getLocation().get(0);
                            locationVo.setX(hashMap.get("x"));
                            locationVo.setY(hashMap.get("y"));
                        }
                    }
//                    if(!this.lock(temp,hashMap.get("x"),hashMap.get("y"),vo.getRed().iterator())){
//                        log.info("将军,但不是绝杀");
//                        flag=false;
//                        break;
//                    }
                }

            }
            if(flag){
                res.put("type","3");
                res.put("msg","红方绝杀黑方!");
                log.info("分析结果:红方绝杀黑方!");
            }
        }
        else{  // 当前黑方在行动
            log.info("判断黑方是否可以 将军 红方");
            // 找到红方的 帅/将 的位置
            it = vo.getRed().iterator();
            while (it.hasNext()){
                ChineseChessBaseVo baseVo = it.next();
                if(baseVo.getPiece()==7){
                    x=baseVo.getLocation().get(0).getX();
                    y=baseVo.getLocation().get(0).getY();
                    break;
                }
            }
//            if(this.lock(vo.getWhole(),x,y,vo.getBlack().iterator())){
//                res.put("type","2");
//                res.put("msg","黑方将军!");
//            }
        }
        return res;
    }

    // 指定玩家,给定 帅/将 的位置,判断是否被另一玩家将军
    private boolean lock(ArrayList<ChineseChessBaseVo> whole,Integer x,Integer y,Iterator<ChineseChessBaseVo> chineseChessBaseVoIterator){
        boolean flag = false;  // T:将军 F:未被将军
        // 遍历红方棋子,判断是否存在棋子 将军 黑方
//         =vo.getRed().iterator();
        while (chineseChessBaseVoIterator.hasNext()){
            ChineseChessBaseVo baseVo = chineseChessBaseVoIterator.next();
            if(!flag&&baseVo.getPiece()==1){  // 兵/卒
                for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : baseVo.getLocation()) {
                    if (chineseChessBaseLocationVo.getX() == (x + 1) && (chineseChessBaseLocationVo.getY().equals(y))  // 左
                            || (chineseChessBaseLocationVo.getX() == (x - 1) && chineseChessBaseLocationVo.getY().equals(y))  // 右
                            || (chineseChessBaseLocationVo.getX().equals(x) && chineseChessBaseLocationVo.getY().equals(y + 1))) {  // 上
                        log.info("兵/卒 > 将军");
                        flag = true;
                        break;
                    }
                }
            }
            else if(!flag&&baseVo.getPiece()==2){  // 炮/砲
                for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : baseVo.getLocation()) {
                    if (this.interval(whole,x,y,chineseChessBaseLocationVo.getX(),chineseChessBaseLocationVo.getY())==1) {  // 炮/砲 与 帅/将 在同一行或同一列上且之间仅存在一个棋子
                        log.info("炮/砲 > 将军");
                        flag = true;
                        break;
                    }
                }
            }
            else if(!flag&&baseVo.getPiece()==3){  // 车
                for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : baseVo.getLocation()) {
                    if (this.interval(whole,x,y,chineseChessBaseLocationVo.getX(),chineseChessBaseLocationVo.getY())==0) {  // 车 与 帅/将 在同一行或同一列上且之间仅存在一个棋子
                        log.info("车 > 将军");
                        flag = true;
                        break;
                    }
                }
            }
            else if(!flag&&baseVo.getPiece()==4){  // 马
                for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : baseVo.getLocation()) {
                    if(chineseChessBaseLocationVo.getX().equals(x+1)&&chineseChessBaseLocationVo.getY().equals(y+2)&&this.isNullChess(whole,x+1,y+1)){  // 上左
                        log.info("马 > 将军");
                        flag = true;
                        break;
                    }
                    if(chineseChessBaseLocationVo.getX().equals(x-1)&&chineseChessBaseLocationVo.getY().equals(y+2)&&this.isNullChess(whole,x-1,y+1)){  // 上右
                        log.info("马 > 将军");
                        flag = true;
                        break;
                    }
                    if(chineseChessBaseLocationVo.getX().equals(x+1)&&chineseChessBaseLocationVo.getY().equals(y-2)&&this.isNullChess(whole,x+1,y+1)){  // 下左
                        log.info("马 > 将军");
                        flag = true;
                        break;
                    }
                    if(chineseChessBaseLocationVo.getX().equals(x-1)&&chineseChessBaseLocationVo.getY().equals(y-2)&&this.isNullChess(whole,x-1,y+1)){  // 下右
                        log.info("马 > 将军");
                        flag = true;
                        break;
                    }

                    if(chineseChessBaseLocationVo.getX().equals(x+2)&&chineseChessBaseLocationVo.getY().equals(y-1)&&this.isNullChess(whole,x+1,y+1)){  // 左上
                        log.info("马 > 将军");
                        flag = true;
                        break;
                    }
                    if(chineseChessBaseLocationVo.getX().equals(x+2)&&chineseChessBaseLocationVo.getY().equals(y-1)&&this.isNullChess(whole,x+1,y-1)){  // 左下
                        log.info("马 > 将军");
                        flag = true;
                        break;
                    }
                    if(chineseChessBaseLocationVo.getX().equals(x-2)&&chineseChessBaseLocationVo.getY().equals(y+1)&&this.isNullChess(whole,x-1,y+1)){  // 右上
                        log.info("马 > 将军");
                        flag = true;
                        break;
                    }
                    if(chineseChessBaseLocationVo.getX().equals(x-2)&&chineseChessBaseLocationVo.getY().equals(y-1)&&this.isNullChess(whole,x-1,y-1)){  // 右下
                        log.info("马 > 将军");
                        flag = true;
                        break;
                    }
                }
            }
            else if(!flag&&baseVo.getPiece()==5){  // 相/象
                for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : baseVo.getLocation()) {
                    if (chineseChessBaseLocationVo.getX().equals(x + 2) && chineseChessBaseLocationVo.getY().equals(y + 2) && this.isNullChess(whole, x + 1, y + 1)) {  // 左上
                        log.info("相/象 > 将军");
                        flag = true;
                        break;
                    }
                    if (chineseChessBaseLocationVo.getX().equals(x + 2) && chineseChessBaseLocationVo.getY().equals(y - 2) && this.isNullChess(whole, x + 1, y - 1)) {  // 左下
                        log.info("相/象 > 将军");
                        flag = true;
                        break;
                    }
                    if (chineseChessBaseLocationVo.getX().equals(x - 2) && chineseChessBaseLocationVo.getY().equals(y + 2) && this.isNullChess(whole, x - 1, y + 1)) {  // 右上
                        log.info("相/象 > 将军");
                        flag = true;
                        break;
                    }
                    if (chineseChessBaseLocationVo.getX().equals(x - 2) && chineseChessBaseLocationVo.getY().equals(y - 2) && this.isNullChess(whole, x - 1, y - 1)) {  // 右下
                        log.info("相/象 > 将军");
                        flag = true;
                        break;
                    }
                }
            }
            else if(!flag&&baseVo.getPiece()==6){  // 士/仕
                for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : baseVo.getLocation()) {
                    if (chineseChessBaseLocationVo.getX().equals(x + 2) && chineseChessBaseLocationVo.getY().equals(y + 2)) {  // 左上
                        log.info("士/仕 > 将军");
                        flag = true;
                        break;
                    }
                    if (chineseChessBaseLocationVo.getX().equals(x + 2) && chineseChessBaseLocationVo.getY().equals(y - 2)) {  // 左下
                        log.info("士/仕 > 将军");
                        flag = true;
                        break;
                    }
                    if (chineseChessBaseLocationVo.getX().equals(x - 2) && chineseChessBaseLocationVo.getY().equals(y + 2)) {  // 右上
                        log.info("士/仕 > 将军");
                        flag = true;
                        break;
                    }
                    if (chineseChessBaseLocationVo.getX().equals(x - 2) && chineseChessBaseLocationVo.getY().equals(y - 2)) {  // 右下
                        log.info("士/仕 > 将军");
                        flag = true;
                        break;
                    }
                }
            }
            else if(!flag&&baseVo.getPiece()==7){  // 帅/将
                for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : baseVo.getLocation()) {
                    if (this.interval(whole,x,y,chineseChessBaseLocationVo.getX(),chineseChessBaseLocationVo.getY())==0) {  // 炮/砲 与 帅/将 在同一行或同一列上且之间仅存在一个棋子
                        log.info("帅/将 > 将军");
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    // 判断两棋子之间的棋子数量,-1表示不在同一列或同一行
    private Integer interval(ArrayList<ChineseChessBaseVo> whole,Integer x1,Integer y1,Integer x2,Integer y2){
        Integer count=0;
        if(x1.equals(x2)){  // 同一行
            if(y1>y2){
                Integer t=y1;
                y1=y2;
                y2=t;
            }
            for (ChineseChessBaseVo baseVo : whole) {
                for (ChineseChessBaseLocationVo locationVo : baseVo.getLocation()) {
                    if(locationVo.getX().equals(x1) && y1<locationVo.getY() && locationVo.getY()<y2){
                        count++;
                    }
                }
            }
        }
        else if(y1.equals(y2)){  // 同一列
            if(x1>x2){
                Integer t=x1;
                x1=x2;
                x2=t;
            }
            for (ChineseChessBaseVo baseVo : whole) {
                for (ChineseChessBaseLocationVo locationVo : baseVo.getLocation()) {
                    if(locationVo.getY().equals(y1) && x1<locationVo.getX() && locationVo.getX()<x2){
                        count++;
                    }
                }
            }
        }
        else{
            return -1;
        }
        return count;
    }

    /**
     * 给定 帅/将 的当前位置,获取其所有可能下一步的位置
     */
    private ArrayList<HashMap<String,Integer>> getGeneralAllNext(ChineseChessRoomVo vo,Integer x,Integer y){
        ArrayList<HashMap<String,Integer>> data= new ArrayList<>();
        Integer skill=vo.isActorIsRoomOwner()?vo.getRoomOwnerSkill():vo.getRivalSkill();
        if(skill==7){

        }
        else{
            if(((3>y&&y>0)||(y>7))&&!this.isSelfChess(vo,x,y-1)){  // 上
                HashMap<String,Integer> map = new HashMap<>();
                map.put("x",x);
                map.put("y",y-1);
                data.add(map);
            }
            if((2>y||(9>y&&y>6))&&!this.isSelfChess(vo,x,y+1)){  // 下
                HashMap<String,Integer> map = new HashMap<>();
                map.put("x",x);
                map.put("y",y+1);
                data.add(new HashMap<>());
            }
            if((6>x&&x>3)&&!this.isSelfChess(vo,x-1,y)){  // 左
                HashMap<String,Integer> map = new HashMap<>();
                map.put("x",x-1);
                map.put("y",y);
                data.add(map);
            }
            if((5>x&&x>2)&&!this.isSelfChess(vo,x+1,y)){  // 右
                HashMap<String,Integer> map = new HashMap<>();
                map.put("x",x+1);
                map.put("y",y);
                data.add(map);
            }
        }
        return data;
    }

    // 指定一枚棋子,将其
    private void getGuessVo(){

    }

    /**
     * 断开 WebSocket 连接时被调用
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        log.info("onClose");
        onlineUsers.remove(user.getId());
        chatInfo = new ChatInfo();
        chatInfo.setContent(user.getName()+"下线了");
        chatInfo.setCreateTimeStr(TimeUtils.getNow());
    }

    @OnError
    public void onError(Session session, Throwable t) {
        log.info("onError 出现未知错误:{}",session);
        t.printStackTrace();
    }

    private UserInfo getUserInfo(String token){
        return loginService.getUserInfoByToken(token);
    }

    @Autowired
    private void setLoginService(LoginService loginService){
        ChineseChessEndpoint.loginService = loginService;
    }

    @Autowired
    private void setChineseChessService(ChineseChessService chineseChessService){
        ChineseChessEndpoint.chineseChessService = chineseChessService;
    }

    @Autowired
    public void setChineseChessSnapshotService(ChineseChessSnapshotService chineseChessSnapshotService) {
        ChineseChessEndpoint.chineseChessSnapshotService = chineseChessSnapshotService;
    }

    @Autowired
    private void setUserService(UserService userService){
        ChineseChessEndpoint.userService = userService;
    }

    /**
     * 时间通知,
     */
    public static void timeNotification(String id,boolean end){
        for (ChineseChessRoomVo vo : onlineRoomList) {
            if (vo.getId().equals(id)&&vo.getStatus()==3) {  // 找到对应房间
                log.info("房间ID: {}", vo.getId());
                HashMap<String, Object> result = new HashMap<>();
                result.put("countdown", vo.getCountdown());  // tip,如果是黑方(对手)则需要将tip镜像
                String data = JSON.toJSONString(result);
                Session session = onlineUsers.get(vo.getRoomOwner());
                if(session!=null){
                    try {
                        session.getBasicRemote().sendText(MessageUtils.getMessage(true,19,"对战中!刷新房主倒计时!",data));  // 通知房主对手已进入房间

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    log.info("向房主发送倒计时失败!");
                }
                session = onlineUsers.get(vo.getRival());
                if(session!=null){
                    try {
                        session.getBasicRemote().sendText(MessageUtils.getMessage(true,19,"对战中!刷新对手倒计时!",data));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    log.info("向对手发送倒计时失败!");
                }

                if(end){
                    log.info("房间ID:{} 倒计时结束.",vo.getId());
                    // 结算
                    endBattle(vo,vo.isActorIsRoomOwner()?vo.getRival():vo.getRoomOwner(),vo.isActorIsRoomOwner(),"败方超时","当前玩家超时,结束对战!",DictionaryCode.CHINESE_CHESS_WINNER_WAY_3,"战败!","对方已超时!获胜!");  // 结束对战
                }
                else{
                    log.info("房间ID:{} 倒计时:{}",vo.getId(),vo.getCountdown());
                }
            }
        }
    }

    /**
     * 销毁空闲房间,长期未活动的房间
     * 房间创建/开始对战/移动棋子后,双方在接下来30分钟内未移动棋子/开始对战,系统将房间移除
     * 移除时双方均在准备中,或仅房主在房间内,不用担心在双方对战时将房间移除
     * 想着是否通知一声,不过长时间未行动通知未必有意义,大概率早就关闭了浏览器,通知也未必看得到
     * 但需要更新房间列表
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    private void destructionFreeRoom(){
        log.info("---开始销毁空闲房间任务---");
        boolean flag= false;  // 是否有房间被销毁
        for (int i = onlineRoomList.size()-1; i >= 0; i--) {
            long difference = Math.abs(Duration.between(onlineRoomList.get(i).getRecentActivityTime(), LocalDateTime.now()).getSeconds());
            if(difference>1800L){
//            if(difference>10L){
                flag=true;
                log.info("房间 {} 长时间未行动,已移除",onlineRoomList.get(i).getId());
                onlineRoomList.remove(i);
            }
        }
        if(flag){
            broadcastAllUsers(2);  // 更新在线房间列表
        }
        log.info("---销毁空闲房间任务结束---");
    }

}