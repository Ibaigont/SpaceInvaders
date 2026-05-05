package Eredua;

public class PortaeraAzkar implements EtsaiPortaera {
    private int mugimenduGehigarri = 0;

    @Override
    public String getNorabidea() {
        double r = Math.random();
        if (r < 0.7) return "BEHERA"; 
        else if (r < 0.85) return "EZKERRA";
        else return "ESKUINA";
    }

    @Override
    public boolean tiroEginNahiDu() { return false; }
    @Override public int getTiroX() { return 0; }
    @Override public int getTiroY() { return 0; }
}