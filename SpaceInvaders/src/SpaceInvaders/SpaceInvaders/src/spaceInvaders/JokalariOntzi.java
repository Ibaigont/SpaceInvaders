package spaceInvaders;

public class JokalariOntzi extends Ontzia {

    public JokalariOntzi(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
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

    // Shooting logic moved from MatrizeEredua
    public void tirokatu(Gelaxka[][] gelaxka) {
        int tx = getX();
        int ty = getY() - 1;

        if (ty > 0 && gelaxka[tx][ty].getEdukia() == Edukia.Hutsa) {
            gelaxka[tx][ty].setEdukia(Edukia.Tiroa);
            update();
        }
    }

}