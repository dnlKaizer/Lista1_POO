public class Software {
    public static void main(String[] args) throws Exception {

        Estoque estoque = new Estoque();
        init(estoque);
    }

    static void init(Estoque estoque) {
        Produto p = new Produto();

        String nomes[] = {"Arroz", "Feijão", "Batata Frita", "Picanha Suína", "Suco de Laranja"};
        String marcas[] = {"PratoFino", "Camil", "BemBrasil", "Saudali", "Del Valle"};
        float precos[] = {11.99f, 8.89f, 41.49f, 29.99f, 4.29f};
        
        for (int i = 0; i < 5; i++) {
            p.addNome(nomes[i]);
            p.addMarca(marcas[i]);
            p.addPreco(precos[i]);
            p.addQuantidade(20);
            if (estoque.inserir(p)) {
                System.out.println(p.lerNome() + " inserido.");
            } else {
                System.out.println("Falha ao inserir.");
            }
        }
    }

    static void imprimirVetor(Produto[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            try {
                System.out.println(vetor[i].nome);
            } catch (Exception e) {

            }
        }
    }
}
