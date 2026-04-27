package Eredua;

public class JokalariOntziFactory {

    private static JokalariOntziFactory nireJokalariOntziFactory;

    private JokalariOntziFactory() {
    }

    public static JokalariOntziFactory getJokalariOntziFactory() {
        if (nireJokalariOntziFactory == null) {
            nireJokalariOntziFactory = new JokalariOntziFactory();
        }
        return nireJokalariOntziFactory;
    }

    
    public JokalariOntzi sortuJokalariOntzia(String kolorea, int x, int y, int minX, int maxX, int minY, int maxY) {
        if (kolorea.equalsIgnoreCase("Berdea")) {
            return new FormaBerdea(x, y, minX, maxX, minY, maxY);
        } else if (kolorea.equalsIgnoreCase("Gorria")) {
            return new FormaGorria(x, y, minX, maxX, minY, maxY);
        } else {
            return new FormaUrdina(x, y, minX, maxX, minY, maxY);
        }
    }

    public JokalariOntzi sortuJokalariOntziMatrizetik(String kolorea, int zabalera, int altuera) {
        int hasieraX = zabalera / 2;
        int hasieraY = altuera - 5;
        int maxX;
        int minY;
        
        if (kolorea.equalsIgnoreCase("Berdea")) {
            maxX = zabalera - 7;
            minY = 3;
        } else if (kolorea.equalsIgnoreCase("Gorria")) {
            maxX = zabalera - 5;
            minY = 3;
        } else {
            maxX = zabalera - 4;
            minY = 2;
        }
        return sortuJokalariOntzia(kolorea, hasieraX, hasieraY, 1, maxX, minY, altuera - 2);
    }
}