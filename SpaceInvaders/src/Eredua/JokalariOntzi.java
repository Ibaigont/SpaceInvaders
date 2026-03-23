package Eredua;

public class JokalariOntzi extends Ontzia {

    public JokalariOntzi(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
    }

    public Tiroa tirokatu() {
        int tx = getX();
        int ty = getY() - 1;
        update();
        return new Tiroa(tx, ty);
    }

}