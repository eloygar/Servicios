package tcpSaludo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloEco extends Thread {
    private BufferedReader fentrada;
    private PrintWriter fsalida;
    private Socket socket = null;

    // Constructor
    public HiloEco(Socket s) {
        this.socket = s;
        try {
            // Se crean flujos de entrada y salida
            fsalida = new PrintWriter(socket.getOutputStream(), true);
            fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tarea a realizar con el cliente
    @Override
    public void run() {
        String cadena = "";
        try {
            while (!cadena.trim().equals("*")) {
                System.out.println("COMUNICO CON: " + socket.toString());
                cadena = fentrada.readLine(); // Obtener cadena
                fsalida.println(cadena.trim().toUpperCase()); // Enviar cadena en mayÃºscula
            }
            System.out.println("FIN CON: " + socket.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cerrando recursos
                if (fsalida != null) fsalida.close();
                if (fentrada != null) fentrada.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}