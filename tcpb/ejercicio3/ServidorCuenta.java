package tcpb.ejercicio3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServidorCuenta{
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
		ArrayList<CuentaCorriente> recibeCuentas = new ArrayList<CuentaCorriente>();

		
		recibeCuentas = (ArrayList<CuentaCorriente>) entidad.readObject();
		
		String resultado = hacerCuenta(recibeCuentas);
		
		
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


	private static String hacerCuenta(ArrayList<CuentaCorriente> cuentas) {
		 double total = 0;
		 double iva = 0;
//		for (CuentaCorriente cuenta : cuentas) {
//			subtotal += cuenta.getPrecio() * cuenta.getCantidad();
//		}
		 // Calcular el saldo total de las cuentas corrientes
	    for (CuentaCorriente cuenta : cuentas) {
	        total += cuenta.getSaldo(); // Acceso directo al atributo saldo
	    }
	    iva = total * 0.10;
	    double saldoNeto = total - iva;

		 return "Total: " + String.format("%.2f", total) + "; Retención: " + String.format("%.2f", iva) + "; Saldo Neto: " + String.format("%.2f", saldoNeto);
	}
	
}