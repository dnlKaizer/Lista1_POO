package Ex41.entidades.animal;

public abstract class Animal {
    protected int numeroPernas;
    protected String nome;

    protected Animal(String nome, int numeroPernas) {
        this.numeroPernas = numeroPernas;
        this.nome = nome;
    }

    protected void caminhar() {
        System.out.println("Anda com " + numeroPernas + " pernas.");
    }

    protected abstract void comer();

    public int getNumeroPernas() {
        return numeroPernas;
    }

    public void setNumeroPernas(int numeroPernas) {
        this.numeroPernas = numeroPernas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
