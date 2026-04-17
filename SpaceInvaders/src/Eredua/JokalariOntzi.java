package Eredua;

import java.util.List;

public class JokalariOntzi extends Ontzia implements JokalariElementua {
    private OntziPortaera oPortaera;
    private TiroPortaera tiroa;
    private JokalariNodoa egitura;

    protected JokalariOntzi(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY, OntziPortaera pForma) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
        oPortaera = pForma; 
        tiroa = new TiroBakuna(); 
        egitura = new JokalariNodoa();
        List<Gelaxka> hasierakoGelaxkak = oPortaera.kalkulatuFormarenGelaxkak(hasieraX, hasieraY);
        
        for (Gelaxka g : hasierakoGelaxkak) {
            JokalariHostoa hostoa = new JokalariHostoa(g.getZabalera(), g.getAltuera());
             egitura.addElementu(hostoa);
        }
    }

    @Override
    public void mugitu(String norabidea) {
        int unekoX = getX();
        int unekoY = getY();
        super.mugitu(norabidea);
        if (unekoX != getX() || unekoY != getY()) {
            egitura.mugitu(norabidea);
        }
    }

    @Override
    public void suntsitu() {
    	egitura.suntsitu();
    }

    @Override
    public List<Gelaxka> getGelaxkak() {
        return egitura.getGelaxkak();
    }

    public List<Gelaxka> getFormaOsoa() {
        return getGelaxkak(); 
    }
    
    public void aldatuTiroMota() {
        if (tiroa instanceof TiroBakuna) {
            if (oPortaera instanceof FormaBerdea) {
                tiroa = new TiroGezia();
            } else if (oPortaera instanceof FormaUrdina) {
                tiroa = new TiroErronboa();
            } else if (oPortaera instanceof FormaGorria) {
                tiroa = new TiroGezia();
            }
        } else if (tiroa instanceof TiroGezia) {
            if (oPortaera instanceof FormaGorria) {
                tiroa = new TiroErronboa();
            } else {
                tiroa = new TiroBakuna();
            }
        } else {
            tiroa = new TiroBakuna();
        }
    }
    
    public TiroPortaera getTiroPortaera() {
        return tiroa; 
    }
    
    public TiroElementua tirokatu() {
        int tx = oPortaera.getTiroZentroX(getX()); 
        int ty = getY() - 2;
        
        return tiroa.TiroMota(tx, ty);
    }
}