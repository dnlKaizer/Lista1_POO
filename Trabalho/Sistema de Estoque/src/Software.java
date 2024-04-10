public class Software {
    public static void main(String[] args) throws Exception {

        // Estoque estoque = new Estoque();
        // Produto p = new Produto();
        
    }

    static void imprimirVetor(Produto[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            try {
                System.out.println(vetor[i].nome);
            } catch (Exception e) {}
        }
    }
}
