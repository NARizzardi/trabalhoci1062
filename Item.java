public abstract class  Item extends Entidade{

    public Item(){
        this(null);
    }

    public Item(Posicao posicao){
        super(posicao);
    }
    
    public abstract int realizaAcao();

}
