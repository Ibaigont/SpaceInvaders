package SpaceInvaders;

import java.util.Observable;

public abstract class Ontzia extends Observable {
    private int x;
    private int y;
    private static int abiadura = 1; 
    private boolean ezkerra;
    private boolean eskuma;
    private boolean gora;
    private boolean behera;
    private int minX, maxX, minY, maxY;

    public Ontzia(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        this.x = hasieraX;
        this.y = hasieraY;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    // Default movement implementation shared by player and enemies.
    public void mugitu(String norabidea) {
        int nx = x;
        int ny = y;
        int a = getAbiadura();

        if ("EZKERRA".equals(norabidea)) {
            nx = Math.max(getMinX(), x - a);
        } else if ("ESKUINA".equals(norabidea)) {
            nx = Math.min(getMaxX(), x + a);
        } else if ("GORA".equals(norabidea)) {
            ny = Math.max(getMinY(), y - a);
        } else if ("BEHERA".equals(norabidea)) {
            ny = Math.min(getMaxY(), y + a);
        }

        setX(nx);
        setY(ny);
        update();
    }

    public void setEzkerra(boolean ezk) {
        this.ezkerra = ezk;
    }

    public void setEskuma(boolean esk) {
        this.eskuma = esk;
    }

    public void setGora(boolean gora) {
        this.gora = gora;
    }

    public void setBehera(boolean behera) {
        this.behera = behera;
    }

    public void update() {
        setChanged();
        notifyObservers();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Protected helpers for subclasses to adjust coordinates
    protected int getMinX() { return minX; }
    protected int getMaxX() { return maxX; }
    protected int getMinY() { return minY; }
    protected int getMaxY() { return maxY; }
    protected int getAbiadura() { return abiadura; }

    protected void setX(int x) { this.x = x; }
    protected void setY(int y) { this.y = y; }
}