import java.util.*;

public class Denuncia extends Item{
    public Denuncia(Posicao posicao){
        super(posicao);
    }
    
    public int realizaAcao(){
        return 0;
    }

    public boolean adjacente(Posicao jogador, Posicao fakenews){
    
        int jogadorX = jogador.getPosX();
        int jogadorY = jogador.getPosY();

        int fakenewsX = fakenews.getPosX();
        int fakenewsY = fakenews.getPosY();

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){

                if(jogadorX + i == fakenewsX && jogadorY + j == fakenewsY) return true;
                
            }
        }
       
        return false;
    }

    public int realizaAcao(Tabuleiro tabuleiro, Jogador jogador){

        ArrayList<FakeNews> fakenews = tabuleiro.getFakeNews();
        Posicao posicaoJogador = jogador.getPosicao();

        for (FakeNews fakeNews : fakenews) {
            if(adjacente(posicaoJogador, fakeNews.getPosicao()))
               tabuleiro.apagaEntidade(fakeNews.getPosicao());
        }

        return 0;
    }
        
    

}
