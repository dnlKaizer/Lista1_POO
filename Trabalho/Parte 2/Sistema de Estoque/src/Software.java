import java.text.DecimalFormat;
import java.util.Scanner;

public class Software {

    static Estoque estoque = Estoque.getInstance();
    static Scanner sc = new Scanner(System.in, "CP850"); 

    public static void main(String[] args) throws Exception {
        
        int comando;
        init();

        Menu:
        while (true) {

            printMenu();
            comando = sc.nextInt();

            switch (comando) {
                case 0: 
                    System.out.println("\nPrograma finalizado.");
                    Thread.sleep(1000);
                    break Menu;

                case 1:
                    cadastrar();
                    Thread.sleep(1000);
                    break;

                case 2:
                    excluir();
                    Thread.sleep(1000);
                    break;
                    
                case 3:
                    alterar();
                    Thread.sleep(1000);
                    break;

                case 4:
                    listar();
                    Thread.sleep(2000);
                    break;
            
                case 5:
                    detalhar();
                    Thread.sleep(1000);
                    break;

                default:
                    System.out.println("\nComando inválido. Tente novamente.");
                    Thread.sleep(1000);
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println("\nO que deseja fazer?\n");
        System.out.println("0. Sair");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Excluir produto");
        System.out.println("3. Alterar produto");
        System.out.println("4. Listar produtos");
        System.out.println("5. Detalhar produto");
        System.out.print("\nDigite o comando: ");
    }
    
    /* Cadastra 5 produtos automaticamente */
    static void init() {
        String nomes[] = {"Arroz", "Feijão", "Batata Frita", "Picanha Suína", "Suco de Laranja"};
        String marcas[] = {"PratoFino", "Camil", "BemBrasil", "Saudali", "Del Valle"};
        float precos[] = {11.99f, 8.89f, 41.49f, 29.99f, 4.29f};
        
        for (int i = 0; i < 5; i++) {
            estoque.inserir(Produto.getInstance(nomes[i], marcas[i], precos[i], 20));
        }
    }

    /* Cadastrar produto */
    static void cadastrar() {
        Produto produto = lerProduto();
        if (estoque.inserir(produto)) {
            System.out.println(produto.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("\nFalha ao cadastrar.");
        }
    }

    static Produto lerProduto() {
        String nome;
        String marca;
        float preco;
        int quantidade;

        int m = 0;
        sc.nextLine();
        
        System.out.println("\nDigite as informações do produto a seguir:\n");
        do {
            if (m != 0) {
                System.out.println("\nEste nome já existe. Tente novamente.\n");
            }
            System.out.print("Nome: ");
            nome = sc.nextLine();
            if (nome.equals(".")) return null;
            m++;
        } while (estoque.verificaNome(nome));

        System.out.print("Marca: ");
        marca = sc.nextLine();
        if (marca.equals(".")) return null;

        System.out.print("Preço: ");
        preco = sc.nextFloat();
        if (preco == -1) return null;

        System.out.print("Quantidade: ");
        quantidade = sc.nextInt();
        if (quantidade == -1) return null;

        System.out.println();

        return Produto.getInstance(nome, marca, preco, quantidade);
    }

    /* Excluir produto */
    static void excluir() {
        listarTodos(estoque.listarTodos());
        System.out.print("\nDigite o código do produto que deseja excluir: ");
        int codigo = sc.nextInt();
        Produto produto = estoque.buscar(codigo);
        
        System.out.println();
        if (produto == null) {
            System.out.println("Não foi possível encontrar um produto com esse código.");
            return;
        }
        System.out.println("Tem certeza que deseja excluir " + produto.getNome() + "?\n");
        System.out.println("0. Não");
        System.out.println("1. Sim");
        System.out.print("\nConfirmar: ");
        int comando = sc.nextInt();
        if (comando == 1) {
            System.out.println();
            if (estoque.excluir(codigo)) {
                System.out.println(produto.getNome() + " excluído com sucesso.");
            } else {
                System.out.println("Falha ao excluir.");
            }
        } else if (comando != 0) System.out.println("\nComando inválido.");
    }
    
    /* Altera um atributo escolhido de um produto */
    static void alterar() {
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
                produto.setNome(sc.nextLine());
                break;
            case 2:
                System.out.print("Digite a nova marca: ");
                produto.setMarca(sc.nextLine());
                break;
            case 3:
                System.out.print("Digite o novo preço: ");
                produto.setPreco(sc.nextFloat());
                break;
            case 4:
                System.out.print("Digite a nova quantidade: ");
                produto.setQuantidade(sc.nextInt());
                break;
            
            default:
                System.out.println("Falha ao alterar.");
                return;
        }
        System.out.println();
        if (estoque.alterar(produto)) {
            System.out.println(produto.getNome() + " alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar.");
        }
    }

    /* Listar todos os produtos cadatrados */
    static void listar() {
        
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
                listarTodos(estoque.listarPorNome());
                break;
        
            default:
                System.out.println("\nERRO\n");
                break;
        }
    }

    /* Imprime tabela com todos os produtos */
    static void listarTodos(Produto[] produtos) {
        int[] tamColuna = acharTamanhoColuna(produtos);

        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        int nProdutos = produtos.length;
        String linhaMenu = gerarLinha(tamColuna[5]);

        System.out.println();
        System.out.println(linhaMenu);
        System.out.printf("|  %-" + tamColuna[0] + "s  |  %-" + tamColuna[1] + "s  |  %-" + tamColuna[2] + "s  |  %-" + tamColuna[3] + "s     |  %-" + tamColuna[4] + "s  |", 
            "CÓDIGO", "NOME", "MARCA", "PREÇO", "QUANTIDADE");
        System.out.println();
        System.out.println(linhaMenu);
            
        for (int i = 0; i < nProdutos; i++) {
            System.out.printf(
                "|  %-" + tamColuna[0] + "d  |  %-" + tamColuna[1] + "s  |  %-" + tamColuna[2] + "s  |  R$ %" + tamColuna[3] + "s  |  %-" + tamColuna[4] + "d  |",
                produtos[i].getCodigo(), produtos[i].getNome(), produtos[i].getMarca(),
                moeda.format(produtos[i].getPreco()),produtos[i].getQuantidade());
            System.out.println();
        }
        System.out.println(linhaMenu);
    }

    /* Detalha um único produto */
    static void detalhar() {

        System.out.println("\nComo deseja detalhar?\n");
        System.out.println("1. Por Código");
        System.out.println("2. Por Nome");
        System.out.print("\nDigite o comando: ");
        int caso = sc.nextInt();
        Produto produto;
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

    static String gerarLinha(int n) {
        String linha = "";
        for (int i = 0; i < n; i++) {
            linha += "-";
        }
        return linha;
    }

    /* Algoritmo que encontra o tamanho necessário para cada coluna da tabela */
    static int[] acharTamanhoColuna(Produto[] produtos) {
        int[] tamColuna = new int[6];
        tamColuna[0] = 6;
        tamColuna[1] = 4;
        tamColuna[2] = 5;
        tamColuna[3] = 5;
        tamColuna[4] = 10;

        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        
        if ((produtos[produtos.length - 1].getCodigo() + "").length() > tamColuna[0]) {
            tamColuna[0] = (produtos[produtos.length - 1].getCodigo() + "").length();
        }

        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].getNome().length() > tamColuna[1]) {
                tamColuna[1] = produtos[i].getNome().length();
            }
            if (produtos[i].getMarca().length() > tamColuna[2]) {
                tamColuna[2] = produtos[i].getMarca().length();
            }
            if (moeda.format(produtos[i].getPreco()).length() > tamColuna[3]) {
                tamColuna[3] = moeda.format(produtos[i].getPreco()).length();
            }
            if ((produtos[i].getQuantidade() + "").length() > tamColuna[4]) {
                tamColuna[4] = (produtos[i].getQuantidade() + "").length();
            }
        }

        tamColuna[5] = tamColuna[0] + tamColuna[1] + tamColuna[2] + tamColuna[3] + tamColuna[4] + 29;
        return tamColuna;
    }
}
