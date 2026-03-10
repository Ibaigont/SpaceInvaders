package Bista;

import javax.swing.JPanel;

import Eredua.Edukia;
import Eredua.Gelaxka;

import java.awt.Color;
import java.awt.GridLayout;

public class JokoPanela extends JPanel {

    private GelaxkaBista[][] bistaMatrizea;
    private int zabalera = 0;
    private int altuera = 0;
    private static JokoPanela panel = null;

    private JokoPanela() {
        this.setBackground(Color.BLACK);
    }

    public static JokoPanela getJokoPanela() {
        if (panel == null)
            panel = new JokoPanela();
        return panel;
    }

    public void inicializatuEtaKonektatu(Gelaxka[][] matrizea, int zabalera, int altuera) {
        this.zabalera = zabalera;
        this.altuera = altuera;
        panel.removeAll();
        panel.setLayout(new GridLayout(altuera, zabalera));
        bistaMatrizea = new GelaxkaBista[zabalera][altuera];

        for (int y = 0; y < altuera; y++) {
            for (int x = 0; x < zabalera; x++) {
                bistaMatrizea[x][y] = new GelaxkaBista();
                bistaMatrizea[x][y].update(matrizea[x][y], null);
                matrizea[x][y].addObserver(bistaMatrizea[x][y]);
                panel.add(bistaMatrizea[x][y]);
            }
        }

        panel.revalidate();
        panel.repaint();
    }
}
