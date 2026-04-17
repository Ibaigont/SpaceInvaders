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
        for (JokalariElementua e : osagaiak) {
            e.mugitu(norabidea);
        }
    }

    @Override
    public void suntsitu() {
        for (JokalariElementua e : osagaiak) {
            e.suntsitu();
        }
        osagaiak.clear();
    }

    @Override
    public List<Gelaxka> getGelaxkak() {
        List<Gelaxka> jokalariOsoarenGelaxkak = new ArrayList<>();
        for (JokalariElementua e : osagaiak) {
            jokalariOsoarenGelaxkak.addAll(e.getGelaxkak());
        }
        return jokalariOsoarenGelaxkak;
    }
}