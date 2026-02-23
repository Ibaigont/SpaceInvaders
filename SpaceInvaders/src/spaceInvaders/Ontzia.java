package spaceInvaders;

import java.util.Observable;

public class Ontzia extends Observable {
    private int x;
    private int y;
    private static int abiadura = 2;
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

    public void mugituEzkerra() {
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

    public void update() {
        limitar();
        setChanged();
        notifyObservers();
    }

    private void limitar() {
        if (x < minX) x = minX;
        if (x > maxX) x = maxX;
        if (y < minY) y = minY;
        if (y > maxY) y = maxY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
