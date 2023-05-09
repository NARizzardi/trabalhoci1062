import java.util.Random;

public class F3 extends FakeNews{
    public F3(Posicao posicao){
        super(posicao);
    }

    public Posicao movimentaFakeNews(){
        Random rand = new Random();
        Posicao p = this.getPosicao();
        switch (rand.nextInt(4)){
            case 0:
                return new Posicao(p.getPosX()+1, p.getPosY()+1);
            case 1:
                return new Posicao(p.getPosX()+1, p.getPosY()-1);
            case 2:
                return new Posicao(p.getPosX()-1, p.getPosY()+1);
            case 3:
                return new Posicao(p.getPosX()-1, p.getPosY()-1);
        }
        return null;
    }
}
