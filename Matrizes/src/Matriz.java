public class Matriz {
    
    int nColunas;
    int nLinhas;
    double[][] indice;

    /**
     * Insere uma matriz ao objeto <code>Matriz</code>. Salva o tamanho das linhas em <em>nLinhas</em> e das colunas em <em>nColunas</em>. 
     * @param matriz de índices */
    void inserirMatriz(double matriz[][]) {
        nLinhas = matriz.length;
        nColunas = matriz[0].length;
        indice = new double[nLinhas][nColunas];

        for (int i = 0; i < nLinhas; i++) {
            for (int j = 0; j < nColunas; j++) {
                indice[i][j] = matriz[i][j];
            }
        }
    }

    /**
     * Retorna uma <em>cópia</em> da matriz do Objeto
     * @return matriz de índices
      */
    double[][] lerMatriz() {
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
    double lerIndice(int i, int j) {
        return indice[i][j];
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
}
