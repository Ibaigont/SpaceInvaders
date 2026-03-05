package spaceInvaders;

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

    public abstract void mugitu(String norabidea);

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

    // Individual movement helpers
    /*public void mugituEzkerra() {
        x -= abiadura;
        update();
    }

    public void mugituEskuma() {
        x += abiadura;
        update(); 
    }

    public void mugituGora() {
        y -= abiadura;
        update();
    }

    public void mugituBehera() {
        y += abiadura;
        update();
    }
*/
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