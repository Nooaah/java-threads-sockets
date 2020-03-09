import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.lang.Math;

public class Serveur {

    public static void main(String[] test) {

        final ServerSocket serveurSocket;
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);
        

        try {
            serveurSocket = new ServerSocket(60000);
            clientSocket = serveurSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread envoi = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    while (true) {
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });
            envoi.start();

            Thread recevoir = new Thread(new Runnable() {
                String msg;
                String nom;

                @Override
                public void run() {
                    try {
                        int numberClient = 0;
                        numberClient++;
                        double numberCode;
                        numberCode = Math.round(Math.random() * ( 100 - 0 ));
                        System.out.println("Un nouveau client est connecté avec le code : " + numberCode);
                        
                        out.println("Bonjour, vous êtes le client numéro " + numberClient + ". Votre nom :");
                        out.flush();
                        msg = in.readLine();
                        // tant que le client est connecté
                        while (msg != null) {
                            if (msg.equals("oui")) {
                                System.out.println("Bien joué ! la bonne réponse était oui ! : " + msg);
                            } else {
                                System.out.println("Client : " + msg);
                                nom = msg;
                            }
                            out.println("Bienvenue " + nom + ", votre code est le " + numberCode);
                            out.flush();
                            msg = in.readLine();
                        }
                        // sortir de la boucle si le client a déconecté
                        System.out.println("Client déconecté");
                        // fermer le flux et la session socket
                        out.close();
                        clientSocket.close();
                        serveurSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }   @
            });
            
            recevoir.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}