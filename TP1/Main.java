import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int nbThreads = 4;

        ArrayList<MyThread> tab = new ArrayList<MyThread>();

        for (int i = 0; i < nbThreads; i++) {
            MyThread thread = new MyThread();
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