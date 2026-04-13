package Eredua;

import java.util.ArrayList;
import java.util.List;

public class JokalariOntzi extends Ontzia {
    private OntziPortaera oPortaera;
    private List<TiroPortaera> tiroAukerak;
    private int unekoTiroIndizea = 0;

    protected JokalariOntzi(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY, OntziPortaera pForma) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
        this.oPortaera = pForma; 
        
        tiroAukerak = new ArrayList<>();
        tiroAukerak.add(new TiroBakuna()); 
        
        if (pForma instanceof FormaBerdea) {
            tiroAukerak.add(new TiroGezia());
        } else if (pForma instanceof FormaUrdina) {
            tiroAukerak.add(new TiroErronboa());
        } else if (pForma instanceof FormaGorria) {
            tiroAukerak.add(new TiroGezia());
            tiroAukerak.add(new TiroErronboa());
        }
    }

    public List<Gelaxka> getFormaOsoa() {
        return oPortaera.ontziMota(getX(), getY());
    }
    
    public void aldatuTiroMota() {
        unekoTiroIndizea++;
        if (unekoTiroIndizea >= tiroAukerak.size()) {
            unekoTiroIndizea = 0; 
        }
    }
    public TiroPortaera getTiroPortaera() {
   
        return tiroAukerak.get(unekoTiroIndizea); 
    }
    

    public TiroElementua tirokatu() {
        int tx = getX() + 1;
        if (this.oPortaera instanceof FormaBerdea) {
            tx = getX() + 2; 
        }
        int ty = getY() - 2;
        update();
        
        return tiroAukerak.get(unekoTiroIndizea).TiroMota(tx, ty);
    }
}