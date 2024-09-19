package Ex41.entidades.animal;

import Ex41.entidades.animal.interfaces.AnimalDeEstimacao;

public class Gato extends Animal implements AnimalDeEstimacao {

    public Gato(String nome) {
        super(nome, 4);
    }
    public Gato() {
        this("");
    }

    @Override
    public void comer() {
        System.out.println("Gato comeu.");
    }
    
    @Override
    public void brincar() {
        System.out.println("Gato brincou.");    
    }
}
