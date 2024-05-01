import java.util.Random;

public class Operar {

    int numMatrizes = 0;
    Matriz[] matrizes = new Matriz[0];
    Random random = new Random();

    /**
     * Adiciona uma matriz ao objeto Operar
     * @param matriz a ser inserida
     * @return <code>true</code> se o parâmetro for <code>not null</code>, <code>false</code> se o parâmetro for <code>null</code>
      */
    boolean adicionarMatriz(Matriz matriz) {
        if (matriz != null) {
            matrizes = adicionaVetor(matrizes, matriz);
            numMatrizes++;
            return true;
        }
        return false;
    }

    /**
     * Lê uma matriz do vetor de matrizes
     * @param index
     * @return
      */
    Matriz lerMatriz(int index) {
        if(index < numMatrizes && index >= 0) {
            return matrizes[index];
        }
        return null;
    }

    /**
     * Soma duas matrizes do tipo <code>Matriz</code>.
     * @param matriz1 matriz 1
     * @param matriz2 matriz 2
     * @return <code>null</code> se não podem ser somadas, ou a <code>Matriz</code> resultante
      */
    Matriz soma(Matriz matriz1, Matriz matriz2) {
        if (matriz1 == null || matriz2 == null) {
            return null;
        }

        int nLinhas = matriz1.lerNLinhas();
        int nColunas = matriz1.lerNColunas();

        if (nLinhas == matriz2.lerNLinhas() && nColunas == matriz2.lerNColunas()) {
            double valor;
            Matriz soma = new Matriz();
            soma.inserirTamanho(nLinhas, nColunas);

            for (int i = 0; i < nLinhas; i++) {
                for (int j = 0; j < nColunas; j++) {
                    valor = matriz1.lerValor(i, j) + matriz2.lerValor(i, j);
                    soma.inserirValor(valor, i, j);
                }
            }
            return soma;
        }
        return null;
    }

    /**
     * Multiplica duas matrizes do tipo <code>Matriz</code>
     * @param matriz1 matriz 1
     * @param matriz2 matriz 2
     * @return <code>null</code> se não podem ser multplicadas, ou a <code>Matriz</code> resultante
      */
    Matriz multiplicaMatriz(Matriz matriz1, Matriz matriz2) {

        if (matriz1 != null && matriz2 != null && matriz1.lerNColunas() == matriz2.lerNLinhas()) {
            int nLinhas = matriz1.lerNLinhas();
            int nColunas = matriz2.lerNColunas();
            double valor;
            Matriz resultado = new Matriz();
            resultado.inserirTamanho(nLinhas, nColunas);

            for (int i = 0; i < nLinhas; i++) {
                for (int j = 0; j < nColunas; j++) {
                    valor = multiplicaVetor(matriz1.lerLinha(i), matriz2.lerColuna(j));
                    resultado.inserirValor(valor, i, j);
                }
            }
            return resultado;
        }

        return null;
    }
    
    /**
     * Realiza a eliminação gaussiana de qualquer matriz. Não altera a matriz original.
     * @param matriz1
     * @return nova matriz da forma escalonada
      */
    Matriz[] eliminacaoGaussiana(Matriz matrizOriginal) {
        if (matrizOriginal == null) {
            return null;
        }

        Matriz[] processos = new Matriz[0];
        Matriz matriz = new Matriz();
        matriz.copiarMatriz(matrizOriginal);
        int nLinhas = matriz.lerNLinhas();
        int nColunas = matriz.lerNColunas();
        int indexPivo = 0;
        int nPivos = 0;
        boolean temNaoNulo;
        int index1;
        int index2;
        double k;
        
        processos = adicionaVetor(processos, matriz);

        VerificarColuna:
        for (int j = 0; j < nColunas; j++) {
            temNaoNulo = false;

            // Achar Pivô
            indexPivo = nPivos;
            for (int i = nPivos; i < nLinhas; i++) {
                if (Math.abs(matriz.lerValor(i, j)) == 1) {
                    indexPivo = i;
                    if (matriz.lerValor(i, j) == -1) {
                        index1 = indexPivo;
                        k = -1;
                        matriz.multiplicarEscalar(index1, k);
                        processos = adicionaVetor(processos, matriz);
                    }
                    if (nPivos != indexPivo) {
                        index1 = indexPivo;
                        index2 = nPivos;
                        matriz.trocarLinhas(index1, index2);
                        processos = adicionaVetor(processos, matriz);
                        indexPivo = nPivos;
                    }
                    nPivos++;
                    break;
                } else {
                    if (!temNaoNulo) {
                        if (matriz.lerValor(i, j) != 0) {
                            temNaoNulo = true;
                            indexPivo = i;
                        }
                    }
                }
                
                if (i == nLinhas - 1) {
                    if (temNaoNulo) {
                        index1 = indexPivo;
                        k = 1 / matriz.lerValor(indexPivo, j);
                        matriz.multiplicarEscalar(index1, k);
                        processos = adicionaVetor(processos, matriz);
                        if (nPivos != indexPivo) {
                            index1 = indexPivo;
                            index2 = nPivos;
                            matriz.trocarLinhas(index1, index2);
                            processos = adicionaVetor(processos, matriz);
                            indexPivo = nPivos;
                        }
                        nPivos++;
                    } else {
                        continue VerificarColuna;
                    }
                }
            }
            
            for (int i = nPivos; i < nLinhas; i++) {
                if (matriz.lerValor(i, j) != 0) {
                    index1 = i;
                    index2 = indexPivo;
                    k =  (matriz.lerValor(i, j)) * (-1);
                    matriz.somarMultiplo(index1, index2, k);
                    processos = adicionaVetor(processos, matriz);
                }
            }
        }
        return processos;
    } 

    /**
     * Realiza o método de Gauss-Jordan a qualquer matriz. Não altera a matriz original.
     * @param matriz1
     * @return nova matriz da forma escalonada
      */
    Matriz[] metodoGaussJordan(Matriz matrizOriginal) {
        Matriz[] processos = eliminacaoGaussiana(matrizOriginal);
        Matriz matriz = new Matriz();
        matriz.inserirMatrizDeIndices(processos[processos.length - 1].lerMatrizDeIndices());
        int nLinhas = matriz.lerNLinhas();
        int nColunas = matriz.lerNColunas();

        AcharPivo:
        for (int i = nLinhas - 1; i >= 0; i--) {
            for (int j = 0; j < nColunas; j++) {
                if (matriz.lerValor(i, j) == 1) {
                    for (int index = i - 1; index >= 0; index--) {
                        if (matriz.lerValor(index, j) != 0) {
                            matriz.somarMultiplo(index, i, (matriz.lerValor(index, j)) * (-1));
                            processos = adicionaVetor(processos, matriz);
                        }
                    }
                    continue AcharPivo;
                }
            }
        }
        return processos;
    }

    /**
     * Realiza a decomposição LU de uma matriz. Caso o parâmetro seja uma matriz do tipo n x (n+1), ou seja, uma matriz aumentada, 
     * @param matrizOriginal matriz quadrada ou matriz aumentada
     * @return retorna um vetor que contém 2 matrizes, sendo a primeira a matriz U e a segunda a matriz L. Caso o parâmetro seja uma matriz aumentada, a primeira matriz do vetor passa a ser a matriz [ U | y ], sendo o y o vetor y = Ux. Explicação: Ax = b -> LUx = b -> L<strong>y</strong> = b; de modo que y = Ux
      */
    Matriz[] decomposicaoLU(Matriz matrizOriginal, Matriz matrizP) {
        if (matrizOriginal == null) {
            return null;
        }

        Matriz matrizUy = new Matriz();
        Matriz matrizL = new Matriz();

        matrizUy.copiarMatriz(matrizOriginal);
        int nLinhas = matrizUy.lerNLinhas();
        int nPivos = 0;
        int indexPivo = 0;
        boolean temNaoNulo;
        double k;
        matrizL.inserirTamanho(nLinhas, nLinhas);

        Geral:
        for (int j = 0; j < nLinhas; j++) {
            temNaoNulo = false;
            indexPivo = nPivos;
            k = (matrizUy.lerValor(indexPivo, j));
            // Verifica se será necessário trocar linhas
            if (k != 0) {
                // Introduzir pivô
                matrizUy.multiplicarEscalar(indexPivo, 1/k);
                matrizL.inserirValor(k, indexPivo, j);
                nPivos++;
    
                // Zerar entradas abaixo do pivô
                for (int i = nPivos; i < nLinhas; i++) {
                    k = matrizUy.lerValor(i, j);
                    matrizUy.somarMultiplo(i, indexPivo, k * (-1));
                    matrizL.inserirValor(k, i, j);
                }
            } else {
                int indexLinha = -1;
                // Verifica se tem algum número não nulo na coluna
                for (int i = nPivos + 1; i < nLinhas && !temNaoNulo; i++) {
                    if (matrizUy.lerValor(i, j) != 0) {
                        temNaoNulo = true;
                        indexLinha = i;
                    }
                }

                if (temNaoNulo) { // Caso em que será necessário calcular PLU
                    int[][] trocas = new int[0][2];
                    if (matrizP != null) {
                        trocas = matrizP.lerTrocas();
                    } else {
                        matrizP = new Matriz();
                    }

                    matrizP.inserirMatrizDeIndices(gerarIdentidade(nLinhas).lerMatrizDeIndices());
                    matrizP.trocarLinhas(indexPivo, indexLinha);
                    matrizP.adicionarTroca(indexPivo, indexLinha);

                    for (int i = trocas.length - 1; i >= 0; i--) {
                        matrizP.trocarLinhas(trocas[i][0], trocas[i][1]);
                    }
                    
                    matrizOriginal.trocarLinhas(indexPivo, indexLinha);

                    Matriz[] aux = decomposicaoLU(matrizOriginal, matrizP);
                    if (aux == null) {
                        return null;
                    }
                    matrizUy = aux[0];
                    matrizL = aux[1];
                    matrizP = aux[2];
                    // matrizP.inserirTrocas(aux[2].lerTrocas());
                    break Geral;

                } else { // Caso em que a matriz não é invertível
                    return null;
                }
            }
        }
        double[] operacao = {-1, -1, 0};
        Matriz[] resultante;
        if (matrizP != null) {
            resultante = new Matriz[3];
            resultante[2] = matrizP;
        } else {
            resultante = new Matriz[2];
        }
        resultante[0] = matrizUy;
        resultante[0].inserirOperacao(operacao);
        resultante[1] = matrizL;
        resultante[1].inserirOperacao(operacao);

        return resultante;
    }

    double determinante(Matriz matriz) {
        double result = 1;
        if (matriz.isTriangular()) {
            double[] diagonalPrincipal = matriz.lerDiagonalPrincipal();
            for (double valor : diagonalPrincipal) {
                result *= valor;
            }
        } else {
            Matriz[] matrizFatorada = decomposicaoLU(matriz, null);
            if (matrizFatorada == null) {
                return 0;
            }
            result *= determinante(matrizFatorada[1]);
            if (matrizFatorada.length == 3) {
                result *= Math.pow(-1, matrizFatorada[2].lerTrocas().length);
            }
            
        }
        return result;
    }

    /**
     * Gera uma matriz identidade
     * @param tamanho da matriz
     * @return matriz identidade
      */
    Matriz gerarIdentidade(int tamanho) {
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
     * Gera uma matriz invertível, de tamanho entre 2 e 5.
     * @return matriz invertível
      */
    Matriz gerarMatrizInvertivel() {
        int tamanho = 100 /* + random.nextInt(4) */;
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
     * Gera vetor com entradas no intervalo [-10, 10]
     * @param tamanho do vetor
     * @return vetor gerado
      */
    double[] geraVetor(int tamanho) {
        double[] vetor = new double[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(21) - 10;
        }
        return vetor;
    }

    /**
     * Adiciona um objeto <code>Matriz</code> ao vetor[] de <code>Matriz</code>. Faz uma cópia do vetor original, porém, com tamanho maior em 1 unidade, onde será armazenado o novo objeto.
     * @param vetor de matrizes
     * @param matriz a ser adicionada
     * @return novo vetor com a matriz adicionada
      */
    private Matriz[] adicionaVetor(Matriz[] vetor, Matriz matriz) {
        int tamanho = vetor.length;
        Matriz[] novoVetor = new Matriz[tamanho + 1];

        for (int i = 0; i < tamanho; i++) {
            novoVetor[i] = vetor[i];
        }
        novoVetor[tamanho] = new Matriz();
        novoVetor[tamanho].copiarMatriz(matriz);
        return novoVetor;
    }

    /**
     * Multiplica dois vetores do tipo <code>double</code>. <strong>Não funciona</strong> para vetores de tamanhos diferentes.
     * @param vet1 vetor 1
     * @param vet2 vetor 2
     * @return resultado <code>double</code>
      */
    private double multiplicaVetor(double[] vet1, double[] vet2) {
        int tamanho = vet1.length;
        double result = 0;
        for (int i = 0; i < tamanho; i++) {
            result += vet1[i] * vet2[i];
        }
        return result;
    }
}
