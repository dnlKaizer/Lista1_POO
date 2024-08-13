public class Venda {
    int codigo;
    Data data;
    Carrinho carrinho;

    static int codigoAutoGerado;

    private Venda(Carrinho carrinho, Data data) {
        codigoAutoGerado++;
        this.codigo = codigoAutoGerado;
        this.data = data;
        this.carrinho = carrinho;
    }
    public static Venda getInstance(Carrinho carrinho, Data data) {
        if (data == null) return null;
        if (carrinho == null) return null;
        return new Venda(carrinho, data);
    }

    // Constructor para o copy, não precisa de método fábrica
    private Venda(int codigo, Carrinho carrinho, Data data) {
        this.codigo = codigo;
        this.data = data;
        this.carrinho = carrinho;
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

    public Venda copy() {
        return new Venda(codigo, carrinho, data);
    }
}