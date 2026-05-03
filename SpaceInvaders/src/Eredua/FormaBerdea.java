package Eredua;

import java.util.ArrayList;
import java.util.List;

public class FormaBerdea extends JokalariOntzi {
	
	
	  public FormaBerdea(int x, int y, int minX, int maxX, int minY, int maxY) {
	        super(x, y, minX, maxX, minY, maxY);
	  }
    @Override
    public List<Gelaxka> kalkulatuForma(int x, int y) {
        List<Gelaxka> formak = new ArrayList<>();
        for (int i = 0; i < 5; i++) formak.add(new Gelaxka(x + i, y));
        for (int i = 1; i < 4; i++) formak.add(new Gelaxka(x + i, y - 1));
        formak.add(new Gelaxka(x + 1, y - 2));
        formak.add(new Gelaxka(x + 3, y - 2)); 
        return formak; 
    }

    @Override
    public int getTiroZentroX() {
        return getX() + 2 ;
    }
    @Override
    public void aldatuTiroMota() {
        if (tiroa instanceof TiroBakuna) {
            tiroa = new TiroGezia();
        } else {
            tiroa = new TiroBakuna();
        }
    }




}


