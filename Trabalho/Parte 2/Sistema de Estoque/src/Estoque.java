public class Estoque {
    private Produto[] produtos;
    private int nProdutos;
    private int codigo;

    // Classe Singleton
    private static Estoque instance;

	private Estoque() {
		this.produtos = new Produto[10];
        this.nProdutos = 0;
        this.codigo = 1;
	}

	public static Estoque getInstance() {
		if (instance == null)
			instance = new Estoque();

		return instance;
	}

    /* Inserir novo produto */
    public boolean inserir(Produto novoProduto) {
        if (novoProduto == null) {
            return false;
        }
        /* Verificar se a lista de produtos está cheia */
        if (nProdutos == produtos.length) {
            Produto[] vetAux = new Produto[nProdutos * 2];
            for (int i = 0; i < nProdutos; i++) {
                vetAux[i] = produtos[i];
            }
            produtos = vetAux;
        }
        novoProduto.setCodigo(this.codigo);
        produtos[nProdutos] = novoProduto;

        this.codigo++;
        nProdutos++;
        return true;
    }

    /* Método para excluir um produto */
    public boolean excluir(int codigo) {
        if (codigo <= 0) return false;
        
        for (int i = 0; i < nProdutos; i++) {
            if (produtos[i].getCodigo() == codigo) {
                for (int j = i; j < nProdutos - 1; j++) {
                    produtos[j] = produtos[j + 1];
                }
                nProdutos--;
                produtos[nProdutos - 1] = null;
                return true;
            }
        }
        
        return false;
    }

    /* Retorna cópia da lista de produtos em ordem por código */
    public Produto[] listarTodos() {
        Produto[] vetAux = new Produto[nProdutos];
        for (int i = 0; i < nProdutos; i++) {
            vetAux[i] = produtos[i].copy();
        }
        return vetAux;
    }

    /* Retorna cópia da lista de produtos em ordem alfabética */
    public Produto[] listarPorNome() {
        Produto[] vetAux = ordenarProdutos();
        return vetAux;
    }    

    /* Retorna a quantidade de produtos cadastrados */
    public int getNProdutos() {
        return nProdutos;
    }

    /* Busca produto no estoque pelo código */
    public Produto buscar(int codigo) {
        for (int i = 0; i < nProdutos; i++) {
            if (produtos[i].getCodigo() == codigo) {
                return produtos[i].copy();
            }
        }
        return null;
    }

    /* Busca produto no estoque pelo nome */
    public Produto buscarPorNome(String nome) {
        for (int i = 0; i < nProdutos; i++) {
            if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                return produtos[i].copy();
            }
        }
        return null;
    }

    /* Verifica se existe produto com mesmo nome */
    public boolean verificaNome(String nome) {
        for (int i = 0; i < nProdutos; i++) {
            if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                return true;
            } 
        }
        return false;
    }

    /* Retorna cópia da lista de produtos ordenada lexicograficamente */
    private Produto[] ordenarProdutos() {
        Produto auxP;
        Produto[] vetAux = listarTodos();
        for (int i = 0; i < nProdutos; i++) {
            for (int j = i + 1; j < nProdutos; j++) {
                if ((vetAux[i].getNome()).compareToIgnoreCase(vetAux[j].getNome()) > 0) {
                    auxP = vetAux[i];
                    vetAux[i] = vetAux[j];
                    vetAux[j] = auxP;
                }
            }
        }
        return vetAux;
    }

    public boolean alterar(Produto produtoAlterado) {
        if (produtoAlterado != null) {
            int codigo = produtoAlterado.getCodigo();
            for (int i = 0; i < nProdutos; i++) {
                if (produtos[i].getCodigo() == codigo) {
                    produtos[i] = produtoAlterado;
                    return true;
                }
            }
        } 
        return false;
    }

}
