import java.util.*;

public class Tabuleiro {
    
    private ArrayList<ArrayList<Entidade>> entidades; //tabuleiro propriamente dito
    private ArrayList<FakeNews> fakeNews;
    private ArrayList<Jogador> jogadores;
    
    //construtor
    public Tabuleiro() {}

    //getters e setters
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
    public int getJogadorIndex(Jogador jog) {
        for(int i = 0; i < this.jogadores.size(); i++){
            Jogador j = this.jogadores.get(i);
            if(jog.equals(j))
                return i;
        }
        return 0;
    }
    public int getFakeNewsIndex(FakeNews fake) {
        for(int i = 0; i < this.fakeNews.size(); i++){
            FakeNews f = this.fakeNews.get(i);
            if(fake.equals(f))
                return i;
        }
        return 0;
    }

    public Jogador getJogador(int index){
        return this.jogadores.get(index);
    }
    public Jogador getJogador(Posicao pos){
        ArrayList<Entidade> al = this.entidades.get(pos.getPosX());
        Entidade e = al.get(pos.getPosY());

        if(this.getEntidadeTipo(e) == 2)
            return (Jogador) e;
        return null;
    }
    public FakeNews getOneFakeNews(int index){
        return this.fakeNews.get(index);
    }

    //done
    public int getJogadoresQtd(){
        return this.jogadores.size();
    }
    public int getFakeNewsQtd(){
        return this.fakeNews.size();
    }


    //done
    public void iniciaJogo(ArrayList<Jogador> jogadores){
        
        this.criaMapa();
        

        this.posicionaJogadores(jogadores);
        

       
        
        for(int i = 0; i < 4; i++){
            this.inicializaAreaRestrita();
        }
        

        for(int i = 0; i < 2; i++){
            this.geraItens();
        }

        this.inicializaFakeNews();
        
        
    }

    //done
    public void turnoFakeNews() throws InterruptedException{
        for(FakeNews fake : this.fakeNews){
            
            System.out.print("Turno da fakenews " + this.getFakeNewsIndex(fake));
            Thread.sleep(3000);
            Posicao pos = fake.movimentaFakeNews();
            this.resolveMovimento(fake, pos);
            this.imprimeTabuleiro();
            
        }
    }

    //done
    public FakeNews geraFakeNews(int tipo, boolean duplicada, Posicao posAtual){
        Posicao pos = new Posicao();
        if(!duplicada)
            pos = geraPosicaoVazia(7,1);
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

    //done
    public void geraItens(){
        Posicao pos = this.geraPosicaoVazia(9,0);
        Random aleatorio = Aleatorio.getAleatorio();
        int tipo = (aleatorio.nextInt(5) % 4);
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

        ArrayList<Entidade> al = this.entidades.get(y);
        Entidade e = al.get(x);
        al.set(y, null);
        if(this.getEntidadeTipo(e) == 1){
            FakeNews f = (FakeNews) e;
            fakeNews.remove(f);
        } else if(this.getEntidadeTipo(e) == 2){
            Jogador j = (Jogador) e;
            jogadores.remove(j);
        }
    }

    
    public int resolveMovimento(Entidade e, Posicao newPos){
        int xAxys = newPos.getPosX();
        int yAxys = newPos.getPosY();
        int valorPosicao = 3;
        if(xAxys >= 0 && yAxys >= 0){
            valorPosicao = checaPosicao(newPos);
        }
        if(this.getEntidadeTipo(e) == 2){
            Jogador j = (Jogador) e;
            String nome = j.getNome();

            if(valorPosicao == 1 || valorPosicao == 3 || xAxys < 0 || yAxys < 0){
                System.out.println("O jogador " + nome + " se matou ao acessar uma casa que não devia");
                this.apagaEntidade(j.getPosicao());
            } else {

                this.removeDoTabuleiro(j.getPosicao());
                j.setPosicao(newPos);

                if(valorPosicao == 4) {    
                    
                    Item i = this.pegaItem(newPos);
                    j.setItem(i);
                    this.geraItens();
                    System.out.println("O jogador " + nome + " coletou um item!");
                } else if(valorPosicao == 2) {
                    
                    Jogador j2 = this.getJogador(newPos);
                    String nomeJ2 = j2.getNome();

                    System.out.println("O jogador " + nome + " morreu ao acessar a casa de " + nomeJ2);
                    this.apagaEntidade(j.getPosicao());
                    return -1; // um jogador morreu
                }
                this.adicionaNoTabuleiro(j);
                return 1; //jogador completou a ação dele
                
            }
        } else {
            FakeNews f = (FakeNews) e; 
            if(valorPosicao == 3 || valorPosicao == 1){
                this.apagaEntidade(f.getPosicao());
                System.out.println("A fakenews se eliminou...");
            } else {

                    this.removeDoTabuleiro(f.getPosicao());
                    f.setPosicao(newPos);

                if(valorPosicao == 2) {
                    Jogador j = this.getJogador(newPos);
                    String nome = j.getNome();
                    System.out.println("A Fake News matou o jogador " + nome + ".");
                    
                    this.apagaEntidade(newPos);
                } else if(valorPosicao == 4){
                    int tipo;
                    if(f instanceof F1){
                        tipo = 1;
                    }else if(f instanceof F2){
                        tipo = 2;
                    }else{
                        tipo = 3;
                    }
                    this.geraFakeNews(tipo, true, newPos);
                }
                this.adicionaNoTabuleiro(e);
            }
        }
        return 0;
    }
    
    //done
    public boolean fimDeJogo(){
        if(this.getFakeNewsQtd() == 0 || this.getJogadoresQtd() == 0)
            return true;

        return false;
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

        Posicao pos = geraPosicaoVazia(9,0);
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

    //done
    private Item pegaItem(Posicao pos){
        ArrayList<Entidade> row = this.entidades.get(pos.getPosY());
        Entidade e = row.get(pos.getPosX());

        if(this.getEntidadeTipo(e) == 4)
            return (Item) e;

        return null;
    }

    //done
    private void adicionaNoTabuleiro(Entidade e){
        ArrayList<Entidade> row = this.entidades.get(e.getPosicao().getPosY());
        row.set(e.getPosicao().getPosX(), e);
    }

    //done
    private void removeDoTabuleiro(Posicao pos){
        ArrayList<Entidade> row = this.entidades.get(pos.getPosY());
        row.set(pos.getPosX(), null);
    }
    
    //done
    private Posicao geraPosicaoVazia(int limit, int offset){
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
                        int numJogador = this.getJogadorIndex(jog)+1;
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
        System.out.println("\n\n\n\n");
        
    }

    //done
    private void imprimeEntidade(String cor, String tipoEntidade){
        String clearColor = Cores.ANSI_RESET;
        System.out.print(cor+tipoEntidade+clearColor);
    }
}
