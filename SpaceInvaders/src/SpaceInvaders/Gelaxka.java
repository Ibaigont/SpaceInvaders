package SpaceInvaders;

import java.util.Observable;

public class Gelaxka extends Observable {
    private int x;
    private int y;
    private Edukia edukia;

    public Gelaxka(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public Edukia getEdukia() { return this.edukia; }

    public void setEdukia(Edukia edukia) {
        this.edukia = edukia;
        setChanged();
        notifyObservers();
    }
}