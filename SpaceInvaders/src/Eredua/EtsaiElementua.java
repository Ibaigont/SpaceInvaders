package Eredua;

import java.util.ArrayList;
import java.util.List;

public interface EtsaiElementua {
    public void mugitu(String norabidea);
    public void suntsitu();
    public List<Gelaxka> getGelaxkak();
}

class EtsaiHostoa implements EtsaiElementua {
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

// Clase package-private
class EtsaiNodoa implements EtsaiElementua {
    private List<EtsaiElementua> osagaiak = new ArrayList<>();

    public void addElementu(EtsaiElementua e) {
        osagaiak.add(e);
    }
    
    public void removeElementu(EtsaiElementua e) {
        osagaiak.remove(e);
    }

    @Override
    public void mugitu(String norabidea) {
        for (EtsaiElementua e : osagaiak) {
            e.mugitu(norabidea);
        }
    }

    @Override
    public void suntsitu() {
        for (EtsaiElementua e : osagaiak) {
            e.suntsitu();
        }
        osagaiak.clear();
    }

    @Override
    public List<Gelaxka> getGelaxkak() {
        List<Gelaxka> etsaiOsoarenGelaxkak = new ArrayList<>();
        for (EtsaiElementua e : osagaiak) {
            etsaiOsoarenGelaxkak.addAll(e.getGelaxkak());
        }
        return etsaiOsoarenGelaxkak;
    }
}