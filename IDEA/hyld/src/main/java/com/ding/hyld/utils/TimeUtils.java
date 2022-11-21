package com.ding.hyld.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TimeUtils {

    public static final String FORMAT_1="yyyy年MM月dd日 HH:mm:ss";
    public static final String FORMAT_2="yyyy/MM/dd";

    public static Map<String,LocalDateTime> toString(List<LocalDateTime> localDateTimeList){
        HashMap<String,LocalDateTime> result = new HashMap<>();
        for(LocalDateTime localDateTime:localDateTimeList){
            result.put(toString(localDateTime,TimeUtils.FORMAT_1),localDateTime);
        }
        return result;
    }

    public static String toString(LocalDateTime localDateTime,String format){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(format);
        return dateTimeFormatter.format(localDateTime);
    }

    public static LocalDateTime toLocalDateTime(String settlementTime) {
        return LocalDateTime.parse(settlementTime);
    }

    public static LocalDateTime getToday() {
        LocalDateTime localDateTime=LocalDateTime.now();
        return localDateTime.withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime getFirstDayOfTheMonth() {
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
    }
}
