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
        if (arg instanceof String) {
            String state = (String) arg;
            Color c = Color.BLACK;
            if (state.equals("EspazioOntzia")) c = Color.GREEN;
            else if (state.equals("Etsaia")) c = Color.RED;
            else if (state.equals("Tiroa")) c = Color.WHITE;
            else if (state.equals("Horma")) c = Color.GRAY;
            
            final Color bg = c;
            SwingUtilities.invokeLater(() -> setKolorea(bg));
        }
	}
}