package com.base.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Slf4j
public class main {

    @Test
    public void fun1() {

        System.out.println("date:"+LocalDateTime.now());

        /**
         * Calendar 自定义时间与格式化
         */
        // //默认是当前日期
        Calendar calendar = Calendar.getInstance();
//        calendar.set(2022,3,1,0,0,0);
        calendar.add(Calendar.MONTH, 1); // 以当前时间进行计算
        calendar.add(Calendar.DAY_OF_MONTH, 1); // 以当前时间进行计算
//        calendar.add(Calendar.MONTH, 1); // 一个月后
//        calendar.add(Calendar.MONTH, -1); // 一个月前
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 设置为指定值
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Calendar 获取本月第一天的时间:" + calendar.getTime());
        System.out.println("Calendar 获取本月第一天的时间(Str):" + format.format(calendar.getTime()));

        /**
         * LocalDateTime 自定义时间与格式化
         */
        LocalDateTime localDateTime=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        DateTimeFormatter dateTimeFormatter2=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("LocalDateTime 当前日期和时间(Str):"+dateTimeFormatter.format(localDateTime));
        System.out.println("LocalDateTime 今天的起始日期:"+localDateTime.withHour(0).withMinute(0).withSecond(0));
        System.out.println("LocalDateTime 今天的起始日期(Str):"+dateTimeFormatter.format(localDateTime.withHour(0).withMinute(0).withSecond(0)));

        System.out.println("yyyyMMddHHmmss:"+DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()));

        LocalDateTime localDateTime1 = LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
        System.out.println("LocalDateTime 本月第一天的起始日期(精确):"+localDateTime1);

        LocalDateTime localDateTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        System.out.println("LocalDateTime 今天的起始日期(精确):"+localDateTime2+",(时间戳):"+localDateTime2.toInstant(ZoneOffset.of("+8")).toEpochMilli()+",(Str):"+dateTimeFormatter2.format(LocalDateTime.of(LocalDate.now(), LocalTime.MIN)));

        LocalDateTime localDateTime3 = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.out.println("LocalDateTime 今天的结束日期(精确):"+localDateTime3+",(时间戳):"+localDateTime3.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        /**
         * LocalDateTime 转时间戳
         */
        System.out.println("LocalDateTime转时间戳:"+localDateTime1.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());

        /**
         * Calendar 转 LocalDateTime
         */
        System.out.println("Calendar转LocalDateTime:" + LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.systemDefault()));

        /**
         * String 转 LocalDateTime
         */
        System.out.println("String转LocalDateTime:" + LocalDateTime.parse("2020-01-01T12:12"));
        System.out.println("String转LocalDateTime:" + LocalDateTime.parse("2022-11-10T19:40:51.657207"));
    }

    @Test
    public void fun2(){
        System.out.println("year:"+LocalDateTime.now().getYear());  // 当前年份
        System.out.println("month(En):"+LocalDateTime.now().getMonth());  // 当前月份(英文缩写)
        System.out.println("month:"+LocalDateTime.now().getMonthValue());  // 当前月份
        System.out.println("day:"+LocalDateTime.now().getDayOfMonth());  // 当前日
    }

    @Test
    public void fun3(){
        System.out.println("时间戳:"+LocalDateTime.now());
        System.out.println("时间戳:"+ System.currentTimeMillis());
    }

    /**
     * 时间比较大小
     * 比时间戳数值大小
     */
    @Test
    public void fun4(){
        LocalDateTime localDateTime1=LocalDateTime.of(2001,1,1,1,1,1);
        LocalDateTime localDateTime2=LocalDateTime.of(2000,1,1,1,1,1);
        LocalDateTime localDateTime3=LocalDateTime.of(2000,1,1,1,1,1);
        log.info("localDateTime1.compareTo(localDateTime2)={}",localDateTime2.compareTo(localDateTime1));
    }

    /**
     * 时间运算
     */
    @Test
    public void fun5(){
        LocalDateTime localDateTime1=LocalDateTime.of(2001,1,1,1,1,1);
        LocalDateTime localDateTime2=LocalDateTime.of(2000,1,1,1,1,1);
        log.info("Duration.between(localDateTime1,localDateTime2).getSeconds()   {}", Duration.between(localDateTime1,localDateTime2).getSeconds());
        log.info("Math.abs(Duration.between(localDateTime1,localDateTime2).getSeconds())   {}", Math.abs(Duration.between(localDateTime1,localDateTime2).getSeconds()));
    }
}
