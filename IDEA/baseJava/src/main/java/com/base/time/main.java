package com.base.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public class main {
    public static void main(String[] args) {

        // 本月第一天
        Calendar calendar = Calendar.getInstance();
//        calendar.set(2022,3,1,0,0,0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Calendar 获取本月第一天的时间:" + calendar.getTime());
        System.out.println("Calendar 获取本月第一天的时间(Str):" + format.format(calendar.getTime()));

        LocalDateTime localDateTime=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        DateTimeFormatter dateTimeFormatter2=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("LocalDateTime 当前日期和时间(Str):"+dateTimeFormatter.format(localDateTime));
        System.out.println("LocalDateTime 今天的起始日期:"+localDateTime.withHour(0).withMinute(0).withSecond(0));
        System.out.println("LocalDateTime 今天的起始日期(Str):"+dateTimeFormatter.format(localDateTime.withHour(0).withMinute(0).withSecond(0)));

        LocalDateTime localDateTime1 = LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
        System.out.println("LocalDateTime 本月第一天的起始日期(精确):"+localDateTime1);

        LocalDateTime localDateTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        System.out.println("LocalDateTime 今天的起始日期(精确):"+localDateTime2+",(时间戳):"+localDateTime2.toInstant(ZoneOffset.of("+8")).toEpochMilli()+",(Str):"+dateTimeFormatter2.format(LocalDateTime.of(LocalDate.now(), LocalTime.MIN)));

        LocalDateTime localDateTime3 = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.out.println("LocalDateTime 今天的结束日期(精确):"+localDateTime3+",(时间戳):"+localDateTime3.toInstant(ZoneOffset.of("+8")).toEpochMilli());


        System.out.println("LocalDateTime转时间戳:"+localDateTime1.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());

        System.out.println("Calendar转LocalDateTime:" + LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.systemDefault()));
    }
}
