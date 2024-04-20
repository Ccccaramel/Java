package com.ding.hyld.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class TimeUtils {

    public static final String FORMAT_1="yyyy年MM月dd日 HH:mm:ss";
    public static final String FORMAT_2="yyyy/MM/dd";
    public static final String FORMAT_3="yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_4="yyyy年MM月dd日";

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

    public static String getNow(){
        return toString(LocalDateTime.now(),TimeUtils.FORMAT_1);
    }

    public static LocalDateTime getToday() {
        LocalDateTime localDateTime=LocalDateTime.now();
        return localDateTime.withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime getFirstDayOfTheMonth() {
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
    }

    public static String getFirstDayOfTheMonthStr() {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(getFirstDayOfTheMonth());
    }

    /**
     * 以当前时间前移或后移
     * @param year
     * @param month
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Calendar getBeforeTheSpecifiedTime(Integer year,Integer month,Integer date,Integer hour,Integer minute,Integer second) {
        // //默认是当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_MONTH, date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return calendar;
    }

    public static String getBeforeTheSpecifiedTimeStr(Integer year,Integer month,Integer date,Integer hour,Integer minute,Integer second,String form) {
        SimpleDateFormat format = new SimpleDateFormat(form);
        return format.format(getBeforeTheSpecifiedTime(year,month,date,hour,minute,second).getTime());
    }
}
