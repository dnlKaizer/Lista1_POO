import java.util.Random;

public class Protozoario {
    int[] genotipo = new int[10];

    public Protozoario() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            genotipo[i] = random.nextInt(0, 4);
        }
    }

    private Protozoario(int[] genotipo) {
        this.genotipo = genotipo;
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
}
