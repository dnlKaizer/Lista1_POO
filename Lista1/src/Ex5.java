import java.util.Random;
import java.util.Scanner;

public class Ex5 {

    public void empresaCaminhoes() {
        Scanner sc = new Scanner(System.in);

        TCaminhao[] caminhoes = new TCaminhao[10];
        caminhoes = gerarCaminhoes(caminhoes);
        int distancias[][] = gerarDistanciaCidade();

        System.out.println("\nMatriz de Distâncias: ");
        imprimirMatriz(distancias);

        System.out.print("\nDigite o número do caminhão para calcular a distância total: ");
        int numCaminhao = sc.nextInt() - 1;

        int codCidades[] = lerFichaPercurso(caminhoes[numCaminhao]);
        int distanciaTotal = calcularDistancia(caminhoes, numCaminhao, distancias, codCidades);
        
        imprimirCaminhao(caminhoes[numCaminhao]);
        System.out.println("\nDistância total: " + distanciaTotal + "\n");

        sc.close();
    }

    private int calcularDistancia(TCaminhao[] caminhao, int numCaminhao, int[][] distancias, int[] codCidades) {

        int distanciaTotal = 0;
        for (int i = 0; i < caminhao[numCaminhao].numCidades - 1; i++) {
            distanciaTotal += distancias[codCidades[i]][codCidades[i + 1]];
        }

        return distanciaTotal;
    }

    private int[] lerFichaPercurso(TCaminhao caminhao) {
        
        int[] codCidades = new int[caminhao.numCidades];

        for (int i = 0; i < caminhao.numCidades; i++) {
            codCidades[i] = caminhao.codCidades[i];
        }

        return codCidades;
    }

    private int[][] gerarDistanciaCidade() {

        Random random = new Random();
        int tamanhoMatriz = 11;
        int[][] distancias = new int[tamanhoMatriz][tamanhoMatriz];

        for (int i = 1; i < tamanhoMatriz; i++) {
            for (int j = i+1; j < tamanhoMatriz; j++) {
                distancias[i][j] = random.nextInt(19) + 2;
                distancias[j][i] = distancias[i][j];
            }
        }

        return distancias;
    }

    private TCaminhao[] gerarCaminhoes(TCaminhao[] caminhao) {
        
        Random random = new Random();
        for(int i = 0; i < caminhao.length; i++) {
            caminhao[i] = new TCaminhao();
            caminhao[i].numCaminhao = i + 1;
            caminhao[i].numCidades = random.nextInt(5) + 2;
            caminhao[i].codCidades = new int[caminhao[i].numCidades];
            for (int j = 0; j < caminhao[i].numCidades; j++) {
                if (j > 0) {    
                    do {
                        caminhao[i].codCidades[j] = random.nextInt(10) + 1;
                    } while(caminhao[i].codCidades[j] == caminhao[i].codCidades[j - 1]);
                } else {
                    caminhao[i].codCidades[j] = random.nextInt(10) + 1;
                }
            }
        }

        return caminhao;
    }

    private class TCaminhao {
        int numCaminhao;
        int numCidades;
        int[] codCidades;
    }

    private void imprimirCaminhao(TCaminhao caminhao) {

        System.out.println("\nNúmero do Caminhão: " + caminhao.numCaminhao);
        System.out.println("Número de Cidades no Percurso: " + caminhao.numCidades);
        imprimirVetor(caminhao.codCidades);
    }

    private void imprimirMatriz(int matriz[][]) {
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]);
                if(j != matriz[0].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }

    }

    private void imprimirVetor(int vetor[]) {
        for(int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i]);
            if(i != vetor.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
