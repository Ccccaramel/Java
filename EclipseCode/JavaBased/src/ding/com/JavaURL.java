package ding.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JavaURL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL url=new URL("http://www.runoob.com/index.html?language=cn#j2se");
			System.out.println("URL:"+url.toString()+
					"\n协议:"+url.getProtocol()+
					"\n验证信息:"+url.getAuthority()+
					"\n文件名及请求参数:"+url.getFile()+
					"\n主机名:"+url.getHost()+
					"\n路径:"+url.getPath()+
					"\n端口:"+url.getPort()+
					"\n默认端口:"+url.getDefaultPort()+
					"\n请求参数:"+url.getQuery()+
					"\n定位位置:"+url.getRef());
			
			URL u2=new URL("http://www.bilibili.com");
			URLConnection uc=u2.openConnection();
			HttpURLConnection c=null;
			if(uc instanceof HttpURLConnection) {
				c=(HttpURLConnection)uc;
			}else {
				System.out.println("请输入 URL 地址:");
				return;
			}
			BufferedReader in=new BufferedReader(new InputStreamReader(c.getInputStream()));
			String cs="";
			String cur;
			while((cur=in.readLine())!=null) {
				cs+=cur;
			}
			System.out.println(cs);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
