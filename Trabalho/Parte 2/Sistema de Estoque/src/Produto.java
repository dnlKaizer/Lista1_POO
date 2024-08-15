import java.text.DecimalFormat;

public class Produto {
    private int cdProduto;
    private String nome;
    private String marca;
    private float preco;
    private int quantidade;

    private static int cdAutoGerado;

    private Produto(String nome, String marca, float preco, int quantidade) {
        cdAutoGerado++;
        this.cdProduto = cdAutoGerado;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    public static Produto getInstance(String nome, String marca, float preco, int quantidade) {
        if (nome.length() <= 0) return null;
        if (marca.length() <= 0) return null;
        if (preco < 0) return null;
        if (quantidade < 0) return null;
        return new Produto(nome, marca, preco, quantidade);
    }

    public int getCdProduto() {
        return cdProduto;
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

    public void addQuantidade(int qtd) {
        this.quantidade += qtd;
    }
    public void subQuantidade(int qtd) {
        if (qtd > quantidade) return;
        this.quantidade -= qtd;
    }
    public void setQuantidade(int qtd) {
        this.quantidade = qtd;
    }
    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        DecimalFormat money = new DecimalFormat("R$ #,##0.00");
        return String.format(
        "{ Código: %d , Nome: %s , Marca: %s , Preço: %s , Quantidade: %d }", 
        this.cdProduto, this.nome, this.marca, money.format(this.preco), this.quantidade);
    }
}