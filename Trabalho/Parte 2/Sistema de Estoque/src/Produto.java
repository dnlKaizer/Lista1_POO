public class Produto {
    private int codigo;
    private String nome;
    private String marca;
    private float preco;
    private int quantidade;

    private static int codigoAutoGerado;

    private Produto(String nome, String marca, float preco, int quantidade) {
        codigoAutoGerado++;
        this.codigo = codigoAutoGerado;
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

    private Produto(int codigo, String nome, String marca, float preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    public static Produto getInstance(int codigo, String nome, String marca, float preco, int quantidade) {
        if (codigo < 0) return null;
        if (nome.length() <= 0) return null;
        if (marca.length() <= 0) return null;
        if (preco < 0) return null;
        if (quantidade < 0) return null;
        return new Produto(codigo, nome, marca, preco, quantidade);
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
        return new Produto(codigo, nome, marca, preco, quantidade);
    }
}