package Eredua;

public class PortaeraNormala implements EtsaiPortaera {
    @Override
    public String getNorabidea() {
        int r = (int) (Math.random() * 3);
        if (r == 0) return "EZKERRA";
        else if (r == 1) return "ESKUINA";
        else return "BEHERA";
    }

    @Override
    public boolean tiroEginNahiDu() { return false; }
    @Override public int getTiroX() { return 0; }
    @Override public int getTiroY() { return 0; }
}