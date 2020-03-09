import java.util.ArrayList;
import java.util.Scanner;

public class MainExercice4 {
    public static void main(String[] args) {

        int nbThreads = 1;

        ArrayList<Thread> tab = new ArrayList<Thread>();

        for (int i = 0; i < nbThreads; i++) {
            Thread thread = new Thread(new Exercice4());
            thread.start();
            tab.add(thread);
            Scanner scanner = new Scanner(System.in);
            System.out.print("");
            String username = scanner.next();
            thread.interrupt();
        }

        try {
            for (int i = 0; i < tab.size(); i++) {
                tab.get(i).join();
            }
        } catch (InterruptedException exc) {

        }
        System.out.println("Fin des Threads");

    }
}