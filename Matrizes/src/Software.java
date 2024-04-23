import java.text.DecimalFormat;

public class Software {
    public static void main(String[] args) throws Exception {
        
        Matriz matriz = new Matriz();
        double[][] matAux = {{1, 0, 0}, {0, 2, 4}, {0, 0, 2}};
        matriz.inserirMatriz(matAux);

        System.out.println();
        imprimirMatriz(matriz);
        multiplicarEscalar(matriz, 1, 0.5);
        multiplicarEscalar(matriz, 2, 0.5);
        somarMultiplo(matriz, 1, 2, -2);
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
    static double multiplicaVetor(double[] vet1, double[] vet2) {
        int tamanho = vet1.length;
        double result = 0;
        for (int i = 0; i < tamanho; i++) {
            result += vet1[i] * vet2[i];
        }
        return result;
    }
    
    static void trocarLinhas(Matriz matriz, int index1, int index2) {
        System.out.println("\nL" + (index1 + 1) + " <-> L" + (index2 + 1) + "\n");
        matriz.trocarLinhas(index1, index2);
        imprimirMatriz(matriz);
    }

    static void multiplicarEscalar(Matriz matriz, int index, double k) {
        System.out.println("\nL" + (index + 1) + " -> " + df.format(k) + " x L" + (index + 1) + "\n");
        matriz.multiplicarEscalar(index, k);
        imprimirMatriz(matriz);
    }

    static void somarLinha(Matriz matriz, int index1, int index2) {
        System.out.println("\nL" + (index1 + 1) + " + L" + (index2 + 1) + " -> L" + (index1 + 1) + "\n");
        matriz.somarLinha(index1, index2);
        imprimirMatriz(matriz);
    }

    static void somarMultiplo(Matriz matriz, int index1, int index2, double k) {
        System.out.println("\nL" + (index1 + 1) + " + " + df.format(k) + " x L" + (index2 + 1) + " -> L" + (index1 + 1) + "\n");
        matriz.somarMultiplo(index1, index2, k);
        imprimirMatriz(matriz);
    }

    static void imprimirMatriz(Matriz matriz) {
        for(int i = 0; i < matriz.lerNLinhas(); i++) {
            for(int j = 0; j < matriz.lerNColunas(); j++) {
                System.out.print(df.format(matriz.lerIndice(i, j)));
                if(j != matriz.lerNColunas() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
}

