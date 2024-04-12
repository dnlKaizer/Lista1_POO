import java.util.Scanner;

public class Software {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        Estoque estoque = new Estoque();
        init(estoque);
        listar(estoque);
    }
    
    /* Cadastra 5 produtos automaticamente */
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
            estoque.inserir(p);
        }
    }
    
    /* Listar todos os produtos cadatrados */
    static void listar(Estoque estoque) {
        
        System.out.println();
        System.out.println("1. Por Código");
        System.out.println("2. Alfabética");
        System.out.print("\nDigite a ordem: ");
        int ordem = sc.nextInt();

        Produto[] produtos = estoque.listar();

        switch (ordem) {
            /* Ordem por código (código, nome, preço e quantidade) */
            case 1:
                System.out.println();
                System.out.printf("%6s \t %20s \t %8s \t %s", "CÓDIGO", "NOME", "PREÇO", "QUANTIDADE");
                System.out.println();

                for (int i = 0; i < estoque.lerNProdutos(); i++) {
                    System.out.printf(
                        "%6d \t %20s \t %8f \t %d",
                        produtos[i].lerCodigo(), produtos[i].lerNome(), produtos[i].lerPreco(), produtos[i].lerQuantidade());
                        System.out.println();
                }
                break;

            /* Ordem alfabética (nome, código, preço) */
            case 2:
                
                break;
        
            default:
                System.out.println("Erro.");
                break;
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
