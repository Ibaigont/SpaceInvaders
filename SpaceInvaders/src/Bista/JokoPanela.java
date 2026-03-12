package Bista;

import javax.swing.JPanel;

import Eredua.Edukia;
import Eredua.Gelaxka;

import java.awt.Color;
import java.awt.GridLayout;

public class JokoPanela extends JPanel {
	 
    private static JokoPanela panel = null;
 
    private JokoPanela() {
        this.setBackground(Color.BLACK);
    }
 
    public static JokoPanela getJokoPanela() {
        if (panel == null) {
        	panel = new JokoPanela();
        }
        return panel;
    	}
    public void hasieratu(GelaxkaBista[][] bistaMatrizea, int zabalera, int altuera) {
        panel.removeAll();
        panel.setLayout(new GridLayout(altuera, zabalera));
 
        for (int y = 0; y < altuera; y++) {
            for (int x = 0; x < zabalera; x++) {
                panel.add(bistaMatrizea[x][y]);
            }
        }
 
        panel.revalidate();
        panel.repaint();
     }
}