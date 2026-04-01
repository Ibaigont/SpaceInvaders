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

    public JokalariOntzi sortuJokalariOntzia(String kolorea, int x, int y, int minX, int maxX, int minY, int maxY) {
        OntziPortaera forma;

        if (kolorea.equalsIgnoreCase("Berdea")) {
            forma = new FormaBerdea();
        } else if (kolorea.equalsIgnoreCase("Gorria")) {
            forma = new FormaGorria();
        } else {
            forma = new FormaUrdina();
        }
        return new JokalariOntzi(x, y, minX, maxX, minY, maxY, forma);
    }
    public JokalariOntzi sortuJokalariOntziMatrizetik(String kolorea, int zabalera, int altuera) {
        int hasieraX = zabalera / 2;
        int hasieraY = altuera - 5;
        return sortuJokalariOntzia(kolorea, hasieraX, hasieraY, 1, zabalera - 2, 1, altuera - 2);
    
    }
}