import com.springboot.enums.Color;
import com.springboot.exception.CommonException;
import com.springboot.start;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest(classes = start.class)
public class CommonTest {
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testCommonException(){
        try {
            subtraction(0,1);
        } catch (CommonException e) {
            System.out.println("异常类型: "+e.getType());
            System.out.println("异常信息: "+e.getMessage());
            e.printStackTrace();
        }
    }

    private void subtraction(int a,int b) throws CommonException {
        if(a<b){
            throw new CommonException("被减数不可以小于减数!","计算异常");
        }
        else{
            System.out.println("a-b="+(a-b));
        }
    }

    @Test
    public void testColor(){
        System.out.println("*** 输出 ***");
        Color color=Color.GREEN;
        System.out.println(color);

        System.out.println("*** 转换成数组并遍历 ***");
        Color[] colors = Color.values();
        for(Color c:colors){
            System.out.println("color:"+c);
        }

        System.out.println("*** 与 switch 搭配使用 ***");
        switch (color){
            case RED:
                System.out.println("red");
                break;
            case BLUE:
                System.out.println("blue");
                break;
            case GREEN:
                System.out.println("green");
                break;
            default:
                System.out.println("null");
        }

        System.out.println("*** 转换成数组 ***");
        System.out.println("是否存在 RED :"+Color.valueOf("RED"));  // 存在输出该枚举常量,不存在抛出异常

        System.out.println("*** 索引值 ***");
        System.out.println("BLUE 的索引值:"+Color.BLUE.ordinal());
    }

    @Test
    public void testDate(){

        long start = System.currentTimeMillis();

        Date date = new Date(1701157039000L);
        date.setTime(1701157040000L);
        System.out.println("date: "+date);

        System.out.println("LocalDate.now(): "+LocalDate.now());
        System.out.println("LocalTime.now(): "+LocalTime.now());
        System.out.println("LocalDateTime.now(): "+LocalDateTime.now());

        LocalDateTime localDateTime1=LocalDateTime.of(2023,1,1,16,6,6);
        LocalDateTime localDateTime2=LocalDateTime.of(2023,1,1,16,12,31);

        System.out.println("localDateTime: "+localDateTime1);

        /**
         * 关于比较大小的返回值
         * 当日期不同时,返回值为 年/月/日 之差
         * 仅时间不同时,前者比后者 大/小/相等 时返回 1/-1/0
         */
        System.out.println("*** 比较大小 ***");
        System.out.println(localDateTime1.compareTo(localDateTime2));

        /**
         * 两个字母表示当数值小于10时十位用"0"填充
         * H表示24小时制,h表示12小时制
         */
        System.out.println("*** 格式化 ***");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy年MM月d日 H时mm分ss秒");
        System.out.println("格式化(yyyy年MM月d日 H时mm分ss秒): "+dateTimeFormatter1.format(localDateTime1));
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy年M月dd日 hh时mm分ss秒");
        System.out.println("格式化(yyyy年M月dd日 hh时mm分ss秒): "+dateTimeFormatter2.format(localDateTime2));

        long end = System.currentTimeMillis();
        System.out.println("耗时: "+(end - start)+"ms");

        /**
         * 字符串不可以包含字母
         */
        System.out.println("*** 字符串转时间 ***");
        String dateTime = "2023年01月02日(123)12:21:11";
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyy年MM月dd日(123)HH:mm:ss");
        System.out.println("字符串转时间: "+LocalDateTime.parse(dateTime, dateTimeFormatter3));
    }

    @Test
    public void testJasypt(){
        /**
         * 也可不通过配置注入
         */
//        BasicTextEncryptor stringEncryptor = new BasicTextEncryptor();
//        stringEncryptor.setPassword("123456");

        String userName = stringEncryptor.encrypt("123");
        System.out.println("加密："+userName);

        // 解密 -基础类型
        String decUserName = stringEncryptor.decrypt(userName);
        System.out.println("解密："+decUserName);
    }
}
