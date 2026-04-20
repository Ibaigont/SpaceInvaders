package Eredua;

import java.awt.Color;

public class EspazioOntziaEgoera implements EdukiaEgoera {
    private Color OntziKolorea;

    public EspazioOntziaEgoera(Color pKolorea) {
        this.OntziKolorea = pKolorea;
    }

    @Override
    public String getIzena() {
        return "EspazioOntzia";
    }

    @Override
    public Color getKolorea() {
        return this.OntziKolorea;
    }
}
