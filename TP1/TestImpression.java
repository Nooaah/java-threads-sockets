public class TestImpression {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Impression("1", 6));
        Thread t2 = new Thread(new Impression("2", 7));
        t1.start();
        t2.start();
    }
}