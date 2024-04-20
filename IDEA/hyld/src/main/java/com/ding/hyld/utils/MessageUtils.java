package com.ding.hyld.utils;

import com.alibaba.fastjson2.JSON;
import com.ding.hyld.info.ChatInfo;
import com.ding.hyld.vo.ChineseChessRoomVo;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageUtils {

    /**
     * 封装消息
     */
    public static String getAllRoomInfo(boolean isSystemMessage, ChatInfo chatInfo) {
        HashMap<String,Object> result=new HashMap<>();
        result.put("isSystemMessage",isSystemMessage);
        result.put("chatInfo",chatInfo);
        return JSON.toJSONString(result);
    }

    public static String getAllRoomInfo(boolean status, Integer instruct, String msg, CopyOnWriteArrayList<ChineseChessRoomVo> onlineRoomList) {
        HashMap<String,Object> result=new HashMap<>();
        result.put("status",status);
        result.put("msg",msg);
        result.put("onlineRoomList",onlineRoomList);
        result.put("instruct",instruct);
        return JSON.toJSONString(result);
    }

    public static String getMessage(boolean status, Integer instruct, String msg,String data) {
        HashMap<String,Object> result=new HashMap<>();
        result.put("status",status);
        result.put("instruct",instruct);
        result.put("msg",msg);
        result.put("data",data);
        return JSON.toJSONString(result);
    }
}
