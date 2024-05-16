package com.ding.office.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
    public static Map<String,Integer> exToLvTools(Integer lv, Integer val, Integer ex) { // 经验转换成等级,和当前等级溢出经验
        Integer tVal = val;
        val = val + lv * 10;
        Map<String,Integer> dataMap =new HashMap<>();
        if (val > ex) {
            dataMap.put("grade",lv);
            int exEx = ex-tVal;
            dataMap.put("exEx",exEx);
            int currentLvMaxEx = lv * 10;
            dataMap.put("currentLvMaxEx",currentLvMaxEx);
            dataMap.put("proportion",exEx*100/currentLvMaxEx < 50 ? 10 : exEx*100/currentLvMaxEx);
            return dataMap;
        } else {
            dataMap = exToLvTools(lv+1,val,ex);
        }
        return dataMap;
    }
}
