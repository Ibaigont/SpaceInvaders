package Bista;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;

import javax.swing.JLabel;

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
	    setKolorea((Color) arg);
	}
}