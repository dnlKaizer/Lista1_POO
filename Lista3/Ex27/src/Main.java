public class Main {
    public static void main(String[] args) throws Exception {
        Protozoario protozoario = Protozoario.getInstance();
        Protozoario parceiro = Protozoario.getInstance();
        Protozoario mate = protozoario.mate(parceiro);
        Protozoario clone = protozoario.getClone();

        System.out.println(protozoario.toString());
        System.out.println(parceiro.toString());
        System.out.println(mate.toString());
        System.out.println(clone.toString());
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