import java.util.Random;

public class Jogador extends Entidade{
    private String nome;
    private Item item;
    private boolean boato_flag;

    
    public Jogador(String nome, Posicao posicao) {
        super(posicao);
        this.setNome(nome);
        this.setBoatoFlag(false);
        this.setItem(null);
    }

    public Jogador(String nome){
        this(nome, null);
    }

    public void setItem(Item item){
        this.item = item;
    }

    public boolean isBoatoFlag(){
        return this.boato_flag;
    }

    public void setBoatoFlag(boolean boato_flag){
        this.boato_flag = boato_flag;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    /*
    *   Movimenta o Personagem 1 casa nas direçoes cardinais
    *   Valores de direcao (Not case sensitive): 
    *       w :: Move para o Norte
    *       a :: Move para o Oeste
    *       s :: Move para o Sul
    *       d :: Move para o Leste
    *   Outro :: Erro
    *   Retorna uma posição apos o movimento ou null em caso de  erro 
    */
    public Posicao movimentoBase(char direcao){
        Posicao p = this.getPosicao();
        switch (direcao){
            case 'D':
            case 'd':
                return new Posicao(p.getPosX()+1, p.getPosY());
            case 'A':
            case 'a':
                return new Posicao(p.getPosX()-1, p.getPosY());
            case 'W':
            case 'w':
                return new Posicao(p.getPosX(), p.getPosY()+1);
            case 'S':
            case 's':
                return new Posicao(p.getPosX(), p.getPosY()-1);
        }
        return null;
    }
    /*
    *   Tenta Movimentar o jogador em uma direção aleatoria
    *   Retorna a posição que o jogador está tentando se mover
    */
    public Posicao movimentoAleatorio(){
        Random rand = Aleatorio.getAleatorio();
        Posicao p = this.getPosicao();
        switch (rand.nextInt(4)){
            case 0:
                return new Posicao(p.getPosX()+1, p.getPosY());
            case 1:
                return new Posicao(p.getPosX()-1, p.getPosY());
            case 2:
                return new Posicao(p.getPosX(), p.getPosY()+1);
            case 3:
                return new Posicao(p.getPosX(), p.getPosY()-1);
        }
        return null;
    }

    /* 
    *   Função para usar o item do personagem, depois de uso jogador perde o item 
    */
    public void utilizarItem(Tabuleiro tabuleiro, Posicao p){
        if(this.item != null){
            this.item.realizaAcao(tabuleiro, p,this);
            this.item = null;
        }
    }
    
    /*
    *   Devolve um numero que identifica o tipo do item que o jogador tem
    *   Retorna
    *      -1 :: Erro
    *       0 :: Não tem item 
    *       1 :: Denuncia 
    *       2 :: Noticia
    *       3 :: Boato 
    *       4 :: Fuga 
    */
    public int ChecarItem(){
        if(this.item == null)
            return 0;
        if(this.item instanceof Denuncia)
            return 1;
        if(this.item instanceof Noticia)
            return 2;
        if(this.item instanceof Boato)
            return 3;
        if(this.item instanceof Fuga)
            return 4;
        return -1;
    }


}
