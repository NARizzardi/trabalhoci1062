import java.util.*;

public class Tabuleiro {
    
    private ArrayList<ArrayList<Entidade>> entidades; //tabuleiro propriamente dito
    private ArrayList<FakeNews> fakeNews;
    private ArrayList<Jogador> jogadores;
    
    public Tabuleiro() {}

    public ArrayList<ArrayList<Entidade>> getEntidades() {
        return entidades;
    }
    public void setEntidades(ArrayList<ArrayList<Entidade>> entidades) {
        this.entidades = entidades;
    }

    public ArrayList<FakeNews> getFakeNews() {
        return fakeNews;
    }
    public void setFakeNews(ArrayList<FakeNews> fakeNews) {
        this.fakeNews = fakeNews;
    }

    public ArrayList<Jogador> getJogadores(){
        return this.jogadores;
    }
    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    //done
    public void iniciaJogo(ArrayList<Jogador> jogadores) throws InterruptedException{
        
        this.criaMapa();
        System.out.println("\n\nGerando tabuleiro...");
        Thread.sleep(3000);
        this.imprimeTabuleiro();

        this.posicionaJogadores(jogadores);
        System.out.println("\n\nAdicionando jogadores...");
        Thread.sleep(3000);
        this.imprimeTabuleiro();

       
        
        for(int i = 0; i < 4; i++){
            this.inicializaAreaRestrita();
        }
        System.out.println("\n\nAdicionando áreas restritas...");
        Thread.sleep(3000);
        this.imprimeTabuleiro();

        for(int i = 0; i < 2; i++){
            this.geraItens();
            System.out.println("\n\nAdicionando os itens");
            Thread.sleep(30);
            this.imprimeTabuleiro();
            System.out.println("\n\n");
        }

        this.inicializaFakeNews();
        System.out.println("\n\nAdicionando Fakenews...");
        Thread.sleep(3000);
        this.imprimeTabuleiro();
        
    }

    //done
    public int getFakeNewsQtd(){
        return this.fakeNews.size();
    }

    public void turnoFakeNews(){
        //insert code here
    }

    //done
    public FakeNews geraFakeNews(int tipo, boolean duplicada, Posicao posAtual){
        Posicao pos = new Posicao();
        if(!duplicada)
            pos = geraPosicao(7,1);
        else{
            int posX = posAtual.getPosX();
            int posY = posAtual.getPosY();
            Random aleatorio = Aleatorio.getAleatorio();
            int newPos = 0;
            do{
                int x = (aleatorio.nextInt(4) % 3) - 1;
                int y = (aleatorio.nextInt(4) % 3) - 1;
                pos.setPosX(posX + x);
                pos.setPosY(posY + y);
                newPos = checaPosicao(pos);
            }while(((newPos % 2 )!= 0) == true);

            if(newPos == 2){  //nova fakeNews gerada em um jogador
                apagaEntidade(pos);
            } else if(newPos == 4){ //nova fakeNews gerada em um novoItem;
                geraFakeNews(tipo, true, pos); //gera uma nova fakeNews
                geraItens(); //repõe o item perdido
            }

        }
        FakeNews fake;
        switch (tipo) {
            case 1:
                fake = new F1(pos);
                break;
            case 2:
                fake = new F2(pos);
                break;
            case 3:
                fake = new F3(pos);
                break;
            default:
                return null;
        }
        this.adicionaNoTabuleiro(fake);
        return fake;
    }

    public void geraItens(){
        Posicao pos = this.geraPosicao(9,0);
        Random aleatorio = Aleatorio.getAleatorio();
        int tipo = (aleatorio.nextInt(5) % 4);
        System.out.println(tipo);
        Item item;
        switch(tipo){
            case 0:
                item = new Boato(pos);
                break;
            case 1:
                item = new Denuncia(pos);
                break;
            case 2:
                item = new Fuga(pos);
                break;
            case 3:
                item = new Noticia(pos);
                break;
            default:
                return;
        }

        this.adicionaNoTabuleiro(item);
    }

    //done
    public void apagaEntidade(Posicao posicao){
        int x = posicao.getPosX();
        int y = posicao.getPosY();

        ArrayList<Entidade> al = this.entidades.get(x);
        Entidade e = al.get(y);
        al.set(y, null);
        if(this.getEntidadeTipo(e) == 0){
            FakeNews f = (FakeNews) e;
            fakeNews.remove(f);
        } else if(this.getEntidadeTipo(e) == 1){
            Jogador j = (Jogador) e;
            jogadores.remove(j);
        }



    }

    //done
    public void imprimeTabuleiro(){
        for(int i = 0; i < 10; i++){
            
            // imprime o divisor de linhas
            for(int j = 0; j < 9; j++){
                System.out.print("+----");   
            }

            System.out.print("+\n");
            
            //imprime a linha em si
            if(i < 9){
                ArrayList<Entidade> al = this.entidades.get(i);
                for(int j = 0; j < 9; j++){

                    Entidade e = al.get(j);
                    System.out.print("| ");
                    int tipo = this.getEntidadeTipo(e);
                    if(tipo == 1){
                        String tipoFakeNews = e.getClass().getSimpleName().concat(" ");
                        this.imprimeEntidade(Cores.ANSI_RED, tipoFakeNews);
                    } 

                    else if (tipo == 2){
                        Jogador jog = (Jogador) e; //Downcasting para usar o metodo getNome da classe Jogador
                        int numJogador = this.getJogador(jog)+1;
                        String player = "J".concat(Integer.toString(numJogador)).concat(" ");
                        this.imprimeEntidade(Cores.ANSI_CYAN, player);
                    } 

                    else if (tipo == 3){
                        this.imprimeEntidade(Cores.ANSI_WHITE, "xx ");
                    } 

                    else if (tipo == 4){
                        this.imprimeEntidade(Cores.ANSI_YELLOW, "?? ");
                        
                    } 
                    
                    else {
                        System.out.print("   ");
                    }
                    
                }
                System.out.print("|\n");
            }

        }
        
    }

    
    public int resolveMovimento(Entidade e){
        //insert code here
        return 0;
    }
    
    //done
    public int getJogador(Jogador jog) {
        for(int i = 0; i < this.jogadores.size(); i++){
            Jogador j = this.jogadores.get(i);
            if(jog.equals(j))
                return i;
        }
        return 0;
    }

    //inside functions
    //done
    private int getEntidadeTipo(Entidade e) {
        
        if(e instanceof FakeNews)
            return 1;
        else if(e instanceof Jogador)
            return 2;
        else if (e instanceof AreaProibida)
            return 3;
        else if (e instanceof Item)
            return 4;

        return 0;
    }

    //done
    private void criaMapa() {

        ArrayList<ArrayList<Entidade>> map = new ArrayList<ArrayList<Entidade>>(9);
        for(int i = 0; i < 9; i++){
            ArrayList<Entidade> row = new ArrayList<Entidade>(9);
            for(int j = 0; j < 9; j++){
                row.add(j, null);
            }
            map.add(row);
        }

        this.setEntidades(map);
    }

    //done
    private void posicionaJogadores(ArrayList<Jogador> jogadores) {
        this.setJogadores(jogadores);
        
        Posicao p1 = new Posicao(4, 0);
        ArrayList<Entidade> row = this.entidades.get(p1.getPosY());
        row.add(p1.getPosX(), jogadores.get(0));
        jogadores.get(0).setPosicao(p1);
        
        if(jogadores.size() == 1)
            return;
        
        Posicao p2 = new Posicao(8, 4);
        row = this.entidades.get(p2.getPosY());
        row.add(p2.getPosX(), jogadores.get(1));
        jogadores.get(1).setPosicao(p2);
        System.out.println(row.get(8).getClass().getSimpleName());
        if(jogadores.size() == 2)
            return;

        Posicao p3 = new Posicao(4, 8);
        row = this.entidades.get(p3.getPosY());
        row.add(p3.getPosX(), jogadores.get(2));
        jogadores.get(2).setPosicao(p3);

        if(jogadores.size() == 3)
            return;

        Posicao p4 = new Posicao(0, 4);
        row = this.entidades.get(p4.getPosY());
        row.add(p4.getPosX(), jogadores.get(3));
        jogadores.get(3).setPosicao(p4);
    }
    
    //done
    private void inicializaFakeNews(){
        ArrayList<FakeNews> fakeNews = new ArrayList<FakeNews>(6);
        
        fakeNews.add(this.geraFakeNews(1, false, null));
        fakeNews.add(this.geraFakeNews(1, false, null));

        fakeNews.add(this.geraFakeNews(2, false, null));
        fakeNews.add(this.geraFakeNews(2, false, null));

        fakeNews.add(this.geraFakeNews(3, false, null));
        fakeNews.add(this.geraFakeNews(3, false, null));
        this.setFakeNews(fakeNews);
    }

    //done
    private void inicializaAreaRestrita(){

        Posicao pos = geraPosicao(9,0);
        AreaProibida ap = new AreaProibida(pos);

        this.adicionaNoTabuleiro(ap);
    }

    //done
    private int checaPosicao(Posicao posicao){
        if(posicao.getPosX() > 8 || posicao.getPosY()>8)
            return -1;
        
        ArrayList<Entidade> row = this.entidades.get(posicao.getPosY());
        int ocupacao = this.getEntidadeTipo(row.get(posicao.getPosX()));
        return ocupacao;
    }

    //
    private void adicionaNoTabuleiro(Entidade e){
        ArrayList<Entidade> row = this.entidades.get(e.getPosicao().getPosY());
        row.set(e.getPosicao().getPosX(), e);
    }

    //done
    private Posicao geraPosicao(int limit, int offset){
        Random aleatorio = Aleatorio.getAleatorio();
        int pX;
        int pY;
        Posicao pos = new Posicao();
        do{
            pX = aleatorio.nextInt(limit) + offset;        
            pY = aleatorio.nextInt(limit) + offset;        
            pos.setPosX(pX);
            pos.setPosY(pY);
        } while(this.checaPosicao(pos) != 0);
        return pos;
    }

    private void imprimeEntidade(String cor, String tipoEntidade){
        String clearColor = Cores.ANSI_RESET;
        System.out.print(cor+tipoEntidade+clearColor);
    }
}
