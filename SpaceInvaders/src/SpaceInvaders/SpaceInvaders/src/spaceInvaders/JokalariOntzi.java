package spaceInvaders;

public class JokalariOntzi extends Ontzia {

    public JokalariOntzi(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
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