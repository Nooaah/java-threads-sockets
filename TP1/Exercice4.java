/**
 * Exercice4
 */
public class Exercice4 extends Thread {
    public int compteur;

    public Exercice4() {
        this.compteur = 0;
    }

    public void incremente() {
        try {
            sleep(1000);
        } catch (Exception e) {
        }
        this.compteur++;
    }

    public void run() {
        while (!isInterrupted()) {
            incremente();
            System.out.println(getName() + " valeur = " + (this.compteur));
        }
        System.out.println("*** Fin de " + getName() + " ***");
        
    }
}