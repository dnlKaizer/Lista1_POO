import java.text.DecimalFormat;
import java.util.Random;

public class Software {
    public static void main(String[] args) throws Exception {
        
        Matriz matriz = op.gerarMatrizInvertivel();
        imprimirMatriz(matriz);

        Matriz[] resultante = op.decomposicaoLU(matriz, null);
        if (resultante != null) {
            int tam = resultante.length;
            Matriz result;
            for (int i = 0; i < tam; i++) {
                imprimirMatriz(resultante[i]);
            }
            result = op.multiplicaMatriz(resultante[tam - 1], resultante[tam - 2]);
            if (tam == 3) {
                result = op.multiplicaMatriz(result, resultante[0]);
            }
            imprimirMatriz(result);
        } else {
            System.out.println("Nula");
        }
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
        for (int i = 0; i < processos.length; i++) {
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

