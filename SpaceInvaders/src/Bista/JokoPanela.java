package Bista;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JokoPanela extends JPanel {
    
    private static JokoPanela panel = null;
    private GelaxkaBista[][] bistaMatrizea = null;
    
    private JLabel tiroKontagailua;
    private JLabel munizioKontagailua;
    private JPanel matrizePanela;

    private JokoPanela() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JPanel infoPanela = new JPanel();
        infoPanela.setBackground(new Color(30, 30, 30)); 
        infoPanela.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        
        tiroKontagailua = new JLabel("Tiroak: 0");
        tiroKontagailua.setForeground(Color.WHITE);
        tiroKontagailua.setFont(new Font("Monospaced", Font.BOLD, 16));

        munizioKontagailua = new JLabel(" | Munizioa: ");
        munizioKontagailua.setForeground(Color.CYAN);
        munizioKontagailua.setFont(new Font("Monospaced", Font.BOLD, 16));

        infoPanela.add(tiroKontagailua);
        infoPanela.add(munizioKontagailua);
        this.add(infoPanela, BorderLayout.NORTH);

        matrizePanela = new JPanel() {
            private Image bgImage;
            {
                URL imgURL = getClass().getResource("1.jpg");
                if (imgURL != null) {
                    bgImage = new ImageIcon(imgURL).getImage();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        matrizePanela.setOpaque(false);
        this.add(matrizePanela, BorderLayout.CENTER);
    }

    public static JokoPanela getJokoPanela() {
        if (panel == null) {
            panel = new JokoPanela();
        }
        return panel;
    }

    public void preparatuMatrizea(int zabalera, int altuera) {
        if (this.bistaMatrizea != null) {
            return;
        }
        this.bistaMatrizea = new GelaxkaBista[zabalera][altuera];
        matrizePanela.setLayout(new GridLayout(altuera, zabalera));

        for (int y = 0; y < altuera; y++) {
            for (int x = 0; x < zabalera; x++) {
                GelaxkaBista g = new GelaxkaBista();
                this.bistaMatrizea[x][y] = g;
                matrizePanela.add(g);
                Eredua.MatrizeEredua.getMatrizea().getGelaxka(x, y).addObserver(g);
            }
        }
        
        this.revalidate();
        this.repaint();
    }

    public void eguneratuInfo(int tiroKopurua, String munizioa) {
        SwingUtilities.invokeLater(() -> {
            this.tiroKontagailua.setText("Tiroak: " + tiroKopurua);
            this.munizioKontagailua.setText(" | Munizioa: " + munizioa);
        });
    }

    public GelaxkaBista[][] getBistaMatrizea() {
        return bistaMatrizea;
    }

    public void hasieratu(GelaxkaBista[][] pBistaMatrizea, int zabalera, int altuera) {
        matrizePanela.removeAll();
        matrizePanela.setLayout(new GridLayout(altuera, zabalera));
        for (int y = 0; y < altuera; y++) {
            for (int x = 0; x < zabalera; x++) {
                matrizePanela.add(pBistaMatrizea[x][y]);
            }
        }
        this.revalidate();
        this.repaint();
    }
}