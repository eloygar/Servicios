package examenA.ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
	public static void main(String[] args) {
		try {
			// Puerto por el que escucha el servidor: 9876
			try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
				byte[] recibidos;
				byte[] enviados;

				System.out.println("Esperando datagrama.....");
				while (true) {
					// RECIBO DATAGRAMA
					recibidos = new byte[1026];
					DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
					serverSocket.receive(paqRecibido);
					String cadena = new String(paqRecibido.getData(), 0, paqRecibido.getLength());

					// DIRECCION ORIGEN
					InetAddress IPOrigen = paqRecibido.getAddress();
					int puerto = paqRecibido.getPort();

					// LLAMAR A METODO DE SERVIDOR
					String respuesta = verificarAmigos(cadena);

					// Preparar respuesta
					enviados = respuesta.getBytes();

					// ENVIO DATAGRAMA AL CLIENTE
					DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
					serverSocket.send(paqEnviado);

					// Para terminar
					if (cadena.trim().equals("*"))
						break;
				}
				System.out.println("Socket cerrado...");
			}
		} catch (Exception e) {
			System.err.println("Error en el servidor: " + e.getMessage());
			e.printStackTrace();
		}
	}

//  return respuesta;
//	    }
	public static String verificarAmigos(String cadena) {
	
		String[] cadenaSplit = cadena.split("-");

		int num1 = Integer.parseInt(cadenaSplit[0]);
		int num2 = Integer.parseInt(cadenaSplit[1]);
		
		
		if (cifras(num1) == num2 && cifras(num2) == num1) {
			return "Son amigos";
		} else {
			return "No son amigos";
		}
	
	}
//	Dos números naturales x e y se llaman amigos si la suma de los
//	divisores propios de x es igual a y y viceversa. Por ejemplo 220 y
//	284.
//	Observa que div(220) = {1,2,4,5,10,11,20,22,44,55,110} y se tiene 1
//	+2+4+5+10+11+20+22+44+55+110=284.
//	Además div(284) = {1,2,4,71,142} y se tiene 1+2+4+71+142=220.


	public static int cifras (int numero) {
		
		int suma = 0;
		//for (int i = 1; i <=numero; i++) {
		// 284/2 142
		// 220/2 110
		
		for (int i = 1; i <=( numero/2); i++) {
			// 1 2 4 5 10 11 20 22 44 55 110
			if (numero % i == 0) {
				//220%1 =0 
				//220%2 =0
				//220%3 !=0
				//220%4 =0
				//220%5 =0
				suma += i;
			}
		}
		return suma;
	}

}
