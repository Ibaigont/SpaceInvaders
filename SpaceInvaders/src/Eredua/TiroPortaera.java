package Eredua;

public interface TiroPortaera {
    public TiroElementua TiroMota(int x, int y);
}

class TiroBakuna implements TiroPortaera {
    @Override
    public TiroElementua TiroMota(int x, int y) {
        return new TiroPixela(x, y); 
    }
}

class TiroGezia implements TiroPortaera {
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

class TiroErronboa implements TiroPortaera {
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