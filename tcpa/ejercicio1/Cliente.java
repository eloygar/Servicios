package tcpa.ejercicio1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Cliente {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int port = 8989;
        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, port);

        String frase1 = "hola me llamo eloy garcia", frase2 ="que tal el dia pepe?";
        
        ArrayList<String> elementos = new ArrayList<String>();
        
        elementos.add(frase1);
        elementos.add(frase2);
        
		// Se prepara un flujo de salida para objetos
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		
		
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
