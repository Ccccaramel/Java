package com.ding.hyld.webSocket;

import com.alibaba.fastjson2.JSON;
import com.ding.hyld.info.ChatInfo;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.service.ChineseChessService;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.MathUtils;
import com.ding.hyld.utils.MessageUtils;
import com.ding.hyld.utils.TimeUtils;
import com.ding.hyld.vo.ChineseChessBaseLocationVo;
import com.ding.hyld.vo.ChineseChessBaseTipVo;
import com.ding.hyld.vo.ChineseChessBaseVo;
import com.ding.hyld.vo.ChineseChessRoomVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@ServerEndpoint(value = "/linkChineseChess/{token}")
@Component
public class ChineseChessEndpoint {

    private static final Map<Integer,Session> onlineUsers = new ConcurrentHashMap<>();  // 所有在线玩家
    private static final CopyOnWriteArrayList<ChineseChessRoomVo> onlineRoomList = new CopyOnWriteArrayList<>();  // 房间列表

    private static LoginService loginService;
    private static ChineseChessService chineseChessService;
    private static UserService userService;

    private UserInfo user;
    private ChatInfo chatInfo;

    /**
     * 建立 WebSocket 连接后调用,将用户信息保存
     *   1.将 Session 保存
     *   2.广播消息
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
        onlineUsers.put(user.getId(),session);
        // 广播消息
//        chatInfo = new ChatInfo();
//        chatInfo.setContent(user.getName()+"上线了");
//        chatInfo.setCreateTimeStr(TimeUtils.getNow());
//        broadcastAllUsers(true);
        broadcastAllUsers(2);  // 更新在线房间列表
    }

    private void broadcastAllUsers(Integer instruct) {  // 刷新房间列表信息
        Set<Map.Entry<Integer, Session>> entrySet = onlineUsers.entrySet();
        for (Map.Entry<Integer, Session> stringSessionEntry : entrySet) {
            Session session = stringSessionEntry.getValue();
            try {
                String message = MessageUtils.getAllRoomInfo(true,instruct,"",getOnlineRoomListInfo());
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
        log.info("onMessage");
        // 将信息保存至数据库
        ChineseChessRoomVo chineseChessRoomVo = JSON.parseObject(message, ChineseChessRoomVo.class);

        // 创建房间
        if(chineseChessRoomVo.getInstruct()==1){
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
                chineseChessRoomVo.setNeedPassword(!chineseChessRoomVo.getPassword().isBlank());
                onlineRoomList.add(chineseChessRoomVo);
                broadcastAllUsers(2);  // 更新在线房间列表
                try {
                    HashMap<String,Object> result=new HashMap<>();
                    result.put("roomId", chineseChessRoomVo.getId());
                    result.put("roomName", chineseChessRoomVo.getName());
                    String data = JSON.toJSONString(result);
                    onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,1,"房间创建成功!",data));  // 告诉自己房间的 id
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 获取所有房间
        else if(chineseChessRoomVo.getInstruct()==2){
            try {
                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getAllRoomInfo(true,2,"",getOnlineRoomListInfo()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 加入房间
        else if(chineseChessRoomVo.getInstruct()==3){
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
                            try {
                                vo.setStatus(2);
                                HashMap<String,Object> result=new HashMap<>();
                                result.put("roomId", vo.getId());
                                result.put("roomName", vo.getName());
                                String data = JSON.toJSONString(result);
                                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,3,"指定对手进入房间成功!",data));
                                broadcastAllUsers(2);
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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
                                try {
                                    vo.setRival(user.getId());
                                    vo.setStatus(2);
                                    HashMap<String,Object> result=new HashMap<>();
                                    result.put("roomId", vo.getId());
                                    result.put("roomName", vo.getName());
                                    String data = JSON.toJSONString(result);
                                    onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,6,"对手已进入房间!",null));  // 通知房主对手已进入房间
                                    onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,3,"暗号正确,进入房间成功!",data));
                                    broadcastAllUsers(2);
                                    return;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
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
                            try {
                                vo.setRival(user.getId());
                                vo.setStatus(2);
                                HashMap<String,Object> result=new HashMap<>();
                                result.put("roomId", vo.getId());
                                result.put("roomName", vo.getName());
                                String data = JSON.toJSONString(result);
                                onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,6,"对手已进入房间!",null));  // 通知房主对手已进入房间
                                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,3,"进入房间成功!",data));
                                broadcastAllUsers(2);
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        // 退出房间
        else if(chineseChessRoomVo.getInstruct()==4){
            for (ChineseChessRoomVo vo:onlineRoomList) {
                if(vo.getId().equals(chineseChessRoomVo.getId())){
                    if(vo.getRoomOwner().equals(user.getId())){  // 房主要退出
                        try {
                            onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,4,"退出房间成功!",null));  // 通知自己(房主)
                            if(vo.getRival()!=null){  // 对手还在房间里
                                onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,4,"房主已退出房间!",null));  // 通知对手,并退出
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        onlineRoomList.remove(vo);  // 移除房间
                    }
                    else{  // 对手要退出
                        vo.setRival(null);
                        vo.setStatus(1);
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
        // 根据房间 id 获取房间内所有信息
        else if(chineseChessRoomVo.getInstruct()==7) {
            for (ChineseChessRoomVo vo:onlineRoomList) {
                if(vo.getId().equals(chineseChessRoomVo.getId())) {
                    HashMap<String,Object> result=new HashMap<>();
                    result.put("roomId", vo.getId());
                    result.put("roomName", vo.getName());
                    result.put("roomOwner", vo.getRoomOwner());
                    result.put("rival", vo.getRival());
                    boolean isRoomOwner = vo.getRoomOwner().equals(user.getId());
                    result.put("isRoomOwner", isRoomOwner);
                    result.put("selfSkill", isRoomOwner?vo.getRoomOwnerSkill():vo.getRivalSkill());
                    result.put("rivalSkill", isRoomOwner?vo.getRivalSkill():vo.getRoomOwnerSkill());
                    result.put("selfIsReady", isRoomOwner?vo.isRoomOwnerIsReady():vo.isRivalIsReady());
                    result.put("rivalIsReady", isRoomOwner?vo.isRivalIsReady():vo.isRoomOwnerIsReady());
                    String data = JSON.toJSONString(result);
                    try {
                        onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,7,"数据已重新加载!",data));
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
        // 根据房间 id 获取房间内所有信息
        else if(chineseChessRoomVo.getInstruct()==8) {
            for (ChineseChessRoomVo vo:onlineRoomList) {
                if (vo.getId().equals(chineseChessRoomVo.getId())) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("selfInfo", userService.findBriefInfoById(user.getId()));
                    result.put("selfBattle", chineseChessService.getBattleInfo(user.getId()));
                    result.put("status", vo.getStatus());
                    if(Arrays.asList(3,4).contains(vo.getStatus())){  // 已经开始对战
                        result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(user.getId().equals(vo.getRoomOwner())?vo.getRoomOwnerSkill():vo.getRivalSkill()));  // 技能信息
                    }
                    if(vo.getRival() != null){  // 双方已到场
                        if(user.getId().equals(vo.getRoomOwner())){  // 当前用户是房主
                            result.put("isRoomOwner", true);
                            result.put("rivalInfo", userService.findBriefInfoById(vo.getRival()));
                            result.put("rivalBattle", chineseChessService.getBattleInfo(vo.getRival()));
                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                            if(Arrays.asList(3,4).contains(vo.getStatus())){
                                result.put("isActor", vo.isActorIsRoomOwner());
                                result.put("oneself", vo.getRed());
                                result.put("opponent", vo.getBlack());
                            }
                        }
                        else{  // 当前用户不是房主
                            result.put("isRoomOwner", false);
                            result.put("rivalInfo", userService.findBriefInfoById(vo.getRoomOwner()));
                            result.put("rivalBattle", chineseChessService.getBattleInfo(vo.getRoomOwner()));
                            result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                            if(Arrays.asList(3,4).contains(vo.getStatus())) {
                                result.put("isActor", !vo.isActorIsRoomOwner());
                                result.put("oneself", this.image(vo.getBlack()));
                                result.put("opponent", this.image(vo.getRed()));
                            }
                        }
                    }

                    String data = JSON.toJSONString(result);
                    try {
                        onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(false,8,"",data));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // 当前用户修改了自己的技能
        else if(chineseChessRoomVo.getInstruct()==10) {
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
                        onlineUsers.get(rivalId).getBasicRemote().sendText(MessageUtils.getMessage(false,10,"",data));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // 当前用户准备好了
        else if(chineseChessRoomVo.getInstruct()==11) {
            for (ChineseChessRoomVo vo : onlineRoomList) {
                if (vo.getId().equals(chineseChessRoomVo.getId())) {
                    Integer recipient;
                    boolean next = false;
                    if(vo.getRoomOwner().equals(user.getId())){  // 当前用户就是房主,被通知的是对手
                        recipient = vo.getRival();
                        vo.setRoomOwnerIsReady(true);
                        if(vo.isRivalIsReady()){  // 感觉需要加个锁
                            next=true;
                        }
                    }
                    else{  // 当前用户不是房主,被通知的是房主
                        recipient = vo.getRoomOwner();
                        vo.setRivalIsReady(true);
                        if(vo.isRoomOwnerIsReady()){
                            next=true;
                        }
                    }
                    try {
                        onlineUsers.get(recipient).getBasicRemote().sendText(MessageUtils.getMessage(true,11,"对手准备好了",null));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(next){  // 双方都准备好了
                        try {
                            onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,13,"开始决定先后手",null));
                            onlineUsers.get(recipient).getBasicRemote().sendText(MessageUtils.getMessage(true,13,"开始决定先后手",null));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        // 当前用户取消了准备
        else if(chineseChessRoomVo.getInstruct()==12) {
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
        // 在先后手判断中,用户选择了数字
        else if(chineseChessRoomVo.getInstruct()==14) {
            for (ChineseChessRoomVo vo : onlineRoomList) {
                if (vo.getId().equals(chineseChessRoomVo.getId())) {
                    if(vo.getRoomOwner().equals(user.getId())){  // 当前用户就是房主
                        vo.setRoomOwnerValue(chineseChessRoomVo.getFingerGuessValue());
                    }
                    else{  // 当前用户不是房主
                        vo.setRivalValue(chineseChessRoomVo.getFingerGuessValue());
                    }
                    // 当双方都选择了数字,则立即判断并通知
                    try {
                        if(vo.getRoomOwnerValue()!=null && vo.getRivalValue()!=null){
                            this.initChineseChess(vo);
                            vo.setStatus(3);  // 对战开始
                            HashMap<String, Object> result = new HashMap<>();
                            result.put("round", 1);  // 当前回合
                            result.put("oneself", vo.getRed());  // 自己
                            result.put("opponent", vo.getBlack());  // 对手
                            if((vo.getRoomOwnerValue()==-1&&vo.getRivalValue()!=-1) && (vo.getRoomOwnerValue()!=-1&&vo.getRivalValue()!=-1&&Objects.equals(vo.getRoomOwnerValue(), vo.getRivalValue()))){
                                vo.setActorIsRoomOwner(false);
                            }
                            else{
                                vo.setActorIsRoomOwner(true);
                            }
                            if(vo.isActorIsRoomOwner()){  // 先手是房主
                                result.put("isActor", true);
                                result.put("isRoomOwner", true);
                                result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                                result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                                String data = JSON.toJSONString(result);
                                onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是先手!",data));
                                result.put("isActor", false);
                                result.put("isRoomOwner", false);
                                result.put("oneself", this.image(vo.getBlack()));  // 自己
                                result.put("opponent", this.image(vo.getRed()));  // 对手
                                result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                                result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                                data = JSON.toJSONString(result);
                                onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是后手!",data));
                            }
                            else{  // 先手是对手
                                result.put("isActor", false);
                                result.put("isRoomOwner", true);
                                result.put("selfSkillName",ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                                result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                                String data = JSON.toJSONString(result);
                                onlineUsers.get(vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是后手!",data));
                                result.put("isActor", true);
                                result.put("isRoomOwner", false);
                                result.put("oneself", this.image(vo.getBlack()));  // 自己
                                result.put("opponent", this.image(vo.getRed()));  // 对手
                                result.put("selfSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRivalSkill()));
                                result.put("rivalSkillName", ChineseChessRoomVo.SKILL_NAME.get(vo.getRoomOwnerSkill()));
                                data = JSON.toJSONString(result);
                                onlineUsers.get(vo.getRival()).getBasicRemote().sendText(MessageUtils.getMessage(true,14,"你是先手!",data));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
         */
        else if(chineseChessRoomVo.getInstruct()==15) {
            for (ChineseChessRoomVo vo : onlineRoomList) {
                if (vo.getId().equals(chineseChessRoomVo.getId())) {  // 找到对应房间
                    // Step1
                    Integer chessPlayer = vo.isActorIsRoomOwner()?1:2;  // 当前用户标识 1:红方(房主) 2:黑方(挑战者)
                    boolean isRoomOwner=vo.getRoomOwner().equals(user.getId());
                    Integer skill=isRoomOwner?vo.getRoomOwnerSkill():vo.getRivalSkill();  // 获取当前用户的技能标识

                    // 将上次选中的位置信息赋值给"上一次"
                    vo.setPreviousChessOwner(vo.getSelectedChessOwner());
                    vo.setPreviousChess(vo.getSelectedChess());
                    vo.setPreviousChessX(vo.getSelectedChessX());
                    vo.setPreviousChessY(vo.getSelectedChessY());
                    vo.setSelectedChess(0);

                    if(chessPlayer==2){  // 如果是黑方(对手)需要将其坐标转换成镜像
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

                    boolean updateOpponent = false;  // 是否更新对手的棋局
                    if(chineseChessRoomVo.getSelectedChessX()==-1||chineseChessRoomVo.getSelectedChessY()==-1){  // 越界了,本次选中了无效处
                        vo.setChineseChessBaseTip(null);  // 清除所有提示
                        vo.setPreviousChessOwner(-1);  // 置空"上次选择的棋子"的拥有者
                        vo.setPreviousChess(0);  // 置空"上次选择的棋子"的类型
                    }
                    else{
                        int[] res = this.getChessType(vo,chineseChessRoomVo.getSelectedChessX(),chineseChessRoomVo.getSelectedChessY());  // 获取本次点击的位置的信息

                        // Step2
                        if(res[0]==0){  // 本次选中了空白处
                            if(Objects.equals(vo.getPreviousChessOwner(), chessPlayer)){  // 上次点击的棋子的拥有者就是当前用户
                                this.moveChess(vo,chessPlayer,0);  // 移动棋子
                                updateOpponent=true;
                            }
                            else{  // 上次点击的棋子的拥有者不是当前用户
                                vo.setChineseChessBaseTip(null);  // 清除所有提示
                                vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chineseChessRoomVo.getSelectedChessX(),chineseChessRoomVo.getSelectedChessY()));  // 将本次点击的空白位置添加高亮
                            }
                        }
//                        else if(res[0]==1){  // 选中了红方(房主)棋子
                        else if(res[0]==chessPlayer){  // 本次选中了自己的棋子
                            vo.setChineseChessBaseTip(null);  // 清除所有提示
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(0,chineseChessRoomVo.getSelectedChessX(),chineseChessRoomVo.getSelectedChessY()));  // 将本次点击的自己的棋子添加高亮
                            this.accessibility(vo,skill);
                        }
//                        else if(res[0]==2){  // 选中了黑方(对手)棋子
                        else {  // 本次选中了对方的棋子
                            boolean flag = false;// 判断本次命中的棋子的坐标是否存在 tip 中
                            for (ChineseChessBaseTipVo chineseChessBaseTipVo:vo.getChineseChessBaseTip()) {
                                if(vo.getSelectedChessX().equals(chineseChessBaseTipVo.getX())&&vo.getSelectedChessY().equals(chineseChessBaseTipVo.getY())){
                                    flag=true;
                                    break;
                                }
                            }
                            if(this.isSelfChess(vo,vo.getPreviousChessX(),vo.getPreviousChessY())&&flag){  // 上一次选中的是自己的棋子,并且当前选中的对方棋子在 tip 中
                                this.moveChess(vo,chessPlayer,1);  // 移动棋子
                                updateOpponent=true;
                            }
                            else{  // 上一次选中的是自己的棋子,但当前选中的对方棋子不在 tip 中,则清空 tip
                                vo.setChineseChessBaseTip(null);  // 清除所有提示
                            }
                        }
                    }

                    // 返回棋盘信息给当前用户
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("code",0);  // 状态码,与 msg 有关, 0:无,1:红方将军,2:黑方将军,3:红方被绝杀,4:黑方被绝杀
                    result.put("msg","暂无提示信息");
                    if(updateOpponent){  // 更新对手棋局,则意味着当前玩家已移动了棋子,结束了自己的回合
                        result.put("isActor", false);

                        Map<String, String> data = this.analysis(vo);  // 分析棋盘,判断是否绝杀
                        if("1".equals(data.get("type"))||"2".equals(data.get("type"))){  // 将军对手
                            result.put("code",data.get("type"));
                            result.put("msg","将军!");
                            log.info("将军对手");

                        }
                        else if("3".equals(data.get("type"))||"4".equals(data.get("type"))){  // 绝杀对手
                            result.put("code",data.get("type"));
                            result.put("msg","绝杀!");
                            log.info("绝杀对手");
                        }

                    }
                    else{
                        result.put("isActor", true);
                    }

                    // 发送给当前玩家
                    result.put("isRoomOwner", isRoomOwner);
                    result.put("oneself", isRoomOwner?vo.getRed():this.image(vo.getBlack()));  // 自己
                    result.put("opponent", isRoomOwner?vo.getBlack():this.image(vo.getRed()));  // 对手
                    result.put("tip", isRoomOwner? vo.getChineseChessBaseTip():this.imageTip(vo.getChineseChessBaseTip()));  // tip,如果是黑方(对手)则需要将tip镜像
                    String data = JSON.toJSONString(result);
                    try {
                        onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getMessage(true,15,"包含 tip 信息!",data));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(updateOpponent){  // 更新对手棋局并授予控制权,当当前用户移动了棋子时触发
                        result.put("isActor", true);  // 允许对手控制棋子
                        result.put("isRoomOwner", !isRoomOwner);
                        result.put("oneself", isRoomOwner?this.image(vo.getBlack()):vo.getRed());  // 自己棋局
                        result.put("opponent", isRoomOwner?this.image(vo.getRed()):vo.getBlack());  // 对手棋局
//                        result.put("tip", vo.getChineseChessBaseTip());  // tip
                        if("1".equals(result.get("code"))||"2".equals(result.get("code"))){  // 被将军
                            result.put("msg","你被将军了!");
                            log.info("被将军");
                        }
                        else if("3".equals(result.get("code"))||"4".equals(result.get("code"))){  // 被绝杀
                            result.put("msg","你被绝杀了!");
                            log.info("被绝杀");
                        }
                        vo.setActorIsRoomOwner(!vo.isActorIsRoomOwner());  // 变更服务器存储的控制权
                        data = JSON.toJSONString(result);
                        try {
                            onlineUsers.get(isRoomOwner?vo.getRival():vo.getRoomOwner()).getBasicRemote().sendText(MessageUtils.getMessage(true,15,"包含 tip 信息!",data));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
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
                if(type.equals(0)){  // 移至空白处
                    for (ChineseChessBaseVo chineseChessBaseVo : chessPlayer==1?vo.getRed():vo.getBlack()) {  // 判断所属并遍历棋子
                        if(chineseChessBaseVo.getPiece().equals(vo.getPreviousChess())){  // 找到该棋子
                            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {  // 遍历该棋子所有的位置
                                if(chineseChessBaseLocationVo.getX().equals(vo.getPreviousChessX())&&chineseChessBaseLocationVo.getY().equals(vo.getPreviousChessY())){  // 找到该位置
                                    chineseChessBaseLocationVo.setX(vo.getSelectedChessX());  // 移动至新位置
                                    chineseChessBaseLocationVo.setY(vo.getSelectedChessY());
                                    vo.setChineseChessBaseTip(null);  // 清空 tip
                                }
                            }
                        }
                    }
                }
                else if(type.equals(1)){  // 移至对方棋子上,吃掉
                    for (ChineseChessBaseVo chineseChessBaseVo : chessPlayer==1?vo.getRed():vo.getBlack()) {  // 判断所属并遍历棋子
                        if(chineseChessBaseVo.getPiece().equals(vo.getPreviousChess())){  // 找到上次选中的棋子
                            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chineseChessBaseVo.getLocation()) {  // 遍历该棋子所有的位置
                                if(chineseChessBaseLocationVo.getX().equals(vo.getPreviousChessX())&&chineseChessBaseLocationVo.getY().equals(vo.getPreviousChessY())){  // 找到该位置
                                    chineseChessBaseLocationVo.setX(vo.getSelectedChessX());  // 移动至新位置
                                    chineseChessBaseLocationVo.setY(vo.getSelectedChessY());
                                    vo.setChineseChessBaseTip(null);  // 清空 tip
                                }
                            }
                        }
                    }
                    for (ChineseChessBaseVo chineseChessBaseVo : chessPlayer==1?vo.getBlack():vo.getRed()) {  // 遍历对方棋子,并移除棋子
                        if(chineseChessBaseVo.getPiece().equals(vo.getSelectedChess())){  // 找到当前命中的棋子,并移除该位置上的棋子
                            chineseChessBaseVo.getLocation().removeIf(chineseChessBaseLocationVo -> chineseChessBaseLocationVo.getX().equals(vo.getSelectedChessX()) && chineseChessBaseLocationVo.getY().equals(vo.getSelectedChessY()));
                        }
                    }
                }
                else if(type.equals(2)){  // 互换位置,特殊情况,当当前用户拥有【身先士卒】的技能时,将【将/帅】与选定的【車】互换
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
                log.info("moveChess() end.");
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
     * 根据的棋子类型和位置,获取所有可移动的位置,并存入 tip
     * @param vo
     * @return
     */
    private void accessibility(ChineseChessRoomVo vo,Integer skill) {
        int tableX = 9,tableY=10;
        int offsetX=0,offsetY=0;
        Integer chessX = vo.getSelectedChessX();
        Integer chessY = vo.getSelectedChessY();
        switch (vo.getSelectedChess()){  // 1:兵/卒 2:炮/砲 3:车 4:马 5:相/象 6:士/仕 7:帅/将
            case 1:  // 兵/卒
                boolean LoggedIn=vo.isActorIsRoomOwner()?(chessY<5):(chessY>4);  // 是否已过河
                if (skill==1) {  // 技能开启
                    offsetX=vo.isActorIsRoomOwner()? -1:1;  // 非房主的视角与房主的视角是呈中心对称的
                    if (chessX>0) {  // 左
                        if (!this.isSelfChess(vo,chessX+offsetX,chessY)) {  // 目标位置不是自己的棋子才能加入
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+offsetX,chessY));
                        }
                    }
                    offsetX=vo.isActorIsRoomOwner()? 1:-1;
                    if ((tableX - 1 - chessX) > 0) {  // 右
                        if (!this.isSelfChess(vo,chessX+offsetX,chessY)) {
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+offsetX,chessY));
                        }
                    }
                    offsetY=vo.isActorIsRoomOwner()? -1:1;
                    if (chessY>0&&!this.isSelfChess(vo,chessX,chessY+offsetY)) {  // 上
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY+offsetY));
                    }
                    offsetY=vo.isActorIsRoomOwner()? 1:-1;
                    if ((tableY-1-chessY) > 0 && !this.isSelfChess(vo,chessX,chessY+offsetY)) {  // 下
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX,chessY+offsetY));
                    }
                }
                else {  // 技能未开启
                    offsetX=vo.isActorIsRoomOwner()? -1:1;
                    if (chessX>0&&LoggedIn) {  // 左
                        if (!this.isSelfChess(vo,chessX+offsetX,chessY)) {  // 过了河界
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+offsetX,chessY));
                        }
                    }
                    offsetX=vo.isActorIsRoomOwner()? 1:-1;
                    if ((tableX - 1 - chessX) > 0&&LoggedIn) {  // 右
                        if (!this.isSelfChess(vo,chessX+offsetX,chessY)) {  // 过了河界
                            vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+offsetX,chessY));
                        }
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

                if(skill==3){
                }
                else{
                    offsetX=vo.isActorIsRoomOwner()? -1:1;
                    for (int i = chessX+offsetX; tableX>i&&i>=0;i+=offsetX) {  // 左
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
                break;
            case 4:  // 马
                Integer shift=vo.isActorIsRoomOwner()?1:-1;
                if(skill==4){

                }
                else{
                    // 上左
                    if(this.isNullChess(vo.getWhole(),chessX,chessY+(-1*shift))  // 判断撇脚
                            && (this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY+(-2*shift))  // 目标位置为空
                            || !this.isSelfChess(vo,chessX+(-1*shift),chessY+(-2*shift)))){  // 目标位置不是自己的棋子,即对手棋子
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(-1*shift),chessY+(-2*shift)));
                    }
                    // 上右
                    if(this.isNullChess(vo.getWhole(),chessX,chessY+(-1*shift))
                            && (this.isNullChess(vo.getWhole(),chessX+(1*shift),chessY+(-2*shift))
                            || !this.isSelfChess(vo,chessX+(1*shift),chessY+(-2*shift)))){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(1*shift),chessY+(-2*shift)));
                    }
                    // 右上
                    if(this.isNullChess(vo.getWhole(),chessX+(1*shift),chessY)
                            && (this.isNullChess(vo.getWhole(),chessX+(-2*shift),chessY+(-1*shift))
                            || !this.isSelfChess(vo,chessX+(-2*shift),chessY+(-1*shift)))){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(2*shift),chessY+(-1*shift)));
                    }
                    // 右下
                    if(this.isNullChess(vo.getWhole(),chessX+(1*shift),chessY)
                            && (this.isNullChess(vo.getWhole(),chessX+(-2*shift),chessY+(1*shift))
                            || !this.isSelfChess(vo,chessX+(-2*shift),chessY+(1*shift)))){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(2*shift),chessY+(1*shift)));
                    }
                    // 下左
                    if(this.isNullChess(vo.getWhole(),chessX,chessY+(1*shift))
                            && (this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY+(2*shift))
                            || !this.isSelfChess(vo,chessX+(-1*shift),chessY+(2*shift)))){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(-1*shift),chessY+(2*shift)));
                    }
                    // 下右
                    if(this.isNullChess(vo.getWhole(),chessX,chessY+(1*shift))
                            && (this.isNullChess(vo.getWhole(),chessX+(1*shift),chessY+(2*shift))
                            || !this.isSelfChess(vo,chessX+(1*shift),chessY+(2*shift)))){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(1*shift),chessY+(2*shift)));
                    }
                    // 左上
                    if(this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY)
                            && (this.isNullChess(vo.getWhole(),chessX+(2*shift),chessY+(-1*shift))
                            || !this.isSelfChess(vo,chessX+(2*shift),chessY+(-1*shift)))){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(-2*shift),chessY+(-1*shift)));
                    }
                    // 左下
                    if(this.isNullChess(vo.getWhole(),chessX+(-1*shift),chessY)
                            && (this.isNullChess(vo.getWhole(),chessX+(2*shift),chessY+(1*shift))
                            || !this.isSelfChess(vo,chessX+(2*shift),chessY+(1*shift)))){
                        vo.getChineseChessBaseTip().add(new ChineseChessBaseTipVo(1,chessX+(-2*shift),chessY+(1*shift)));
                    }
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
                if(vo.isActorIsRoomOwner()){  // 红方(房主)
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
    }


    /**
     * 判断指定位置是否不存在棋子
     * @param whole
     * @param x
     * @param y
     * @return
     */
    private boolean isNullChess(ArrayList<ChineseChessBaseVo> whole,Integer x, Integer y) {
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
     * 初始化棋盘信息
     */
    private void initChineseChess(ChineseChessRoomVo vo) {
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
     * 镜像:x不变,y'=9-y
     * @param vo
     */
    private ArrayList<ChineseChessBaseTipVo> imageTip(ArrayList<ChineseChessBaseTipVo> vo){
        ArrayList<ChineseChessBaseTipVo> res = new ArrayList<>();
        for (ChineseChessBaseTipVo chessBaseVo : vo) {
            res.add(new ChineseChessBaseTipVo(chessBaseVo.getType(),chessBaseVo.getX(),9-chessBaseVo.getY()));
        }
        return res;
    }

    /**
     * 镜像tip,发送给黑方(对手)
     * 镜像:x不变,y'=9-y
     * @param vo
     */
    private ArrayList<ChineseChessBaseVo> image(ArrayList<ChineseChessBaseVo> vo){
        ArrayList<ChineseChessBaseVo> result= new ArrayList<>();
        for (ChineseChessBaseVo chessBaseVo : vo) {
            ChineseChessBaseVo baseVo = new ChineseChessBaseVo();
            baseVo.setPiece(chessBaseVo.getPiece());
            baseVo.setLocation(new ArrayList<>());
            for (ChineseChessBaseLocationVo chineseChessBaseLocationVo : chessBaseVo.getLocation()) {
                ChineseChessBaseLocationVo v = new ChineseChessBaseLocationVo(chineseChessBaseLocationVo.getX(),9-chineseChessBaseLocationVo.getY());
                baseVo.getLocation().add(v);
            }
            result.add(baseVo);
        }
        return result;
    }

    /**
     * 生成指定类型棋子对象集
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
            if(this.lock(vo.getWhole(),x,y,vo.getRed().iterator())){
                res.put("type","1");
                res.put("msg","红方将军!");
            }

            /**
             * 绝杀对手必须满足以下条件之一:
             * 1.将军对方后,对方的 帅/将 无法移动,且无法通过(阻挡/阻碍/吃)移动棋子改变将军的状态
             * 2.将军对方后,移动对方的 帅/将 但可移动的所有位置都已被"将军"
             */
            // 绝杀判断
            boolean flag=true;
            if("1".equals(res.get("type"))){
                for (HashMap<String, Integer> hashMap : map) {
                    ArrayList<ChineseChessBaseVo> temp = new ArrayList<>(vo.getWhole());
                    for (ChineseChessBaseVo baseVo : temp) {  // 变更 帅/将 位置
                        if(7==baseVo.getPiece()){
                            ChineseChessBaseLocationVo locationVo = baseVo.getLocation().get(0);
                            locationVo.setX(hashMap.get("x"));
                            locationVo.setY(hashMap.get("y"));
                        }
                    }
                    if(!this.lock(temp,hashMap.get("x"),hashMap.get("y"),vo.getRed().iterator())){
                        log.info("将军,但不是绝杀");
                        flag=false;
                        break;
                    }
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
            if(this.lock(vo.getWhole(),x,y,vo.getBlack().iterator())){
                res.put("type","2");
                res.put("msg","黑方将军!");
            }
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
     * 判断上一次选择的位置是否为自己的棋子
     * @return
     */
    private boolean selfChess(){
        return false;
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
        log.info("出现未知错误");
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
    private void setUserService(UserService userService){
        ChineseChessEndpoint.userService = userService;
    }

}