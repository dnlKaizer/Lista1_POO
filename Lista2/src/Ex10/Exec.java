package Ex10;

import java.util.Scanner;

public class Exec {
    public void testarPonto() {
        Scanner sc = new Scanner(System.in);
        Ponto ponto = new Ponto();
        double[] var = new double[2];

        System.out.println("\nDigite as coordenadas a seguir:");
        System.out.print("x: ");
        var[0] = sc.nextDouble();
        System.out.print("y: ");
        var[1] = sc.nextDouble();

        ponto.adicionarPonto(var);
        System.out.print("Ponto: ");
        ponto.print();
        System.out.print("Negativo: ");
        ponto.printNegativo();
        System.out.println("Distância: " + ponto.distancia());

        sc.close();
    }
    
}
