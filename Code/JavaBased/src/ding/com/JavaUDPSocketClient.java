package ding.com;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class JavaUDPSocketClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket client=new DatagramSocket(2117);
			DatagramPacket packet=new DatagramPacket(new byte[1024],1024);
			packet.setPort(2116);
			packet.setAddress(InetAddress.getLocalHost());
			packet.setData("Hello Server".getBytes());
			client.send(packet);
			client.receive(packet);
			System.out.println(packet.getAddress().getHostName()+
					"("+packet.getPort()+")"+
					new String(packet.getData()));
			client.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
