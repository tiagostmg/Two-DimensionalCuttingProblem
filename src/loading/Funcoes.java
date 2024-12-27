package loading;

import java.util.ArrayList;

public class Funcoes {

    public ArrayList<Peça> ordenarPeças(ArrayList<Peça> peças){

        for(int i = 0; i < peças.size(); i++) {
            for(int j = i + 1; j < peças.size(); j++) {
                if(peças.get(i).calcularArea() < peças.get(j).calcularArea()) {

                    Peça prov = peças.get(i);
                    peças.set(i, peças.get(j));
                    peças.set(j, prov);

                }
            }
        }
        return peças;
    }
    public ArrayList<Posicao> horizontal(ArrayList<Peça> peças, Placa placa){

        ArrayList<Posicao> pos = new ArrayList<>();
        for(Peça c: peças) {

            int qtdT = c.getQnt();

            for(int i = 0; i < placa.getAltura(); i++) {
                for(int j = 0; j < placa.getComprimento(); j++) {

                    int[] teste = {j, i};


                    if(c.cabe(teste, placa, pos) && qtdT > 0) {
                        pos.add(new Posicao(j, i, c));
                        qtdT--;
                    }
                }
            }
        }
        return pos;
    }

    //Metodo que percorre todos os espacos na vertical para ver se cabe uma peça
    public ArrayList<Posicao> vertical(ArrayList<Peça> peças, Placa placa){

        ArrayList<Posicao> pos = new ArrayList<>();
        for(Peça c: peças) {

            int qtdT = c.getQnt();

            for(int i = 0; i < placa.getComprimento(); i++) {
                for(int j = 0; j < placa.getAltura(); j++) {

                    int[] teste = {i, j};

                    if(c.cabe(teste, placa, pos) && qtdT > 0) {
                        pos.add(new Posicao(i, j, c));
                        qtdT--;
                    }
                }
            }
        }
        return pos;
    }
    public ArrayList<ArrayList<Peça>> superPeças(ArrayList<Peça> peças) {
        ArrayList<ArrayList<Peça>> permutacoes = new ArrayList<>();
        permutacoesPecas(peças, 0, permutacoes);
        return permutacoes;
    }

    //Vai fazendo as permutações e mudando o Array principal
    private void permutacoesPecas(ArrayList<Peça> peças, int start, ArrayList<ArrayList<Peça>> permutacoes) {
        if (start == peças.size() - 1) {

            // Adiciona a permutação atual à lista como uma nova instância de ArrayList
            permutacoes.add(new ArrayList<>(peças));
            return;
        }

        for (int i = start; i < peças.size(); i++) {
            // Troca os elementos na posição 'start' e 'i'
            swap(peças, start, i);

            // Chama recursivamente para a próxima posição
            permutacoesPecas(peças, start + 1, permutacoes);

            // Desfaz a troca para continuar com outras combinações
            swap(peças, start, i);
        }
    }

    //Troca os elementos da çista
    private void swap(ArrayList<Peça> peças, int i, int j) {
        Peça temp = peças.get(i);
        peças.set(i, peças.get(j));
        peças.set(j, temp);
    }

    //Metodo que adiciona as peças nas subPlacas
    public ArrayList<Posicao> subPlaca(ArrayList<Peça> peças, Placa placa, int tipo) {
        ArrayList<Posicao> pos = new ArrayList<>();
        ArrayList<SubPlaca> subPlacas = new ArrayList<>();
        subPlacas.add(new SubPlaca(placa, 0, 0));

        for (Peça c : peças) {
            int qtd = c.getQnt();
            while (qtd > 0) {
                boolean peçaColocada = false;

                for (int i = 0; i < subPlacas.size(); i++) {
                    SubPlaca sub = subPlacas.get(i);

                    if (c.cabe(sub)) {
                        pos.add(new Posicao(sub.getX(), sub.getY(), c));
                        qtd--;
                        peçaColocada = true;

                        //Verifica como deseja formar as subPlacas
                        if(tipo == 1) {
                            criarSubPlacasHorizontal(sub, c, subPlacas);
                        }
                        else {
                            criarSubPlacasVertical(sub, c, subPlacas);

                        }

                        subPlacas.remove(i);

                        // Combina subPlacas após modificar a lista
                        combinarSubPlacas(subPlacas);

                        break;
                    }
                }

                if (!peçaColocada) {
                    break;
                }
            }
        }

        return pos;
    }


    public void criarSubPlacasHorizontal(SubPlaca sub, Peça c, ArrayList<SubPlaca> subPlaca) {
        // Calcula os espaços restantes
        int larguraRestante = sub.getPlaca().getComprimento() - c.getComprimento();
        int alturaRestante = sub.getPlaca().getAltura() - c.getAltura();

        // SubPlaca à direita(no gráfico)
        if (larguraRestante > 0) {
            SubPlaca direita = new SubPlaca(new Placa(larguraRestante, c.getAltura()),sub.getX() + c.getComprimento(),sub.getY());
            subPlaca.add(direita);
        }

        // SubPlaca abaixo(no gráfico)
        if (alturaRestante > 0) {
            SubPlaca abaixo = new SubPlaca(new Placa(sub.getPlaca().getComprimento(), alturaRestante),sub.getX(),sub.getY() + c.getAltura());
            subPlaca.add(abaixo);
        }
    }

    public void criarSubPlacasVertical(SubPlaca sub, Peça c, ArrayList<SubPlaca> subPlaca) {
        // Calcula os espaços restantes
        int larguraRestante = sub.getPlaca().getComprimento() - c.getComprimento();
        int alturaRestante = sub.getPlaca().getAltura() - c.getAltura();

        // SubPlaca à direita
        if (larguraRestante > 0) {
            SubPlaca direita = new SubPlaca(new Placa(larguraRestante, sub.getPlaca().getAltura()),sub.getX() + c.getComprimento(),sub.getY());
            subPlaca.add(direita);
        }

        // SubPlaca abaixo
        if (alturaRestante > 0) {
            SubPlaca abaixo = new SubPlaca(new Placa(c.getComprimento(), alturaRestante),sub.getX(),sub.getY() + c.getAltura());
            subPlaca.add(abaixo);
        }
    }

    //Era pra ver se juntando duas subPlacas conseguia fazer um maior pra colocar mais peças
    private void combinarSubPlacas(ArrayList<SubPlaca> subPlacas) {
        for (int i = 0; i < subPlacas.size(); i++) {
            for (int j = i + 1; j < subPlacas.size(); j++) {

                SubPlaca a = subPlacas.get(i);
                SubPlaca b = subPlacas.get(j);

                // Verifica se são adjacentes verticalmente e têm a mesma largura
                if (a.getX() == b.getX() && a.getPlaca().getComprimento() == b.getPlaca().getComprimento()
                        && a.getY() + a.getPlaca().getAltura() == b.getY()) {
                    // Combina os subcontainers verticalmente
                    a.getPlaca().setAltura(a.getPlaca().getAltura() + b.getPlaca().getAltura());
                    subPlacas.remove(j);
                    j--; // Ajusta o índice após a remoção
                }
                // Verifica se são adjacentes horizontalmente e têm a mesma altura
                else if (a.getY() == b.getY() && a.getPlaca().getAltura() == b.getPlaca().getAltura()
                        && a.getX() + a.getPlaca().getComprimento() == b.getX()) {
                    // Combina os subcontainers horizontalmente
                    a.getPlaca().setComprimento(a.getPlaca().getComprimento() + b.getPlaca().getComprimento());
                    subPlacas.remove(j);
                    j--; // Ajusta o índice após a remoção
                }
            }
        }
    }



}