package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EtsaiNodoa implements EtsaiElementua {
    private List<EtsaiElementua> osagaiak = new ArrayList<>();
    private EtsaiPortaera portaera;

    public EtsaiNodoa(EtsaiPortaera pPortaera) {
        this.portaera = pPortaera;
    }

    public void addElementu(EtsaiElementua e) { osagaiak.add(e); }
    public void removeElementu(EtsaiElementua e) { osagaiak.remove(e); }
    
    public EtsaiPortaera getPortaera() {
        return portaera;
    }

    public void eguneratu() {
        String norabidea = portaera.getNorabidea();
        if (norabidea != null) {
            mugitu(norabidea);
        }

        if (portaera.tiroEginNahiDu()) {
            List<Gelaxka> gelaxkak = getGelaxkak();
            if (!gelaxkak.isEmpty()) {
                int sumX = 0, sumY = 0;
                for (Gelaxka g : gelaxkak) {
                    sumX += g.getZabalera();
                    sumY += g.getAltuera();
                }
                int centroX = sumX / gelaxkak.size();
                int centroY = sumY / gelaxkak.size();
                EtsaiTiroElementua tiro = new EtsaiTiroElementua(centroX, centroY + 1);
                MatrizeEredua.getMatrizea().addEtsaiTiro(tiro);
            }
        }
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