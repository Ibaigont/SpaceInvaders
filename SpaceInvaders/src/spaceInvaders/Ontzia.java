package spaceInvaders;

public class Ontzia {
    private int x;
    private int y;
    private static int abiadura = 1; 
    private int minX, maxX, minY, maxY;

    public Ontzia(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        this.x = hasieraX;
        this.y = hasieraY;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void mugitu(String norabidea) {
        if (norabidea.equals("EZKERRA") && x > minX) x -= abiadura;
        else if (norabidea.equals("ESKUINA") && x < maxX) x += abiadura;
        else if (norabidea.equals("GORA") && y > minY) y -= abiadura;
        else if (norabidea.equals("BEHERA") && y < maxY) y += abiadura;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}