import java.util.*;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        //==========================================
        // Iniciando Variaveis
        //==========================================
        Aleatorio ale = new Aleatorio();
        ale.setAleatorio();
        Scanner s = new Scanner(System.in);

        /* Input numero de Jogadores */
        int num_jogadores = 4;

        /* Jogadores */
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        for(int i = 0; i < num_jogadores; i++){
            /* Get nome do jogador*/
            jogadores.add(new Jogador("Nome", null));
        }

        /* Tabuleiro */
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.iniciaJogo(jogadores);

        /* Variaveis de logica de jogo */
        Jogador atual;
        Posicao p;
        char input;
        int flag;

        //==========================================
        // Jogo
        //==========================================
 
        for(int turno = 0; (turno < 20) && !tabuleiro.fimDeJogo(); turno++){
            /* Vez dos jogadores */
            for(int i = 0; i < num_jogadores; i++){
                atual = tabuleiro.getJogador(i);

                /* Movimento forçado :: Boato */
                if(atual.isBoatoFlag()){
                    /* Mensagem de boato  */
                    p = atual.movimentoAleatorio();
                    atual.setBoatoFlag(false);
                    tabuleiro.resolveMovimento(atual,p);                
                }
                else{
                    /* Processa Input */
                    input = s.next().charAt(0);
                    /* Movimento */
                    if(input != 'i'){
                        p = atual.movimentoBase(input);
                        tabuleiro.resolveMovimento(atual,p);

                    }    
                    else{
                        /* Item */
                        flag = atual.ChecarItem();
                        /* Noticia e Fuga Precisam de uma posição*/
                        if(flag == 4 || flag == 2){
                            /* Pega posição */
                        }
                        /* Tem que devolver se o jogador morreu */
                        atual.utilizarItem(tabuleiro,p);
                    }
                }
            }
            /* Vez das fakeNews */
            tabuleiro.turnoFakeNews();
        }

        //==========================================
        // Fim de Jogo
        //==========================================
        
        /* Tipos de Fim de Jogo */
        if(tabuleiro.getFakeNewsQtd() == 0){
            /* Mensagem de Vitoria */
        }
        else{
            /* Mensagem Derrota    */
        }
    }
}