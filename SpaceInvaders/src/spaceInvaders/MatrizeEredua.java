package spaceInvaders;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class MatrizeEredua extends Observable {
	private Gelaxka[][] gelaxka;
	private static MatrizeEredua nireMatrizea = new MatrizeEredua();
	private int altuera = 30; // Matrizearen balioa erdia da lehen Sprinta errazagoa egiteko
	private int zabalera = 50; // Matrizearen balioa erdia da lehen Sprinta errazagoa egiteko
	private int etsaiKop = 0;
	private int etsaiMin = 4;
	private int etsaiMax = 8;
	
	private MatrizeEredua() {
		this.gelaxka = new Gelaxka[altuera][zabalera];
	}
	public static MatrizeEredua getMatrizea() { 
		return nireMatrizea; 
	}
	public void MatrizeaSortu() {
    for (int x = 0; x < zabalera; x++) {
        for (int y = 0; y < altuera; y++) {
            Gelaxka gelaxkaBerria = new Gelaxka(x, y);

            if (x == 0 || y == 0 || x == (zabalera - 1) || y == (altuera - 1)) {
                gelaxkaBerria.setEdukia(Edukia.Horma);
            } else if (x == 27 && y == 25) {
                gelaxkaBerria.setEdukia(Edukia.EspazioOntzia);
            } else {
                gelaxkaBerria.setEdukia(Edukia.Hutsa);
            }

            gelaxka[x][y] = gelaxkaBerria;
        }
    }

	etsaiKop = (int) Math.floor(Math.random() * (etsaiMax - etsaiMin + 1) + etsaiMin);
	int jarritakoEtsaiKop = 0;
	while (jarritakoEtsaiKop < etsaiKop) {
		int rx = (int) (Math.random() * (zabalera - 2)) + 1;

		if (gelaxka[rx][2].getEdukia() == Edukia.Hutsa) {
			gelaxka[rx][2].setEdukia(Edukia.Etsaia);
			jarritakoEtsaiKop++;
		}
	}
    
}
	public void  AldatuGelaxka (int x, int y, Edukia kolorea) {
		if (x >= 0 && x < zabalera && y >= 0 && y < altuera) {
            Gelaxka zaharra = gelaxka[x][y];
            gelaxka [x][y].setEdukia(kolorea);
		}
		
	}
	
}
