package Eredua;

import java.util.ArrayList;
import java.util.List;

public class JokalariHostoa implements JokalariElementua {
    private Gelaxka gelaxka;

    public JokalariHostoa(int x, int y) {
        this.gelaxka = new Gelaxka(x, y);
    }

    @Override
    public void mugitu(String norabidea) {
        int nx = gelaxka.getZabalera();
        int ny = gelaxka.getAltuera();
        
        if ("EZKERRA".equals(norabidea)) nx--;
        else if ("ESKUINA".equals(norabidea)) nx++;
        else if ("GORA".equals(norabidea)) ny--;
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