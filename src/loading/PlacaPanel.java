package loading;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
class PlacaPanel extends JPanel {
    private final ArrayList<int[][]> pos;
    private final Placa placa;
    private final double scale;

    public PlacaPanel(ArrayList<Posicao> pos, Placa placa) {
        ArrayList<int[][]> posprint = new ArrayList<>();
        for(Posicao p : pos) {
            int[][] ponto = {
                    {p.getX(), p.getY()},
                    {p.getX() + p.getComprimento(), p.getY()},
                    {p.getX(), p.getY() + p.getAltura()},
                    {p.getX() + p.getComprimento(), p.getY() + p.getAltura()},
                    {p.getId()}
            };
            posprint.add(ponto);
        }
        this.pos = posprint;
        this.placa = placa;
        int maxDimension = Math.max(placa.getComprimento(), placa.getAltura());
        int maxDisplaySize = 500; // Tamanho máximo da tela
        this.scale = (double) maxDisplaySize / maxDimension;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhar bordas do contêiner (aplicando escala)
        g.setColor(Color.BLACK);
        int scaledWidth = (int) (placa.getComprimento() * scale);
        int scaledHeight = (int) (placa.getAltura() * scale);
        g.drawRect(10, 10, scaledWidth, scaledHeight);

        // Desenhar as caixas (aplicando escala)
        int colorIndex = 0;
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA, Color.DARK_GRAY, Color.CYAN, Color.PINK, Color.YELLOW};

        // HashMap para associar idcaixa a uma cor
        HashMap<Integer, Color> idToColorMap = new HashMap<>();

        for (int[][] caixa : pos) {
            int[] ponto1 = caixa[0]; // Ponto superior esquerdo
            int[] ponto2 = caixa[1]; // Ponto superior direito
            int[] ponto3 = caixa[2]; // Ponto inferior direito
            int idcaixa = caixa[4][0]; // ID da caixa (ajustando para acessar corretamente)

            // Associar cor ao idcaixa se ainda não estiver no mapa
            if (!idToColorMap.containsKey(idcaixa)) {
                idToColorMap.put(idcaixa, colors[colorIndex % colors.length]);
                colorIndex++;
            }

            // Obter a cor correspondente ao idcaixa
            Color caixaColor = idToColorMap.get(idcaixa);

            // Aplicar escala aos pontos
            int scaledX1 = (int) (ponto1[0] * scale) + 10;
            int scaledY1 = (int) (ponto1[1] * scale) + 10;
            int scaledX2 = (int) (ponto2[0] * scale) + 10;
            int scaledY3 = (int) (ponto3[1] * scale) + 10;

            // Desenhar a caixa com a cor associada
            g.setColor(caixaColor);
            g.fillRect(scaledX1, scaledY1, scaledX2 - scaledX1, scaledY3 - scaledY1);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);

            // Configurar a espessura da borda
            float borderThickness = 1.0f; // Espessura da borda
            g2d.setStroke(new BasicStroke(borderThickness));

            // Desenhar borda da caixa
            g2d.drawRect(scaledX1, scaledY1, scaledX2 - scaledX1, scaledY3 - scaledY1);

            // Restaurar o traço padrão se necessário
            g2d.setStroke(new BasicStroke(1.0f));

        }
    }

}