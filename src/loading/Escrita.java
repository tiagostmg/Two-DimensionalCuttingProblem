package loading;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Escrita {

    public void escrever(ArrayList<Posicao> pos) {
        String fileName = "output.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            for (Posicao p : pos) {

                writer.write(String.format("%d %d %d%n",
                        p.getId(),
                        p.getX(),
                        p.getY()));
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
