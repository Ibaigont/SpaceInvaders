package Eredua;

import java.util.ArrayList;
import java.util.List;

public class JokalariOntzi extends Ontzia {
    private OntziPortaera oPortaera;
    private TiroPortaera tiroa;
    private int unekoTiroIndizea = 0;

    protected JokalariOntzi(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY, OntziPortaera pForma) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
        this.oPortaera = pForma; 
        
        tiroa = new TiroBakuna(); 
                
    }

    public List<Gelaxka> getFormaOsoa() {
        return oPortaera.ontziMota(getX(), getY());
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
    		if (oPortaera instanceof FormaGorria)
			tiroa = new TiroErronboa();
		} else {
			tiroa = new TiroBakuna();
		}
        
    }
    public TiroPortaera getTiroPortaera() {
   
        return tiroa; 
    }
    

    public TiroElementua tirokatu() {
        int tx = getX() + 1;
        if (this.oPortaera instanceof FormaBerdea) {
            tx = getX() + 2; 
        }
        int ty = getY() - 2;
        update();
        
        return tiroa.TiroMota(tx, ty);
    }
}