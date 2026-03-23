package Eredua;

public class Tiroa {
    private int x;
    private int y;

    public Tiroa(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void mugituGora() {
        this.y--;
    }
}
