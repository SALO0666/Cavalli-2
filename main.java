import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        int lunghezzaPercorso = richiediLunghezzaPercorso(scanner);

        
        Gara gara = new Gara(lunghezzaPercorso);

        
        int numeroCavalli = richiediNumeroCavalli(scanner);
        aggiungiCavalliAGara(scanner, gara, numeroCavalli);

        
        gara.iniziaGara();

        
        System.out.print("Inserisci il nome del file per salvare i risultati: ");
        String fileName = scanner.nextLine();

        
        gara.salvaRisultatiSuFile(fileName);

        scanner.close();
    }

    private static int richiediLunghezzaPercorso(Scanner scanner) {
        System.out.print("Inserisci la lunghezza del percorso in metri: ");
        return scanner.nextInt();
    }

    private static int richiediNumeroCavalli(Scanner scanner) {
        System.out.print("Inserisci il numero di cavalli: ");
        int numeroCavalli = scanner.nextInt();
        scanner.nextLine(); 
        return numeroCavalli;
    }

    private static void aggiungiCavalliAGara(Scanner scanner, Gara gara, int numeroCavalli) {
        for (int i = 0; i < numeroCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nomeCavallo = scanner.nextLine();
            System.out.print("Inserisci la velocità del cavallo " + nomeCavallo + " in metri al secondo: ");
            int velocita = scanner.nextInt();
            scanner.nextLine(); 
            gara.aggiungiCavallo(nomeCavallo, velocita);
        }
    }
}
