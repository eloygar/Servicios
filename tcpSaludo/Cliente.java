package tcpSaludo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        final String host = "localhost";
        final int puerto = 12345;

        try (Socket socket = new Socket(host, puerto);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Cliente conectado al servidor en " + host + ":" + puerto);
            String mensaje;

            do {
                System.out.print("Introduce un mensaje (o * para terminar): ");
                mensaje = teclado.readLine();
                salida.println(mensaje);

                if (!mensaje.equals("*")) {
                    String respuesta = entrada.readLine();
                    System.out.println("Respuesta del servidor: " + respuesta);
                }
            } while (!mensaje.equals("*"));

        } catch (Exception e) {
            System.err.println("Excepcion en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}