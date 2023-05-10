public class Boato extends Item{
    public Boato(Posicao posicao){
        super(posicao);
    }

    public int realizaAcao(){
        return 0;
    }

    public void realizaAcao(Jogador jogador){
        jogador.boato_flag = true;
    }
}
