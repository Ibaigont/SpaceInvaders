package Bista;

import javax.swing.JPanel;


import java.awt.Color;
import java.awt.GridLayout;

public class JokoPanela extends JPanel {
     
    private static JokoPanela panel = null;
    private GelaxkaBista[][] bistaMatrizea = null;
 
    private JokoPanela() {
        this.setBackground(Color.BLACK);
    }
 
    public static JokoPanela getJokoPanela() {
        if (panel == null) {
        	panel = new JokoPanela();
        }
        return panel;
     	}

   
    public void preparatuMatrizea(int zabalera, int altuera) {
    	bistaMatrizea = null;
        bistaMatrizea = new GelaxkaBista[zabalera][altuera];
        this.setLayout(new GridLayout(altuera, zabalera));
        for (int y = 0; y < altuera; y++) {
            for (int x = 0; x < zabalera; x++) {
                GelaxkaBista g = new GelaxkaBista();
                bistaMatrizea[x][y] = g;
                this.add(g);
            }
        }
        this.revalidate();
        this.repaint();
    }

    public GelaxkaBista[][] getBistaMatrizea() {
        return bistaMatrizea;
    }
    public void hasieratu(GelaxkaBista[][] bistaMatrizea, int zabalera, int altuera) {
        this.removeAll();
        this.setLayout(new GridLayout(altuera, zabalera));
 
        for (int y = 0; y < altuera; y++) {
            for (int x = 0; x < zabalera; x++) {
                this.add(bistaMatrizea[x][y]);
            }
        }
 
        this.revalidate();
        this.repaint();
     }
}