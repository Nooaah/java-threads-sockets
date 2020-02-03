import java.lang.Thread;

public class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + i + " en execution");
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}