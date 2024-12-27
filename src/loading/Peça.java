package loading;

import java.util.ArrayList;

public class Peça extends Retangulo {
    private int qnt;
    private int id;

    public Peça(int comprimento, int altura, int qnt, int id) {
        super(comprimento,altura);
        this.qnt = qnt;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQnt() {
        return qnt;
    }
    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public  boolean cabe(int[] origemPeça, Placa placa, ArrayList<Posicao> pos) {
        // Verifica se os pontos da caixa estão dentro dos limites do container
        int[][] pontosCaixa = {
                {origemPeça[0], origemPeça[1]},                             // Canto superior esquerdo
                {origemPeça[0] + getComprimento(), origemPeça[1]},             // Canto superior direito
                {origemPeça[0], origemPeça[1] + getAltura()},              // Canto inferior esquerdo
                {origemPeça[0] + getComprimento(), origemPeça[1] + getAltura()} // Canto inferior direito
        };

        for (int[] ponto : pontosCaixa) {
            if (!estaDentro(ponto, placa)) {
                return false; // Se algum ponto da caixa estiver fora, ela não cabe
            }
        }

        // Verifica se os pontos da caixa conflitam com outros pontos já presentes
        for (Posicao p : pos) {
            if (conflitaCom(origemPeça, p)) {
                return false; // Se há conflito, retorna falso
            }
        }

        return true; // A caixa cabe sem problemas
    }

    public  boolean estaDentro(int[] ponto, Placa placa) {
        // Verifica se o ponto está dentro dos limites do container
        return (ponto[0] >= 0 && ponto[0] <= placa.getComprimento()) &&
                (ponto[1] >= 0 && ponto[1] <= placa.getAltura());
    }
    public boolean conflitaCom(int[] origem, Posicao p) {

        int xMinPeça = origem[0];
        int xMaxPeça = getXMax(origem);
        int yMinPeça = origem[1];
        int yMaxPeça = getYMax(origem);


        int xMinExistente = p.getX();
        int xMaxExistente = p.getX() + p.getComprimento();
        int yMinExistente = p.getY();
        int yMaxExistente = p.getY() + p.getAltura();

        boolean xSobrepoe = xMinPeça < xMaxExistente && xMaxPeça > xMinExistente;
        boolean ySobrepoe = yMinPeça < yMaxExistente && yMaxPeça > yMinExistente;

        return xSobrepoe && ySobrepoe;
    }
    public boolean cabe(SubPlaca sub) {
        // Verifica se as dimensões da caixa são menores ou iguais às do subcontainer
        return this.getComprimento() <= sub.getPlaca().getComprimento() &&
                this.getAltura() <= sub.getPlaca().getAltura();
    }



}
