import java.util.*;

public class Denuncia extends Item{
    public Denuncia(Posicao posicao){
        super(posicao);
    }
    
    public int realizaAcao(){
        return 0;
    }

    public int realizaAcao(Tabuleiro tabuleiro, Jogador jogador){

        ArrayList<FakeNews> fakenews = tabuleiro.getFakeNews();
        Posicao posicaoJogador = jogador.getPosicao();

        for(int i = 0; i < fakenews.size(); i++){
            if(adjacente(posicaoJogador, fakenews.get(i).getPosicao())){
               
            }
        }
        return 0;
    }

    public boolean adjacente(Posicao jogador, Posicao fakenews){
       Posicao posicaoJogador = jogador.getPosicao();
       Posicao posicaoFakeNews = fakenews.getPosicao();

       
       
        return false;
    }
        
    

}
