package Eredua;

public class JokalariOntziFactory {

    private static JokalariOntziFactory nireJokalariOntziFactory;

    private JokalariOntziFactory() {}

    public static JokalariOntziFactory getJokalariOntziFactory() {
        if (nireJokalariOntziFactory == null) {
            nireJokalariOntziFactory = new JokalariOntziFactory();
        }
        return nireJokalariOntziFactory;
    }

    public JokalariOntzi sortuJokalariOntzia(int hasieraX, int hasieraY,
                                               int minX, int maxX,
                                               int minY, int maxY) {
        return new JokalariOntzi(hasieraX, hasieraY, minX, maxX, minY, maxY);
    }

    public JokalariOntzi sortuJokalariOntziMatrizetik(int zabalera, int altuera) {
        int hasieraX = zabalera / 2;
        int hasieraY = altuera - 5;
        return new JokalariOntzi(hasieraX, hasieraY, 1, zabalera - 2, 1, altuera - 2);
    }
}