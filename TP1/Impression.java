/**
 * Impression
 */
public class Impression extends Thread {
    public String nameOfDocument;
    public int numberOfPages;

    public Impression() {
        this.nameOfDocument = "";
        this.numberOfPages = 0;
    }

    public Impression(String nameOfDocument, int numberOfPages) {
        this.nameOfDocument = nameOfDocument;
        this.numberOfPages = numberOfPages;
    }

    public void run() {
        synchronized (System.out) {
            for (int i = 0; i < this.numberOfPages; i++) {
                System.out.println(this.nameOfDocument + " (" + i + " page)");
            }
        }
    }
}