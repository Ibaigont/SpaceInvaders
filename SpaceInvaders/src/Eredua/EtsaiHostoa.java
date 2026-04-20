package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EtsaiHostoa implements EtsaiElementua {
    private Gelaxka gelaxka;

    public EtsaiHostoa(int x, int y) {
        this.gelaxka = new Gelaxka(x, y);
    }

    @Override
    public void mugitu(String norabidea) {
        int nx = gelaxka.getZabalera();
        int ny = gelaxka.getAltuera();
        
        if ("EZKERRA".equals(norabidea)) nx--;
        else if ("ESKUINA".equals(norabidea)) nx++;
        else if ("BEHERA".equals(norabidea)) ny++;
        
        this.gelaxka = new Gelaxka(nx, ny);
    }

    @Override
    public void suntsitu() {
        this.gelaxka.setEdukia(new HutsaEgoera());
    }

    @Override
    public List<Gelaxka> getGelaxkak() {
        List<Gelaxka> l = new ArrayList<>();
        l.add(gelaxka);
        return l;
    }
}