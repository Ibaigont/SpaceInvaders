package Eredua;

public class TiroGezia implements TiroPortaera {
    private int munizioa = 30;
   
    public int getMunizioa() {
        return this.munizioa;
    }

    @Override
    public TiroElementua TiroMota(int x, int y) {
        if (munizioa > 0) {
            munizioa--;
            TiroKonposatua gezia = new TiroKonposatua();        
            gezia.addElementu(new TiroPixela(x, y - 1));
            gezia.addElementu(new TiroPixela(x - 1, y));
            gezia.addElementu(new TiroPixela(x + 1, y));
            
            return gezia;
        }
        return null;
    }
}