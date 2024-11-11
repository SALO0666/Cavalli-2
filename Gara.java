import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class Gara {
    private List<Cavallo> cavalli;
    private int lunghezzaPercorso;
    private List<Cavallo> classifica;

    public Gara(int lunghezzaPercorso) {
        this.lunghezzaPercorso = lunghezzaPercorso;
        this.cavalli = new ArrayList<>();
        this.classifica = new ArrayList<>();
    }

    public void aggiungiCavallo(String nome, int velocita) {
        Cavallo cavallo = new Cavallo(nome, lunghezzaPercorso, velocita);
        cavalli.add(cavallo);
    }

    public void iniziaGara() {
        System.out.println("La gara sta per iniziare!");

        
        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

       
        for (Cavallo cavallo : cavalli) {
            try {
                cavallo.join();
                if (cavallo.haTerminato()) {
                    classifica.add(cavallo);
                }
            } catch (InterruptedException e) {
                System.out.println("Errore: un cavallo è stato interrotto.");
            }
        }

      
        classifica.sort((c1, c2) -> Long.compare(c1.getTempoGara(), c2.getTempoGara()));

        System.out.println("La gara è finita!");
        mostraClassifica();
    }

    private void mostraClassifica() {
        System.out.println("Classifica dei primi 3 cavalli:");
        for (int i = 0; i < Math.min(3, classifica.size()); i++) {
            Cavallo cavallo = classifica.get(i);
            System.out.println((i + 1) + " - " + cavallo.getNome() + " con tempo: " + cavallo.getTempoGara() + " ms");
        }
    }

    public void salvaRisultatiSuFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Classifica della gara:\n");
            for (int i = 0; i < Math.min(3, classifica.size()); i++) {
                Cavallo cavallo = classifica.get(i);
                writer.write((i + 1) + " - " + cavallo.getNome() + " con tempo: " + cavallo.getTempoGara() + " ms\n");
            }
            writer.write("\n");
            System.out.println("Risultati salvati su " + fileName);
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio del file: " + e.getMessage());
        }
    }
}
