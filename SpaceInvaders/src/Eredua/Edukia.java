package Eredua;

import java.awt.Color;

public enum Edukia {
    Hutsa(Color.BLACK),
    EspazioOntzia(Color.GREEN),
    Etsaia(Color.RED),
    Tiroa(Color.WHITE),
    Horma(Color.GRAY);

    public final Color kolorea;

    Edukia(Color kolorea) {
        this.kolorea = kolorea;
    }
}