package Eredua;

public class PortaeraTiratzaile implements EtsaiPortaera {
    private int tiroAtzerapena = 0;

    @Override
    public String getNorabidea() {
        int r = (int) (Math.random() * 4);
        if (r == 0) return "EZKERRA";
        else if (r == 1) return "ESKUINA";
        else if (r == 2) return "BEHERA";
        else return null;
    }

    @Override
    public boolean tiroEginNahiDu() {
        tiroAtzerapena++;
        if (tiroAtzerapena >= 8) {
            tiroAtzerapena = 0;
            return true;
        }
        return false;
    }

    @Override
    public int getTiroX() {
        return 0;
    }

    @Override
    public int getTiroY() {
        return 0; 
    }
}