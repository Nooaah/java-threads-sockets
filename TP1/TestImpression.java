import java.util.ArrayList;

public class TestImpression {
    public static void main(String[] args) {

        ArrayList<Impression> tab = new ArrayList<Impression>();

        int nbThreads = 20;

        for (int i = 0; i < nbThreads; i++) {
            synchronized (System.out) {
                Impression impression = new Impression("Document " + (i + 1), (int) (1 + (Math.random() * (20))));
                impression.start();
                tab.add(impression);
            }
        }
    }
}