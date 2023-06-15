import java.util.*;

public class Noticia extends Item{
    public Noticia(Posicao posicao){
        super(posicao);
    }


    public int realizaAcao(Tabuleiro tabuleiro,Posicao posicao,Jogador jogador){
      
        ArrayList<FakeNews> fakenews = tabuleiro.getFakeNews();
        int posX = posicao.getPosX();
        int posY = posicao.getPosY();

        for(int i = 0; i < fakenews.size(); i++){
            if(fakenews.get(i).getPosicao().getPosX() == posX && fakenews.get(i).getPosicao().getPosY() == posY){
                tabuleiro.apagaEntidade(fakenews.get(i).getPosicao());  
                return 1;
            }
        }
        return 0;

    }
}
