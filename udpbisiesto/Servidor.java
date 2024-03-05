package udpbisiesto;

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
	                    String respuesta = comprobarBisiesto(cadena);

	                    // Preparar respuesta
	                    enviados = respuesta.getBytes();

	                    // ENVIO DATAGRAMA AL CLIENTE
	                    DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
	                    serverSocket.send(paqEnviado);

	                    // Para terminar
	                    if (cadena.trim().equals("*")) break;
	                }
	                System.out.println("Socket cerrado...");
	            }
	        } catch (Exception e) {
	            System.err.println("Error en el servidor: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    public static String comprobarBisiesto(String cad) {
	        System.out.println("Año recibido: " + cad.trim());
	        int dato = Integer.parseInt(cad.trim());
	        String respuesta;
	        // Condición corregida para comprobar si el año es bisiesto
	        if ((dato % 4 == 0 && dato % 100 != 0) || dato % 400 == 0) 
	            respuesta = "Es bisiesto";
	        else
	            respuesta = "No es bisiesto";
	        return respuesta;
	    }
}
