package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EtsaiTiroElementua implements TiroElementua {
    private TiroPixela pixel;

    public EtsaiTiroElementua(int x, int y) {
        this.pixel = new TiroPixela(x, y);
    }

    @Override
    public void mugituGora() {
    }

    public void mugituBehera() {
        pixel.mugituBehera();
    }

    @Override
    public List<TiroPixela> getPixelak() {
        List<TiroPixela> l = new ArrayList<>();
        l.add(pixel);
        return l;
    }
}