package tcpb.ejercicio2;

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
					String respuesta = esNumeroNarcisista(cadena);

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
	public static String esNumeroNarcisista(String cadenaNumero) {
		int original = Integer.parseInt(cadenaNumero);
		int sumaCubos = 0;

		for (int i = 0; i < cadenaNumero.length(); i++) {
			int digito = original % 10;
			sumaCubos += Math.pow(digito, 3);
			original /= 10;
		}

		return sumaCubos == Integer.parseInt(cadenaNumero) ? cadenaNumero +" es narcisista" : cadenaNumero + " no es narcisista";
	}

}
					// if (sumaCubos == original) {
					// return "es nacisista";
					// } else {
					// return "no es narcisista";
					// }
