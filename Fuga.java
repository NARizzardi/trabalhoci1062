public class Fuga extends Item{
    public Fuga(Posicao posicao){
        super(posicao);
    }


    public int realizaAcao(Tabuleiro tabuleiro,Posicao posicao,Jogador jogador){
        jogador.setPosicao(posicao);
        return 1;
    }
        

}
