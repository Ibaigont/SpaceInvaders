package Eredua;

import java.util.List;

public class JokalariOntzi extends Ontzia {
	private OntziPortaera oPortaera;
	protected JokalariOntzi(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY, OntziPortaera pForma) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
        this.oPortaera = pForma; 
    }
        
    

    public List<Gelaxka> getFormaOsoa() {
        return oPortaera.ontziMota(getX(), getY());
    }
    public Tiroa tirokatu() {
        int tx = getX() + 1;
        
        if (this.oPortaera instanceof FormaBerdea) {
            tx = getX() + 2; 
        }
        
        int ty = getY() - 2;
        update();
        return new Tiroa(tx, ty);
    }

}