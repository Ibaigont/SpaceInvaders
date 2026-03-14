package Eredua;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class JokoKudeaketa extends Observable {

    private static JokoKudeaketa nireKudeaketa = new JokoKudeaketa();
    private boolean jokoaHasita = false;

    private JokoKudeaketa() {}

    public static JokoKudeaketa getJokoKudeaketa() {
        return nireKudeaketa;
    }

    public void hasieratuJokoa() {
      
   
        jokoaHasita = true;
        MatrizeEredua.getMatrizea().matrizeaSortu();

        
        setChanged();
        notifyObservers("MTRX_SORTUTA");
     
    }

    public void egiaztatuAmaiera() {
     
        if (MatrizeEredua.getMatrizea().isJokoaAmaitua()) return;

        Gelaxka[][] gelaxka = MatrizeEredua.getMatrizea().getGelaxkak();
        int zabalera = MatrizeEredua.getMatrizea().getZabalera();
        int altuera = MatrizeEredua.getMatrizea().getAltuera();
        boolean anyEtsai = false;

        for (int x = 1; x < zabalera - 1; x++) {
            for (int y = 1; y < altuera - 1; y++) {
                Edukia e = gelaxka[x][y].getEdukia();
                if (e == Edukia.Etsaia) {
                    anyEtsai = true;
                    if (y >= altuera - 2) {
                        amaituJokoa(false);
                        return;
                    }
                }
                if (e == Edukia.EspazioOntzia) {
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;
                            if (nx >= 1 && nx < zabalera - 1 && ny >= 1 && ny < altuera - 1) {
                                if (gelaxka[nx][ny].getEdukia() == Edukia.Etsaia && ny == y) {
                                    amaituJokoa(false);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!anyEtsai) {
            amaituJokoa(true);
        }
    }

    public void amaituJokoa(boolean irabazi) {
        MatrizeEredua.getMatrizea().amaituJokoa();
        setChanged();
        notifyObservers(irabazi ? "IRABAZI" : "GALDU");
    }

    public boolean isJokoaHasita() { return jokoaHasita; }
}