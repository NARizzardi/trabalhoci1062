import java.util.*;

public class Main{
    public static void main(String[] args) {
        Aleatorio a = new Aleatorio();
        a.setAleatorio();

        // =========================================
        // Iniciando
        // =========================================

        /* Entrada */
        int num_jogadores = 4;

        Posicao temp = new Posicao(); 
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        for(int i = 0; i < num_jogadores; i++){
            /* Get nome do jogador*/
            jogadores.add(new Jogador("Nome", null));
        }

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.iniciaJogo(jogadores);

        // =========================================
        // Jogo
        // =========================================
        Jogador atual;
        Posicao p;
        char input;
        int flag;
        /* Turno */
        while(!tabuleiro.fimDeJogo()){
            
            for(int i = 0; i < num_jogadores; i++){
                atual = tabuleiro.getJogador(i);

                /* Movimento forçado :: Boato */
                if(atual.isBoatoFlag){
                    /* Mensagem de boato  */
                }
                /* Faz a Jogada  */
                /* Menu */
                /* Processa Input */
                /* Movimento */
                if(input != 'i'){
                    p = atual.movimentoBase(input);
                    flag = tabuleiro.resolveMovimento(atual,p);

                }    
                else{
                    /* Item */
                    flag = atual.ChecarItem();
                    /* Noticia e Fuga Precisam de uma posição*/
                    if(flag == 4 || flag = 2){
                        /* Pega posição */
                    }
                    atual.utilizarItem(tabuleiro,p);
                }           
                if(flag){
                    /* Mensagem de Morte */
                } 
            }
            tabuleiro.turnoFakeNews();
        }
    }
}