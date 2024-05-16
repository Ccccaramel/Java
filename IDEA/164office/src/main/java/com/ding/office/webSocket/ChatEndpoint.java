package com.ding.office.webSocket;

import com.alibaba.fastjson2.JSON;
import com.ding.office.constant.DictionaryCode;
import com.ding.office.info.ChatInfo;
import com.ding.office.info.UserInfo;
import com.ding.office.service.ChatService;
import com.ding.office.service.LoginService;
import com.ding.office.utils.MessageUtils;
import com.ding.office.utils.TimeUtils;
import com.ding.office.vo.ChatVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//,configurator = GetHttpSessionConfig.class  // 如果需要在执行API前进行一些特殊处理,例如解析请求头等,可将此行移至 value 后面
@ServerEndpoint(value = "/linkChat/{token}")
@Component
public class ChatEndpoint {

    private static final Map<Integer,Session> onlineUsers = new ConcurrentHashMap<>();  // <userId,ws>;线程安全
    private static final Map<Integer,UserInfo> onlineUsersInfo = new ConcurrentHashMap<>();  // <userId,userInfo>

    private static LoginService loginService;
    private static ChatService chatService;

    private UserInfo user;
    private ChatInfo chatInfo;

    /**
     * 建立 WebSocket 连接后调用
     *   1.将 Session 保存
     *   2.广播消息
     * @param session webSocket 会话
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value="token") String token){
        log.info("onOpen:{}",token);
        user= getUserInfo(token);  // 根据 token 获取用户信息
        onlineUsers.put(user.getId(),session);
        // 广播消息
        chatInfo = new ChatInfo();
        chatInfo.setContent(user.getName()+"上线了");
        chatInfo.setCreateTimeStr(TimeUtils.getNow());
        broadcastAllUsers(true);
    }

    private void broadcastAllUsers(boolean isSystemMessage) {  // 发送给所有在线用户
        Set<Map.Entry<Integer, Session>> entrySet = onlineUsers.entrySet();
        for (Map.Entry<Integer, Session> stringSessionEntry : entrySet) {
            Session session = stringSessionEntry.getValue();
            try {
                if(Objects.equals(stringSessionEntry.getKey(), user.getId())){
                    chatInfo.setSelf(true);
                }else{
                    chatInfo.setSelf(false);
                }
                String message = MessageUtils.getAllRoomInfo(isSystemMessage, chatInfo);
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 浏览器发送到服务端时调用
     * @param message 客户端发送过来的数据
     */
    @OnMessage
    public void onMessage(String message){
        log.info("onMessage");

        // 将信息保存至数据库
        ChatVo chatVo = JSON.parseObject(message, ChatVo.class);
        chatVo.setSender(user.getId());
        if(chatVo.getCurrentWindows()==0||chatVo.getRecipient()==null){  // 在大厅发消息,则将接收者设为自己
            chatVo.setRecipient(user.getId());
            chatVo.setType(DictionaryCode.CHAT_TYPE_2);
            chatVo.setHall(true);
        }
        else{  // 私人信息
            chatVo.setType(DictionaryCode.CHAT_TYPE_1);
        }
        chatVo.setStatus(DictionaryCode.CHAT_STATUS_2);

        chatService.add(chatVo);
        chatInfo = chatService.searchChat(null, chatVo).get(0);  // 重新获取该条信息,里面包含发送时间
        chatInfo.setCurrentWindows(chatVo.getCurrentWindows());

        if(chatVo.getCurrentWindows()==0||chatVo.getRecipient()==null||!onlineUsers.containsKey(chatVo.getRecipient())){
            broadcastAllUsers(false);
        }
        else{
            try {
                chatInfo.setSelf(true);
                onlineUsers.get(user.getId()).getBasicRemote().sendText(MessageUtils.getAllRoomInfo(false,chatInfo));
                chatInfo.setSelf(false);
                onlineUsers.get(chatVo.getRecipient()).getBasicRemote().sendText(MessageUtils.getAllRoomInfo(false,chatInfo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        broadcastAllUsers(true);
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
        ChatEndpoint.loginService = loginService;
    }

    @Autowired
    private void setChatService(ChatService chatService){
        ChatEndpoint.chatService = chatService;
    }
}