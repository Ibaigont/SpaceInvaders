package SpaceInvaders;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class JokoPanela extends JPanel {
    
    private Gelaxka[][] matrizea;
    private int zabalera = 0;
    private int altuera = 0;
    private final int ESKALA = 10; 

    public JokoPanela() {
        this.setBackground(Color.BLACK);
    }
    public void egoeraEguneratu(Gelaxka[][] matrizea, int zabalera, int altuera) {
        this.matrizea = matrizea;
        this.zabalera = zabalera;
        this.altuera = altuera;
        this.repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if (matrizea == null) return;

        for (int x = 0; x < zabalera; x++) {
            for (int y = 0; y < altuera; y++) {
                Edukia e = matrizea[x][y].getEdukia();
                
                if (e == Edukia.Horma) {
                    g.setColor(Color.GRAY);
                    g.fillRect(x * ESKALA, y * ESKALA, ESKALA, ESKALA);
                } else if (e == Edukia.EspazioOntzia) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * ESKALA, y * ESKALA, ESKALA, ESKALA);
                } else if (e == Edukia.Etsaia) {
                    g.setColor(Color.RED);
                    g.fillRect(x * ESKALA, y * ESKALA, ESKALA, ESKALA);
                } else if (e == Edukia.Tiroa) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x * ESKALA, y * ESKALA, ESKALA, ESKALA);
                }
            }
        }
    }
}