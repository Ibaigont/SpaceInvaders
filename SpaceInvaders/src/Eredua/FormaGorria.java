package Eredua;

import java.util.ArrayList;
import java.util.List;

public class FormaGorria extends JokalariOntzi {
	
	  public FormaGorria(int x, int y, int minX, int maxX, int minY, int maxY) {
	        super(x, y, minX, maxX, minY, maxY);
	    }
    @Override
    public List<Gelaxka> kalkulatuForma(int x, int y) {
        List<Gelaxka> formak = new ArrayList<>();
        for (int i = 0; i < 3; i++) formak.add(new Gelaxka(x + i, y));
        for (int i = 0; i < 3; i++) formak.add(new Gelaxka(x + i, y - 1));
        formak.add(new Gelaxka(x, y - 2));
        formak.add(new Gelaxka(x + 2, y - 2));
        return formak;
    }

    @Override
    public int getTiroZentroX() {
        return getX() +1;
    }
    @Override
    public void aldatuTiroMota() {
        if (this.tiroa instanceof TiroBakuna) {
            this.tiroa = new TiroGezia();
        } 

        else if (this.tiroa instanceof TiroGezia) {
            this.tiroa = new TiroErronboa();
        } 

        else {
            this.tiroa = new TiroBakuna();
        }
    }
        
    
}	