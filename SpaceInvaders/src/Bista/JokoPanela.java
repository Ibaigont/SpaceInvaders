package Bista;

import javax.swing.JPanel;

import Eredua.Gelaxka;
import SpaceInvaders.Edukia;

import java.awt.Color;
import java.awt.GridLayout;

public class JokoPanela extends JPanel {

    private GelaxkaBista[][] bistaMatrizea;
    private int zabalera = 0;
    private int altuera = 0;

    public JokoPanela() {
        this.setBackground(Color.BLACK);
    }

    public void egoeraEguneratu(Gelaxka[][] matrizea, int zabalera, int altuera) {
        
        if (zabalera != this.zabalera || altuera != this.altuera) {
            this.zabalera = zabalera;
            this.altuera = altuera;

            this.removeAll(); 
            this.setLayout(new GridLayout(altuera, zabalera)); 

            bistaMatrizea = new GelaxkaBista[zabalera][altuera];
            for (int y = 0; y < altuera; y++) {
                for (int x = 0; x < zabalera; x++) {
                    bistaMatrizea[x][y] = new GelaxkaBista();
                    matrizea[x][y].addObserver(bistaMatrizea[x][y]);
                    this.add(bistaMatrizea[x][y]); 
                }
            }
        }

        
        for (int x = 0; x < zabalera; x++) {
            for (int y = 0; y < altuera; y++) {
                Edukia e = matrizea[x][y].getEdukia();
                GelaxkaBista bista = bistaMatrizea[x][y];

                if (e == Edukia.Horma) {
                    bista.setKolorea(Color.GRAY);
                } else if (e == Edukia.EspazioOntzia) {
                    bista.setKolorea(Color.GREEN);
                } else if (e == Edukia.Etsaia) {
                    bista.setKolorea(Color.RED);
                } else if (e == Edukia.Tiroa) {
                    bista.setKolorea(Color.WHITE);
                } else {
                    bista.setKolorea(Color.BLACK); 
                }
            }
        }

        this.revalidate(); 
        this.repaint();
    }
}