import java.text.DecimalFormat;
import java.util.Scanner;

public class Software {

    static Scanner sc = new Scanner(System.in, "CP850"); 
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
            System.out.println("5. Detalhar produto");
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
                    excluir(estoque);
                    Thread.sleep(1000);
                    break;
                    
                case 3:
                    alterar(estoque);
                    Thread.sleep(1000);
                    break;

                case 4:
                    listar(estoque);
                    Thread.sleep(2000);
                    break;
            
                case 5:
                    detalhar(estoque);
                    Thread.sleep(1000);
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
        Produto produto = new Produto();
        
        String nomes[] = {"Arroz", "Feijão", "Batata Frita", "Picanha Suína", "Suco de Laranja"};
        String marcas[] = {"PratoFino", "Camil", "BemBrasil", "Saudali", "Del Valle"};
        float precos[] = {11.99f, 8.89f, 41.49f, 29.99f, 4.29f};
        
        for (int i = 0; i < 5; i++) {
            produto.addNome(nomes[i]);
            produto.addMarca(marcas[i]);
            produto.addPreco(precos[i]);
            produto.addQuantidade(20);
            estoque.inserir(produto);
        }
    }

    /* Cadastrar produto */
    static void cadastrar(Estoque estoque) {
        Produto produto = new Produto();
        String nome;
        int m = 0;
        sc.nextLine();
        
        System.out.println("\nDigite as informações do produto a seguir:\n");
        do {
            if (m != 0) {
                System.out.println("\nEste nome já existe. Tente novamente.\n");
            }
            System.out.print("Nome: ");
            nome = sc.nextLine();
            m++;
        } while (estoque.verificaNome(nome));
        produto.addNome(nome);
        System.out.print("Marca: ");
        produto.addMarca(sc.nextLine());
        System.out.print("Preço: ");
        produto.addPreco(sc.nextFloat());
        System.out.print("Quantidade: ");
        produto.addQuantidade(sc.nextInt());
        
        System.out.println();
        if (estoque.inserir(produto)) {
            System.out.println(produto.lerNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("Falha ao cadastrar.");
        }
    }

    /* Excluir produto */
    static void excluir(Estoque estoque) {
        listarTodos(estoque.listarTodos());
        System.out.print("\nDigite o código do produto que deseja excluir: ");
        int codigo = sc.nextInt();
        Produto produto = estoque.buscar(codigo);
        
        System.out.println();
        if (produto == null) {
            System.out.println("Não foi possível encontrar um produto com esse código.");
            return;
        }
        System.out.println("Tem certeza que deseja excluir " + produto.lerNome() + "?\n");
        System.out.println("0. Não");
        System.out.println("1. Sim");
        System.out.print("\nConfirmar: ");
        if (sc.nextInt() == 1) {
            System.out.println();
            if (estoque.excluir(codigo)) {
                System.out.println(produto.lerNome() + " excluído com sucesso.");
            } else {
                System.out.println("Falha ao escluir.");
            }
        }
    }
    
    /* Altera um atributo escolhido de um produto */
    static void alterar(Estoque estoque) {
        listarTodos(estoque.listarTodos());
        System.out.print("\nDigite o código do produto que deseja alterar: ");
        int codigo = sc.nextInt();
        Produto produto = estoque.buscar(codigo);

        System.out.println();
    
        if (produto == null) {
            System.out.println("Não foi possível encontrar um produto com esse código.");
            return;
        }

        /* Sub-Menu */
        System.out.println("Qual informação deseja alterar?\n");
        System.out.println("0. Cancelar alteração");
        System.out.println("1. Nome");
        System.out.println("2. Marca");
        System.out.println("3. Preço");
        System.out.println("4. Quantidade");
        System.out.print("\nDigite o comando: ");
        int caso = sc.nextInt();
        sc.nextLine();
        
        System.out.println();
        switch (caso) {
            case 0:
            System.out.println("Alteração cancelada.");
            return;
            case 1:
            System.out.print("Digite o novo nome: ");
            produto.addNome(sc.nextLine());
            break;
            case 2:
            System.out.print("Digite a nova marca: ");
            produto.addMarca(sc.nextLine());
            break;
            case 3:
            System.out.print("Digite o novo preço: ");
            produto.addPreco(sc.nextFloat());
            break;
            case 4:
            System.out.print("Digite a nova quantidade: ");
            produto.addQuantidade(sc.nextInt());
            break;
            
            default:
            System.out.println("Falha ao alterar.");
            return;
        }
        System.out.println();
        if (estoque.alterar(codigo, produto)) {
            System.out.println(produto.lerNome() + " alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar.");
        }
    }

    /* Listar todos os produtos cadatrados */
    static void listar(Estoque estoque) {
        
        System.out.println();
        System.out.println("1. Por Código");
        System.out.println("2. Alfabética");
        System.out.print("\nDigite a ordem: ");
        int ordem = sc.nextInt();

        switch (ordem) {
            /* Ordem por código (código, nome, preço e quantidade) */
            case 1:
                listarTodos(estoque.listarTodos());
                break;

            /* Ordem alfabética (nome, código, preço) */
            case 2:
                listarPorNome(estoque.listarPorNome());
                break;
        
            default:
                System.out.println("\nERRO\n");
                break;
        }
    }

    /* Imprime tabela dos produtos (código, nome, preço e quantidade) */
    static void listarTodos(Produto[] produtos) {

        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        int nProdutos = produtos.length;
        String linhaMenu = "-------------------------------------------------------" + 
        "-------------------------------";

        System.out.println();
        System.out.println(linhaMenu);
        System.out.printf("|  %-6s  |  %-20s  |  %-14s  |  %-8s    |  %-10s  |", 
            "CÓDIGO", "NOME", "MARCA", "PREÇO", "QUANTIDADE");
        System.out.println();
        System.out.println(linhaMenu);
            
        for (int i = 0; i < nProdutos; i++) {
            System.out.printf(
                "|  %-6d  |  %-20s  |  %-14s  |  R$%8s  |  %-10d  |",
                produtos[i].lerCodigo(), produtos[i].lerNome(), produtos[i].lerMarca(),
                moeda.format(produtos[i].lerPreco()),produtos[i].lerQuantidade());
            System.out.println();
        }
        System.out.println(linhaMenu);
    }

    /* Imprime tabela dos produtos (nome, código, preço) */
    static void listarPorNome(Produto[] produtos) {
        
        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        int nProdutos = produtos.length;
        String linhaMenu = "-------------------------------------------------------" + 
        "-------------------------------";

        System.out.println();
        System.out.println(linhaMenu);
        System.out.printf("|  %-20s  |  %-6s  |  %-14s  |  %-8s    |  %-10s  |", 
            "NOME", "CÓDIGO", "MARCA","PREÇO", "QUANTIDADE");
        System.out.println();
        System.out.println(linhaMenu);
                
        for (int i = 0; i < nProdutos; i++) {
            System.out.printf(
                "|  %-20s  |  %-6d  |  %-14s  |  R$%8s  |  %-10s  |",
                produtos[i].lerNome(), produtos[i].lerCodigo(), produtos[i].lerMarca(),
                moeda.format(produtos[i].lerPreco()), produtos[i].lerQuantidade());
            System.out.println();
        }

        System.out.println(linhaMenu);
    }

    static void detalhar(Estoque estoque) {

        System.out.println("\nComo deseja detalhar?\n");
        System.out.println("1. Por Código");
        System.out.println("2. Por Nome");
        System.out.print("\nDigite o comando: ");
        int caso = sc.nextInt();
        Produto produto = new Produto();
        Produto[] vetAux = new Produto[1];

        System.out.println();
        switch (caso) {
            case 1:
                System.out.print("Digite o código do produto: ");
                int codigo = sc.nextInt();
                produto = estoque.buscar(codigo);
                break;
                
            case 2:
                sc.nextLine();
                System.out.print("Digite o nome do produto: ");
                String nome = sc.nextLine();
                produto = estoque.buscarPorNome(nome); 
                break;
        
            default:
                System.out.println("Comando inválido.");
                return;
        }

        if (produto != null) {
            vetAux[0] = produto;
            listarTodos(vetAux);
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }
}
