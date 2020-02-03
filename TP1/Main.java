public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
        MyThread t2 = new MyThread();
        t2.start();
        MyThread t3 = new MyThread();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch(InterruptedException exc) {
        }
        System.out.println("Fin des Threads");
        
    
    }
}