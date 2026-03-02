package spaceInvaders;

public class Etsaiak extends Ontzia {

    // Constructor matching Ontzia's signature
    public Etsaiak(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
    }

    @Override
    public void mugitu(String norabidea) {
        int x = getX();
        int y = getY();
        int abiadura = getAbiadura();

        if (norabidea.equals("EZKERRA") && x > getMinX()) {
            x -= abiadura;
        } else if (norabidea.equals("ESKUINA") && x < getMaxX()) {
            x += abiadura;
        } else if (norabidea.equals("GORA") && y > getMinY()) {
            y -= abiadura;
        } else if (norabidea.equals("BEHERA") && y < getMaxY()) {
            y += abiadura;
        }

        setX(x);
        setY(y);
        update();
    }

    // Additional enemy-specific behavior can be added here
    public void itsasiakBehavior() {
        // ...existing code...
    }
}