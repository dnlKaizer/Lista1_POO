public class Venda {
    static int codigo;
    Data data;
    Carrinho carrinho;

    public static int getCodigo() {
        return codigo;
    }
    public static void setCodigo(int codigo) {
        Venda.codigo = codigo;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data.copy();
    }
    public Carrinho getCarrinho() {
        return carrinho.copy();
    }
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

}