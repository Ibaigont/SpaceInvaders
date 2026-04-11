package Eredua;

public class EtsaiFactory {
    	
    private static EtsaiFactory nireEtsaiFactory;

    private EtsaiFactory() {}

    public static EtsaiFactory getEtsaiFactory() {
        if (nireEtsaiFactory == null) {
            nireEtsaiFactory = new EtsaiFactory();
        }
        return nireEtsaiFactory;
    }

    public EtsaiElementua createEtsaia(int mota, int x, int y) {
        EtsaiNodoa etsaia = new EtsaiNodoa();
        
        if (mota == 0) { // karratua
            etsaia.addElementu(new EtsaiHostoa(x, y));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y));
            etsaia.addElementu(new EtsaiHostoa(x, y + 1));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y + 1));
            
        } else if (mota == 1) { // laukizuzena
            etsaia.addElementu(new EtsaiHostoa(x, y));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y));
            etsaia.addElementu(new EtsaiHostoa(x + 2, y));
            etsaia.addElementu(new EtsaiHostoa(x, y + 1));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y + 1));
            etsaia.addElementu(new EtsaiHostoa(x + 2, y + 1));
            
        } else if (mota == 2) {	// T forma
            etsaia.addElementu(new EtsaiHostoa(x, y));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y));
            etsaia.addElementu(new EtsaiHostoa(x + 2, y));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y + 1));
            
        } else if (mota == 3) { // fitxategiko forma
            etsaia.addElementu(new EtsaiHostoa(x, y));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y));
            etsaia.addElementu(new EtsaiHostoa(x + 3, y));
            etsaia.addElementu(new EtsaiHostoa(x + 4, y));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y + 1));
            etsaia.addElementu(new EtsaiHostoa(x + 2, y + 1));
            etsaia.addElementu(new EtsaiHostoa(x + 3, y + 1));
            etsaia.addElementu(new EtsaiHostoa(x + 2, y + 2));    
            
        } else { // karratu forma errorerik balego
            etsaia.addElementu(new EtsaiHostoa(x, y));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y));
            etsaia.addElementu(new EtsaiHostoa(x, y + 1));
            etsaia.addElementu(new EtsaiHostoa(x + 1, y + 1));
        }
        
        return etsaia;
    }
}