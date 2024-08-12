package com.ding.office.utils;

import com.alibaba.fastjson2.JSON;
import com.ding.office.info.ChatInfo;
import com.ding.office.vo.ChineseChessRoomVo;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageUtils {

    /**
     * 聊天室模块,封装消息
     * @param isSystemMessage 是否为系统消息
     * @param chatInfo 单条对话数据
     * @return
     */
    public static String getAllRoomInfo(boolean isSystemMessage, ChatInfo chatInfo) {
        HashMap<String,Object> result=new HashMap<>();
        result.put("isSystemMessage",isSystemMessage);
        result.put("chatInfo",chatInfo);
        return JSON.toJSONString(result);
    }

    /**
     * 中国象棋模块
     * @param status T:成功标志 F:失败标志
     * @param instruct 功能标志,依据该值进行对应操作
     * @param msg 消息,推送给用户的弹窗文本
     * @param onlineRoomList 房间列表信息
     * @return
     */
    public static String getAllRoomInfo(boolean status, Integer instruct, String msg, CopyOnWriteArrayList<ChineseChessRoomVo> onlineRoomList) {
        HashMap<String,Object> result=new HashMap<>();
        result.put("status",status);
        result.put("msg",msg);
        result.put("onlineRoomList",onlineRoomList);
        result.put("instruct",instruct);
        return JSON.toJSONString(result);
    }

    /**
     * 中国象棋模块
     * @param status T:成功标志 F:失败标志
     * @param instruct 功能标志,依据该值进行对应操作
     * @param msg 消息,推送给用户的弹窗文本
     * @param data 数据,与 instruct 相关的数据
     * @return
     */
    public static String getMessage(boolean status, Integer instruct, String msg,String data) {
        HashMap<String,Object> result=new HashMap<>();
        result.put("status",status);
        result.put("instruct",instruct);
        result.put("msg",msg);
        result.put("data",data);
        return JSON.toJSONString(result);
    }
}
