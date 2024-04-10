public class Produto {
    int codigo;
    String nome;
    String marca;
    float preco;
    int quantidade;

    void addCodigo(int codigo) {
        this.codigo = codigo;
    }
    int lerCodigo() {
        return codigo;
    }
    
    void addNome(String nome) {
        this.nome = nome;
    }
    String lerNome() {
        return nome;
    }
    
    void addMarca(String marca) {
        this.marca = marca;
    }
    String lerMarca() {
        return marca;
    }

    void addPreco(float preco) {
        this.preco = preco;
    }
    float lerPreco() {
        return preco;
    }

    void addQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    int lerQuantidade() {
        return quantidade;
    }
}