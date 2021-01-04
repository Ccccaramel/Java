package ding.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class JavaStreamFileIO {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("---从控制台读取多字符输入---");
		char c;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("输入字符,按'q'退出");
		do {
			System.out.print("请输入:");
			c=(char)br.read();
			System.out.println("你的输入:"+c);  // 发现执行的次数为(字符串长度+2),或许是因为回车的字符为 "\r\n"
		}while(c!='q');
		
		System.out.println("---从控制台读取字符串---");
		BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
		String s1;
		do {
			System.out.print("请输入:");
			s1=br1.readLine();
			System.out.println("你的输入:"+s1);
		}while(!s1.equals("end"));
		
		System.out.println("---控制台输出---");
//		System.out.write("abc");
		
		System.out.println("---读写文件---");
//		try {
//			byte bw[]= {11,2,21,5,15};
//			OutputStream os=new FileOutputStream("../JavaBased/src/resourse/io.txt");
//			for(int x=0;x<bw.length;x++) {
//				os.write(bw[x]);
//			}
//			os.close();
//			InputStream is=new FileInputStream("../JavaBased/src/resourse/io.txt");
//			int size=is.available();
//			for(int i=0;i<size;i++) {
//				System.out.println((char)is.read()+" ");
//			}
//			is.close();
//		}catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Exception");
//		}  // 乱码
		File f=new File("../JavaBased/src/resourse/io.txt");
		FileOutputStream fop=new FileOutputStream(f);
		OutputStreamWriter writer=new OutputStreamWriter(fop,"UTF-8");
		writer.append("中文输入");
		writer.append("\n");
		writer.append("English");
		writer.close();
		fop.close();
		
		FileInputStream fip=new FileInputStream(f);
		InputStreamReader reader=new InputStreamReader(fip,"UTF-8");
		StringBuffer s=new StringBuffer();
		while(reader.ready()) {
			s.append((char)reader.read());
		}
		System.out.println(s.toString());
		reader.close();
		fip.close();
		
		System.out.println("---创建目录---");
		String dn="../JavaBased/src/test";
		File d=new File(dn);
		if(d.mkdir()) {
			System.out.println("创建成功");
		}else {
			System.out.println("创建失败");
		}
		
		System.out.println("---读取目录---");
		String dirname="../JavaBased";
		File f2=new File(dirname);
		if(f2.isDirectory()) {
			System.out.println("目录:"+dirname);
			String s2[]=f2.list();
			for(int i=0;i<s2.length;i++) {
				File f3=new File(dirname+"/"+s2[i]);
				if(f3.isDirectory()) {
					System.out.println(s2[i]+" 目录");
				}else {
					System.out.println(s2[i]+" 文件");
				}
			}
		}else {
			System.out.println(dirname+" 不是目录");
		}
				
		System.out.println("---删除目录---");
		File folder=new File("../JavaBased/src/test");
		deleteFolder(folder);
		System.out.println("删除目录");
	}
	public static void deleteFolder(File folder) {
		File[] files=folder.listFiles();
		if(files!=null) {
			for(File f:files) {
				if(f.isDirectory()) {
					deleteFolder(f);
				}else {
					f.delete();
				}
			}
		}	
		folder.delete();
	}
}
