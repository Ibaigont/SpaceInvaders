package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroKonposatua implements TiroElementua {
    private List<TiroElementua> osagaiak = new ArrayList<>();

    public void addElementu(TiroElementua e) {
        osagaiak.add(e);
    }

    @Override
    public void mugituGora() {
        osagaiak.forEach(TiroElementua::mugituGora);
    }

    @Override
    public List<TiroPixela> getPixelak() {
        List<TiroPixela> l = new ArrayList<>();
        osagaiak.forEach(e -> l.addAll(e.getPixelak()));
        return l;
    }
}