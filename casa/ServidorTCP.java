package casa;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			System.out.println("Servidor esperando conexiones...");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Cliente conectado desde: " + socket.getInetAddress());

				// Recibir objeto Casa desde el cliente
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				Casa casa = (Casa) inputStream.readObject();

				// Calcular valoración del alquiler
				int valorAlquiler = calcularValorAlquiler(casa);

				// Enviar valoración al cliente
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				outputStream.writeInt(valorAlquiler);
				outputStream.flush();

				// Cerrar streams y socket
				inputStream.close();
				outputStream.close();
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int calcularValorAlquiler(Casa casa) {
		int valor = 75 * casa.getNumHabitaciones() + 50 * casa.getNumBanos() + (casa.tieneTrastero() ? 100 : 0);
		return valor;
	}
}