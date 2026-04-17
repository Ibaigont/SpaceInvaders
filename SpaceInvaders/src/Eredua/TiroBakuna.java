package Eredua;

public class TiroBakuna implements TiroPortaera {
    @Override
    public TiroElementua TiroMota(int x, int y) {
        return new TiroPixela(x, y); 
    }
}