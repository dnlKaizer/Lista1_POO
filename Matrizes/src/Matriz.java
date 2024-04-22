public class Matriz {
    
    int nColunas;
    int nLinhas;
    int[][] indice;

    boolean inserirMatriz(int matriz[][]) {
        if (matriz.length > 0) {
            nLinhas = matriz.length;
            nColunas = matriz[0].length;
        } else {
            return false;
        }
        for (int i = 0; i < nLinhas; i++) {
            for (int j = 0; j < nColunas; j++) {
                indice[i][j] = matriz[i][j];
            }
        }
        return true;
    }

    int[][] lerMatriz() {
        int matriz[][] = new int[nLinhas][nColunas];
        for (int i = 0; i < nLinhas; i++) {
            for (int j = 0; j < nColunas; j++) {
                matriz[i][j] = indice[i][j];
            }
        }
        return matriz;
    }
}
