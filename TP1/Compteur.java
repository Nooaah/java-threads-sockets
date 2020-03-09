/**
 * Compteur
 */
public class Compteur extends Thread {
    public int compteur;
    final int MAX = 10;

    public Compteur() {
        this.compteur = 0;
    }

    public void incremente() {
        int compteurInt = this.compteur;

        try {
            sleep(100);
        } catch (Exception e) {
            System.err.println(e);
        }
        if (compteurInt < MAX)
            this.compteur++;

    }

    boolean isAlive = true;

    public void run() {
        while (isAlive) {
            incremente();
            System.out.println(getName() + " valeur = " + (this.compteur));
            if (this.compteur >= MAX)
                isAlive = false;
        }
        System.out.println("*** Fin de " + getName() + " ***");
    }
}