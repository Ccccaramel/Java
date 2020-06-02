package ding.com;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class JavaUDPSocketServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket server=new DatagramSocket(2116);
			DatagramPacket packet=new DatagramPacket(new byte[1024],1024);
			server.receive(packet);
			System.out.println(packet.getAddress().getHostName()+
					"("+packet.getPort()+"):"+
					new String(packet.getData()));
			packet.setData("Hello Client".getBytes());
			packet.setPort(2117);
			packet.setAddress(InetAddress.getLocalHost());
			server.send(packet);
			server.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
