package Ex10;

public class Ponto {
    double[] ponto = new double[2];

    void adicionarPonto(double[] ponto) {
        this.ponto = ponto;
    }

    double[] negativo() {
        try {
            double pontoNegativo[] = new double[2];
            pontoNegativo[0] = ponto[0] * (-1);
            pontoNegativo[1] = ponto[1] * (-1);
            return pontoNegativo;
        } catch (Exception exception) {
            return null;
        }
    }

    double distancia() {
        double distancia = (double)Math.pow(ponto[0], 2) + (double)Math.pow(ponto[1], 2);
        distancia = (double)Math.sqrt(distancia);
        return distancia;   
    }

    void print() {
        System.out.println("(" + ponto[0] + ", " + ponto[1] +")");
    }
    void printNegativo() {
        ponto = negativo();
        System.out.println("(" + ponto[0] + ", " + ponto[1] +")");
    }
}
