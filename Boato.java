public class Boato extends Item{
    public Boato(Posicao posicao){
        super(posicao);
    }

    public int realizaAcao(Tabuleiro tabuleiro,Posicao posicao,Jogador jogador){
        jogador.setBoatoFlag(true);
        return 1;
    }
}
