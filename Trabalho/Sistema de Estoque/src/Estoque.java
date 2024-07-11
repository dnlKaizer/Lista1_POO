public class Estoque {
    private Produto[] produtos = new Produto[10];
    private int nProdutos = 0;
    private int codigo = 1;

    /* Inserir novo produto */
    public boolean inserir(Produto novoProduto) {
        if (novoProduto != null) {
            /* Verificar se a lista de produtos está cheia */
            if (nProdutos == produtos.length) {
                Produto[] vetAux = new Produto[nProdutos * 2];
                for (int i = 0; i < nProdutos; i++) {
                    vetAux[i] = produtos[i];
                }
                produtos = vetAux;
            }
            if (produtos[nProdutos] == null) {
                produtos[nProdutos] = new Produto();

                produtos[nProdutos].setCodigo(this.codigo);
                produtos[nProdutos].setNome(novoProduto.getNome());
                produtos[nProdutos].setMarca(novoProduto.getMarca());
                produtos[nProdutos].setPreco(novoProduto.getPreco());
                produtos[nProdutos].setQuantidade(novoProduto.getQuantidade());

                this.codigo++;
                nProdutos++;
                return true;
            }
            return false;
        }
        return false;
    }

    /* Método para excluir um produto */
    public boolean excluir(int codigo) {
        if (codigo > 0) {
            for (int i = 0; i < nProdutos; i++) {
                if (produtos[i].getCodigo() == codigo) {
                    for (int j = i; j < produtos.length - 1; j++) {
                        produtos[j] = produtos[j + 1];
                    }
                    nProdutos--;
                    produtos[nProdutos] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /* Retorna cópia da lista de produtos em ordem por código */
    public Produto[] listarTodos() {
        Produto[] vetAux = new Produto[nProdutos];
        for (int i = 0; i < nProdutos; i++) {
            vetAux[i] = new Produto();
            vetAux[i].setCodigo(produtos[i].getCodigo());
            vetAux[i].setNome(produtos[i].getNome());
            vetAux[i].setMarca(produtos[i].getMarca());
            vetAux[i].setPreco(produtos[i].getPreco());
            vetAux[i].setQuantidade(produtos[i].getQuantidade());
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
                return produtos[i];
            }
        }
        return null;
    }

    /* Busca produto no estoque pelo nome */
    public Produto buscarPorNome(String nome) {
        for (int i = 0; i < nProdutos; i++) {
            if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                return produtos[i];
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
        Produto auxP = new Produto();
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
        int codigo = produtoAlterado.getCodigo();
        if (produtoAlterado != null) {
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
