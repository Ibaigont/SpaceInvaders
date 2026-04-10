package Eredua;

import java.awt.Color;

public interface EdukiaEgoera {
    public String getIzena(); 
    public abstract Color getKolorea();
}

class EspazioOntziaEgoera implements EdukiaEgoera {
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

class EtsaiEgoera implements EdukiaEgoera {
    @Override
    public String getIzena() {
        return "Etsaia";
    }
    
    @Override
    public Color getKolorea() {
        return Color.ORANGE;
    }
}

class HormaEgoera implements EdukiaEgoera {
    @Override
    public String getIzena() {
        return "Horma";
    }
    
    @Override
    public Color getKolorea() {
        return Color.GRAY;
    }
}

class HutsaEgoera implements EdukiaEgoera {
    @Override
    public String getIzena() {
        return "Hutsa";
    }
    
    @Override
    public Color getKolorea() {
        return Color.BLACK;
    }
}

class TiroaEgoera implements EdukiaEgoera {
    @Override
    public String getIzena() {
        return "Tiroa";
    }
    
    @Override
    public Color getKolorea() {
        return Color.WHITE;
    }
}