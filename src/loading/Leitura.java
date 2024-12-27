package loading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Leitura {


    ArrayList<Peça> peças;
    Placa placa;

    public Leitura(String filename) {

        ArrayList<Peça> peças = new ArrayList<>();
        Placa placa = null;

        File f = new File(filename);

        Scanner sc;
        try {
            sc = new Scanner(f);
            while(sc.hasNext()){
                int comprimento = sc.nextInt();
                int altura = sc.nextInt();

                placa = new Placa(comprimento, altura);

                int n = sc.nextInt();

                for(int i = 0; i < n; i++){
                    int id = sc.nextInt();
                    comprimento = sc.nextInt();
                    altura = sc.nextInt();
                    int qtd = sc.nextInt();
                    peças.add(new Peça(comprimento,altura, qtd, id));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.peças = peças;
        this.placa = placa;
    }
    public ArrayList<Peça> getPeças() {
        return peças;
    }

    public void setPeças(ArrayList<Peça> peças) {
        this.peças = peças;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

}
