/**
 * CalculArea
 */
public class CalculAlea extends Thread {

    private double[] t;
    int debut, fin;

    public CalculAlea(double[] t, int debut, int fin) {
        this.t = t;
        this.debut = debut;
        this.fin = fin;
    }

    public void run(){
        for (int i=debut; i<fin; i++){
            t[i] = Math.sqrt(Math.pow(Math.random(), 2.0) + Math.pow(Math.random(), 2.0));
        }
    }
}