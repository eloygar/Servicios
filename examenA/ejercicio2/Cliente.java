package examenA.ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); DatagramSocket clientSocket = new DatagramSocket()) {
			byte[] enviados;
			byte[] recibidos = new byte[1026];

			// DATOS DEL SERVIDOR al que enviar mensaje
			InetAddress IPServidor = InetAddress.getLocalHost();
			int puerto = 9876;
			System.out.println("Cliente de comprobación de año bisiesto iniciado.");

			int numero1, numero2;
			System.out.print("Introduce numero 1 : \n");
			// Leer la entrada del usuario
			numero1 = sc.nextInt();
			System.out.print("Introduce numero 2 : \n");
			numero2 = sc.nextInt();
			// Salir del bucle si el usuario introduce "*"
			String cadena = numero1 + "-" + numero2;
			enviados = cadena.getBytes();

			// ENVIANDO DATAGRAMA AL SERVIDOR
			DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
			clientSocket.send(envio);
			System.out.println("Solicitud enviada al servidor.");

			// RECIBIENDO DATAGRAMA DEL SERVIDOR
			DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
			clientSocket.receive(recibo);
			String respuesta = new String(recibo.getData(), 0, recibo.getLength());

			// OBTENIENDO INFORMACIÓN DEL DATAGRAMA
			InetAddress IPOrigen = recibo.getAddress();
			int puertoOrigen = recibo.getPort();
			System.out.println("\tRespuesta del servidor " + respuesta.trim());

			System.out.println("Cliente cerrado.");
		} catch (Exception e) {
			System.err.println("Error en cliente: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
