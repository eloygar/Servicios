package tcpDiaSemana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClienteFecha {

	public static void main(String[] args) throws IOException {
		String host = "localhost";
		int port = 6000; // puerto remoto
		System.out.println("PROGRAMA CLIENTE INICIADO....");
		Socket cliente = new Socket(host, port);

		// CREO FLUJO DE SALIDA AL SERVIDOR
		PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);

		// CREO FLUJO DE ENTRADA DEL SERVIDOR
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

		// FLUJO PARA ENTRADA ESTANDAR
		Scanner sc = new Scanner(System.in);
		String fechaStr, respuesta = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			System.out.print("Introduce fecha (yyyy-MM-dd): ");
			fechaStr = sc.nextLine();
			Date fecha = dateFormat.parse(fechaStr);

			// Envía la fecha al servidor
			fsalida.println(dateFormat.format(fecha));

			// Recibe la respuesta del servidor
			respuesta = fentrada.readLine();
			System.out.println(" =>Día de la semana: " + respuesta);

		} catch (ParseException e) {
			System.out.println("Error al parsear la fecha: " + e.getMessage());
		}

		// CERRAR STREAMS Y SOCKETS
		fentrada.close();
		fsalida.close();
		cliente.close();
	}
}