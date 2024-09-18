package Ex41.entidades.animal;

public class Aranha extends Animal {
    
    public Aranha(String nome) {
        super(nome, 8);
    }

    @Override
    protected void comer() {
        System.out.println("Aranha comeu.");
    }
    
}