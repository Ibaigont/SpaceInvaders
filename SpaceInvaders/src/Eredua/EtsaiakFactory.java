package Eredua;

public class EtsaiakFactory {

    private static EtsaiakFactory nireEtsaiakFactory;

    private EtsaiakFactory() {}

    public static EtsaiakFactory getEtsaiakFactory() {
        if (nireEtsaiakFactory == null) {
            nireEtsaiakFactory = new EtsaiakFactory();
        }
        return nireEtsaiakFactory;
    }

    public Etsaiak sortuEtsaia(int hasieraX, int hasieraY,
                                int minX, int maxX,
                                int minY, int maxY) {
        return new Etsaiak(hasieraX, hasieraY, minX, maxX, minY, maxY);
    }

    public Etsaiak sortuEtsaiaPosiziotik(int[] posizio,
                                          int minX, int maxX,
                                          int minY, int maxY) {
        return new Etsaiak(posizio[0], posizio[1], minX, maxX, minY, maxY);
    }
}