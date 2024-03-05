package tcpa.ejercicio1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Servidor {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		String host = "localhost";
		int puerto = 8989; // puerto remoto
		System.out.println("PROGRAMA SERVIDOR COMPRA INICIADO....");            
		ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Esperando al cliente...");
        Socket cliente = servidor.accept();

		// Flujo de entrada para objetos
		ObjectInputStream entidad = new ObjectInputStream(cliente.getInputStream());
		ArrayList<String> recibeElementos = new ArrayList<String>();

		
		recibeElementos = (ArrayList<String>) entidad.readObject();
		
		String resultado = intercalar(recibeElementos);
		
		
		// FLUJO DE salida para objetos
		OutputStream salida = null;
		salida = cliente.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream(salida);
		
		// ENVIO UN SALUDO AL CLIENTE
		flujoSalida.writeUTF(resultado);

		entidad.close();
		cliente.close();
		salida.close();
	} // Fin de ServidorObjeto


    private static String intercalar(ArrayList<String> cadenas) {
        StringBuilder resultado = new StringBuilder();
        
        // Encontrar la longitud máxima de todas las cadenas
        int maxLength = 0;
        for (String cadena : cadenas) {
            String[] palabras = cadena.split(" ");
            maxLength = Math.max(maxLength, palabras.length);
        }
        
        // Iterar a través de cada palabra
        for (int i = 0; i < maxLength; i++) {
            // Iterar sobre cada cadena en la lista
            for (String cadena : cadenas) {
                String[] palabras = cadena.split(" ");
                // Si la cadena tiene una palabra en esta posición, añadirla al resultado
                if (i < palabras.length) {
                    resultado.append(palabras[i]).append(" ");
                }
            }
        }
        
        return resultado.toString().trim();
    }
	
}
