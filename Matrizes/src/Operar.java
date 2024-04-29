public class Operar {
    
    /**
     * Soma duas matrizes do tipo <code>Matriz</code>.
     * @param m1 matriz 1
     * @param m2 matriz 2
     * @return <code>null</code> se não podem ser somadas, ou a <code>Matriz</code> resultante
      */
    Matriz soma(Matriz m1, Matriz m2) {
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
    Matriz multiplicaMatriz(Matriz m1, Matriz m2) {
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
    double multiplicaVetor(double[] vet1, double[] vet2) {
        int tamanho = vet1.length;
        double result = 0;
        for (int i = 0; i < tamanho; i++) {
            result += vet1[i] * vet2[i];
        }
        return result;
    }

    /**
     * Realiza a eliminação gaussiana de qualquer matriz. Não altera a matriz original.
     * @param matrizOriginal
     * @return nova matriz da forma escalonada
      */
    Matriz[] eliminacaoGaussiana(Matriz matrizOriginal) {
        Matriz[] processos = new Matriz[0];
        Matriz matriz = new Matriz();
        matriz.inserirMatriz(matrizOriginal.lerMatriz());
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
                if (Math.abs(matriz.lerIndice(i, j)) == 1) {
                    indexPivo = i;
                    if (matriz.lerIndice(i, j) == -1) {
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
                        if (matriz.lerIndice(i, j) != 0) {
                            temNaoNulo = true;
                            indexPivo = i;
                        }
                    }
                }

                if (i == nLinhas - 1) {
                    if (temNaoNulo) {
                        index1 = indexPivo;
                        k = 1 / matriz.lerIndice(indexPivo, j);
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
            
            // Zerar entradas abaixo do pivô
            for (int i = nPivos; i < nLinhas; i++) {
                if (matriz.lerIndice(i, j) != 0) {
                    index1 = i;
                    index2 = indexPivo;
                    k =  (matriz.lerIndice(i, j)) * (-1);
                    matriz.somarMultiplo(index1, index2, k);
                    processos = adicionaVetor(processos, matriz);
                }
            }
        }
        return processos;
    } 

    /**
     * Realiza o método de Gauss-Jordan a qualquer matriz. Não altera a matriz original.
     * @param matrizOriginal
     * @return nova matriz da forma escalonada
      */
    Matriz[] metodoGaussJordan(Matriz matrizOriginal) {
        Matriz[] processos = eliminacaoGaussiana(matrizOriginal);
        Matriz matriz = new Matriz();
        matriz.inserirMatriz(processos[processos.length - 1].lerMatriz());
        int nLinhas = matriz.lerNLinhas();
        int nColunas = matriz.lerNColunas();

        AcharPivo:
        for (int i = nLinhas - 1; i >= 0; i--) {
            for (int j = 0; j < nColunas; j++) {
                if (matriz.lerIndice(i, j) == 1) {
                    for (int index = i - 1; index >= 0; index--) {
                        if (matriz.lerIndice(index, j) != 0) {
                            matriz.somarMultiplo(index, i, (matriz.lerIndice(index, j)) * (-1));
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
     * Adiciona um objeto <code>Matriz</code> ao vetor[] de <code>Matriz</code>. Faz uma cópia do vetor original, porém, com tamanho maior em 1 unidade, onde será armazenado o novo objeto.
     * @param vetor de matrizes
     * @param matriz a ser adicionada
     * @return novo vetor com a matriz adicionada
      */
    Matriz[] adicionaVetor(Matriz[] vetor, Matriz matriz) {
        int tamanho = vetor.length;
        Matriz[] novoVetor = new Matriz[tamanho + 1];

        for (int i = 0; i < tamanho; i++) {
            novoVetor[i] = vetor[i];
        }
        novoVetor[tamanho] = new Matriz();
        novoVetor[tamanho].inserirMatriz(matriz.lerMatriz());
        novoVetor[tamanho].inserirOperacao(matriz.lerOperacao());
        return novoVetor;
    }
}
