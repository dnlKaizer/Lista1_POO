public class Software {
    public static void main(String[] args) throws Exception {
        
        Matriz matriz = new Matriz();
        double[][] matAux = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        matriz.inserirMatriz(matAux);
        System.out.println(matriz.isIdentidade());
        matriz.inserirValor(2, 0, 0);
        System.out.println(matriz.isIdentidade());
    }

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
}

