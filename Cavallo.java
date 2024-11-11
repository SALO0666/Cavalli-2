import java.util.Random;

public class Cavallo extends Thread {
    private String nome;
    private int distanzaDaPercorrere;
    private int distanzaPercorsa = 0;
    private int velocita;
    private boolean infortunato = false;
    private long tempoInizio, tempoFine;

    public Cavallo(String nome, int distanzaDaPercorrere, int velocita) {
        this.nome = nome;
        this.distanzaDaPercorrere = distanzaDaPercorrere;
        this.velocita = velocita;
    }

    @Override
    public void run() {
        Random random = new Random();
        tempoInizio = System.currentTimeMillis();
        
        while (distanzaPercorsa < distanzaDaPercorrere && !infortunato) {
    
            if (random.nextDouble() < 0.05) { 
                infortunato = true;
                System.out.println(nome + " si è infortunato ed è uscito dalla gara.");
                break;
            }

            distanzaPercorsa += velocita;
            
           
            if (distanzaPercorsa > distanzaDaPercorrere) {
                distanzaPercorsa = distanzaDaPercorrere;
            }

            System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri.");

         
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(nome + " è stato interrotto.");
            }
        }

        if (!infortunato) {
            tempoFine = System.currentTimeMillis();
            System.out.println(nome + " ha completato la gara!");
        }
    }

    public boolean haTerminato() {
        return !infortunato && distanzaPercorsa >= distanzaDaPercorrere;
    }

    public String getNome() {
        return nome;
    }

    public long getTempoGara() {
        return tempoFine - tempoInizio;
    }
}
