import java.text.DecimalFormat;
import java.util.Random;

public class Software {
    public static void main(String[] args) throws Exception {
        
        // Matriz matriz = new Matriz();
        // double[][] matAux = {{1, 3, 2, 5}, {6, 5, 4, 8}, {1, 8, 5, 3}};
        // matriz.inserirMatriz(matAux);
        Matriz matriz = gerarMatrizInvertivel();
        double[] coluna = geraVetor(matriz.lerNLinhas());

        matriz.adicionarNovaColuna(coluna, -1);
        metodoGaussJordan(matriz);
    }

    static DecimalFormat df = new DecimalFormat("#,##0.##");

    static Random random = new Random();


    /**
     * Soma duas matrizes do tipo <code>Matriz</code>.
     * @param m1 matriz 1
     * @param m2 matriz 2
     * @return <code>null</code> se não podem ser somadas, ou a <code>Matriz</code> resultante
      */
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

    /**
     * Multiplica duas matrizes do tipo <code>Matriz</code>
     * @param m1 matriz 1
     * @param m2 matriz 2
     * @return <code>null</code> se não podem ser multplicadas, ou a <code>Matriz</code> resultante
      */
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
    /**
     * Multiplica dois vetores do tipo <code>double</code>. <strong>Não funciona</strong> para vetores de tamanhos diferentes.
     * @param vet1 vetor 1
     * @param vet2 vetor 2
     * @return resultado <code>double</code>
      */
    static double multiplicaVetor(double[] vet1, double[] vet2) {
        int tamanho = vet1.length;
        double result = 0;
        for (int i = 0; i < tamanho; i++) {
            result += vet1[i] * vet2[i];
        }
        return result;
    }

    /**
     * Imprime os processos para eliminação gaussiana da matriz. <strong>Não altera</strong> a matriz original.
     * @param matrizOriginal matriz a ser escalonada
     * @return matriz resultante da eliminação
      */
    static Matriz eliminacaoGaussiana(Matriz matrizOriginal) {
        Matriz matriz = new Matriz();
        matriz.inserirMatriz(matrizOriginal.lerMatriz());
        int nLinhas = matriz.lerNLinhas();
        int nColunas = matriz.lerNColunas();
        int indexPivo = 0;
        int nPivos = 0;
        boolean temNaoNulo;
        
        imprimirMatriz(matriz);

        VerificarColuna:
        for (int j = 0; j < nColunas; j++) {
            temNaoNulo = false;

            // Achar Pivô
            indexPivo = nPivos;
            for (int i = nPivos; i < nLinhas; i++) {
                if (Math.abs(matriz.lerIndice(i, j)) == 1) {
                    indexPivo = i;
                    if (matriz.lerIndice(i, j) == -1) {
                        multiplicarLinhaPorEscalar(matriz, indexPivo, -1);
                    }
                    if (nPivos != indexPivo) {
                        trocarLinhas(matriz, indexPivo, nPivos);
                        indexPivo = nPivos;
                    }
                    nPivos++;
                    break;
                } else {
                    if (!temNaoNulo) {
                        if (matriz.lerIndice(i, j) != 0) {
                            temNaoNulo = true;
                            indexPivo = i;
                        }
                    }
                }

                if (i == nLinhas - 1) {
                    if (temNaoNulo) {
                        multiplicarLinhaPorEscalar(matriz, indexPivo, (1 / matriz.lerIndice(indexPivo, j)));
                        if (nPivos != indexPivo) {
                            trocarLinhas(matriz, indexPivo, nPivos);
                            indexPivo = nPivos;
                        }
                        nPivos++;
                    } else {
                        continue VerificarColuna;
                    }
                }
            }

            // Zerar entradas abaixo do pivô
            for (int i = nPivos; i < nLinhas; i++) {
                if (matriz.lerIndice(i, j) != 0) {
                    somarMultiploLinha(matriz, i, indexPivo, (matriz.lerIndice(i, j)) * (-1));
                }
            }
        }

        return matriz;
    }   

    /**
     * Imprime os processos para eliminação de Gauss-Jordan da matriz. <strong>Não altera</strong> a matriz original.
     * @param matrizOriginal matriz a ser escalonada reduzida por linhas
      */
    static void metodoGaussJordan(Matriz matrizOriginal) {
        Matriz matriz = eliminacaoGaussiana(matrizOriginal);
        int nLinhas = matriz.lerNLinhas();
        int nColunas = matriz.lerNColunas();

        AcharPivo:
        for (int i = nLinhas - 1; i >= 0; i--) {
            for (int j = 0; j < nColunas; j++) {
                if (matriz.lerIndice(i, j) == 1) {
                    for (int index = i - 1; index >= 0; index--) {
                        if (matriz.lerIndice(index, j) != 0) {
                            somarMultiploLinha(matriz, index, i, (matriz.lerIndice(index, j)) * (-1));
                        }
                    }
                    continue AcharPivo;
                }
            }
        }
    }
    
    /**
     * Imprime o processo de troca de linha da matriz
     * @param matriz
     * @param index1 index da primeira linha
     * @param index2 index da segunda linha
      */
    static void trocarLinhas(Matriz matriz, int index1, int index2) {
        System.out.println("\nL" + (index1 + 1) + " <-> L" + (index2 + 1));
        matriz.trocarLinhas(index1, index2);
        imprimirMatriz(matriz);
    }

    /**
     * Imprime o processo de multiplicar linha por escalar de uma matriz
     * @param matriz
     * @param index index da linha
     * @param k escalar
      */
    static void multiplicarLinhaPorEscalar(Matriz matriz, int index, double k) {
        System.out.println("\nL" + (index + 1) + " -> " + df.format(k) + " x L" + (index + 1));
        matriz.multiplicarEscalar(index, k);
        imprimirMatriz(matriz);
    }

    /**
     * Imprime o processo de soma de duas linhas da matriz
     * @param matriz
     * @param index1 index da linha que será alterada
     * @param index2 index da linha que será somada
      */
    static void somarLinhas(Matriz matriz, int index1, int index2) {
        System.out.println("\nL" + (index1 + 1) + " + L" + (index2 + 1) + " -> L" + (index1 + 1));
        matriz.somarLinha(index1, index2);
        imprimirMatriz(matriz);
    }


    /**
     * Imprime o processo de somar uma linha com múltiplo de outra
     * @param matriz
     * @param index1 index da linha que será alterada 
     * @param index2 index da linha que será somada
     * @param k escalar
      */
    static void somarMultiploLinha(Matriz matriz, int index1, int index2, double k) {
        System.out.println("\nL" + (index1 + 1) + " + " + df.format(k) + " x L" + (index2 + 1) + " -> L" + (index1 + 1));
        matriz.somarMultiplo(index1, index2, k);
        imprimirMatriz(matriz);
    }

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
                number = matriz.lerIndice(i, j);
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
}

