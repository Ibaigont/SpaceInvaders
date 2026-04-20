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
    boolean tiroEginDa;
    private int tiroKopurua;

    private JokoKudeaketa() {}

    public static JokoKudeaketa getJokoKudeaketa() {
        return nireKudeaketa;
    }

    public void teklaSakatu(String tekla) {
        if (tekla.equals("TIROA")) {
            if (!tiroEginDa) { 
                teclasPresionadas.add(tekla);
                tiroEginDa = true; 
            }
        } else {
            teclasPresionadas.add(tekla);
        }
    }

    public void teklaAskatu(String tekla) {
        teclasPresionadas.remove(tekla);
        if (tekla.equals("TIROA")) {
            tiroEginDa = false;
        }
    }

    public void hasieratuJokoa(String pkol) {
        // RESET GARRANTZITSUAK
        this.jokoaHasita = true;
        this.tickKontagailua = 0;
        this.teclasPresionadas.clear();
        this.tiroEginDa = false;
        this.tiroKopurua = 0;

        MatrizeEredua.getMatrizea().matrizeaSortu(pkol);

        setChanged();
        notifyObservers("MTRX_SORTUTA");

        if (jokoBegizta != null) {
            jokoBegizta.cancel();
            jokoBegizta.purge();
        }
        
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
            teclasPresionadas.remove("TIROA"); 
        }
        if (teclasPresionadas.contains("TIROA_ALDATU")) {
			MatrizeEredua.getMatrizea().ontziarenTiroaAldatu();
			teclasPresionadas.remove("TIROA_ALDATU"); 
		}
        MatrizeEredua.getMatrizea().jokoZikloaEguneratu();

        tickKontagailua++;
        if (tickKontagailua >= 4) {
            MatrizeEredua.getMatrizea().etsaiakMugitu();
            egiaztatuAmaiera();
            tickKontagailua = 0;
        }
        berrituBistakoDatuak();
    }
    public void egiaztatuAmaiera() {
        // Ez begiratu ezer jokoa amaituta badago
        if (MatrizeEredua.getMatrizea().isJokoaAmaitua()) return;

        Gelaxka[][] gelaxkak = MatrizeEredua.getMatrizea().getGelaxkak();
        int zabalera = MatrizeEredua.getMatrizea().getZabalera();
        int altuera = MatrizeEredua.getMatrizea().getAltuera();
        boolean etsaiakBadaude = false;

        for (int x = 1; x < zabalera - 1; x++) {
            for (int y = 1; y < altuera - 1; y++) {
                EdukiaEgoera e = gelaxkak[x][y].getEdukia();
                if (e instanceof EtsaiEgoera) {
                    etsaiakBadaude = true;
                    // BALDINTZA: Etsaia beheko mugaraino iritsi bada, GALDU
                    if (y >= altuera - 2) {
                        amaituJokoa(false);
                        return;
                    }
                }
            }
        }

        // BALDINTZA: Etsai guztiak hil badira, IRABAZI
        if (!etsaiakBadaude) {
            amaituJokoa(true);
        }
    }
    public void berrituBistakoDatuak() {
        String munizioa = MatrizeEredua.getMatrizea().getUnekoMunizioaTestua();
        Bista.JokoPanela.getJokoPanela().eguneratuInfo(this.tiroKopurua, munizioa);
    }

    // Tiro bat egitean deituko dugu kontagailua igotzeko
    public void tiroaKontatuEtaBerritu() {
        this.tiroKopurua++;
        berrituBistakoDatuak();
    }

    public void amaituJokoa(boolean irabazi) {
        MatrizeEredua.getMatrizea().amaituJokoa();
        if (jokoBegizta != null) {
            jokoBegizta.cancel();
            jokoBegizta.purge();
        }
        setChanged();
        notifyObservers(irabazi ? "IRABAZI" : "GALDU");
    }

    public boolean isJokoaHasita() { return jokoaHasita; }


}