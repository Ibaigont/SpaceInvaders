package spaceInvaders;

public class Etsaiak extends Ontzia {

    // Constructor matching Ontzia's signature
    public Etsaiak(int hasieraX, int hasieraY, int minX, int maxX, int minY, int maxY) {
        super(hasieraX, hasieraY, minX, maxX, minY, maxY);
    }

    @Override
    public void mugitu(String norabidea) {
         
        }

        setX(x);
        setY(y);
        update();
    }

    // Additional enemy-specific behavior can be added here
    public void itsasiakBehavior() {
        // ...existing code...
    }
}
