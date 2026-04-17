package Eredua;

import java.awt.Color;

public class HormaEgoera implements EdukiaEgoera {
    @Override
    public String getIzena() {
        return "Horma";
    }
    
    @Override
    public Color getKolorea() {
        return Color.GRAY;
    }
}