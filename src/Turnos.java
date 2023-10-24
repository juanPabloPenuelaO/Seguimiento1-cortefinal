import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Turnos {
    private static Queue<String> preferencialQueue = new LinkedList<>();
    private static Queue<String> normalQueue = new LinkedList<>();
    private static int turnoPreferencial = 1;
    private static int turnoNormal = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("¿Eres miembro del banco? (s/n): ");
            String respuesta = scanner.nextLine().toLowerCase();

            String turno;
            if (respuesta.equals("s")) {
                turno = "Preferencial: " + turnoPreferencial++;
                preferencialQueue.add(turno);
            } else {
                turno = "Normal: " + turnoNormal++;
                normalQueue.add(turno);
            }

            Thread clienteThread;
            if (respuesta.equals("s")) {
                clienteThread = new Thread(new ClientePreferencial(turno));
            } else {
                clienteThread = new Thread(new ClienteNormal(turno));
            }
            clienteThread.start();

            System.out.println("Turno generado: " + turno);
        }
    }

    static class ClientePreferencial implements Runnable {
        private String turno;

        public ClientePreferencial(String turno) {
            this.turno = turno;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000); // Espera 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Cliente preferencial atendido: " + turno);
            preferencialQueue.remove(turno);
        }
    }

    static class ClienteNormal implements Runnable {
        private String turno;

        public ClienteNormal(String turno) {
            this.turno = turno;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10000); // Espera 10 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Cliente normal atendido: " + turno);
            normalQueue.remove(turno);
        }
    }
}
