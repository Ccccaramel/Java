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
			System.out.println("���ӵ�����:"+serverName+",�˿ں�:"+port);
			Socket client=new Socket(serverName,port);
			System.out.println("Զ��������ַ:"+client.getRemoteSocketAddress());
			OutputStream outToServer=client.getOutputStream();
			DataOutputStream out=new DataOutputStream(outToServer);
			out.writeUTF("Hello from "+client.getLocalSocketAddress());
			InputStream inFromServer=client.getInputStream();
			DataInputStream in=new DataInputStream(inFromServer);
			System.out.println("��������Ӧ:"+in.readUTF());
			client.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
