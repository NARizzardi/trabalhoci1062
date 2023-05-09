public abstract class Entidade {
    protected Posicao posicao;

    public Entidade(Posicao posicao) {
        this.setPosicao(posicao);
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
