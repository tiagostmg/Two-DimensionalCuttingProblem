package loading;

public class Posicao {
    private int x;
    private int y;
    private Peça c;
    public Posicao(int x, int y, Peça c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Peça getPeça() {
        return c;
    }
    public int getComprimento() {
        return c.getComprimento();
    }
    public int getAltura() {
        return c.getAltura();
    }
    public int getId() {
        return c.getId();
    }
    @Override
    public String toString() {
        return "[ " + getX() + ", " + getY() + " ] id = "  + getId();
    }

}
