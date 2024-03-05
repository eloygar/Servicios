package tcpb.ejercicio3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ClienteCuenta { 
	public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int port = 8989;
        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, port);

		// Se prepara un flujo de salida para objetos
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

		ArrayList<CuentaCorriente> cuentas = new ArrayList<CuentaCorriente>();
		
		CuentaCorriente c = new CuentaCorriente(2000);
		c.cargo(300);
		c.ingreso(20000);
		c.movimientos();
		cuentas.add(c);
		CuentaCorriente c1 = new CuentaCorriente(400);
		c1.ingreso(100);
		c1.transferencia(c,200);
		c1.movimientos();
		cuentas.add(c1);
		CuentaCorriente c2 = new CuentaCorriente(700000);
		c2.ingreso(5600);
		c2.transferencia(c1,10);
		c2.movimientos();
		cuentas.add(c2);
		CuentaCorriente c3 = new CuentaCorriente(20);
		c3.ingreso(7000);
		c3.transferencia(c,200);
		c3.movimientos();
		cuentas.add(c3);
		CuentaCorriente c4 = new CuentaCorriente(21341521);
		c4.ingreso(500);
		c4.transferencia(c3,100);
		c4.movimientos();
		cuentas.add(c4);
		CuentaCorriente c5 = new CuentaCorriente(4134);
		c5.ingreso(200);
		c5.transferencia(c4,100);
		c5.movimientos();
		cuentas.add(c5);
		
		for (CuentaCorriente cuenta : cuentas) {
			System.out.println("VAMOS ENVIAR: "+cuenta.toString());
		}
		
		outObjeto.writeObject(cuentas);


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
