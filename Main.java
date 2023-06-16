import java.util.*;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        //==========================================
        //Variaveis
        //==========================================
        Aleatorio ale = new Aleatorio();
        ale.setAleatorio();

        Scanner scanner = new Scanner(System.in);

        /* Jogadores */
        int num_jogadores;
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        Posicao iniciais[] = new Posicao[4];
        iniciais[0] = new Posicao(4, 0); 
        iniciais[1] = new Posicao(8, 4); 
        iniciais[2] = new Posicao(4, 8); 
        iniciais[3] = new Posicao(0, 4);
        /* Tabuleiro */
        Tabuleiro tabuleiro = new Tabuleiro();

        /* Variaveis de logica de jogo */
        Jogador atual;
        Posicao p = new Posicao(0,0);
        String input,temp, str[];
        int flag = 0, turno;

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
            jogadores.add(new Jogador(Integer.toString(num_jogadores-i), iniciais[i]));
        }

        System.out.println("==============================");
        System.out.println("Iniciando...   ");

        try {
            tabuleiro.iniciaJogo(jogadores); 
        } catch (Exception e) {
            scanner.close();
            return;
        }
        //==========================================
        // Jogo
        //==========================================
        for(turno = 0; (turno < 20) && !tabuleiro.fimDeJogo(); turno++){
            /* Vez dos jogadores */
            for(int i = tabuleiro.getJogadoresQtd()-1; i >= 0; i--){
                /* Gambiarra para limpar tela */
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");

                tabuleiro.imprimeTabuleiro();
                atual = tabuleiro.getJogador(i);

                    
                
                /* Teste */
                atual.setItem(new Denuncia(atual.getPosicao()));
                /*********/

                /* Movimento forçado :: Boato */
                if(atual.isBoatoFlag()){
                    /* Mensagem de boato  */
                    System.out.println("==============================");
                    System.out.println("           BOATO!             ");
                    System.out.println("==============================");
                    System.out.printf( "Jogador %s esta confuso!      \n",atual.getNome());
                    System.out.println("==============================");
                    Thread.sleep(2000);
                    p = atual.movimentoAleatorio();
                    atual.setBoatoFlag(false);
                    tabuleiro.resolveMovimento(atual,p);                
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
                    while(((atual.ChecarItem() == 0 && input.equals("ITEM")) || !(input.equals("ITEM")) && !input.equals("CIMA")&&!input.equals("BAIXO")&&!input.equals("DIREITA")&&!input.equals("ESQUERDA"))){
                        System.out.println("Tente novamente");
                        input = scanner.nextLine();
                        input = input.toUpperCase();
                    }

                    /* Movimento */
                    if(!input.equals("ITEM")){
                        p = atual.movimentoBase(input.charAt(0));
                        tabuleiro.resolveMovimento(atual,p);
                    }    
                    else{
                        /* Item */
                        flag = atual.ChecarItem();
                        switch(flag){
                            /* Denuncia */
                            case 1:
                                System.out.println("==============================");
                                System.out.println("           Denuncia!          ");
                                System.out.println("==============================");
                                System.out.println( "É mentira!                  \n");
                                System.out.println("==============================");
                                Thread.sleep(2000);
                                break;
                            /* Noticia */
                            case 2:
                                System.out.println("==============================");
                                System.out.println("           Noticia!           ");
                                System.out.println("==============================");
                                System.out.println( "Mas é verdade isso?        \n");
                                System.out.println("==============================");
                                Thread.sleep(2000);

                                //System.out.println( "Digite uma casa [x,y] para investigar: \n");
                                //temp = scanner.nextLine();
                                //str  = temp.split(",");
                                //p.setPosX( Integer.parseInt(str[0]) );
                                //p.setPosY( Integer.parseInt(str[1]) );
                                break;
                            /* Boato   */                    
                            case 3:
                                System.out.println("==============================");
                                System.out.println("           Boato!             ");
                                System.out.println("==============================");
                                System.out.println( "Fofocar nunca matou niguém...\n");
                                System.out.println("==============================");
                                Thread.sleep(2000);
                                break;
                            /* Fuga */
                            case 4:
                                System.out.println("==============================");
                                System.out.println("           Fuga!              ");
                                System.out.println("==============================");
                                System.out.println("Me tira dessa!        \n");
                                System.out.println("Digite uma casa [x,y] para fugir: \n");
                                temp = scanner.nextLine();
                                str  = temp.split(",");
                                p.setPosX( Integer.parseInt(str[0]) );
                                p.setPosY( Integer.parseInt(str[1]) );
                                break;
                        }

                        atual.utilizarItem(tabuleiro,p);
                    }
                }
            }
            /* Vez das fakeNews */
            tabuleiro.turnoFakeNews();
        }
        scanner.close();

        //==========================================
        // Fim de Jogo
        //==========================================
        if(tabuleiro.getFakeNewsQtd() == 0){
            System.out.println("======================================");
            System.out.println("                Vitoria!              ");
            System.out.println("======================================");
            System.out.printf( "Jogadores :: %d\n",tabuleiro.getJogadores().size());
            System.out.printf( "Turnos    :: %d\n",turno);
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