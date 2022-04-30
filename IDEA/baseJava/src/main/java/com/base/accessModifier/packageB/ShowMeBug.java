package com.base.accessModifier.packageB;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShowMeBug {
    /**
     * 要求对这组数据进行数据转换和计算处理，按照日期(精确到天)和病人分类数据, 用控制台输出即可
     * 输出示例:
     * 2020-01-23 2020-01-24 2020-01-25 2020-01-26
     * 张三 36.5       36.3       ...        ... 36.33（最后一列平均温度）
     * ...   ...       ...         ...         ...
     * 要求:
     * 1. 日期从小到大输出,
     * 2. 如果病人当日没有测试温度, 统一认为是 36.5 摄氏度(在控制台打出36.5)
     * 3. 计算平均温度
     * patientList只是参考数据，请不要写死
     *
     *  允许使用IDE!!!
     */
    public static void main(String[] args) throws ParseException {
        List<PatientTemperatureVo> patientList = getPatientList();
        Map<String,TreeMap<String,Double>> patientMap=new HashMap<>();
        TreeSet<String> timeSet=new TreeSet<>();
        TreeSet<String> patientNameSet=new TreeSet<>();
        // TODO 请编写代码实现题目要求
        SimpleDateFormat date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat date2=new SimpleDateFormat("yyyy-MM-dd");
        for(PatientTemperatureVo patient:patientList){
            String key=patient.getDateStr();
            String name=patient.getName();
            key=date2.format(date1.parse(key));

            timeSet.add(key);
            patientNameSet.add(name);
            if(patientMap.containsKey(name)){
                patientMap.get(name).put(key, patient.getTemperature());
            }else{
                TreeMap<String,Double> map= new TreeMap<>();
                map.put(key, patient.getTemperature());
                patientMap.put(name,map);
            }

        }
        for(String key:patientMap.keySet()){
            Map<String,Double> temperatureMap=patientMap.get(key);
            for(String k:temperatureMap.keySet()){
            }
        }

        System.out.print("name\\time ");
        for(String time:timeSet){
            System.out.print(" "+time);
        }
        System.out.println("   平均温度");

        int days=timeSet.size();
        for(String name:patientNameSet){
            System.out.print(name);
            TreeMap<String, Double> timeMap = patientMap.get(name);
            double temperatureCount=0;
            for(String time:timeSet){
                Double temperature= (double) 36.5;
                if(timeMap.containsKey(time)){
                    temperature = (Double) timeMap.get(time);
                }
                temperatureCount+= temperature ;
                System.out.print("        "+temperature);
            }
            double result=temperatureCount/days;
            System.out.printf("   %.2f\n",result);
        }

        int i=1;
        int j=1;
        String s1="1";
        String s2="1";
        String s3=new String("1");
        String s4=new String("1");
        if(i==j){
            System.out.println("i==j");
        }
        if(s1==s2){
            System.out.println("s1==s2");
        }
        if(s3==s4){
            System.out.println("s3==s4");
        }
    }

    static class PatientTemperatureVo {
        public String name;
        public String dateStr;
        public double temperature;

        public PatientTemperatureVo(String name, String dateStr, double temperature) {
            this.name = name;
            this.dateStr = dateStr;
            this.temperature = temperature;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDateStr() {
            return dateStr;
        }

        public void setDateStr(String dateStr) {
            this.dateStr = dateStr;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
    }

    static List<PatientTemperatureVo> getPatientList() {
        List<PatientTemperatureVo> list = new ArrayList<>();
        list.add(new PatientTemperatureVo("赵六", "2020-01-25 12:13:26", 37.2));
        list.add(new PatientTemperatureVo("王五", "2020-01-25 12:13:26", 36.5));
        list.add(new PatientTemperatureVo("张三", "2020-01-24 12:25:26", 36.2));
        list.add(new PatientTemperatureVo("李四", "2020-01-25 12:24:26", 36.5));
        list.add(new PatientTemperatureVo("李四", "2020-01-24 21:13:25", 36.9));
        list.add(new PatientTemperatureVo("张三", "2020-01-25 11:13:26", 36.7));
        list.add(new PatientTemperatureVo("张三", "2020-01-27 12:13:26", 36.3));
        list.add(new PatientTemperatureVo("张三", "2020-01-28 12:35:23", 36.4));
        list.add(new PatientTemperatureVo("李四", "2020-01-27 13:15:26", 37));
        list.add(new PatientTemperatureVo("李四", "2020-01-23 15:13:26", 36.6));
        list.add(new PatientTemperatureVo("赵六", "2020-01-24 12:13:26", 36.9));
        list.add(new PatientTemperatureVo("王五", "2020-01-27 12:25:54", 36.4));
        list.add(new PatientTemperatureVo("李四", "2020-01-26 12:13:26", 36.2));
        list.add(new PatientTemperatureVo("李四", "2020-01-28 19:13:26", 36.1));
        list.add(new PatientTemperatureVo("黄七", "2020-01-23 12:13:26", 36.9));
        list.add(new PatientTemperatureVo("黄七", "2020-01-26 06:13:26", 37.5));
        list.add(new PatientTemperatureVo("王五", "2020-01-23 12:13:26", 36.2));
        list.add(new PatientTemperatureVo("王五", "2020-01-24 18:13:21", 36.9));
        list.add(new PatientTemperatureVo("刘八", "2020-01-26 12:13:26", 36.8));
        list.add(new PatientTemperatureVo("黄七", "2020-01-25 12:13:26", 37.2));
        list.add(new PatientTemperatureVo("黄七", "2020-01-28 12:13:26", 35.9));
        list.add(new PatientTemperatureVo("赵六", "2020-01-26 12:14:26", 37.5));
        list.add(new PatientTemperatureVo("刘八", "2020-01-25 17:13:23", 36.5));
        list.add(new PatientTemperatureVo("王五", "2020-01-26 12:13:26", 36.8));
        list.add(new PatientTemperatureVo("王五", "2020-01-28 12:13:26", 36.1));
        list.add(new PatientTemperatureVo("赵六", "2020-01-27 08:12:46", 37.9));
        list.add(new PatientTemperatureVo("黄七", "2020-01-24 12:13:26", 37.1));
        list.add(new PatientTemperatureVo("黄七", "2020-01-27 12:43:26", 37.1));
        list.add(new PatientTemperatureVo("刘八", "2020-01-23 11:13:26", 36.1));
        list.add(new PatientTemperatureVo("刘八", "2020-01-28 12:13:26", 36.3));
        list.add(new PatientTemperatureVo("张三", "2020-01-23 12:13:26", 36.5));
        return list;
    }
}
