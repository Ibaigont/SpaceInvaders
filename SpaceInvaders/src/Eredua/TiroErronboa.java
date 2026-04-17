package Eredua;

public class TiroErronboa implements TiroPortaera {
    private int munizioa = 20; 
    
    public int getMunizioa() {
        return this.munizioa;
    }

    @Override
    public TiroElementua TiroMota(int x, int y) {
        if (munizioa > 0) {
            munizioa--;
            TiroKonposatua erronboa = new TiroKonposatua();
            erronboa.addElementu(new TiroPixela(x, y - 4));
            erronboa.addElementu(new TiroPixela(x - 1, y - 3));
            erronboa.addElementu(new TiroPixela(x, y - 3));
            erronboa.addElementu(new TiroPixela(x + 1, y - 3));
            erronboa.addElementu(new TiroPixela(x - 2, y - 2));
            erronboa.addElementu(new TiroPixela(x - 1, y - 2));
            erronboa.addElementu(new TiroPixela(x, y - 2));
            erronboa.addElementu(new TiroPixela(x + 1, y - 2));
            erronboa.addElementu(new TiroPixela(x + 2, y - 2));
            erronboa.addElementu(new TiroPixela(x - 1, y - 1));
            erronboa.addElementu(new TiroPixela(x, y - 1));
            erronboa.addElementu(new TiroPixela(x + 1, y - 1));
            erronboa.addElementu(new TiroPixela(x, y));
            
            return erronboa;
        }
        return null;
    }
}