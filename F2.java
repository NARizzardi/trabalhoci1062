import java.util.Random;

public class F2 extends FakeNews{
    public F2(Posicao posicao){
        super(posicao);
    }

    public Posicao movimentaFakeNews(){
        Aleatorio rand = Aleatorio.getAleatorio();
        Posicao p = this.getPosicao();
        switch (rand.nextInt(4)){
            case 0:
                return new Posicao(p.getPosX()+2, p.getPosY());
            case 1:
                return new Posicao(p.getPosX()-2, p.getPosY());
            case 2:
                return new Posicao(p.getPosX(), p.getPosY()+2);
            case 3:
                return new Posicao(p.getPosX(), p.getPosY()-2);
        }
        return null;
    }
}
