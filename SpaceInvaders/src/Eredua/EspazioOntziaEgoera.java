package Eredua;
import java.awt.Color;

public class EspazioOntziaEgoera implements EdukiaEgoera {
    @Override
    public String getIzena() {
        return "EspazioOntzia";
    }
    private Color OntziKolorea;

    public EspazioOntziaEgoera(Color pKolorea) {
        this.OntziKolorea = pKolorea;
    }

    @Override
    public Color getKolorea() {
        return this.OntziKolorea;
    }
}
