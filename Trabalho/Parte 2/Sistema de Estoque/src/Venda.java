public class Venda {
    int cdVenda;
    Data data;
    Carrinho carrinho;

    static int codigoAutoGerado;

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

    @Override
    public String toString() {
        return String.format(
        "{ Código: %d , Data: %s , Carrinho: [%s] }", 
        this.cdVenda, this.data, this.carrinho);
    }
}