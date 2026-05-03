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
        osagaiak.forEach(e -> e.mugitu(norabidea));
    }

    @Override
    public void suntsitu() {
        osagaiak.forEach(EtsaiElementua::suntsitu);
        osagaiak.clear();
    }

    @Override
    public List<Gelaxka> getGelaxkak() {
        List<Gelaxka> etsaiOsoarenGelaxkak = new ArrayList<>();
        osagaiak.forEach(e -> etsaiOsoarenGelaxkak.addAll(e.getGelaxkak()));
        return etsaiOsoarenGelaxkak;
    }
}