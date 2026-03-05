package spaceInvaders;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Etsaiak extends Ontzia {

    // Constructor matching Ontzia's signature
    public Etsaiak(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
    }

    @Override
    public void mugitu(String norabidea) {
         
    }

    // Additional enemy-specific behavior can be added here
    public void itsasiakBehavior() {
        // ...existing code...
    }

    // Moved from MatrizeEredua: handle movement of enemies using the MatrizeEredua public API
    public static void etsaiakMugitu(MatrizeEredua eredua) {
        if (eredua.isJokoaAmaitua()) return;

        Gelaxka[][] gelaxka = eredua.getGelaxkak();
        int zabalera = eredua.getZabalera();
        int altuera = eredua.getAltuera();

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
                // Collision with player
                if (gelaxka[xBerria][yBerria].getEdukia() == Edukia.EspazioOntzia) {
                    eredua.amaituJokoa();
                    eredua.bistaEguneratu();
                    return;
                }
                gelaxka[xBerria][yBerria].setEdukia(Edukia.Etsaia);
            }
        }

        eredua.bistaEguneratu();
    }
}