package ding.com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class JavaTCPSocketClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * Client
		 */
		String serverName="localhost";
		int port=6164;
		try {
			System.out.println("连接到主机:"+serverName+",端口号:"+port);
			Socket client=new Socket(serverName,port);
			System.out.println("远程主机地址:"+client.getRemoteSocketAddress());
			OutputStream outToServer=client.getOutputStream();
			DataOutputStream out=new DataOutputStream(outToServer);
			out.writeUTF("Hello from "+client.getLocalSocketAddress());
			InputStream inFromServer=client.getInputStream();
			DataInputStream in=new DataInputStream(inFromServer);
			System.out.println("服务器响应:"+in.readUTF());
			client.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
