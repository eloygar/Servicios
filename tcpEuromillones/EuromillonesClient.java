package tcpEuromillones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EuromillonesClient {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket clienteSocket = new Socket(host, puerto);
             DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());
             DataOutputStream salida = new DataOutputStream(clienteSocket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                // Solicitar al usuario que ingrese los números y estrellas
                int[] numeros = new int[5];
                int[] estrellas = new int[2];

                System.out.println("Introduzca 5 números entre 1 y 52:");
                for (int i = 0; i < 5; i++) {
                    numeros[i] = scanner.nextInt();
                }

                System.out.println("Introduzca 2 estrellas entre 1 y 12:");
                for (int i = 0; i < 2; i++) {
                    estrellas[i] = scanner.nextInt();
                }

                // Enviar combinación al servidor
                for (int numero : numeros) {
                    salida.writeInt(numero);
                }

                for (int estrella : estrellas) {
                    salida.writeInt(estrella);
                }

                // Recibir respuesta del servidor
                int aciertosNumeros = entrada.readInt();
                int aciertosEstrellas = entrada.readInt();
                String premio = entrada.readUTF();

                // Mostrar resultados al cliente
                System.out.println("Número de aciertos de números: " + aciertosNumeros);
                System.out.println("Número de aciertos de estrellas: " + aciertosEstrellas);
                System.out.println("Premio: " + premio);

                // Verificar si el usuario quiere salir
                System.out.print("Introduzca '*' para salir o cualquier otra tecla para continuar: ");
                String input = scanner.next();

                if (input.equals("*")) {
                    salida.writeUTF("*");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}