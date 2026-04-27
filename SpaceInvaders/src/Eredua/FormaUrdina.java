package Eredua;

import java.util.ArrayList;
import java.util.List;

public class FormaUrdina extends JokalariOntzi {

    public FormaUrdina(int x, int y, int minX, int maxX, int minY, int maxY) {
        super(x, y, minX, maxX, minY, maxY);
    }

    @Override
    protected List<Gelaxka> kalkulatuForma(int x, int y) {
        List<Gelaxka> formak = new ArrayList<>();
        for (int i = 0; i < 3; i++) formak.add(new Gelaxka(x + i, y));
        formak.add(new Gelaxka(x + 1, y - 1));
        return formak;
    }

    @Override
    public int getTiroZentroX() {
        return getX() + 1;
    }

    @Override
    public void aldatuTiroMota() {
        if (tiroa instanceof TiroBakuna) {
            tiroa = new TiroErronboa();
        } else {
            tiroa = new TiroBakuna();
        }
    }

}