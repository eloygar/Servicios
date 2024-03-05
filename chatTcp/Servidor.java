package chatTcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		int numPort = 6000; // Puerto
		try (ServerSocket servidor = new ServerSocket(numPort);
				Socket cliente = servidor.accept();
				PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()))) {

			System.out.println("Esperando conexiones en el puerto " + numPort + "...");

			String cad;
			while ((cad = entrada.readLine()) != null && !cad.equals("exit")) {
				salida.println(cad);
				System.out.println("Recibiendo: " + cad);
			}

		} catch (IOException e) {
			System.out.println("Error en el servidor: " + e.getMessage());
		}
	}

}
