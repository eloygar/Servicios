package tcpEuromillones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class EuromillonesServer {
    private static final int[] COMBINACION_NUMEROS_GANADORA = {1, 2, 3, 4, 5};
    private static final int[] COMBINACION_ESTRELLAS_GANADORA = {1, 2};

    public static void main(String[] args) {
        int puerto = 6000; // Puerto donde escucha el servidor
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket cliente = servidor.accept(); // Acepta una conexión con el cliente
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress() + ":" + cliente.getPort());

                // Procesar la conexión en un hilo separado
                Thread clienteThread = new Thread(new ClienteHandler(cliente));
                clienteThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servidor != null) {
                    servidor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método para comprobar la combinación del cliente y devolver el número de aciertos
    private static int[] comprobarCombinacion(int[] numeros, int[] estrellas) {
        int aciertosNumeros = 0;
        int aciertosEstrellas = 0;

        for (int numero : numeros) {
            for (int numeroGanador : COMBINACION_NUMEROS_GANADORA) {
                if (numero == numeroGanador) {
                    aciertosNumeros++;
                    break;
                }
            }
        }

        for (int estrella : estrellas) {
            for (int estrellaGanadora : COMBINACION_ESTRELLAS_GANADORA) {
                if (estrella == estrellaGanadora) {
                    aciertosEstrellas++;
                    break;
                }
            }
        }

        return new int[]{aciertosNumeros, aciertosEstrellas};
    }

    // Método para determinar el premio según los aciertos
    private static String determinarPremio(int aciertosNumeros, int aciertosEstrellas) {
        if (aciertosNumeros == 5 && aciertosEstrellas == 2) {
            return "¡Felicidades! Ha ganado el premio de 1ª Categoría: 5 números y 2 estrellas";
        } else if (aciertosNumeros == 5 && aciertosEstrellas == 1) {
            return "¡Felicidades! Ha ganado el premio de 2ª Categoría: 5 números y 1 estrella";
        } else if (aciertosNumeros == 5) {
            return "¡Felicidades! Ha ganado el premio de 3ª Categoría: 5 números";
        } else {
            return "Lo sentimos, no ha ganado ningún premio.";
        }
    }

    // Clase interna para manejar la conexión de cada cliente
    static class ClienteHandler implements Runnable {
        private final Socket clienteSocket;

        public ClienteHandler(Socket socket) {
            this.clienteSocket = socket;
        }

        @Override
        public void run() {
            try {
                DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());
                DataOutputStream salida = new DataOutputStream(clienteSocket.getOutputStream());

                while (true) {
                    // Recibir combinación de números y estrellas desde el cliente
                    int[] numeros = new int[5];
                    int[] estrellas = new int[2];

                    for (int i = 0; i < 5; i++) {
                        numeros[i] = entrada.readInt();
                    }

                    for (int i = 0; i < 2; i++) {
                        estrellas[i] = entrada.readInt();
                    }

                    // Comprobar la combinación y calcular premios
                    int[] aciertos = comprobarCombinacion(numeros, estrellas);
                    String premio = determinarPremio(aciertos[0], aciertos[1]);

                    // Enviar respuesta al cliente
                    salida.writeInt(aciertos[0]);
                    salida.writeInt(aciertos[1]);
                    salida.writeUTF(premio);

                    // Verificar si el cliente quiere salir
                    if (entrada.readUTF().equals("*")) {
                        break;
                    }
                }

                // Cerrar streams y socket
                entrada.close();
                salida.close();
                clienteSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}