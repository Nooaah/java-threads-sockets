import java.util.ArrayList;

public class MainCompteur {
    public static void main(String[] args) {

        int nbThreads = 4;

        ArrayList<Thread> tab = new ArrayList<Thread>();

        for (int i = 0; i < nbThreads; i++) {
            Thread thread = new Thread(new Compteur());
            thread.start();
            tab.add(thread);
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