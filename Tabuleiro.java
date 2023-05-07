import java.util.*;

public class Tabuleiro {
    
    private ArrayList<ArrayList<Entidade>> entidades;
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
    public void iniciaJogo(ArrayList<Jogador> jogadores){
        
        this.criaMapa();
        
        this.posicionaJogadores(jogadores);
        
        // this.inicializaFakeNews();

        for(int i = 0; i < 4; i++){
            this.inicializaAreaRestrita();
        }

        for(int i = 0; i < 2; i++){
         //   this.geraItens();
        }
        
    }

    //done
    public int getFakeNewsQtd(){
        return this.fakeNews.size();
    }

    public void turnoFakeNews(){
        //insert code here
    }

    public void geraFakeNews(int tipo, boolean duplicada){
        //insert code here
    }

    public void geraItens(){
        //insert code here
    }

    //done
    public void apagaEntidade(Posicao posicao){
        //insert code here
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
        this.geraFakeNews(1, false);
        this.geraFakeNews(1, false);

        this.geraFakeNews(2, false);
        this.geraFakeNews(2, false);

        this.geraFakeNews(3, false);
        this.geraFakeNews(3, false);
    }

    //done
    private void inicializaAreaRestrita(){
        Random aleatorio = Aleatorio.getAleatorio();
        int pX;
        int pY;
        Posicao pos = new Posicao();
        do{
            pX = aleatorio.nextInt(7) + 1;        
            pY = aleatorio.nextInt(7) + 1;        
            pos.setPosX(pX);
            pos.setPosY(pY);
        } while(this.checaPosicao(pos) != 0);

        AreaProibida ap = new AreaProibida();
        ap.setPosicao(pos);

        ArrayList<Entidade> row = this.entidades.get(pY);
        row.set(pX, ap);
    }

    //done
    private int checaPosicao(Posicao posicao){
        ArrayList<Entidade> row = this.entidades.get(posicao.getPosY());
        int ocupacao = this.getEntidadeTipo(row.get(posicao.getPosX()));
        return ocupacao;
    }

    private void imprimeEntidade(String cor, String tipoEntidade){
        String clearColor = Cores.ANSI_RESET;
        System.out.print(cor+tipoEntidade+clearColor);
    }
}
