package Bista;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

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
	    if (arg instanceof Eredua.EdukiaEgoera) {
	        Eredua.EdukiaEgoera egoera = (Eredua.EdukiaEgoera) arg;
	        final Color bg = egoera.getKolorea();
	        
	        SwingUtilities.invokeLater(() -> setKolorea(bg));
	    }
	}
	
}