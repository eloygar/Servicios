package tcpa.ejercicio5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloEco extends Thread {
	
	DataInputStream fentrada;
	DataOutputStream fsalida;
	OutputStream salida;
	InputStream entrada;
	Socket socket = null;

	public HiloEco(Socket s) {// Constructor
		socket = s;
		// se crean flujos de entrada y salida
		try {
			salida = socket.getOutputStream();
			fsalida = new DataOutputStream(salida);
			entrada = socket.getInputStream();
			fentrada = new DataInputStream(entrada);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {// tarea a realizar con el cliente
		String cadena = "";

		System.out.println("COMUNICO CON: " + socket.toString());
		try {
			cadena = fentrada.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // obtener cadena
		try {
			fsalida.writeUTF(cadena.trim().toUpperCase());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // enviar cadena en mayuscula

		System.out.println("FIN CON: " + socket.toString());

		try {
			fentrada.close();
			entrada.close();
			fsalida.close();
			salida.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
