package Eredua;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class MatrizeEredua {

    private Gelaxka[][] gelaxka;
    private static MatrizeEredua nireMatrizea = new MatrizeEredua();
    private int altuera = 60;
    private int zabalera = 100;
    private int etsaiKop = 0;
    private int etsaiMin = 4;
    private int etsaiMax = 8;
    
    private List<Gelaxka> aldatutakoGelaxkak = new ArrayList<>();
    private String aukeratutakoKoloreaString;

    private JokalariOntzi ontzia;
    private boolean jokoaAmaitu = false;
    private List<Tiroa> tiroak = new ArrayList<>();

    // ERAIKITZAILEA: Hemen sortzen dira gelaxka guztiak behin bakarrik
    private MatrizeEredua() {
        this.gelaxka = new Gelaxka[zabalera][altuera];
        for (int x = 0; x < zabalera; x++) {
            for (int y = 0; y < altuera; y++) {
                this.gelaxka[x][y] = new Gelaxka(x, y);
            }
        }
    }

    public static MatrizeEredua getMatrizea() {
        return nireMatrizea;
    }

    private Color koloreaLortu(String pkol) {
        if (pkol == null) return Color.GREEN;
        if (pkol.equalsIgnoreCase("Gorria")) return Color.RED;
        if (pkol.equalsIgnoreCase("Urdina")) return Color.BLUE;
        return Color.GREEN; 
    }

    public void matrizeaSortu(String pkol) {
        this.aukeratutakoKoloreaString = pkol;
        this.jokoaAmaitu = false; 
        this.tiroak.clear();
        this.aldatutakoGelaxkak.clear();

        for (int x = 0; x < zabalera; x++) {
            for (int y = 0; y < altuera; y++) {
                if (x == 0 || y == 0 || x == (zabalera - 1) || y == (altuera - 1)) {
                    gelaxka[x][y].setEdukia(new HormaEgoera());
                } else {
                    gelaxka[x][y].setEdukia(new HutsaEgoera());
                }
            }
        }

        ontzia = JokalariOntziFactory.getJokalariOntziFactory().sortuJokalariOntziMatrizetik(pkol, zabalera, altuera);
        Color k = koloreaLortu(pkol);
        
        for (Gelaxka gForma : ontzia.getFormaOsoa()) {
            setEdukiaTracked(gForma.getZabalera(), gForma.getAltuera(), new EspazioOntziaEgoera(k));
        }

        etsaiKop = (int) Math.floor(Math.random() * (etsaiMax - etsaiMin + 1) + etsaiMin);
        int jarritakoEtsaiKop = 0;
        while (jarritakoEtsaiKop < etsaiKop) {
            int rx = (int) (Math.random() * (zabalera - 2)) + 1;
            if (gelaxka[rx][5].getEdukia() instanceof HutsaEgoera) {
                gelaxka[rx][5].setEdukia(new EtsaiEgoera());
                jarritakoEtsaiKop++;
            }
        }
        
        // Hasierako egoera bista guztian marraztu
        gelaxkaGuztiakNotifikatu();
    }

    public void setEdukiaTracked(int x, int y, EdukiaEgoera edukia) {
        if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
            gelaxka[x][y].setEdukia(edukia);
            aldatutakoGelaxkak.add(gelaxka[x][y]);
        }
    }

    public void ontziaMugitu(String norabidea) {
        if (ontzia == null || jokoaAmaitu) return;
        Color k = koloreaLortu(this.aukeratutakoKoloreaString);

        for (Gelaxka g : ontzia.getFormaOsoa()) {
            setEdukiaTracked(g.getZabalera(), g.getAltuera(), new HutsaEgoera());
        }

        ontzia.mugitu(norabidea);

        for (Gelaxka g : ontzia.getFormaOsoa()) {
            int x = g.getZabalera();
            int y = g.getAltuera();

            if (gelaxka[x][y].getEdukia() instanceof EtsaiEgoera) {
                bistaEguneratu();
                JokoKudeaketa.getJokoKudeaketa().amaituJokoa(false);
                return;
            }
            setEdukiaTracked(x, y, new EspazioOntziaEgoera(k));
        }
        bistaEguneratu();
    }

    public void tirokatu() {
        if (ontzia == null || jokoaAmaitu) return;
        Tiroa t = ontzia.tirokatu();
        int x = t.getX();
        int y = t.getY();
        if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
        	EdukiaEgoera unekoEgoera = gelaxka[x][y].getEdukia();
        	if (unekoEgoera instanceof HutsaEgoera || unekoEgoera instanceof EspazioOntziaEgoera) {
                setEdukiaTracked(x, y, new TiroaEgoera());
                tiroak.add(t);
        	}
        }
        bistaEguneratu();
    }

    public void jokoZikloaEguneratu() {
        if (jokoaAmaitu) return;

        List<Tiroa> borratzekoak = new ArrayList<>();
        for (Tiroa t : tiroak) {
            int x = t.getX();
            int y = t.getY();
            
            if (gelaxka[x][y].getEdukia() instanceof TiroaEgoera) {
                setEdukiaTracked(x, y, new HutsaEgoera());
            }

            t.mugituGora();
            int berriaY = t.getY();

            if (berriaY > 0) {
                EdukiaEgoera aurreanDagoena = gelaxka[x][berriaY].getEdukia();
                if (aurreanDagoena instanceof EtsaiEgoera) {
                    setEdukiaTracked(x, berriaY, new HutsaEgoera());
                    borratzekoak.add(t);
                } else if (aurreanDagoena instanceof HutsaEgoera) {
                    setEdukiaTracked(x, berriaY, new TiroaEgoera());
                } else {
                    borratzekoak.add(t);
                }
            } else {
                borratzekoak.add(t);
            }
        }
        tiroak.removeAll(borratzekoak);
        bistaEguneratu();
    }

    public void etsaiakMugitu() {
        if (jokoaAmaitu) return;

        List<int[]> etsaiPosizioak = new ArrayList<>();
        for (int x = 1; x < zabalera - 1; x++) {
            for (int y = 1; y < altuera - 1; y++) {
                if (gelaxka[x][y].getEdukia() instanceof EtsaiEgoera) {
                    etsaiPosizioak.add(new int[] { x, y });
                    setEdukiaTracked(x, y, new HutsaEgoera());
                }
            }
        }

        for (int[] p : etsaiPosizioak) {
            Etsaiak e = EtsaiakFactory.getEtsaiakFactory().sortuEtsaiaPosiziotik(p, 1, zabalera - 2, 1, altuera - 2);
            e.mugitu(Etsaiak.norabideRandom());

            int xBerria = e.getX();
            int yBerria = e.getY();

            if (xBerria <= 0 || xBerria >= zabalera - 1 || yBerria <= 0 || yBerria >= altuera - 1) {
                xBerria = p[0]; yBerria = p[1];
            }
            
            if (gelaxka[xBerria][yBerria].getEdukia() instanceof EspazioOntziaEgoera) {
                bistaEguneratu();
                JokoKudeaketa.getJokoKudeaketa().amaituJokoa(false);
                return;
            }
            setEdukiaTracked(xBerria, yBerria, new EtsaiEgoera());
        }
        bistaEguneratu();
    }

    public void bistaEguneratu() {
        for (Gelaxka g : aldatutakoGelaxkak) {
            g.notifikatu();
        }
        aldatutakoGelaxkak.clear();
    }

    public void gelaxkaGuztiakNotifikatu() {
        for (int x = 0; x < zabalera; x++) {
            for (int y = 0; y < altuera; y++) {
                gelaxka[x][y].notifikatu();
            }
        }
    }

    public void amaituJokoa() { this.jokoaAmaitu = true; }
    public boolean isJokoaAmaitua() { return jokoaAmaitu; }
    public Gelaxka[][] getGelaxkak() { return gelaxka; }
    public int getZabalera() { return zabalera; }
    public int getAltuera() { return altuera; }
    public Gelaxka getGelaxka(int x, int y) { return this.gelaxka[x][y]; }
}