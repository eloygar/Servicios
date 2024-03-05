package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] enviados = new byte[1024];
		byte[] recibidos = new byte[1024];

		InetAddress IPServidor = InetAddress.getLocalHost();
		int puerto = 9876;

		System.out.println("introduce mensaje ");
		String cadena = in.readLine();
		enviados = cadena.getBytes();
		System.out.println("enviado "+enviados.length+" bytes la sevidor")
		;
		DatagramPacket envio = new DatagramPacket(enviados,enviados.length,IPServidor, puerto);
		clientSocket.send(envio);
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		System.out.println("esperando Datagrama ...");
		clientSocket.receive(recibo);
		String mayusculas =  new String(recibo.getData());
		
		InetAddress IPOrigen = recibo.getAddress();
		int puertoOrigen = recibo.getPort()
;
		System.out.println("\t Procede de :"+IPOrigen+":"+ puertoOrigen);
		System.out.println("\t Datos "+ mayusculas.trim());
		clientSocket.close();
		
		
		
	}
}
