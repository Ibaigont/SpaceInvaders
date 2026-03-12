package Eredua;

import java.util.Observable;
import Bista.GelaxkaBista;
import Bista.JokoPanela;

@SuppressWarnings("deprecation")
public class JokoKudeaketa extends Observable {

    private static JokoKudeaketa nireKudeaketa = new JokoKudeaketa();
    private boolean jokoaHasita = false;

    private JokoKudeaketa() {}

    public static JokoKudeaketa getJokoKudeaketa() {
        return nireKudeaketa;
    }

    public void hasieratuJokoa() {
        MatrizeEredua eredua = MatrizeEredua.getMatrizea();
        jokoaHasita = true;
        eredua.matrizeaSortu();
        Gelaxka[][] gelaxkak = eredua.getGelaxkak();
        int zabalera = eredua.getZabalera();
        int altuera = eredua.getAltuera();

        GelaxkaBista[][] bistaMatrizea = new GelaxkaBista[zabalera][altuera];
        for (int y = 0; y < altuera; y++) {
            for (int x = 0; x < zabalera; x++) {
                bistaMatrizea[x][y] = new GelaxkaBista();
                gelaxkak[x][y].addObserver(bistaMatrizea[x][y]);
            }
        }

        JokoPanela.getJokoPanela().hasieratu(bistaMatrizea, zabalera, altuera);

        eredua.bistaEguneratu();
        setChanged();
        notifyObservers("MTRX_SORTUTA");
    }

    public void egiaztatuAmaiera() {
        MatrizeEredua eredua = MatrizeEredua.getMatrizea();
        if (eredua.isJokoaAmaitua()) return;

        Gelaxka[][] gelaxka = eredua.getGelaxkak();
        int zabalera = eredua.getZabalera();
        int altuera = eredua.getAltuera();
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