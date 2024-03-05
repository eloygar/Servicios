package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class Client {

	public static void main(String[] args) throws IOException {
		InetAddress destino = InetAddress.getLocalHost();
		int port = 12345;
		byte[] mensaje = new byte[1024];
		String Saludo = "Enviando Saludo !!";
		mensaje = Saludo.getBytes();
		
		DatagramPacket envio = new DatagramPacket(mensaje,mensaje.length,destino, port);
		DatagramSocket socket = new DatagramSocket(3456);
		System.out.println("Enviadno datagrama longitud "+mensaje.length);
		System.out.println("Host de destino"+destino.getHostName());
		System.out.println("IP desrtino "+destino.getHostAddress());
		System.out.println("Puerto local de sockets "+socket.getLocalPort() );
		System.out.println("puerto aal que envio "+socket.getPort());
		socket.send(envio);
		socket.close();
	}
}
