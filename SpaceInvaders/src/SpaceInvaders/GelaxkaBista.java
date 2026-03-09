
package SpaceInvaders;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

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
		// TODO Auto-generated method stub
		
	}
}
