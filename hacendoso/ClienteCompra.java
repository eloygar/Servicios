package hacendoso;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class ClienteCompra {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int port = 8989;
        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, port);

		// Se prepara un flujo de salida para objetos
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

		ArrayList<Elemento> elementos = new ArrayList<Elemento>();

		Elemento e = new Elemento("Jolimbos", 22.05, 3);
		elementos.add(e);
		e = new Elemento("Nieve", 64.95, 2);
		elementos.add(e);
		e = new Elemento("Champiñones", 33.99, 6);
		elementos.add(e);
		e = new Elemento("Cañopos", 12.12, 2);
		elementos.add(e);
		e = new Elemento("Fariña", 44.44, 1);
		elementos.add(e);
		e = new Elemento("Java", 0.99, 3);
		elementos.add(e);
		
		for (Elemento elemento : elementos) {
			System.out.println("VAMOS ENVIAR: "+elemento.toString());
		}
		
		outObjeto.writeObject(elementos);


		// CREO FLUJO DE ENTRADA AL SERVIDOR
		DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
		// EL SERVIDOR ME ENVIA UN MENSAJE
		String resultado = flujoEntrada.readUTF();
		System.out.println("Recibiendo del SERVIDOR: "+ resultado);
		
		
		// CERRAR STREAMS Y SOCKETS
		outObjeto.close();
		flujoEntrada.close();
		cliente.close();
	} // Fin de main


}
