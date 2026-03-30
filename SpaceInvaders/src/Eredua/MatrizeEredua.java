package Eredua;

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
	
	// Izen berria hemen ezarri dugu
	private List<Gelaxka> aldatutakoGelaxkak = new ArrayList<>();

	private JokalariOntzi ontzia;
	private boolean jokoaAmaitu = false;
	private List<Tiroa> tiroak = new ArrayList<>();

	private MatrizeEredua() {
		this.gelaxka = new Gelaxka[zabalera][altuera];
	}

	public static MatrizeEredua getMatrizea() {
		return nireMatrizea;
	}

	public void matrizeaSortu() {
		tiroak.clear();
		for (int x = 0; x < zabalera; x++) {
			for (int y = 0; y < altuera; y++) {
				Gelaxka gelaxkaBerria = new Gelaxka(x, y);
				gelaxka[x][y] = gelaxkaBerria;
				if (x == 0 || y == 0 || x == (zabalera - 1) || y == (altuera - 1)) {
					gelaxkaBerria.setEdukia(new HormaEgoera());
				} else {
					gelaxkaBerria.setEdukia(new HutsaEgoera());
				}
			}
		}

		ontzia = JokalariOntziFactory.getJokalariOntziFactory().sortuJokalariOntziMatrizetik(zabalera, altuera);
		gelaxka[ontzia.getX()][ontzia.getY()].setEdukia(new EspazioOntziaEgoera());

		etsaiKop = (int) Math.floor(Math.random() * (etsaiMax - etsaiMin + 1) + etsaiMin);
		int jarritakoEtsaiKop = 0;
		while (jarritakoEtsaiKop < etsaiKop) {
			int rx = (int) (Math.random() * (zabalera - 2)) + 1;
			if (gelaxka[rx][5].getEdukia() instanceof HutsaEgoera) {
				gelaxka[rx][5].setEdukia(new EtsaiEgoera());
				jarritakoEtsaiKop++;
			}
		}
	}

	public void setEdukiaTracked(int x, int y, EdukiaEgoera edukia) {
		gelaxka[x][y].setEdukia(edukia);
		// Zerrenda berria erabiliz
		aldatutakoGelaxkak.add(gelaxka[x][y]);
	}

	public void ontziaMugitu(String norabidea) {
		if (ontzia == null)
			return;
		setEdukiaTracked(ontzia.getX(), ontzia.getY(), new HutsaEgoera());
		ontzia.mugitu(norabidea);
		if (gelaxka[ontzia.getX()][ontzia.getY()].getEdukia() instanceof EtsaiEgoera) {
			bistaEguneratu();
			JokoKudeaketa.getJokoKudeaketa().amaituJokoa(false); // ← GALDU
			return;
		}
		setEdukiaTracked(ontzia.getX(), ontzia.getY(), new EspazioOntziaEgoera());
		bistaEguneratu();
	}

	public void tirokatu() {
		if (ontzia == null)
			return;
		Tiroa t = ontzia.tirokatu();
		if (t.getY() > 0 && gelaxka[t.getX()][t.getY()].getEdukia() instanceof HutsaEgoera) {
			setEdukiaTracked(t.getX(), t.getY(), new TiroaEgoera());
			tiroak.add(t);
		}
		bistaEguneratu();
	}

	public void jokoZikloaEguneratu() {
		if (jokoaAmaitu)
			return;

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
		if (jokoaAmaitu)
			return;

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
				xBerria = p[0];
				yBerria = p[1];
			}
			if (gelaxka[xBerria][yBerria].getEdukia() instanceof EtsaiEgoera) {
				xBerria = p[0];
				yBerria = p[1];
			}
			if (gelaxka[xBerria][yBerria].getEdukia() instanceof EspazioOntziaEgoera) {
				bistaEguneratu();
				JokoKudeaketa.getJokoKudeaketa().amaituJokoa(false); // ← GALDU
				return;
			}
			setEdukiaTracked(xBerria, yBerria, new EtsaiEgoera());
			if (xBerria != p[0] || yBerria != p[1]) {
				// Zerrenda berria erabiliz
				aldatutakoGelaxkak.add(gelaxka[p[0]][p[1]]);
			}
		}

		bistaEguneratu();
	}

	public void AldatuGelaxka(int x, int y, EdukiaEgoera kolorea) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
			setEdukiaTracked(x, y, kolorea);
			bistaEguneratu();
		}
	}

	public void bistaEguneratu() {
		// Zerrenda berria iteratzeko eta garbitzeko
		for (Gelaxka g : aldatutakoGelaxkak) {
			g.notifikatu();
		}
		aldatutakoGelaxkak.clear();
	}

	public void amaituJokoa() {
		this.jokoaAmaitu = true;
	}

	public boolean isJokoaAmaitua() {
		return jokoaAmaitu;
	}

	public Gelaxka[][] getGelaxkak() {
		return gelaxka;
	}

	public int getZabalera() {
		return zabalera;
	}

	public int getAltuera() {
		return altuera;
	}

	public Gelaxka getGelaxka(int x, int y) {

		return this.gelaxka[x][y];
	}

	public void gelaxkaGuztiakNotifikatu() {
		for (int x = 0; x < zabalera; x++) {
			for (int y = 0; y < altuera; y++) {
				gelaxka[x][y].notifikatu();
			}
		}
	}
}
