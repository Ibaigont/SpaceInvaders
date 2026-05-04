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
    private List<TiroElementua> tiroak = new ArrayList<>();
    private List<EtsaiNodoa> etsaiakLista = new ArrayList<>();
    
    private int puntuazioa = 0;
    private int bizitzak = 3;
    private int frameInvencible = 0;
    private boolean mugagabe = false;
    private int olaZenbakia = 1;

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

    public void matrizeaSortu(String pkol, boolean pMugagabe) {
        this.aukeratutakoKoloreaString = pkol;
        this.mugagabe = pMugagabe;
        this.jokoaAmaitu = false; 
        this.tiroak.clear();
        this.aldatutakoGelaxkak.clear();
        this.etsaiakLista.clear(); 
        this.puntuazioa = 0;
        this.olaZenbakia = 1;
        if (mugagabe) {
            this.bizitzak = 3;
        } else {
            this.bizitzak = 1;
        }
        this.frameInvencible = 0;

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
        sortuEtsaiak(etsaiKop);
        gelaxkaGuztiakNotifikatu();
    }

    private void sortuEtsaiak(int kopurua) {
        int jarritakoEtsaiKop = 0;
        int maxAukerak = 1000;
        int aukera = 0;
        int maxRX = zabalera - 6;
        if (maxRX < 1) maxRX = 1;
        
        while (jarritakoEtsaiKop < kopurua && aukera < maxAukerak) {
            aukera++;
            int rx = (int) (Math.random() * maxRX) + 1;
            
            if (rx + 4 >= zabalera - 1) continue;
            
            if (gelaxka[rx][5].getEdukia() instanceof HutsaEgoera && 
                gelaxka[rx+1][5].getEdukia() instanceof HutsaEgoera) {
                
                int formaRandom = (int) (Math.random() * 4);
                EtsaiNodoa etsaiBerria = (EtsaiNodoa) EtsaiFactory.getEtsaiFactory().createEtsaia(formaRandom, rx, 5);
                
                boolean kabitzenDa = true;
                for (Gelaxka g : etsaiBerria.getGelaxkak()) {
                    int xg = g.getZabalera();
                    int yg = g.getAltuera();
                    if (xg <= 0 || xg >= zabalera - 1 || yg <= 0 || yg >= altuera - 1) {
                        kabitzenDa = false;
                        break;
                    }
                    if (!(gelaxka[xg][yg].getEdukia() instanceof HutsaEgoera)) {
                        kabitzenDa = false;
                        break;
                    }
                }
                
                if (kabitzenDa) {
                    etsaiakLista.add(etsaiBerria);
                    for (Gelaxka g : etsaiBerria.getGelaxkak()) {
                        gelaxka[g.getZabalera()][g.getAltuera()].setEdukia(new EtsaiEgoera());
                    }
                    jarritakoEtsaiKop++;
                    aukera = 0;
                }
            }
        }
    }

    public void hurrengoOlata() {
        if (!mugagabe) return;
        for (EtsaiNodoa e : etsaiakLista) {
            for (Gelaxka g : e.getGelaxkak()) {
                setEdukiaTracked(g.getZabalera(), g.getAltuera(), new HutsaEgoera());
            }
            e.suntsitu();
        }
        etsaiakLista.clear();
        olaZenbakia++;
        int berriKop = etsaiKop + 1;
        if (berriKop > 20) berriKop = 20;
        etsaiKop = berriKop;
        sortuEtsaiak(etsaiKop);
        bistaEguneratu();
    }

    public void setEdukiaTracked(int x, int y, EdukiaEgoera edukia) {
        if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
            gelaxka[x][y].setEdukia(edukia);
            aldatutakoGelaxkak.add(gelaxka[x][y]);
        }
    }

    private void ontziaBerrezarriPosizioa() {
        Color k = koloreaLortu(this.aukeratutakoKoloreaString);
        for (Gelaxka g : ontzia.getFormaOsoa()) {
            setEdukiaTracked(g.getZabalera(), g.getAltuera(), new HutsaEgoera());
        }
        int hasieraX = zabalera / 2;
        int hasieraY = altuera - 5;
        int maxX;
        int minY;
        if (aukeratutakoKoloreaString.equalsIgnoreCase("Berdea")) {
            maxX = zabalera - 6;
            minY = 3;
        } else if (aukeratutakoKoloreaString.equalsIgnoreCase("Gorria")) {
            maxX = zabalera - 4;
            minY = 3;
        } else {
            maxX = zabalera - 4;
            minY = 2;
        }
        ontzia = JokalariOntziFactory.getJokalariOntziFactory().sortuJokalariOntzia(aukeratutakoKoloreaString, hasieraX, hasieraY, 1, maxX, minY, altuera - 2);
        for (Gelaxka g : ontzia.getFormaOsoa()) {
            setEdukiaTracked(g.getZabalera(), g.getAltuera(), new EspazioOntziaEgoera(k));
        }
    }

    private void bizitzaGaldu() {
        if (!mugagabe) {
            JokoKudeaketa.getJokoKudeaketa().amaituJokoa(false);
            return;
        }
        if (frameInvencible > 0) return;
        bizitzak--;
        if (bizitzak <= 0) {
            JokoKudeaketa.getJokoKudeaketa().amaituJokoa(false);
            return;
        }
        frameInvencible = 60;
        ontziaBerrezarriPosizioa();
        JokoKudeaketa.getJokoKudeaketa().bistaEguneratu();
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
                bizitzaGaldu();
                bistaEguneratu();
                return;
            }
            setEdukiaTracked(x, y, new EspazioOntziaEgoera(k));
        }
        bistaEguneratu();
    }

    public void tirokatu() {
        if (ontzia == null || jokoaAmaitu) return;
        TiroElementua t = ontzia.tirokatu();
        if (t != null) {
            for (TiroPixela p : t.getPixelak()) {
                int x = p.getX();
                int y = p.getY();
                if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
                    EdukiaEgoera unekoEgoera = gelaxka[x][y].getEdukia();
                    if (unekoEgoera instanceof HutsaEgoera) {
                        setEdukiaTracked(x, y, new TiroaEgoera());
                    }
                }
            }
            tiroak.add(t);
            JokoKudeaketa.getJokoKudeaketa().tiroaKontatuEtaBerritu();
        }
        bistaEguneratu();
    }

    public void ontziarenTiroaAldatu() {
        if (ontzia != null && !jokoaAmaitu) {
            ontzia.aldatuTiroMota();
        }
    }

    public void jokoZikloaEguneratu() {
        if (jokoaAmaitu) return;

        if (frameInvencible > 0) {
            frameInvencible--;
        }

        List<TiroElementua> borratzekoak = new ArrayList<>();
        for (TiroElementua t : tiroak) {
            for (TiroPixela p : t.getPixelak()) {
                int x = p.getX();
                int y = p.getY();
                if (gelaxka[x][y].getEdukia() instanceof TiroaEgoera) {
                    setEdukiaTracked(x, y, new HutsaEgoera());
                }
            }
            t.mugituGora();
            boolean ezabatuTiroa = false;
            for (TiroPixela p : t.getPixelak()) {
                int x = p.getX();
                int berriaY = p.getY();

                if (berriaY >= 0 && berriaY < altuera && x >= 0 && x < zabalera) {
                    EdukiaEgoera aurreanDagoena = gelaxka[x][berriaY].getEdukia();

                    if (aurreanDagoena instanceof EtsaiEgoera) {
                        EtsaiNodoa joDuenEtsaia = null;
                        for (EtsaiNodoa e : etsaiakLista) {
                            for (Gelaxka g : e.getGelaxkak()) {
                                if (g.getZabalera() == x && g.getAltuera() == berriaY) {
                                    joDuenEtsaia = e;
                                    break;
                                }
                            }
                            if (joDuenEtsaia != null) break;
                        }
                        if (joDuenEtsaia != null) {
                            for (Gelaxka g : joDuenEtsaia.getGelaxkak()) {
                                setEdukiaTracked(g.getZabalera(), g.getAltuera(), new HutsaEgoera());
                            }
                            joDuenEtsaia.suntsitu();
                            etsaiakLista.remove(joDuenEtsaia);
                            puntuazioa += 100;
                            JokoKudeaketa.getJokoKudeaketa().bistaEguneratu();
                        }
                        setEdukiaTracked(x, berriaY, new HutsaEgoera());
                        ezabatuTiroa = true;
                    } else if (!(aurreanDagoena instanceof HutsaEgoera)) {
                        ezabatuTiroa = true;
                    }
                } else {
                    ezabatuTiroa = true; 
                }
            }
            if (ezabatuTiroa) {
                borratzekoak.add(t);
            } else {
                for (TiroPixela p : t.getPixelak()) {
                    setEdukiaTracked(p.getX(), p.getY(), new TiroaEgoera());
                }
            }
        }
        tiroak.removeAll(borratzekoak);
        bistaEguneratu();
    }
    
    private String norabideRandom() {
        int r = (int) (Math.random() * 3);
        if (r == 0)
            return "EZKERRA";
        else if (r == 1)
            return "ESKUINA";
        else
            return "BEHERA";
    }

    public void etsaiakMugitu() {
        if (jokoaAmaitu) return;

        for (EtsaiNodoa e : etsaiakLista) {
            
            for (Gelaxka g : e.getGelaxkak()) {
                int px = g.getZabalera();
                int py = g.getAltuera();
                if (px > 0 && px < zabalera - 1 && py > 0 && py < altuera - 1) {
                    setEdukiaTracked(px, py, new HutsaEgoera());
                }
            }
            String norabidea = norabideRandom();
            
            boolean mugimenduaBaimenduta = true;
            for (Gelaxka g : e.getGelaxkak()) {
                int px = g.getZabalera();
                int py = g.getAltuera();
                
                int nx = px;
                int ny = py;
                if (norabidea.equals("EZKERRA")) nx--;
                else if (norabidea.equals("ESKUINA")) nx++;
                else if (norabidea.equals("BEHERA")) ny++;

                if (nx <= 0 || nx >= zabalera - 1 || ny >= altuera - 1) {
                    mugimenduaBaimenduta = false;
                    break;
                }
                
                if (gelaxka[nx][ny].getEdukia() instanceof EtsaiEgoera) {
                    mugimenduaBaimenduta = false;
                    break;
                }
            }

            if (mugimenduaBaimenduta) {
                e.mugitu(norabidea);
            }

            for (Gelaxka g : e.getGelaxkak()) {
                int px = g.getZabalera();
                int py = g.getAltuera();

                if (px > 0 && px < zabalera - 1 && py > 0 && py < altuera - 1) {
                    if (gelaxka[px][py].getEdukia() instanceof EspazioOntziaEgoera) {
                        bizitzaGaldu();
                        bistaEguneratu();
                        return;
                    }
                    setEdukiaTracked(px, py, new EtsaiEgoera());
                }
            }
        }
        bistaEguneratu();
    }

    public String getUnekoMunizioaTestua() {
        if (ontzia == null) return "0";
        
        TiroPortaera t = ontzia.getTiroPortaera(); 
        

        if (t instanceof TiroGezia) {
            return String.valueOf(((TiroGezia) t).getMunizioa());
        } 
        
        if (t instanceof TiroErronboa) {
            return String.valueOf(((TiroErronboa) t).getMunizioa());
        }
        
      
        if (t instanceof TiroBakuna) {
            return "∞";
        }
        
        return "0";
    }

    public void bistaEguneratu() {
        aldatutakoGelaxkak.forEach(Gelaxka::notifikatu);
        aldatutakoGelaxkak.clear();
        JokoKudeaketa.getJokoKudeaketa().berrituBistakoDatuak();
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
    public int getPuntuazioa() { return puntuazioa; }
    public int getBizitzak() { return bizitzak; }
    public boolean isMugagabe() { return mugagabe; }
}