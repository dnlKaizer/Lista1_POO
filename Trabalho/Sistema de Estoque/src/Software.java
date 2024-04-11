import java.text.DecimalFormat;
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
    
    /* Listar todos os produtos cadatrados por código */
    static void listar(Estoque estoque) {
        
        System.out.println();
        System.out.println("1. Por Código");
        System.out.println("2. Alfabética");
        System.out.print("\nDigite a ordem: ");
        int ordem = sc.nextInt();

        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        int nProdutos = estoque.lerNProdutos();
        Produto[] produtos;
        String linhaMenu = "----------------------------------------------------";
        
        switch (ordem) {
            /* Ordem por código (código, nome, preço e quantidade) */
            case 1:
                produtos = estoque.listarTodos();

                linhaMenu += "---------------";
                System.out.println();
                System.out.println(linhaMenu);
                System.out.printf("|  %-6s  |  %-20s  |  %-8s    |  %-10s  |", 
                "CÓDIGO", "NOME", "PREÇO", "QUANTIDADE");
                System.out.println();
                System.out.println(linhaMenu);
                
                for (int i = 0; i < nProdutos; i++) {
                    System.out.printf(
                        "|  %-6d  |  %-20s  |  R$%8s  |  %-10d  |",
                        produtos[i].lerCodigo(), produtos[i].lerNome(),
                        moeda.format(produtos[i].lerPreco()),produtos[i].lerQuantidade());
                        System.out.println();
                }

                System.out.println(linhaMenu);
                break;

            /* Ordem alfabética (nome, código, preço) */
            case 2:
                produtos = estoque.listarPorNome();

                System.out.println();
                System.out.println(linhaMenu);
                System.out.printf("|  %-20s  |  %-6s  |  %-8s    |", 
                "NOME", "CÓDIGO", "PREÇO");
                System.out.println();
                System.out.println(linhaMenu);
                
                for (int i = 0; i < nProdutos; i++) {
                    System.out.printf(
                        "|  %-20s  |  %-6d  |  R$%8s  |",
                        produtos[i].lerNome(), produtos[i].lerCodigo(),
                        moeda.format(produtos[i].lerPreco()));
                        System.out.println();
                }

                System.out.println(linhaMenu);
                break;
        
            default:

                System.out.println("\nERRO\n");
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
