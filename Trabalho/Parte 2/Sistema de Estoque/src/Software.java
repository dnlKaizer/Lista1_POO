import java.text.DecimalFormat;
import java.util.Scanner;

public class Software {

    static Scanner sc = new Scanner(System.in, "CP850"); 

    public static void main(String[] args) throws Exception {
        
        init();
        login();
    }

    static void login() throws InterruptedException {
        int comando;
        printLoginMenu();
        comando = sc.nextInt();
        switch (comando) {
            case 0: 
                System.out.println("\nPrograma finalizado.");
                break;
            case 1:
                admin();
                Thread.sleep(1000);
                break;
            case 2:
                atendente();
                Thread.sleep(1000);
                break;
            default:
                System.out.println("\nComando inválido.");
                Thread.sleep(500);
                break;
        }
    }
    static void printLoginMenu() {
        System.out.println("\nQual o módulo de acesso?\n");
        System.out.println("0. Sair");
        System.out.println("1. Administrador");
        System.out.println("2. Atendente");
        System.out.print("\nDigite o comando: ");
    }

    /* Módulo de administrador */
    static void admin() throws InterruptedException {
        int comando;
        Menu:
        while (true) {
            printAdminMenu();
            comando = sc.nextInt();
            switch (comando) {
                case 0: 
                    System.out.println("\nPrograma finalizado.");
                    break Menu;
                case 1:
                    cadastrar(lerNovoProduto());
                    Thread.sleep(1000);
                    break;
                case 2:
                    excluir(lerProdutoExistente());
                    Thread.sleep(1000);
                    break;
                    
                case 3:
                    alterar(lerProdutoExistente());
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
                case 6:
                    listarVendas();
                    Thread.sleep(2000);
                    break;
                case 7:
                    detalharVenda();
                    Thread.sleep(1000);
                    break;
                default:
                    System.out.println("\nComando inválido. Tente novamente.");
                    Thread.sleep(500);
                    break;
            }
        }
    }
    static void printAdminMenu() {
        System.out.println("\nO que deseja fazer?\n");
        System.out.println("0. Sair");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Excluir produto");
        System.out.println("3. Alterar produto");
        System.out.println("4. Listar produtos");
        System.out.println("5. Detalhar produto");
        System.out.println("6. Listar vendas");
        System.out.println("7. Detalhar venda");
        System.out.print("\nDigite o comando: ");
    }
    
    /* Módulo de atendente */
    static void atendente() throws InterruptedException {
        Carrinho carrinho = new Carrinho(); 
        int comando;
        Menu:
        while (true) {
            printAtendenteMenu();
            comando = sc.nextInt();
            switch (comando) {
                case 0: 
                    System.out.println("\nPrograma finalizado.");
                    break Menu;
                case 1:
                    addCarrinho(carrinho, lerItem());
                    Thread.sleep(1000);
                    break;

                case 2:
                    
                    Thread.sleep(1000);
                    carrinho = new Carrinho();
                    break;
                default:
                    System.out.println("\nComando inválido. Tente novamente.");
                    Thread.sleep(500);
                    break;
            }
        }
    }
    static void printAtendenteMenu() {
        System.out.println("\nO que deseja fazer?\n");
        System.out.println("0. Sair");
        System.out.println("1. Inserir no carrinho");
        System.out.println("2. Finalizar compra");
        System.out.print("\nDigite o comando: ");
    }

    /* Executa initProdutos e initVendas */
    static void init() {
        initProdutos();
        initVendas();
    }
    /* Cadastra 5 produtos automaticamente */
    static void initProdutos() {
        String nomes[] = {"Arroz", "Feijão", "Batata Frita", "Picanha Suína", "Suco de Laranja"};
        String marcas[] = {"PratoFino", "Camil", "BemBrasil", "Saudali", "Del Valle"};
        float precos[] = {11.99f, 8.89f, 41.49f, 29.99f, 4.29f};
        
        for (int i = 0; i < 5; i++) {
            Sistema.getInstance().inserir(Produto.getInstance(nomes[i], marcas[i], precos[i], 20));
        }
    }
    /* Cadastra 2 vendas automaticamente */
    static void initVendas() {
        int[][] codigos = {{1,2,3}, {2,4}};
        int[][] quantidades = {{3,4,5}, {2,7}};
        String[] nmClientes = {"José","Maria"};

        for (int i = 0; i < 2; i++) {
            Carrinho carrinho = new Carrinho();
            for (int j = 0; j < codigos[i].length; j++) {
                carrinho.addItem(Item.getInstance(Sistema.getInstance().buscar(codigos[i][j]), quantidades[i][j]));
            }
            Sistema.getInstance().gerarVenda(carrinho, Data.getInstance("5/3/2020"), nmClientes[i]);
        }
    }

    /* ------------------- MÓDULO ADMINISTRADOR ------------------- */

    /* Cadastrar produto */
    static void cadastrar(Produto produto) {
        if (produto == null) return;
        if (Sistema.getInstance().inserir(produto)) {
            System.out.println(produto.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("\nFalha ao cadastrar.");
        }
    }

    /* Excluir produto */
    static void excluir(Produto produto) {
        if (produto == null) return;
        System.out.println("Tem certeza que deseja excluir " + produto.getNome() + "?\n");
        System.out.println("0. Não");
        System.out.println("1. Sim");
        System.out.print("\nConfirmar: ");
        int comando = sc.nextInt();
        if (comando == 1) {
            System.out.println();
            if (Sistema.getInstance().excluir(produto.getCdProduto())) {
                System.out.println(produto.getNome() + " excluído com sucesso.");
            } else {
                System.out.println("Falha ao excluir.");
            }
        } else if (comando != 0) System.out.println("\nComando inválido.");
    }

    /* Altera um atributo escolhido de um produto */
    static void alterar(Produto produto) {
        if (produto == null) return;
        
        printSubMenu();
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
                System.out.println("Comando inválido.");
                return;
        }

        System.out.println();
        if (Sistema.getInstance().alterar(produto)) {
            System.out.println(produto.getNome() + " alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar.");
        }
    }
    static void printSubMenu() {
        System.out.println("Qual informação deseja alterar?\n");
        System.out.println("0. Cancelar alteração");
        System.out.println("1. Nome");
        System.out.println("2. Marca");
        System.out.println("3. Preço");
        System.out.println("4. Quantidade");
        System.out.print("\nDigite o comando: ");
    }

    /* Listar todos os produtos cadatrados */
    static void listar() {
        System.out.println();
        System.out.println("1. Por Código");
        System.out.println("2. Alfabética");
        System.out.print("\nDigite a ordem: ");
        int comando = sc.nextInt();

        switch (comando) {
            /* Ordem por código (código, nome, preço e quantidade) */
            case 1:
                imprimirProdutos(Sistema.getInstance().listarTodos());
                break;
            /* Ordem alfabética (nome, código, preço) */
            case 2:
                imprimirProdutos(Sistema.getInstance().listarPorNome());
                break;
            default:
                System.out.println("\nComando inválido.\n");
        }
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
                produto = Sistema.getInstance().buscar(codigo);
                break;
                
            case 2:
                sc.nextLine();
                System.out.print("Digite o nome do produto: ");
                String nome = sc.nextLine();
                produto = Sistema.getInstance().buscarPorNome(nome); 
                break;
        
            default:
                System.out.println("Comando inválido.");
                return;
        }

        if (produto != null) {
            vetAux[0] = produto;
            imprimirProdutos(vetAux);
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }

    /* Listar todas as vendas realizadas */
    static void listarVendas() {
        System.out.println();
        System.out.println("1. Listar Todas");
        System.out.println("2. Listar por Data");
        System.out.print("\nDigite o comando: ");
        int comando = sc.nextInt();
        sc.nextLine();

        switch (comando) {
            case 1:
                imprimirVendas(Sistema.getInstance().listarVendas());
                break;
            case 2:
                System.out.println();
                System.out.print("\nDigite a data: ");
                Data data = Data.getInstance(sc.nextLine());
                if (data == null) System.out.println("\nData inválida.\n");
                else imprimirVendas(Sistema.getInstance().listarVendasPorData(data));
                break;
            default:
                System.out.println("\nComando inválido.\n");
        }
    }

    /* Detalha uma única venda */
    static void detalharVenda() {
        System.out.print("\nDigite o código da venda: ");
        int codigo = sc.nextInt();
        Venda venda = Sistema.getInstance().listarVendaPorCd(codigo);
        if (venda == null) System.out.println("\nCódigo inválido.");
        else imprimirVenda(venda);
    }

    /* --------------------- MÓDULO ATENDENTE --------------------- */

    /* Adiciona Item no carrinho */
    static void addCarrinho(Carrinho carrinho, Item item) {
        if (carrinho == null || item == null) return;
        if (carrinho.addItem(item)) System.out.println("Item inserido com sucesso.");
        else System.out.println("Falha ao inserir item no carrinho");
    }

    /* ------------------------------------------------------------ */

    /* Cria um novo produto pelo terminal */
    static Produto lerNovoProduto() {
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
        } while (Sistema.getInstance().verificaNome(nome));

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

        Produto p = Produto.getInstance(nome, marca, preco, quantidade);
        if (p == null) System.out.println("Falha ao criar produto.");
        return p;
    }
    /* Lê um produto existente pelo terminal */
    static Produto lerProdutoExistente() {
        imprimirProdutos(Sistema.getInstance().listarTodos());
        System.out.print("\nDigite o código do produto: ");
        int codigo = sc.nextInt();
        System.out.println();
        Produto produto = Sistema.getInstance().buscar(codigo);
        if (produto == null) System.out.println("Falha na leitura do produto.");
        return produto;
    }
    /* Cria um novo item pelo terminal */
    static Item lerItem() {
        Produto p = lerProdutoExistente();
        if (p == null) return null;
        System.out.print("Digite a quantidade que deseja inserir: ");
        int qtd = sc.nextInt();
        System.out.println();
        Item item = Item.getInstance(p, qtd);
        if (item == null) System.out.println("Falha ao criar item.");
        return item;
    }

    /* Imprime tabela com os produtos */
    static void imprimirProdutos(Produto[] produtos) {
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
                produtos[i].getCdProduto(), produtos[i].getNome(), produtos[i].getMarca(),
                moeda.format(produtos[i].getPreco()),produtos[i].getQuantidade());
            System.out.println();
        }
        System.out.println(linhaMenu);
    }
    /* Imprime tabela com as vendas */
    static void imprimirVendas(Venda[] vendas) {
        int[] tamColuna = acharTamanhoColuna(vendas);

        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        int nProdutos = vendas.length;
        String linhaMenu = gerarLinha(tamColuna[3]);

        System.out.println();
        System.out.println(linhaMenu);
        System.out.printf("|  %-" + tamColuna[0] + "s  |  %-" + tamColuna[1] + "s  |  %-" + tamColuna[2] + "s     |", 
            "CÓDIGO", "DATA", "VALOR");
        System.out.println();
        System.out.println(linhaMenu);
            
        for (int i = 0; i < nProdutos; i++) {
            System.out.printf(
                "|  %-" + tamColuna[0] + "d  |  %-" + tamColuna[1] + "s  |  R$ %" + tamColuna[2] + "s  |",
                vendas[i].getCdVenda(), vendas[i].getData().toString(), moeda.format(vendas[i].getPrecoTotal()));
            System.out.println();
        }
        System.out.println(linhaMenu);
    }
    /* Imprime tabela com uma única venda detalhada */
    static void imprimirVenda(Venda venda) {
        int[] tamColuna = acharTamanhoColuna(venda);
        String linhaMenu = gerarLinha(tamColuna[4]);
        int col = tamColuna[4] - 22;

        DecimalFormat moeda = new DecimalFormat("R$ #,##0.00");

        System.out.println();
        System.out.printf(
            linhaMenu +
            "\n|  %-11s  |  %-" + col + "d  |\n" +
            linhaMenu +
            "\n|  %-11s  |  %-" + col + "s  |\n" +
            linhaMenu +
            "\n|  %-11s  |  %-" + col + "s  |\n" +
            linhaMenu +
            "\n|  %-11s  |  %-" + col + "s  |\n" +
            linhaMenu, 
            
            "CÓDIGO", venda.getCdVenda(),
            "DATA", venda.getData().toString(),
            "CLIENTE", venda.getNmCliente(),
            "VALOR TOTAL", moeda.format(venda.getPrecoTotal())
        );
        moeda = new DecimalFormat("#,##0.00");
        System.out.printf(
            "\n|  %-" + tamColuna[0] + "s  |  %-" + tamColuna[1] + "s  |  %-" + tamColuna[2] + "s     |  %-" + tamColuna[3] + "s     |\n",
            "PRODUTO", "QUANTIDADE", "VALOR", "TOTAL"
        );
        System.out.println(linhaMenu);
        for (int i = 0; i < venda.getNItens(); i++) {
            System.out.printf(
                "|  %-" + tamColuna[0] + "s  |  %-" + tamColuna[1] + "s  |  R$ %-" + tamColuna[2] + "s  |  R$ %-" + tamColuna[3] + "s  |\n",
                venda.buscarItem(i).getNmProduto(), venda.buscarItem(i).getQuantidade(), moeda.format(venda.buscarItem(i).getPreco()), moeda.format(venda.buscarItem(i).getPrecoTotal())
            );
        }
        System.out.println(linhaMenu);
        System.out.println();
    }

    /* Gerador de linha para tabela */
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
        
        if ((produtos[produtos.length - 1].getCdProduto() + "").length() > tamColuna[0]) {
            tamColuna[0] = (produtos[produtos.length - 1].getCdProduto() + "").length();
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
    static int[] acharTamanhoColuna(Venda[] vendas) {
        int[] tamColuna = new int[4];
        tamColuna[0] = 6;
        tamColuna[1] = 10;
        tamColuna[2] = 5;

        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        
        if ((vendas[vendas.length - 1].getCdVenda() + "").length() > tamColuna[0]) {
            tamColuna[0] = (vendas[vendas.length - 1].getCdVenda() + "").length();
        }

        for (int i = 0; i < vendas.length; i++) {
            if (vendas[i].getData().toString().length() > tamColuna[1]) {
                tamColuna[1] = vendas[i].getData().toString().length();
            }
            if (moeda.format(vendas[i].getPrecoTotal()).length() > tamColuna[2]) {
                tamColuna[2] = moeda.format(vendas[i].getPrecoTotal()).length();
            }
        }
        tamColuna[3] = tamColuna[0] + tamColuna[1] + tamColuna[2] + 19;
        return tamColuna;
    }
    static int[] acharTamanhoColuna(Venda venda) {
        int[] tamColuna = new int[5];
        tamColuna[0] = 7;
        tamColuna[1] = 10;
        tamColuna[2] = 5;
        tamColuna[3] = 5;

        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        for (int i = 0; i < venda.getNItens(); i++) {
            int tamNmProduto = venda.buscarItem(i).getNmProduto().length();
            if (tamNmProduto > tamColuna[0]) tamColuna[0] = tamNmProduto;

            int tamQuantidade = (venda.buscarItem(i).getQuantidade() + "").length();
            if (tamQuantidade > tamColuna[1]) tamColuna[1] = tamQuantidade;

            int tamValor = moeda.format(venda.buscarItem(i).getPreco()).length();
            if (tamValor > tamColuna[2]) tamColuna[2] = tamValor;

            int tamTotal = moeda.format(venda.buscarItem(i).getPrecoTotal()).length();
            if (tamTotal > tamColuna[3]) tamColuna[3] = tamTotal;
        }
        
        tamColuna[4] = tamColuna[0] + tamColuna[1] + tamColuna[2] + tamColuna[3] + 27;
        return tamColuna;
    }
}
