public class Estoque {
    Produto[] produtos = new Produto[10];
    int nProdutos = 0;
    int codigo = 1;

    /* Inserir novo produto */
    boolean inserir(Produto novoProduto) {
        if (novoProduto != null) {
            /* Verificar se a lista de produtos está cheia */
            if (nProdutos == produtos.length) {
                Produto[] vetAux = new Produto[nProdutos * 2];
                for (int i = 0; i < nProdutos; i++) {
                    vetAux[i] = produtos[i];
                }
                produtos = vetAux;
            }
            if (!verificaNome(novoProduto)) {
                for (int i = 0; i < produtos.length; i++) {
                    if (produtos[i] == null) {
                        produtos[i] = new Produto();

                        produtos[i].addCodigo(codigo);
                        produtos[i].addNome(novoProduto.lerNome());
                        produtos[i].addMarca(novoProduto.lerMarca());
                        produtos[i].addPreco(novoProduto.lerPreco());
                        produtos[i].addQuantidade(novoProduto.lerQuantidade());

                        codigo++;
                        nProdutos++;
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    /* Método para excluir um produto */
    boolean excluir(int codigo) {
        if (codigo > 0) {
            for (int i = 0; i < nProdutos; i++) {
                if (produtos[i].lerCodigo() == codigo) {
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
    Produto[] listarTodos() {
        Produto[] vetAux = new Produto[nProdutos];
        for (int i = 0; i < nProdutos; i++) {
            vetAux[i] = new Produto();
            vetAux[i].addCodigo(produtos[i].lerCodigo());
            vetAux[i].addNome(produtos[i].lerNome());
            vetAux[i].addMarca(produtos[i].lerMarca());
            vetAux[i].addPreco(produtos[i].lerPreco());
            vetAux[i].addQuantidade(produtos[i].lerQuantidade());
        }
        return vetAux;
    }

    /* Retorna cópia da lista de produtos em ordem alfabética */
    Produto[] listarPorNome() {
        Produto[] vetAux = ordenarProdutos();
        return vetAux;
    }    

    /* Retorna a quantidade de produtos cadastrados */
    int lerNProdutos() {
        return nProdutos;
    }

    /* Busca produto no estoque pelo código */
    Produto buscar(int codigo) {
        for (int i = 0; i < nProdutos; i++) {
            if (produtos[i].lerCodigo() == codigo) {
                return produtos[i];
            }
        }
        return null;
    }

    /* Verifica se existe produto com mesmo nome */
    private boolean verificaNome(Produto novoProduto) {
        for (int i = 0; i < nProdutos; i++) {
            if (produtos[i].nome.equalsIgnoreCase(novoProduto.nome)) {
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
                if ((vetAux[i].lerNome()).compareToIgnoreCase(vetAux[j].lerNome()) > 0) {
                    auxP = vetAux[i];
                    vetAux[i] = vetAux[j];
                    vetAux[j] = auxP;
                }
            }
        }
        return vetAux;
    }
}
