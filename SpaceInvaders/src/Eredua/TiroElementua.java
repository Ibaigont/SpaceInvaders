package Eredua;

import java.util.ArrayList;
import java.util.List;

public interface TiroElementua {
    public void mugituGora();
    public List<TiroPixela> getPixelak();
}

class TiroPixela implements TiroElementua {
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

    @Override
    public List<TiroPixela> getPixelak() {
        List<TiroPixela> l = new ArrayList<>();
        l.add(this);
        return l;
    }
}

class TiroKonposatua implements TiroElementua {
    private List<TiroElementua> osagaiak = new ArrayList<>();

    public void addElementu(TiroElementua e) {
        osagaiak.add(e);
    }

    @Override
    public void mugituGora() {
        for (TiroElementua e : osagaiak) {
            e.mugituGora(); 
        }
    }

    @Override
    public List<TiroPixela> getPixelak() {
        List<TiroPixela> l = new ArrayList<>();
        for (TiroElementua e : osagaiak) {
            l.addAll(e.getPixelak());
        }
        return l;
    }
}