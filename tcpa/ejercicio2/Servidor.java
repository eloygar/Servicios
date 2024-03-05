package tcpa.ejercicio2;

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

//	    public static String comprobarAmigo(String cad) {
//	        System.out.println("Año recibido: " + cad.trim());
//	        int dato = Integer.parseInt(cad.trim());
//	        String respuesta;
//	        // Condición corregida para comprobar si el año es bisiesto
//	        if ((dato % 4 == 0 && dato % 100 != 0) || dato % 400 == 0) 
//	            respuesta = "Es bisiesto";
//	        else
//	            respuesta = "No es bisiesto";
//	        return respuesta;
//	    }
	public static String verificarAmigos(String cadena) {
		String[] cadenaSplit = cadena.split("-");

		int num1 = Integer.parseInt(cadenaSplit[0]);
		int num2 = Integer.parseInt(cadenaSplit[1]);
		if (sumaDivisores(num1) == num2 && sumaDivisores(num2) == num1) {
			return  num1 +" y "+ num2+" Son amigos";
		} else {
			return "No son amigos";
		}
	
	}

	public static int sumaDivisores(int numero) {
		int suma = 0;
		for (int i = 1; i <= numero / 2; i++) {
			if (numero % i == 0) {
				suma += i;
			}
		}
		return suma;
	}

}
