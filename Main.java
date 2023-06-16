import java.util.*;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        //==========================================
        //Variaveis
        //==========================================
        Aleatorio ale = new Aleatorio();
        ale.setAleatorio();
        Scanner s = new Scanner(System.in);

        Scanner scanner = new Scanner(System.in);

        /* Jogadores */
        int num_jogadores;
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        String nome_jogador;

        /* Tabuleiro */
        Tabuleiro tabuleiro = new Tabuleiro();

        /* Variaveis de logica de jogo */
        Jogador atual;
        Posicao p = new Posicao(0,0);
        String input;
        int flag = 0;
        int turno;

        //==========================================
        //Tela de Inicio
        //==========================================

        System.out.println("==============================");
        System.out.println("       Corra das Fake News!   ");
        System.out.println("==============================");
        System.out.printf("Digite o numero de jogadores: ");


        num_jogadores = scanner.nextInt();
        while(num_jogadores > 4 || num_jogadores < 1){
            System.out.println("É necessario 1 a 4 jogadores!\n");
            num_jogadores = scanner.nextInt();
        }
        /* Limpa o buffer */
        scanner.nextLine();

        for(int i = 0; i < num_jogadores; i++){
            /* Get nome do jogador*/
            System.out.printf("Nome do jogador %d:\n",i);;
            nome_jogador = scanner.nextLine();
            while(nome_jogador.isBlank()){
                System.out.println("Nome Invalido!");;
                nome_jogador = scanner.nextLine();
            }
            jogadores.add(new Jogador(nome_jogador, null));
        }

        System.out.println("==============================");
        System.out.println("Iniciando...   ");

        try {
            tabuleiro.iniciaJogo(jogadores); 
        } catch (Exception e) {
            return;
        }
        //==========================================
        // Jogo
        //==========================================
        for(turno = 0; (turno < 20) /*&& !tabuleiro.fimDeJogo()*/; turno++){
            /* Vez dos jogadores */
            for(int i = 0; i < num_jogadores; i++){
                /* Gambiarra para limpar tela */
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");

                tabuleiro.imprimeTabuleiro();
                atual = tabuleiro.getJogadores().get(i);
                /* Movimento forçado :: Boato */
                if(atual.isBoatoFlag()){
                    /* Mensagem de boato  */
                    System.out.println("==============================");
                    System.out.println("           BOATO!             ");
                    System.out.println("==============================");
                    System.out.printf( "Jogador %s esta confuso!      \n",atual.getNome());
                    System.out.println("==============================");
                    p = atual.movimentoAleatorio();
                    atual.setBoatoFlag(false);
                    //tabuleiro.resolveMovimento(atual,p);                
                }
                else{
                    /* Processa Input */
                    System.out.println("==============================");
                    System.out.printf("Jogador :: %s\n",atual.getNome());
                    System.out.println("Digite [CIMA] [BAIXO] [DIREITA] [ESQUERDA] para se mover");
                    if(atual.ChecarItem() != 0)
                        System.out.println("Digite  [ITEM] para usar seu item");
                    System.out.println("==============================");

                    input = scanner.nextLine();
                    input = input.toUpperCase();
                    while((atual.ChecarItem() == 0 && input.equals("ITEM"))|| !input.equals("CIMA")&&!input.equals("BAIXO")&&!input.equals("DIREITA")&&!input.equals("ESQUERDA")){
                        System.out.println("Tente novamente");
                        input = scanner.nextLine();
                        input = input.toUpperCase();
                    }

                    /* Movimento */
                    if(!input.equals("ITEM")){
                        p = atual.movimentoBase(input.charAt(i));
                        //tabuleiro.resolveMovimento(atual,p);

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
            System.out.println("======================================");
            System.out.println("                Vitoria!              ");
            System.out.println("======================================");
            System.out.printf( "Jogadores :: %d\n",tabuleiro.getJogadores().size());
            System.out.printf( "Turnos    :: %i\n",turno);
            System.out.println("======================================");
        }
        else{
            System.out.println("======================================");
            System.out.println("                Derrota!              ");
            System.out.println("======================================");
            System.out.printf( "Jogadores :: %d\n",tabuleiro.getJogadores().size());
            System.out.printf( "Fakenews  :: %d\n",tabuleiro.getFakeNewsQtd());
            System.out.printf( "Turnos    :: %d\n",turno);
            System.out.println("======================================");
        }
    }
}