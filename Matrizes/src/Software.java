import java.text.DecimalFormat;
import java.util.Random;

public class Software {
    public static void main(String[] args) throws Exception {
        
        // double[][] matIndices = {{1,2},{3,4}};
        Matriz matriz = new Matriz();
        // matriz.inserirMatrizDeIndices(matIndices);
        matriz = op.gerarMatrizInvertivel();
        Matriz inversa = op.inversa(matriz);
        imprimirMatriz(matriz);
        imprimirMatriz(inversa);
        imprimirMatriz(op.multiplicaMatriz(matriz, inversa));
        imprimirMatriz(op.multiplicaMatriz(inversa, matriz));
    }

    static Operar op = new Operar();

    static DecimalFormat df = new DecimalFormat("#,##0.##");

    static Random random = new Random();

    static void imprimirMatriz(Matriz matriz) {
        if (matriz == null) {
            return;
        }
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
        imprimirMatriz(processos[0]);
        for (int i = 1; i < processos.length; i++) {
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

