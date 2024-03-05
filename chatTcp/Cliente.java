package chatTcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException {
		String host = "localhost";
		int port = 6000; // puerto remoto
		System.out.println("PROGRAMA CLIENTE INICIADO....");
		try (Socket cliente = new Socket(host, port);
				PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);
				BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				Scanner sc = new Scanner(System.in)) {

			System.out.println("Bienvenido al chat. Introduce tu mensaje (escribe 'exit' para salir):");

			String cadena, eco;
			do {
				System.out.print("Introduce cadena: ");
				cadena = sc.nextLine(); // lectura por teclado
				fsalida.println(cadena);
				eco = fentrada.readLine();
				System.out.println(" =>ECO:" + eco);

			} while (!cadena.equals("exit"));

			// ENVÍO UN SALUDO AL SERVIDOR
			fsalida.println("Saludos al SERVIDOR DESDE EL CLIENTE");

			// EL SERVIDOR ME ENVÍA UN MENSAJE
			eco = fentrada.readLine();
			System.out.println("Recibiendo del SERVIDOR: \n\t" + eco);

		} catch (IOException e) {
			System.out.println("Error en el cliente: " + e.getMessage());
		}
	}
}
