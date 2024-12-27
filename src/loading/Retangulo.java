package loading;

public abstract class Retangulo {
    private int altura;
    private int comprimento;

    public Retangulo(int comprimento, int altura) {
        this.comprimento = comprimento;
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
    }
    public int getComprimento() {
        return comprimento;
    }
    public int calcularArea() {
        return altura * comprimento;
    }
    public int getXMax(int[] origem) {
        int xMax = origem[0] + comprimento;
        return xMax;
    }
    public int getYMax(int[] origem) {
        int yMax = origem[1] + altura;
        return yMax;
    }
}
