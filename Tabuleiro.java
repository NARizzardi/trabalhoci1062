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

    public void iniciaJogo(ArrayList<Jogador> jogadores){
        //insert code here
    }

    //done
    public int getFakeNewsQtd(){
        return this.fakeNews.size();
    }

    public void turnoFakeNews(){
        //insert code here
    }

    public void geraFakeNews(){
        //insert code here
    }

    public void geraItens(){
        //insert code here
    }

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
            ArrayList<Entidade> al = this.entidades.get(i);
            
            // imprime o divisor de linhas
            for(int j = 0; j < 9; j++){
                System.out.print("+----");   
            }

            System.out.print("+\n");

            //imprime a linha em si
            if(i < 9){
                for(int j = 0; j < 9; j++){

                    Entidade e = al.get(j);
                    System.out.print("| ");
                    int tipo = this.getEntidadeTipo(e);
                    if(tipo == 0){
                        String tipoFakeNews = e.getClass().getSimpleName();
                        System.out.printf("%s ", tipoFakeNews);
                    } 

                    else if (tipo == 1){
                        Jogador jog = (Jogador) e; //Downcasting para usar o metodo getNome da classe Jogador
                        int numJogador = this.getJogador(jog);
                        System.out.printf("J%d ", numJogador);
                    } 

                    else if (tipo == 2){
                        System.out.print("XX ");
                    } 

                    else if (tipo == 3){
                        System.out.print("?? ");
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
    
    public int getJogador(Jogador jog) {
        for(int i = 0; i < this.jogadores.size(); i++){
            Jogador j = this.jogadores.get(i);
            if(jog.equals(j))
                return i;
        }
        return 0;
    }

    //inside functions
    private int getEntidadeTipo(Entidade e) {
        if(e instanceof FakeNews)
            return 0;
        else if(e instanceof Jogador)
            return 1;
        else if (e instanceof AreaProibida)
            return 2;

        return 0;
    }

    
}
