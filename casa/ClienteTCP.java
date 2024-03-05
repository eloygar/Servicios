package casa;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClienteTCP {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 9999);

			// Enviar objeto Casa al servidor
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			Casa casa = new Casa(3, 2, true); // Ejemplo de una casa
			outputStream.writeObject(casa);
			outputStream.flush();

			// Recibir valoración del alquiler desde el servidor
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			int valorAlquiler = inputStream.readInt();
			System.out.println("El alquiler de la casa vale: " + valorAlquiler + "€");

			// Cerrar streams y socket
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}