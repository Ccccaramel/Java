package ding.com;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateText {

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间" + df.format(new Date()));
        //1代表为一个月    正的往前  负的为后
        Date date = stepMonth(new Date(), -1);
        System.out.println("前一个月时间为" + df.format(date));
    }

    /**
     * * 在给定的日期加上或减去指定月份后的日期
     * *
     * * @param sourceDate 原始时间
     * * @param month      要调整的月份，向前为负数，向后为正数
     * * @return
     */
    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }


}
