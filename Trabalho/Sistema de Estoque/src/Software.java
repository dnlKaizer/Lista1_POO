import java.text.DecimalFormat;
import java.util.Scanner;

public class Software {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        Estoque estoque = new Estoque();
        int comando;
        init(estoque);

        Menu:
        while (true) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Excluir produto");
            System.out.println("3. Alterar produto");
            System.out.println("4. Listar produtos");
            System.out.print("\nDigite o comando: ");
            comando = sc.nextInt();

            switch (comando) {
                case 0: 
                    System.out.println("\nPrograma finalizado.");
                    Thread.sleep(1000);
                    break Menu;

                case 1:
                    cadastrar(estoque);
                    Thread.sleep(1000);
                    break;

                case 2:
                    // excluir(estoque);
                    break;

                case 3:
                    // alterar(estoque);
                    break;

                case 4:
                    listar(estoque);
                    Thread.sleep(2000);
                    break;
            
                default:
                    System.out.println("\nComando inválido. Tente novamente.");
                    Thread.sleep(1000);
                    break;
            }
        }
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

    /* Cadastrar produto */
    static void cadastrar(Estoque estoque) {
        Produto p = new Produto();
        sc.nextLine();
        
        System.out.println("\nDigite as informações do produto a seguir:\n");
        System.out.print("Nome: ");
        p.addNome(sc.nextLine());
        System.out.print("Marca: ");
        p.addMarca(sc.nextLine());
        System.out.print("Preço: ");
        p.addPreco(sc.nextFloat());
        System.out.print("Quantidade: ");
        p.addQuantidade(sc.nextInt());
        
        if (estoque.inserir(p)) {
            System.out.println("\n" + p.lerNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("\nFalha ao cadastrar.");
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
}
