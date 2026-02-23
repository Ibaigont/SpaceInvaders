package spaceInvaders;

import java.util.Observable;
import java.util.ArrayList; 
import java.util.List;      
import java.awt.Point;      

public class MatrizeEredua extends Observable {
	
	private Gelaxka[][] gelaxka;
	private static MatrizeEredua nireMatrizea = new MatrizeEredua();
	private int altuera = 30; 
	private int zabalera = 50; 
	private int etsaiKop = 0;
	private int etsaiMin = 4;
	private int etsaiMax = 8;
	
	private Ontzia ontzia; 
	private int etsaienNorabidea = 1; //  1 eskuma, -1 ezkerrera
	
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
	    
	    ontzia = new Ontzia(25, 27, 1, zabalera - 2, 1, altuera - 2);
	    gelaxka[ontzia.getX()][ontzia.getY()].setEdukia(Edukia.EspazioOntzia);

		etsaiKop = (int) Math.floor(Math.random() * (etsaiMax - etsaiMin + 1) + etsaiMin);
		int jarritakoEtsaiKop = 0;
		while (jarritakoEtsaiKop < etsaiKop) {
			int rx = (int) (Math.random() * (zabalera - 2)) + 1;

			if (gelaxka[rx][2].getEdukia() == Edukia.Hutsa) {
				gelaxka[rx][2].setEdukia(Edukia.Etsaia);
				jarritakoEtsaiKop++;
			}
		}
		
		bistaEguneratu();
	}
	
	public void ontziaMugitu(String norabidea) {
		if (ontzia == null) return;
		
		gelaxka[ontzia.getX()][ontzia.getY()].setEdukia(Edukia.Hutsa);
		ontzia.mugitu(norabidea); 
		gelaxka[ontzia.getX()][ontzia.getY()].setEdukia(Edukia.EspazioOntzia);
		
		bistaEguneratu();
	}
	
	public void tirokatu() {
		if (ontzia == null) return;
		int tx = ontzia.getX();
		int ty = ontzia.getY() - 1; 
		
		if (ty > 0 && gelaxka[tx][ty].getEdukia() == Edukia.Hutsa) {
			gelaxka[tx][ty].setEdukia(Edukia.Tiroa);
			bistaEguneratu();
		}
	}

	public void jokoZikloaEguneratu() {
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
			// 0 = Ezkerra, 1 = Eskuina, 2 = Behera
			int erabakia = (int) (Math.random() * 3); 
			int xBerria = p.x;
			int yBerria = p.y;

			if (erabakia == 0) xBerria--; 
			else if (erabakia == 1) xBerria++; 
			else if (erabakia == 2) yBerria++; 

			boolean mugituDaiteke = true;

			
			if (xBerria <= 0 || xBerria >= zabalera - 1) {
				mugituDaiteke = false;
			}
			
			
			if (mugituDaiteke && yBerria < altuera - 1 && gelaxka[xBerria][yBerria].getEdukia() == Edukia.Etsaia) {
				mugituDaiteke = false;
			}

			
			if (!mugituDaiteke) {
				xBerria = p.x;
				yBerria = p.y;
			}

			
			if (yBerria < altuera - 1) {
				gelaxka[xBerria][yBerria].setEdukia(Edukia.Etsaia);
			}
		}
		
		bistaEguneratu();
	}
	
	public void AldatuGelaxka (int x, int y, Edukia kolorea) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
            gelaxka [x][y].setEdukia(kolorea);
		}
	}
	
	public Gelaxka[][] getGelaxkak() { return gelaxka; }
	public int getZabalera() { return zabalera; }
	public int getAltuera() { return altuera; }
	
	public void bistaEguneratu() {
		setChanged();
		notifyObservers();
	}
}