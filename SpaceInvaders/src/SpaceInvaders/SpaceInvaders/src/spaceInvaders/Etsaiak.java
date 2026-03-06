package spaceInvaders;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Etsaiak extends Ontzia {

    // Constructor matching Ontzia's signature
    public Etsaiak(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
    }

    public static String norabideRandom() {
        int r = (int) (Math.random() * 3);
        if (r == 0) return "EZKERRA";
        else if (r == 1) return "ESKUINA";
        else return "BEHERA";
    }
}