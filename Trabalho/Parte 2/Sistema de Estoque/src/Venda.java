public class Venda {
    private int cdVenda;
    private Data data;
    private Carrinho carrinho;

    static int codigoAutoGerado;

    private Venda(int cdVenda, Carrinho carrinho, Data data) {
        this.cdVenda = cdVenda;
        this.data = data;
        this.carrinho = carrinho;
    }
    private Venda(Carrinho carrinho, Data data) {
        codigoAutoGerado++;
        this.cdVenda = codigoAutoGerado;
        this.data = data;
        this.carrinho = carrinho;
    }
    public static Venda getInstance(Carrinho carrinho, Data data) {
        if (data == null) return null;
        if (carrinho == null) return null;
        return new Venda(carrinho, data);
    }

    public int getCdVenda() {
        return cdVenda;
    }
    public Data getData() {
        return data.copy();
    }

    public Item buscarItem(int pos) {
        return this.carrinho.buscarItem(pos);
    }
    public int getNItens() {
        return this.carrinho.getNItens();
    }

    public float getPrecoTotal(int pos) {
        return carrinho.getPrecoTotal(pos);
    }
    public float getPrecoTotal() {
        return carrinho.getPrecoTotal();
    }

    public Venda copy() {
        return new Venda(this.cdVenda, this.carrinho.copy(), this.data.copy());
    }

    @Override
    public String toString() {
        return String.format(
        "{ Código: %d , Data: %s , Carrinho: [%s] }", 
        this.cdVenda, this.data, this.carrinho);
    }
}