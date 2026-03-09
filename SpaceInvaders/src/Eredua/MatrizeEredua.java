package Eredua;

import java.util.Observable;
import java.util.ArrayList; 
import java.util.List;      
import java.awt.Point;

public class MatrizeEredua extends Observable {
	
	private Gelaxka[][] gelaxka;
	private static MatrizeEredua nireMatrizea = new MatrizeEredua();
	private int altuera = 60; 
	private int zabalera = 100; 
	private int etsaiKop = 0;
	private int etsaiMin = 4;
	private int etsaiMax = 8;
	
	private JokalariOntzi ontzia; 
	private int etsaienNorabidea = 1;
	private boolean jokoaAmaitu = false;
	
	private MatrizeEredua() {
		this.gelaxka = new Gelaxka[zabalera][altuera];
	}
	
	public static MatrizeEredua getMatrizea() { 
		return nireMatrizea; 
	}
	
	public void matrizeaSortu() {
	    for (int x = 0; x < zabalera; x++) {
	        for (int y = 0; y < altuera; y++) {
	            Gelaxka gelaxkaBerria = new Gelaxka(x, y);
	            if (x == 0 || y == 0 || x == (zabalera - 1) || y == (altuera - 1)) {
	                gelaxkaBerria.setEdukia(Edukia.Horma);
	            } else {
	                gelaxkaBerria.setEdukia(Edukia.Hutsa);
	            }
	            gelaxka[x][y] = gelaxkaBerria;
	        }
	    }
	    	
	    ontzia = new JokalariOntzi(25, 27, 1, zabalera - 2, 1, altuera - 2);
	    gelaxka[ontzia.getX()][ontzia.getY()].setEdukia(Edukia.EspazioOntzia);

		etsaiKop = (int) Math.floor(Math.random() * (etsaiMax - etsaiMin + 1) + etsaiMin);
		int jarritakoEtsaiKop = 0;
		while (jarritakoEtsaiKop < etsaiKop) {
			int rx = (int) (Math.random() * (zabalera - 2)) + 1;
			if (gelaxka[rx][5].getEdukia() == Edukia.Hutsa) {
				gelaxka[rx][5].setEdukia(Edukia.Etsaia);
				jarritakoEtsaiKop++;
			}
		}
		
		
	}
	
	public void ontziaMugitu(String norabidea) {
		if (ontzia == null) return;
		gelaxka[ontzia.getX()][ontzia.getY()].setEdukia(Edukia.Hutsa);
		ontzia.mugitu(norabidea); 
		if (gelaxka[ontzia.getX()][ontzia.getY()].getEdukia()== Edukia.Etsaia) {
			amaituJokoa();
		}
		gelaxka[ontzia.getX()][ontzia.getY()].setEdukia(Edukia.EspazioOntzia);
		
		bistaEguneratu();
	}
	
	public void tirokatu() {
		if (ontzia == null) return;
		ontzia.tirokatu(gelaxka);
		bistaEguneratu();
	}

	public void jokoZikloaEguneratu() {
		if (jokoaAmaitu) {
			return;
		}
		for (int y = 1; y < altuera - 1; y++) {
			for (int x = 1; x < zabalera - 1; x++) {
				if (gelaxka[x][y].getEdukia() == Edukia.Tiroa) {
					gelaxka[x][y].setEdukia(Edukia.Hutsa); 
					int berriaY = y - 1; 
					
					if (berriaY > 0) {
						Edukia aurreanDagoena = gelaxka[x][berriaY].getEdukia();
						if (aurreanDagoena == Edukia.Etsaia) {
							gelaxka[x][berriaY].setEdukia(Edukia.Hutsa);
						} else if (aurreanDagoena == Edukia.Hutsa) {
							gelaxka[x][berriaY].setEdukia(Edukia.Tiroa);
						}
					}
				}
			}
		}
		bistaEguneratu(); 
	}
	
	public void etsaiakMugitu() {
		if (jokoaAmaitu) return;

		List<Point> etsaiPosizioak = new ArrayList<>();
		for (int x = 1; x < zabalera - 1; x++) {
			for (int y = 1; y < altuera - 1; y++) {
				if (gelaxka[x][y].getEdukia() == Edukia.Etsaia) {
					etsaiPosizioak.add(new Point(x, y));
					gelaxka[x][y].setEdukia(Edukia.Hutsa);
				}
			}
		}

		for (Point p : etsaiPosizioak) {
			Etsaiak e = new Etsaiak(p.x, p.y, 1, zabalera - 2, 1, altuera - 2);
			String norabidea = Etsaiak.norabideRandom();
			e.mugitu(norabidea);

			int xBerria = e.getX();
			int yBerria = e.getY();

			if (xBerria <= 0 || xBerria >= zabalera - 1) {
				xBerria = p.x;
				yBerria = p.y;
			}

			if (yBerria < altuera - 1 && gelaxka[xBerria][yBerria].getEdukia() == Edukia.Etsaia) {
				xBerria = p.x;
				yBerria = p.y;
			}

			if (yBerria < altuera - 1) {
				if (gelaxka[xBerria][yBerria].getEdukia() == Edukia.EspazioOntzia) {
					amaituJokoa();
					bistaEguneratu();
					return;
				}
				gelaxka[xBerria][yBerria].setEdukia(Edukia.Etsaia);
			} else {
				gelaxka[p.x][p.y].setEdukia(Edukia.Etsaia);
			}
		}

		bistaEguneratu();
	}

	public void AldatuGelaxka(int x, int y, Edukia kolorea) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
            gelaxka[x][y].setEdukia(kolorea);
		}
	}
	
	public boolean isJokoaAmaitua() {
	    return jokoaAmaitu;
	}
	
	public Gelaxka[][] getGelaxkak() { return gelaxka; }
	public int getZabalera() { return zabalera; }
	public int getAltuera() { return altuera; }
	
	public void bistaEguneratu() {
		setChanged();
		notifyObservers();
	}

	public void amaituJokoa() {
		this.jokoaAmaitu = true;
	}
}