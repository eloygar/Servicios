package udpuno;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUno {
	public static void main(String[] args) throws IOException {
		byte[] bufer= new byte[1024];
		DatagramSocket socket = new DatagramSocket(12345);
		System.out.println("esperando al datagrma");
		DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
		socket.receive(recibo);
		int bytesRec=recibo.getLength();
		
	}

}
