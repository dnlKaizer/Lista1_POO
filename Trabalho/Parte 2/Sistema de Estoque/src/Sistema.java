public class Sistema {
    private Produto[] produtos;
    private int nProdutos;

    // Classe Singleton
    private static Sistema instance;

	private Sistema() {
		this.produtos = new Produto[10];
        this.nProdutos = 0;
	}

	public static Sistema getInstance() {
		if (instance == null)
			instance = new Sistema();

		return instance;
	}

    /* Inserir novo produto */
    public boolean inserir(Produto novoProduto) {
        if (novoProduto == null) return false;
        if (verificaNome(novoProduto.getNome())) return false;
        /* Verificar se a lista de produtos está cheia */
        if (nProdutos == produtos.length) ampliarEstoque();
        produtos[nProdutos] = novoProduto;

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
                produtos[nProdutos] = null;
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
        if (codigo <= 0) return null;
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
        if (produtoAlterado == null) return false;
        int codigo = produtoAlterado.getCodigo();
        for (int i = 0; i < nProdutos; i++) {
            if (produtos[i].getCodigo() == codigo) {
                produtos[i] = produtoAlterado;
                return true;
            }
        }
        return false;
    }

    private void ampliarEstoque() {
        Produto[] vetAux = new Produto[this.nProdutos * 2];
        for (int i = 0; i < this.nProdutos; i++) {
            vetAux[i] = this.produtos[i];
        }
        this.produtos = vetAux;
    }
}
