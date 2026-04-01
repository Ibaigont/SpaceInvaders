package Eredua;

import java.util.ArrayList;
import java.util.List;

public class FormaGorria implements OntziPortaera {
	public List<Gelaxka> ontziMota(int x, int y) {
        List<Gelaxka> formak = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            formak.add(new Gelaxka(x + i, y));
        }
        for (int i = 0; i < 4; i++) {
            formak.add(new Gelaxka(x + i, y - 1));
        }
        formak.add(new Gelaxka(x, y - 2));
        formak.add(new Gelaxka(x + 3, y - 2));
        
        return formak;
    }

}