import java.util.*;

public class a{
    public static void main(String[] args) {
        Aleatorio a = new Aleatorio();
        a.setAleatorio();
        Tabuleiro t = new Tabuleiro();
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        Jogador j1 = new Jogador("Nico");
        jogadores.add(j1);
        t.iniciaJogo(jogadores);
        t.imprimeTabuleiro();
    }
}