package Eredua;

import java.util.List;

public abstract class JokalariOntzi extends Ontzia implements JokalariElementua {
    protected TiroPortaera tiroa;
    protected JokalariNodoa egitura;

    protected JokalariOntzi(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
        this.tiroa = new TiroBakuna();
        this.egitura = new JokalariNodoa();
        

        List<Gelaxka> hasierakoGelaxkak = kalkulatuForma(hasieraX, hasieraY);
        
        for (Gelaxka g : hasierakoGelaxkak) {
            JokalariHostoa hostoa = new JokalariHostoa(g.getZabalera(), g.getAltuera());
            egitura.addElementu(hostoa);
        }
    }
    protected abstract List<Gelaxka> kalkulatuForma(int x, int y);
    public abstract int getTiroZentroX();

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
    public void suntsitu() { egitura.suntsitu(); }
    
    public List<Gelaxka> getFormaOsoa() {
        return getGelaxkak(); 
    }

    @Override
    public List<Gelaxka> getGelaxkak() { return egitura.getGelaxkak(); }
    
    public abstract void aldatuTiroMota();

    public TiroElementua tirokatu() {
        int tx = getTiroZentroX(); 
        int ty = getY() - 2;
        return tiroa.TiroMota(tx, ty);
    }
	public TiroPortaera getTiroPortaera() {
		return this.tiroa;
	}
}