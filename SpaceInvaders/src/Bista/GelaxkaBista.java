package Bista;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import Eredua.Gelaxka;
import Eredua.Edukia;

public class GelaxkaBista extends JLabel implements Observer {

    public GelaxkaBista() {
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
    }

    public void setKolorea(Color color) {
        this.setBackground(color); 
    }

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Gelaxka) {
			Gelaxka g = (Gelaxka) o ;
			Edukia edukia = g.getEdukia();
			Color color;
			switch(edukia) {
			    case Hutsa: color = Color.BLACK; break;
			    case EspazioOntzia: color = Color.GREEN; break;
			    case Etsaia: color = Color.RED; break;
			    case Tiroa: color = Color.WHITE; break;
			    case Horma: color = Color.GRAY; break;
			    default: color = Color.BLACK;
			}
			setKolorea(color);
	    }	
	}
}