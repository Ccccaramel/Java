package com.ding.hyld.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeUtils {
    public static Map<String,LocalDateTime> getTime(List<LocalDateTime> localDateTimeList){
        HashMap<String,LocalDateTime> result = new HashMap<>();
        for(LocalDateTime localDateTime:localDateTimeList){
            result.put(getTime(localDateTime),localDateTime);
        }
        return result;
    }

    public static String getTime(LocalDateTime localDateTime){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        return dateTimeFormatter.format(localDateTime);
    }
}
