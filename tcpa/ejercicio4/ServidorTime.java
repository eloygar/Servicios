package tcpa.ejercicio4;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServidorTime {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int puerto = 12345; // Puerto multicast
		InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Grupo multicast

		// Se crea el socket multicast y se une al grupo
		MulticastSocket ms = new MulticastSocket(puerto);
		ms.joinGroup(grupo);

		String cadena = "";
		while (!cadena.trim().equals("quit")) {
			// System.out.print("Datos a enviar al grupo: ");

			cadena = mostrarTiempo();
			Thread.sleep(10000);
			// ENVIANDO AL GRUPO
			DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupo, puerto);
			ms.send(paquete);
		}

		// Cerrar el socket fuera del bucle
		// Opcional, para salir del grupo multicast
		ms.close();
		System.out.println("Socket cerrado...");
	}

	public static String mostrarTiempo() {
		// Obtener la fecha y hora actual
		Date date = new Date();

		// Formatear la hora actual en formato hh:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String horaActual = sdf.format(date);

		// Obtener la hora actual en segundos
		long segundosActuales = date.getTime() / 1000;

		// Calcular los segundos restantes hasta las 12 PM (mediodía)

		int segundos = 0;
		segundos += (int) (date.getHours()) * 3600;
		System.out.println(segundos);
		segundos += (int) (date.getMinutes() * 60);
		System.out.println(segundos);
		segundos += (int) (date.getSeconds());
		System.out.println(segundos);
		long segundosHastaMediodia = calcularSegundosHastaMediodia(segundos);
		// long segundosHastaMediodia = segundosHastaMediodia();
		// Devolver la hora actual y los segundos restantes hasta mediodía
		return "Hora actual: " + horaActual + "\nSegundos restantes hasta las 12 PM: " + segundosHastaMediodia
				+ " segundos";
	}

	private static long calcularSegundosHastaMediodia(int segundos) {
	    long mitadDia = 43200; 
	    long diaTotal = 86400; 

	    if (segundos == mitadDia) {
	        return 0;
	    } else if (segundos > mitadDia) {
	        return (diaTotal - segundos) + mitadDia;
	    } else {
	        return mitadDia - segundos;
	    }
	}

}
