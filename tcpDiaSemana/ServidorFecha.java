package tcpDiaSemana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServidorFecha {

	public static void main(String[] args) {
		int numPort = 6000; // Puerto
		ServerSocket servidor = null;
		Socket cliente = null;
		PrintWriter salida = null;
		BufferedReader entrada = null;

		try {
			servidor = new ServerSocket(numPort);
			System.out.println("Esperando al cliente...");

			cliente = servidor.accept();

			salida = new PrintWriter(cliente.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			String fechaStr;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			while ((fechaStr = entrada.readLine()) != null) {
				try {
					Date fecha = dateFormat.parse(fechaStr);
					String diaSemana = obtenerDiaSemana(fecha);
					salida.println(diaSemana);
					System.out.println("Recibiendo fecha: " + fechaStr + ", enviando día de la semana: " + diaSemana);
				} catch (Exception e) {
					System.out.println("Error al procesar la fecha: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("Error en el servidor: " + e.getMessage());
		} finally {
			try {
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
				if (cliente != null)
					cliente.close();
				if (servidor != null)
					servidor.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar los recursos: " + e.getMessage());
			}
		}
	}

	private static String obtenerDiaSemana(Date fecha) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
		return dateFormat.format(fecha);
	}
}