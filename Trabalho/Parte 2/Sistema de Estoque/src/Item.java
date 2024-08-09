public class Item {
    Produto produto;
    float preco;
    int quantidade;

    private Item(Produto produto, float preco, int quantidade) {
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Item getInstance(Produto produto, float preco, int quantidade) {
        if (produto == null) return null;
        if (preco <= 0) return null;
        if (quantidade < 0) return null;
        return new Item(produto, preco, quantidade);
    }

    public Produto getProduto() {
        return produto.copy();
    }
    public float getPreco() {
        return preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
}
