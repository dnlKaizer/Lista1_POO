import java.text.DecimalFormat;

public class Software {
    public static void main(String[] args) throws Exception {
        
        Matriz matriz = new Matriz();
        double[][] matAux = {{1, 0, 0}, {0, 0.5, 0}, {0, 0, 1}};
        matriz.inserirMatriz(matAux);

        imprimirMatriz(matriz.lerMatriz());
        somarMultiplo(matriz, 0, 1, 2);
    }

    static DecimalFormat df = new DecimalFormat("#,##0.##");

    static Matriz somaMatriz(Matriz m1, Matriz m2) {
        int nLinhas = m1.lerNLinhas();
        int nColunas = m1.lerNColunas();
        if (nLinhas == m2.lerNLinhas() && nColunas == m2.lerNColunas()) {
            double valor;
            Matriz soma = new Matriz();
            soma.inserirTamanho(nLinhas, nColunas);

            for (int i = 0; i < nLinhas; i++) {
                for (int j = 0; j < nColunas; j++) {
                    valor = m1.lerIndice(i, j) + m2.lerIndice(i, j);
                    soma.inserirValor(valor, i, j);
                }
            }
            return soma;
        }
        return null;
    }

    static Matriz multiplicaMatriz(Matriz m1, Matriz m2) {
        if(m1.lerNColunas() == m2.lerNLinhas()) {
            int nLinhas = m1.lerNLinhas();
            int nColunas = m2.lerNColunas();
            double valor;
            Matriz resultado = new Matriz();
            resultado.inserirTamanho(nLinhas, nColunas);

            for (int i = 0; i < nLinhas; i++) {
                for (int j = 0; j < nColunas; j++) {
                    valor = multiplicaVetor(m1.lerLinha(i), m2.lerColuna(j));
                    resultado.inserirValor(valor, i, j);
                }
            }
            return resultado;
        }
        return null;
    }

    static void trocarLinhas(Matriz matriz, int index1, int index2) {
        System.out.println("\nL" + (index1 + 1) + " <-> L" + (index2 + 1) + "\n");
        matriz.trocarLinhas(index1, index2);
        imprimirMatriz(matriz.lerMatriz());
    }

    static void multiplicarEscalar(Matriz matriz, int index, int k) {
        System.out.println("\nL" + (index + 1) + " -> " + df.format(k) + " x L" + (index + 1) + "\n");
        matriz.multiplicarEscalar(index, k);
        imprimirMatriz(matriz.lerMatriz());
    }

    static void somarLinha(Matriz matriz, int index1, int index2) {
        System.out.println("\nL" + (index1 + 1) + " + L" + (index2 + 1) + " -> L" + (index1 + 1) + "\n");
        matriz.somarLinha(index1, index2);
        imprimirMatriz(matriz.lerMatriz());
    }

    static void somarMultiplo(Matriz matriz, int index1, int index2, double k) {
        System.out.println("\nL" + (index1 + 1) + " + " + df.format(k) + " x L" + (index2 + 1) + " -> L" + (index1 + 1) + "\n");
        matriz.somarMultiplo(index1, index2, k);
        imprimirMatriz(matriz.lerMatriz());
    }

    static double multiplicaVetor(double[] vet1, double[] vet2) {
        int tamanho = vet1.length;
        double result = 0;
        for (int i = 0; i < tamanho; i++) {
            result += vet1[i] * vet2[i];
        }
        return result;
    }

    static void imprimirVetor(double vetor[]) {
        System.out.print("[");
        for(int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i]);
            if(i != vetor.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    static void imprimirMatriz(double matriz[][]) {
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                System.out.print(df.format(matriz[i][j]));
                if(j != matriz[0].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
}

