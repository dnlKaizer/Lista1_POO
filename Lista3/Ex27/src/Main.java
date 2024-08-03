public class Main {
    public static void main(String[] args) throws Exception {
        Protozoario protozoario = new Protozoario();
        imprimirVetor(protozoario.getGenotipo());
        protozoario.mutate();
        imprimirVetor(protozoario.getGenotipo());
    }

    static void imprimirVetor(int[] vet) {
        int tam = vet.length;
        System.out.println();
        System.out.print("[");
        for (int i = 0; i < tam; i++) {
            System.out.print(vet[i]);
            if (i != tam - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();
    }
}