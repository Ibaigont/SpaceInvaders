package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroPixela implements TiroElementua {
    private int x;
    private int y;

    public TiroPixela(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public void mugituGora() {
        this.y--;
    }
    
    public void mugituBehera() {
        this.y++;
    }

    @Override
    public List<TiroPixela> getPixelak() {
        List<TiroPixela> l = new ArrayList<>();
        l.add(this);
        return l;
    }
}