package ding.com;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateText {

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("��ǰʱ��" + df.format(new Date()));
        //1����Ϊһ����    ������ǰ  ����Ϊ��
        Date date = stepMonth(new Date(), -1);
        System.out.println("ǰһ����ʱ��Ϊ" + df.format(date));
    }

    /**
     * * �ڸ��������ڼ��ϻ��ȥָ���·ݺ������
     * *
     * * @param sourceDate ԭʼʱ��
     * * @param month      Ҫ�������·ݣ���ǰΪ���������Ϊ����
     * * @return
     */
    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }


}
