package tcpa.ejercicio3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class ServidorCompra {
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
		ArrayList<Elemento> recibeElementos = new ArrayList<Elemento>();

		
		recibeElementos = (ArrayList<Elemento>) entidad.readObject();
		
		String resultado = hacerCuenta(recibeElementos);
		
		
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


	private static String hacerCuenta(ArrayList<Elemento> elementos) {
		double subtotal = 0;
		double total = 0;
		double iva = 0;
		for (Elemento elemento : elementos) {
			subtotal += elemento.getPrecio() * elemento.getCantidad();
		}
		iva = subtotal * 0.21;
		total = subtotal+iva;
		return "SUBTOTAL = "+String.format("%.2f", subtotal) + "; IVA = "+String.format("%.2f", iva) + "; TOTAL = "+String.format("%.2f", total);
	}
	
}
