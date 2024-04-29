import java.text.DecimalFormat;
import java.util.Random;

public class Software {
    public static void main(String[] args) throws Exception {
        
        Matriz matriz = new Matriz();
        double[][] matAux = {{2, 6, 2, 2}, {-3, -8, 0, 2}, {4, 9, 2, 3}};
        matriz.inserirMatrizDeIndices(matAux);
        op.adicionarMatriz(matriz);

        imprimirMatriz(op.decomposicaoLU(matriz));
    }

    static Operar op = new Operar();

    static DecimalFormat df = new DecimalFormat("#,##0.##");

    static Random random = new Random();

    /**
     * Gera uma matriz invertível, de tamanho entre 2 e 5.
     * @return matriz invertível
      */
    static Matriz gerarMatrizInvertivel() {
        int tamanho = 2 + random.nextInt(4);
        Matriz matriz = gerarIdentidade(tamanho);
        int nLinhas = matriz.lerNLinhas();
        int caso;

        for (int i = 0; i < (random.nextInt(12) + 4) * tamanho; i++) {
            caso = random.nextInt(3);
            int linha1 = random.nextInt(nLinhas);
            int linha2;
            do {
                linha2 = random.nextInt(nLinhas);
            } while (linha1 == linha2);

            int escalar;
            do {
                escalar = -3 + random.nextInt(7);
            } while (escalar == 0);

            switch (caso) {
                case 0:
                    matriz.multiplicarEscalar(linha1, escalar);
                break;
    
                case 1:
                    matriz.trocarLinhas(linha1, linha2);
                break;
    
                case 2:
                    matriz.somarMultiplo(linha1, linha2, escalar);
                break;
            }
        }

        return matriz;
    }

    /**
     * Gera uma matriz identidade
     * @param tamanho da matriz
     * @return matriz identidade
      */
    static Matriz gerarIdentidade(int tamanho) {
        Matriz matriz = new Matriz();
        matriz.inserirTamanho(tamanho, tamanho);

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if(i == j) {
                    matriz.inserirValor(1, i, j);
                } else {
                    matriz.inserirValor(0, i, j);
                }
            }
        }

        return matriz;
    }

    /**
     * Gera vetor com entradas no intervalo [-10, 10]
     * @param tamanho do vetor
     * @return vetor gerado
      */
    static double[] geraVetor(int tamanho) {
        double[] vetor = new double[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(21) - 10;
        }
        return vetor;
    }


    static void imprimirMatriz(Matriz matriz) {
        System.out.println();
        double number;
        for(int i = 0; i < matriz.lerNLinhas(); i++) {
            for(int j = 0; j < matriz.lerNColunas(); j++) {
                number = matriz.lerValor(i, j);
                if (number == 0) {
                    number = Math.abs(number);
                } 
                System.out.print(df.format(number));
                if(j != matriz.lerNColunas() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
    
    static void imprimirProcessos(Matriz[] processos) {
        for (int i = 0; i < processos.length; i++) {
            imprimirOperacaoElementar(processos[i].lerOperacao());
            imprimirMatriz(processos[i]);
        }
    }
    static void imprimirOperacaoElementar(double[] operacao) {
        int index1 = (int)operacao[0];
        int index2 = (int)operacao[1];
        double k = operacao[2];
        if (operacao[0] != -1) {
            if(operacao[2] == 0) {
                System.out.println("\nL" + (index1 + 1) + " <-> L" + (index2 + 1));
            } else if (index2 == -1) {
                System.out.println("\n" + df.format(k) + " x L" + (index1 + 1) + " -> " + " L" + (index1 + 1));
            } else if (k == 1) {
                System.out.println("\nL" + (index1 + 1) + " + L" + (index2 + 1) + " -> L" + (index1 + 1));
            } else {
                System.out.println("\nL" + (index1 + 1) + " + " + df.format(k) + " x L" + (index2 + 1) + " -> L" + (index1 + 1));
            }
        } 
    }
}

