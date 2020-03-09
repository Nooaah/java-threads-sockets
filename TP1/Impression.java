public class Impression implements Runnable {
    String name;
    int number;

    public Impression() {
        this.name = "";
        this.number = 0;
    }

    public Impression(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void run() {
        synchronized (System.out) {
            for (int i = 0; i < this.number; i++) {
                System.out.println("Document " + this.name + " - page " + i);
            }
        }
    }

}