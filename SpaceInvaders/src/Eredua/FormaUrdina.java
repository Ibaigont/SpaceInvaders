package Eredua;

import java.util.ArrayList;
import java.util.List;

public class FormaUrdina implements OntziPortaera {
    public List<Gelaxka> ontziMota(int x, int y) {
        List<Gelaxka> formak = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            formak.add(new Gelaxka(x + i, y));
        }
        formak.add(new Gelaxka(x + 1, y - 1));
        
        return formak;
    }
}