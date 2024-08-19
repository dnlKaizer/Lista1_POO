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
        while (true) {
            printLoginMenu();
            comando = sc.nextInt();
            switch (comando) {
                case 0: 
                    System.out.println("\nPrograma finalizado.");
                    return;
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
        int[][] tamColuna = {{6,4,5,5,10}, {6,10,7}, {7,10,5,5}};
        int comando;
        Menu:
        while (true) {
            printAdminMenu();
            comando = sc.nextInt();
            switch (comando) {
                case 0: 
                    System.out.println("\nMódulo encerrado.");
                    break Menu;
                case 1:
                    cadastrar(lerNovoProduto());
                    Thread.sleep(1000);
                    break;
                case 2:
                    excluir(lerProdutoExistente(tamColuna[0]));
                    Thread.sleep(1000);
                    break;
                    
                case 3:
                    alterar(lerProdutoExistente(tamColuna[0]));
                    Thread.sleep(1000);
                    break;
                case 4:
                    listar(tamColuna[0]);
                    Thread.sleep(2000);
                    break;
                case 5:
                    detalhar(tamColuna[0]);
                    Thread.sleep(1000);
                    break;
                case 6:
                    listarVendas(tamColuna[1]);
                    Thread.sleep(2000);
                    break;
                case 7:
                    detalharVenda(tamColuna[2]);
                    Thread.sleep(1000);
                    break;
                case 8:
                    int[] tamColConfig = config();
                    Thread.sleep(500);
                    if (tamColConfig != null) {
                        tamColuna[tamColConfig[0]][tamColConfig[1]] = tamColConfig[2];
                    }
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
        System.out.println("8. Configurar tabelas");
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
                    System.out.println("\nMódulo encerrado.");
                    break Menu;
                case 1:
                    addCarrinho(carrinho, lerItem());
                    Thread.sleep(1000);
                    break;

                case 2:
                    sc.nextLine();
                    efetuarVenda(carrinho, lerData(), lerNomeCliente());
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
    static void listar(int[] tamColuna) {
        System.out.println();
        System.out.println("1. Por Código");
        System.out.println("2. Alfabética");
        System.out.print("\nDigite a ordem: ");
        int comando = sc.nextInt();

        switch (comando) {
            /* Ordem por código (código, nome, preço e quantidade) */
            case 1:
                imprimirProdutos(Sistema.getInstance().listarTodos(), tamColuna);
                break;
            /* Ordem alfabética (nome, código, preço) */
            case 2:
                imprimirProdutos(Sistema.getInstance().listarPorNome(), tamColuna);
                break;
            default:
                System.out.println("\nComando inválido.\n");
        }
    }

    /* Detalha um único produto */
    static void detalhar(int[] tamColuna) {
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
            imprimirProdutos(vetAux, tamColuna);
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }

    /* Listar todas as vendas realizadas */
    static void listarVendas(int[] tamColuna) {
        System.out.println();
        System.out.println("1. Listar Todas");
        System.out.println("2. Listar por Data");
        System.out.print("\nDigite o comando: ");
        int comando = sc.nextInt();
        sc.nextLine();

        switch (comando) {
            case 1:
                imprimirVendas(Sistema.getInstance().listarVendas(), tamColuna);
                break;
            case 2:
                System.out.println();
                System.out.print("\nDigite a data: ");
                Data data = Data.getInstance(sc.nextLine());
                if (data == null) System.out.println("\nData inválida.\n");
                else imprimirVendas(Sistema.getInstance().listarVendasPorData(data), tamColuna);
                break;
            default:
                System.out.println("\nComando inválido.\n");
        }
    }

    /* Detalha uma única venda */
    static void detalharVenda(int[] tamColuna) {
        System.out.print("\nDigite o código da venda: ");
        int codigo = sc.nextInt();
        Venda venda = Sistema.getInstance().listarVendaPorCd(codigo);
        if (venda == null) System.out.println("\nCódigo inválido.");
        else imprimirVenda(venda, tamColuna);
    }

    /* Configura a largura das colunas das tabelas */
    static int[] config() {
        System.out.println();
        System.out.println("Qual tabela deseja alterar?\n");
        System.out.println("1. Produtos");
        System.out.println("2. Vendas");
        System.out.println("3. Venda detalhada");
        System.out.print("\nDigite o comando: ");
        int comando = sc.nextInt();
        System.out.println();

        switch (comando) {
            case 1:
                return configProdutos();
            case 2:
                return configVendas();
            case 3:
                return configVenda();
            default:
                System.out.println("Comando inválido.");
                return null;
        }
    }
    static int[] configProdutos() {
        System.out.println();
        System.out.println("Qual coluna deseja alterar?\n");
        System.out.println("0. Cancelar");
        System.out.println("1. Código");
        System.out.println("2. Nome");
        System.out.println("3. Marca");
        System.out.println("4. Preço");
        System.out.println("5. Quantidade");
        System.out.print("\nDigite o comando: ");
        int comando = sc.nextInt();
        System.out.println();

        if (comando == 0) return null;
        if (comando < 0 || comando > 5) {
            System.out.println("Comando inválido.");
            return null;
        }

        System.out.print("\nDigite o novo tamanho da coluna: ");
        int tam = sc.nextInt();
        int[] tamColuna = {0, comando - 1, tam};
        return tamColuna;
    }
    static int[] configVendas() {
        System.out.println();
        System.out.println("Qual coluna deseja alterar?\n");
        System.out.println("0. Cancelar");
        System.out.println("1. Código");
        System.out.println("2. Data");
        System.out.println("3. Valor");
        System.out.print("\nDigite o comando: ");
        int comando = sc.nextInt();
        System.out.println();

        if (comando == 0) return null;
        if (comando < 0 || comando > 3) {
            System.out.println("Comando inválido.");
            return null;
        }

        System.out.print("\nDigite o novo tamanho da coluna: ");
        int tam = sc.nextInt();
        int[] tamColuna = {1, comando - 1, tam};
        return tamColuna;
    }
    static int[] configVenda() {
        System.out.println();
        System.out.println("Qual coluna deseja alterar?\n");
        System.out.println("0. Cancelar");
        System.out.println("1. Produto");
        System.out.println("2. Quantidade");
        System.out.println("3. Valor");
        System.out.println("4. Total");
        System.out.print("\nDigite o comando: ");
        int comando = sc.nextInt();
        System.out.println();

        if (comando == 0) return null;
        if (comando < 0 || comando > 4) {
            System.out.println("Comando inválido.");
            return null;
        }

        System.out.print("\nDigite o novo tamanho da coluna: ");
        int tam = sc.nextInt();
        int[] tamColuna = {2, comando - 1, tam};
        return tamColuna;
    }

    /* --------------------- MÓDULO ATENDENTE --------------------- */

    /* Adiciona Item no carrinho */
    static void addCarrinho(Carrinho carrinho, Item item) {
        if (carrinho == null || item == null) return;
        if (carrinho.addItem(item)) System.out.println("Item inserido com sucesso.");
        else System.out.println("Falha ao inserir item no carrinho");
    }

    /* Finaliza uma venda */
    static void efetuarVenda(Carrinho carrinho, Data data, String nome) {
        if (carrinho == null) return;
        if (Sistema.getInstance().gerarVenda(carrinho, data, nome)) System.out.println("\nVenda realizada com sucesso.");
        else System.out.println("\nFalha ao efetuar venda.");
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
    static Produto lerProdutoExistente(int[] tamColuna) {
        imprimirProdutos(Sistema.getInstance().listarTodos(), tamColuna);
        System.out.print("\nDigite o código do produto: ");
        int codigo = sc.nextInt();
        System.out.println();
        Produto produto = Sistema.getInstance().buscar(codigo);
        if (produto == null) System.out.println("Falha na leitura do produto.");
        return produto;
    }
    /* Cria um novo item pelo terminal */
    static Item lerItem() {
        int[] tamColuna = {6,4,5,5,10};
        Produto p = lerProdutoExistente(tamColuna);
        if (p == null) return null;
        System.out.print("Digite a quantidade que deseja inserir: ");
        int qtd = sc.nextInt();
        System.out.println();
        Item item = Item.getInstance(p, qtd);
        if (item == null) System.out.println("Falha ao criar item.");
        return item;
    }
    /* Lê a data pelo terminal */
    static Data lerData() {
        System.out.print("\nDigite a data: ");
        Data data = Data.getInstance(sc.nextLine());
        if (data == null) System.out.println("\nData inválida");
        return data;
    }
    /* Lê o nome do cliente pelo terminal */
    static String lerNomeCliente() {
        System.out.print("\nDigite seu nome: ");
        String str = sc.nextLine();
        return str;
    }

    /* Imprime tabela com os produtos */
    static void imprimirProdutos(Produto[] produtos, int[] tamColuna) {
        if (produtos == null || produtos.length == 0) {
            System.out.println("\nNão foram encontrados produtos.");
            return;
        }
        tamColuna = acharTamanhoColuna(produtos, tamColuna);

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
    static void imprimirVendas(Venda[] vendas, int[] tamColuna) {
        if (vendas == null || vendas.length == 0) {
            System.out.println("\nNão foram encontradas vendas.");
            return;
        }
        tamColuna = acharTamanhoColuna(vendas, tamColuna);

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
    static void imprimirVenda(Venda venda, int[] tamColuna) {
        if (venda == null) {
            System.out.println("\nVenda não foi encontrada.");
            return;
        }
        tamColuna = acharTamanhoColuna(venda, tamColuna);
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
    static int[] acharTamanhoColuna(Produto[] produtos, int[] tamColuna) {
        DecimalFormat moeda = new DecimalFormat("#,##0.00");
        
        if (tamColuna[0] < 6) tamColuna[0] = 6;
        if (tamColuna[1] < 4) tamColuna[0] = 4;
        if (tamColuna[2] < 5) tamColuna[0] = 5;
        if (tamColuna[3] < 5) tamColuna[0] = 5;
        if (tamColuna[4] < 10) tamColuna[0] = 10;

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

        int[] tamColunaAux = new int[6];
        for (int i = 0; i < 5; i++) {
            tamColunaAux[i] = tamColuna[i];
        }
        tamColunaAux[5] = tamColuna[0] + tamColuna[1] + tamColuna[2] + tamColuna[3] + tamColuna[4] + 29;
        return tamColunaAux;
    }
    static int[] acharTamanhoColuna(Venda[] vendas, int[] tamColuna) {
        DecimalFormat moeda = new DecimalFormat("#,##0.00");

        if (tamColuna[0] < 6) tamColuna[0] = 6;
        if (tamColuna[1] < 4) tamColuna[0] = 4;
        if (tamColuna[2] < 5) tamColuna[0] = 5;
        
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
        int[] tamColunaAux = new int[4];
        for (int i = 0; i < 3; i++) {
            tamColunaAux[i] = tamColuna[i];
        }
        tamColunaAux[3] = tamColuna[0] + tamColuna[1] + tamColuna[2] + 19;
        return tamColunaAux;
    }
    static int[] acharTamanhoColuna(Venda venda, int[] tamColuna) {
        DecimalFormat moeda = new DecimalFormat("#,##0.00");

        if (tamColuna[0] < 6) tamColuna[0] = 7;
        if (tamColuna[1] < 4) tamColuna[0] = 10;
        if (tamColuna[2] < 5) tamColuna[0] = 5;
        if (tamColuna[3] < 5) tamColuna[0] = 5;

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
        
        int[] tamColunaAux = new int[5];
        for (int i = 0; i < 4; i++) {
            tamColunaAux[i] = tamColuna[i];
        }
        tamColunaAux[4] = tamColuna[0] + tamColuna[1] + tamColuna[2] + tamColuna[3] + 27;
        return tamColunaAux;
    }
}
