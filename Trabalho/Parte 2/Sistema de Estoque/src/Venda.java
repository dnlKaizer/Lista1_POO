public class Venda {
    int codigo;
    Data data;
    Carrinho carrinho;

    private Venda(int codigo, Data data, Carrinho carrinho) {
        this.codigo = codigo;
        this.data = data;
        this.carrinho = carrinho;
    }
    public static Venda getInstance(int codigo, Data data, Carrinho carrinho) {
        if (codigo <= 0) return null;
        if (data == null) return null;
        if (carrinho == null) return null;
        return new Venda(codigo, data, carrinho);
    }

    public int getCodigo() {
        return codigo;
    }
    public Data getData() {
        return data.copy();
    }
    public Carrinho getCarrinho() {
        return carrinho.copy();
    }
}