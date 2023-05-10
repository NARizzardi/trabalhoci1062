public class Fuga extends Item{
    public Fuga(Posicao posicao){
        super(posicao);
    }

    public int realizaAcao(){
        
        return 0;
    }

    public int realizaAcao(Tabuleiro tabuleiro,Posicao posicao,Jogador jogador){
        
        if((posicao.getPosX() > 0 && posicao.getPosX() < 10) && (posicao.getPosY() > 0 && posicao.getPosY() < 10)){
            jogador.posicao = posicao;
            return 1;
        }

        return 0;
    }
}
