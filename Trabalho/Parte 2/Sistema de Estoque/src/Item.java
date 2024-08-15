import java.text.DecimalFormat;

public class Item {
    Produto produto;
    float preco;
    int quantidade;

    private Item(Produto produto, float preco, int quantidade) {
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public static Item getInstance(Produto produto, int quantidade) {
        if (produto == null) return null;
        if (quantidade <= 0) return null;
        return new Item(produto, produto.getPreco(), quantidade);
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

    public void merge(Item item2) {
        quantidade += item2.getQuantidade();
    }
    public void addQuantidade(int qtd) {
        this.quantidade += qtd;
    }
    public void subQuantidade(int qtd) {
        if (qtd > quantidade) return;
        this.quantidade -= qtd;
    }

    public Item copy() {
        return new Item(this.produto, this.preco, this.quantidade);
    }

    @Override
    public String toString() {
        DecimalFormat money = new DecimalFormat("R$ #,##0.00");
        return String.format(
        "{ Produto: (%s) , Preço: %s , Quantidade: %d }", 
        this.produto, money.format(this.preco), this.quantidade);
    }
}
