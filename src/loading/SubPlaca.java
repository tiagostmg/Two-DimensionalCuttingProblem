package loading;

public class SubPlaca {
    private Placa placa;
    private int x;
    private int y;

    public SubPlaca(Placa placa, int x, int y) {
        this.placa = placa;
        this.x = x;
        this.y = y;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}