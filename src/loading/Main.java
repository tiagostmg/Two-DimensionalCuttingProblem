package loading;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        Leitura leitura = new Leitura("input.txt");

        ArrayList<Peça> peças = leitura.getPeças();
        Placa placa = leitura.getPlaca();
        Funcoes funcao = new Funcoes();

        ArrayList<ArrayList<Peça>> superPeças = funcao.superPeças(peças);
        peças = funcao.ordenarPeças(peças);

        Escrita escrita = new Escrita();
        int q = 1;

        if(q == 1) {
            double maiorRend = 0;
            String tipo = "";
            ArrayList<Posicao> maiorPos = null;
            for(int i = 0; i < superPeças.size(); i++) {

                ArrayList<Posicao> pos1 = funcao.horizontal(superPeças.get(i), placa);
                ArrayList<Posicao> pos2 = funcao.vertical(superPeças.get(i), placa);
                ArrayList<Posicao> pos3 = funcao.subPlaca(superPeças.get(i), placa, 1);
                ArrayList<Posicao> pos4 = funcao.subPlaca(superPeças.get(i), placa, 2);

                double rend1 = rendimento(pos1, placa);
                double rend2 = rendimento(pos2, placa);
                double rend3 = rendimento(pos3, placa);
                double rend4 = rendimento(pos4, placa);

                if(i == 0) {
                    maiorRend = rend1;
                }

                if(rend1 >= rend2 && rend1 >= rend3 && rend1 >= rend4 && rend1 >= maiorRend) {
                    maiorPos = pos1;
                    maiorRend = rend1;
                    tipo = "Horizontal";
                }
                else if(rend2 >= rend1 && rend2 >= rend3 && rend2 >= rend4 && rend2 >= maiorRend) {
                    maiorPos = pos2;
                    maiorRend = rend2;
                    tipo = "Vertical";
                }
                else if(rend3 >= rend1 && rend3 >= rend2 && rend3 >= rend4 && rend3 >= maiorRend) {
                    maiorPos = pos3;
                    maiorRend = rend3;
                    tipo = "SubPlaca Horizontal";
                }
                else if(rend4 >= rend1 && rend4 >= rend2 && rend4 >= rend3 && rend4 >= maiorRend) {
                    maiorPos = pos4;
                    maiorRend = rend4;
                    tipo = "SubPlaca Vertical";
                }

            }

            System.out.println("Tipo: " + tipo);
            mostraRendimento(maiorRend);
            mostraPeças(maiorPos, placa);
            escrita.escrever(maiorPos);
        }
        else if(q == 2) {
            ArrayList<Posicao> pos1 = funcao.horizontal(peças, placa);
            ArrayList<Posicao> pos2 = funcao.vertical(peças, placa);
            ArrayList<Posicao> pos3 = funcao.subPlaca(peças, placa, 1);
            ArrayList<Posicao> pos4 = funcao.subPlaca(peças, placa, 2);

            double rend1 = rendimento(pos1, placa);
            double rend2 = rendimento(pos2, placa);
            double rend3 = rendimento(pos3, placa);
            double rend4 = rendimento(pos4, placa);

            String tipo = "";
            double maiorRend = rend1;
            ArrayList<Posicao> maiorPos = pos1;

            if(rend1 >= rend2 && rend1 >= rend3 && rend1 >= rend4 ) {
                maiorPos = pos1;
                maiorRend = rend1;
                tipo = "Horizontal";
            }
            else if(rend2 >= rend1 && rend2 >= rend3 && rend2 >= rend4 ) {
                maiorPos = pos2;
                maiorRend = rend2;
                tipo = "Vertical";
            }
            else if(rend3 >= rend1 && rend3 >= rend2 && rend3 >= rend4 && rend3 >= maiorRend) {
                maiorPos = pos3;
                maiorRend = rend3;
                tipo = "SubPlaca Horizontal";
            }
            else if(rend4 >= rend1 && rend4 >= rend2 && rend4 >= rend3 && rend4 >= maiorRend) {
                maiorPos = pos4;
                maiorRend = rend4;
                tipo = "SubPlaca Vertical";
            }

            System.out.println("Tipo: " + tipo);
            mostraRendimento(maiorRend);
            mostraPeças(maiorPos, placa);
            escrita.escrever(maiorPos);
        }

    }

    private static double rendimento(ArrayList<Posicao> pos, Placa placa) {
        double areaPeças = 0;
        int areaTotal = placa.calcularArea();

        for(Posicao p : pos) {
            areaPeças += p.getPeça().calcularArea();
        }

        double rendimento =  100 * (areaPeças / areaTotal);
        return rendimento;

    }
    private static void mostraRendimento(double rendimento) {
        System.out.println("Rendimento: " + rendimento + "%");

    }

    private static void createAndShowGUI(ArrayList<Posicao> pos, Placa placa) {
        JFrame frame = new JFrame("Visualização do Container");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(placa.getComprimento() + 50, placa.getAltura() + 50);
        frame.add(new PlacaPanel(pos, placa));
        frame.setVisible(true);
    }

    private static void mostraPeças(ArrayList<Posicao> pos, Placa placa) {
        SwingUtilities.invokeLater(() -> createAndShowGUI(pos, placa));

    }

    private static void printCaixasAdicionadas(ArrayList<Posicao> pos) {
        for(int i = 0; i < pos.size(); i++) {
            System.out.println(pos.get(i).toString());
        }
    }
}
