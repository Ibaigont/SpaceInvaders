package Eredua;

import java.util.ArrayList;
import java.util.List;

public class JokalariNodoa implements JokalariElementua {
    private List<JokalariElementua> osagaiak = new ArrayList<>();

    public void addElementu(JokalariElementua e) {
        osagaiak.add(e);
    }
    
    public void removeElementu(JokalariElementua e) {
        osagaiak.remove(e);
    }

    @Override
    public void mugitu(String norabidea) {
        osagaiak.forEach(e -> e.mugitu(norabidea));
    }

    @Override
    public void suntsitu() {
        osagaiak.forEach(JokalariElementua::suntsitu);
        osagaiak.clear();
    }

    @Override
    public List<Gelaxka> getGelaxkak() {
        List<Gelaxka> jokalariOsoarenGelaxkak = new ArrayList<>();
        osagaiak.forEach(e -> jokalariOsoarenGelaxkak.addAll(e.getGelaxkak()));
        return jokalariOsoarenGelaxkak;
    }
}