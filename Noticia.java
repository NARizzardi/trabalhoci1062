import java.util.*;

public class Noticia extends Item{
    public Noticia(Posicao posicao){
        super(posicao);
    }


    public int realizaAcao(Tabuleiro tabuleiro,Posicao posicao,Jogador jogador){
      
        Random rand = Aleatorio.getAleatorio();

        int fakeId = rand.nextInt(tabuleiro.getFakeNewsQtd());
        tabuleiro.apagaEntidade(tabuleiro.getOneFakeNews(fakeId).posicao);

        //if(fakenews.get(i).getPosicao().getPosX() == posX && fakenews.get(i).getPosicao().getPosY() == posY){
        //    tabuleiro.apagaEntidade(fakenews.get(i).getPosicao());  
        //    return 1;
        //}
        
        return 0;

    }
}
