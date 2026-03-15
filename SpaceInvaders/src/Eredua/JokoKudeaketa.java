package Eredua;

import java.util.Observable;
import java.util.Set;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class JokoKudeaketa extends Observable {

    private static JokoKudeaketa nireKudeaketa = new JokoKudeaketa();
    private boolean jokoaHasita = false;
    private Timer jokoBegizta;
    private int tickKontagailua = 0;
    private Set<String> teclasPresionadas = new HashSet<>();

    private JokoKudeaketa() {}

    public static JokoKudeaketa getJokoKudeaketa() {
        return nireKudeaketa;
    }

    public void teklaSakatu(String tekla) {
        teclasPresionadas.add(tekla);
    }

    public void teklaAskatu(String tekla) {
        teclasPresionadas.remove(tekla);
    }

    public void hasieratuJokoa() {
        jokoaHasita = true;
        MatrizeEredua.getMatrizea().matrizeaSortu();

        setChanged();
        notifyObservers("MTRX_SORTUTA");

        if (jokoBegizta != null) jokoBegizta.cancel();
        jokoBegizta = new Timer("JokoBegizta", true);
        jokoBegizta.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                jokoZikloa();
            }
        }, 0, 50);
    }

    private void jokoZikloa() {
        if (MatrizeEredua.getMatrizea().isJokoaAmaitua()) {
            if (jokoBegizta != null) jokoBegizta.cancel();
            return;
        }

        if (teclasPresionadas.contains("EZKERRA")) {
            MatrizeEredua.getMatrizea().ontziaMugitu("EZKERRA");
        } else if (teclasPresionadas.contains("ESKUINA")) {
            MatrizeEredua.getMatrizea().ontziaMugitu("ESKUINA");
        } else if (teclasPresionadas.contains("GORA")) {
            MatrizeEredua.getMatrizea().ontziaMugitu("GORA");
        } else if (teclasPresionadas.contains("BEHERA")) {
            MatrizeEredua.getMatrizea().ontziaMugitu("BEHERA");
        }

        if (teclasPresionadas.contains("TIROA")) {
            MatrizeEredua.getMatrizea().tirokatu();
        }

        MatrizeEredua.getMatrizea().jokoZikloaEguneratu();

        tickKontagailua++;
        if (tickKontagailua >= 4) {
            MatrizeEredua.getMatrizea().etsaiakMugitu();
            egiaztatuAmaiera();
            tickKontagailua = 0;
        }
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
        if (jokoBegizta != null) jokoBegizta.cancel();
        setChanged();
        notifyObservers(irabazi ? "IRABAZI" : "GALDU");
    }

    public boolean isJokoaHasita() { return jokoaHasita; }
}