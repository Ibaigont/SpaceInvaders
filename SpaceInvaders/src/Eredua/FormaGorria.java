package Eredua;

import java.util.ArrayList;
import java.util.List;

public class FormaGorria implements OntziPortaera {
    @Override
    public List<Gelaxka> kalkulatuFormarenGelaxkak(int x, int y) {
        List<Gelaxka> formak = new ArrayList<>();
        for (int i = 0; i < 3; i++) formak.add(new Gelaxka(x + i, y));
        for (int i = 0; i < 3; i++) formak.add(new Gelaxka(x + i, y - 1));
        formak.add(new Gelaxka(x, y - 2));
        formak.add(new Gelaxka(x + 2, y - 2));
        return formak;
    }

    @Override
    public int getTiroZentroX(int x) {
        return x + 1;
    }
}	