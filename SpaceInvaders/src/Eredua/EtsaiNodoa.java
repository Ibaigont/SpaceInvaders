package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EtsaiNodoa implements EtsaiElementua {
    
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