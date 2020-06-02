package ding.com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class JavaTCPSocketServer extends Thread {
	private ServerSocket serverSocket;
	public JavaTCPSocketServer(int port) throws IOException{
		serverSocket=new ServerSocket(port);
		serverSocket.setSoTimeout(100000);		
	}
	public void run() {
		while(true) {
			try {
				System.out.println("等待远程连接,端口号:"+serverSocket.getLocalPort()+"...");
				Socket server=serverSocket.accept();
				System.out.println("远程主机地址:"+server.getRemoteSocketAddress());
				DataInputStream in=new DataInputStream(server.getInputStream());
				System.out.println(in.readUTF());
				DataOutputStream out=new DataOutputStream(server.getOutputStream());
				out.writeUTF("谢谢连接我:"+server.getLocalSocketAddress()+"\nGoodbye!");
				server.close();
			}catch (SocketTimeoutException e) {
				// TODO: handle exception
				System.out.println("Socket timed out!");
				break;
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * Server
		 */
		int port=6164;
		try {
			Thread t=new JavaTCPSocketServer(port);
			t.run();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
