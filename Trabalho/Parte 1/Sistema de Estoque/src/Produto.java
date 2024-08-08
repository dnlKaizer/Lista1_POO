public class Produto {
    private int codigo;
    private String nome;
    private String marca;
    private float preco;
    private int quantidade;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    public float getPreco() {
        return preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public Produto copy() {
        Produto auxP = new Produto();
        auxP.setCodigo(getCodigo());
        auxP.setNome(getNome());
        auxP.setMarca(getMarca());
        auxP.setPreco(getPreco());
        auxP.setQuantidade(getQuantidade());
        return auxP;
    }
}