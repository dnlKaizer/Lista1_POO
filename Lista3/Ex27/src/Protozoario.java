import java.util.Random;

public class Protozoario {
    private int[] genotipo;
    private String string = "";
    private static Random random = new Random();
    
    private Protozoario(int[] genotipo) {
        this.genotipo = genotipo;
        if (random.nextInt(100) < 7) {
            this.mutate();
        }
        for (int i : genotipo) {
            this.string += i;
        }
    }
    public static Protozoario getInstance() {
        int[] genotipoAux = new int[10];
        for (int i = 0; i < 10; i++) {
            genotipoAux[i] = random.nextInt(0, 4);
        }
        return new Protozoario(genotipoAux);
    }
    public static Protozoario getInstance(int[] genotipo) {
        if (genotipo == null || genotipo.length != 10) {
            return null;
        }
        for (int gene : genotipo) {
            if (gene < 0 || gene > 3) {
                return null;
            }
        }
        return new Protozoario(genotipo);
    }

    public int[] getGenotipo() {
        return genotipo.clone();
    }
    public String toString() {
        return string;
    }

    public Protozoario getClone() {
        Protozoario clone = Protozoario.getInstance(genotipo);
        return clone;
    }

    public void mutate() {
        int index = random.nextInt(0, 10);
        genotipo[index]--;
        if (genotipo[index] < 0) {
            genotipo[index] += 2;
        }
    }

    public Protozoario mate(Protozoario parceiro) {
        int[] genotipoP = parceiro.getGenotipo();
        for (int i = 0; i < 10; i++) {
            if (random.nextBoolean()) {
                genotipoP[i] = genotipo[i];
            }
        }
        return new Protozoario(genotipoP);
    }

}
