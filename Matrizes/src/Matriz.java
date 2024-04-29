public class Matriz {
    
    int nColunas;
    int nLinhas;
    double[][] indice;
    double[] operacao = {-1, -1, 0};

    /**,
     * Insere os índices ao objeto <code>Matriz</code>. Salva o tamanho das linhas em <em>nLinhas</em> e das colunas em <em>nColunas</em>. 
     * @param matriz de índices */
    void inserirMatrizDeIndices(double indice[][]) {
        nLinhas = indice.length;
        nColunas = indice[0].length;
        this.indice = new double[nLinhas][nColunas];

        for (int i = 0; i < nLinhas; i++) {
            for (int j = 0; j < nColunas; j++) {
                inserirValor(indice[i][j], i, j);
            }
        }
    }

    /**
     * Insere um valor do tipo <code>double</code> ao índice i, j da matriz
     * @param valor a ser inserido
     * @param i índice da linha
     * @param j índice da coluna
      */
    void inserirValor(double valor, int i, int j) {
        indice[i][j] = valor;
    }

    /**
     * Insere uma linha inteira na matriz
     * @param linha que será inserida
     * @param i index da linha
      */
    void inserirLinha(double[] linha, int i) {
        indice[i] = linha;
    }

    /**
     * Insere uma coluna inteira na matriz
     * @param coluna que será inserida
     * @param j index da coluna
      */
    void inserirColuna(double[] coluna, int j) {
        for (int i = 0; i < nLinhas; i++) {
            indice[i][j] = coluna[i];
        }
    }

    /**
     * Retorna uma <em>cópia</em> da matriz do Objeto
     * @return matriz de índices
      */
    double[][] lerMatrizDeIndices() {
        double matriz[][] = new double[nLinhas][nColunas];
        for (int i = 0; i < nLinhas; i++) {
            for (int j = 0; j < nColunas; j++) {
                matriz[i][j] = indice[i][j];
            }
        }
        return matriz;
    }

    /**
     * Retorna o valor guardado pelos índices i e j.
     * @param i índice da linha
     * @param j índice da coluna 
     * @return valor do índice
      */
    double lerValor(int i, int j) {
        return indice[i][j];
    }

    double[] lerOperacao() {
        return operacao;
    }
    void inserirOperacao(double[] operacao) {
        for (int i = 0; i < operacao.length; i++) {
            this.operacao[i] = operacao[i];
        }
    }

    /**
     * Retorna uma linha inteira da matriz
     * @param i índice da linha 
     * @return vetor linha
      */
    double[] lerLinha(int i) {
        double[] vetAux = new double[nColunas];
        for (int j = 0; j < nColunas; j++) {
            vetAux[j] = indice[i][j];
        }
        return vetAux;
    }

    /**
     * Retorna uma coluna inteira da matriz
     * @param j índice da coluna
     * @return vetor coluna
      */
    double[] lerColuna(int j) {
        double[] vetAux = new double[nLinhas];
        for (int i = 0; i < nLinhas; i++) {
            vetAux[i] = indice[i][j];
        }
        return vetAux;
    }

    /**
     * Declara o tamanho da matriz de índices
     * @param nLinhas
     * @param nColunas
      */
    void inserirTamanho(int nLinhas, int nColunas) {
        this.nLinhas = nLinhas;
        this.nColunas = nColunas;
        indice = new double[nLinhas][nColunas];
    }

    /**
     * Método que retorna o número <code>int</code> de linhas da matriz
     * @return número de linhas da matriz
      */
    int lerNLinhas() {
        return nLinhas;
    }
    
    /**
     * Método que retorna o número <code>int</code> de colunas da matriz
     * @return número de colunas da matriz
      */
    int lerNColunas() {
        return nColunas;
    }

    /**
     * Retorna se a matriz é quadrada ou não
     * @return <code>true</code> se for quadrada, e <code>false</code> caso não seja
     */
    boolean isQuadrada() {
        if(nLinhas == nColunas) {
            return true;
        }
        return false;
    }

    /**
     * Retorna se a matriz é triangualar inferior ou não
     * @return <code>true</code> se for triangular inferior, e <code>false</code> caso não seja
      */
    boolean isTriangularInferior() {
        if(isQuadrada()) {
            for (int i = 0; i < nLinhas; i++) {
                for (int j = i + 1; j < nColunas; j++) {
                    if (indice[i][j] != 0) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * Retorna se a matriz é triangualar superior ou não
     * @return <code>true</code> se for triangular superior, e <code>false</code> caso não seja
      */
    boolean isTriangularSuperior() {
        if(isQuadrada()) {
            for (int i = nColunas - 1; i >= 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    if (indice[i][j] != 0) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Retorna se a matriz é triangular ou não
     * @return <code>true</code> se for triangular, e <code>false</code> caso não seja
      */
    boolean isTriangular() {
        if(isTriangularSuperior() || isTriangularInferior()) {
            return true;
        }
        return false;
    }

    /**
     * Retorna se a matriz é diagonal ou não
     * @return <code>true</code> se for diagonal, e <code>false</code> caso não seja
      */
    boolean isDiagonal() {
        if(isTriangularSuperior() && isTriangularInferior()) {
            return true;
        }
        return false;
    }

    /**
     * Retorna se a matriz é identidade ou não
     * @return <code>true</code> se for identidade, e <code>false</code> caso não seja
      */
    boolean isIdentidade() {
        if(isDiagonal()) {
            for (int i = 0; i < nLinhas; i++) {
                if (indice[i][i] != 1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Operação elementar de troca de linhas
     * @param index1 index da linha 1
     * @param index2 index da linha 2
      */
    void trocarLinhas(int index1, int index2) {
        double[] linhaM = lerLinha(index1);
        indice[index1] = lerLinha(index2);
        indice[index2] = linhaM;
        double[] operacao = {index1, index2, -1};
        inserirOperacao(operacao);
    }

    /**
     * Operação elementar de multiplicar por escalar
     * @param index da linha que será multiplicada
     * @param k escalar
      */
    void multiplicarEscalar(int index, double k) {
        inserirLinha(
            retornaMultiploLinha(index, k),
            index);
        double[] operacao = {index, -1, k};
        inserirOperacao(operacao);
    }

    /**
     * Operação elementar de soma de linhas
     * @param index1 index da linha 1 (linha que será alterada)
     * @param index2 index da linha 2
      */
    void somarLinha(int index1, int index2) {
        inserirLinha(
            retornaSomaLinhas(index1, lerLinha(index2)),
            index1);
        double[] operacao = {index1, index2, 1};
        inserirOperacao(operacao);
    }

    /**
     * Operação elementar de soma pelo múltiplo de uma linha
     * @param index1 index da linha 1 (linha que será alterada)
     * @param index2 index da linha 2
     * @param k múltiplo
      */
    void somarMultiplo(int index1, int index2, double k) {
        inserirLinha(
            retornaSomaLinhas(index1, retornaMultiploLinha(index2, k)),
            index1);
        double[] operacao = {index1, index2, k};
        inserirOperacao(operacao);
    }

    /**
     * Adiciona uma nova coluna na matriz
     * @param coluna a ser inserida
     * @param index onde será inserida a coluna. Caso seja <code>-1</code>, é inserida automaticamente ao final da matriz
      */
    void adicionarNovaColuna(double[] coluna, int index) {
        if (index == -1) {
            index = nColunas;
        }

        Matriz matAux = new Matriz();
        matAux.inserirTamanho(nLinhas, nColunas + 1);

        matAux.inserirColuna(coluna, index);
        for (int j = index - 1; j >= 0; j--) {
            matAux.inserirColuna(lerColuna(j), j);
        }

        if (index <= nColunas) {
            for (int j = index + 1; j < nColunas + 1; j++) {
                matAux.inserirColuna(lerColuna(j - 1), j);
            }
        }

        inserirMatrizDeIndices(matAux.lerMatrizDeIndices());
    }

    /**
     * Adiciona uma nova linha na matriz
     * @param linha a ser inserida
     * @param index onde será inserida a linha. Caso seja <code>-1</code>, é inserida automaticamente ao final da matriz
      */
    void adicionarNovaLinha(double[] linha, int index) {
        if (index == -1) {
            index = nLinhas;
        }

        Matriz matAux = new Matriz();
        matAux.inserirTamanho(nLinhas + 1, nColunas);

        matAux.inserirLinha(linha, index);
        for (int i = index - 1; i >= 0; i--) {
            matAux.inserirLinha(lerLinha(i), i);
        }

        if (index <= nLinhas) {
            for (int i = index + 1; i < nLinhas + 1; i++) {
                matAux.inserirLinha(lerLinha(i - 1), i);
            }
        }

        inserirMatrizDeIndices(matAux.lerMatrizDeIndices());
    }

    private double[] retornaSomaLinhas(int index1, double[] linha2) {
        double[] linha1 = lerLinha(index1);
        for (int i = 0; i < nColunas; i++) {
            linha1[i] += linha2[i];
        }
        return linha1;
    }

    private double[] retornaMultiploLinha(int index, double k) {
        double[] linha = lerLinha(index);
        for (int i = 0; i < nColunas; i++) {
            linha[i] *= k;
        }
        return linha;
    }
}
