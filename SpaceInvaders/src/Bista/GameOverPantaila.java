package Bista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class GameOverPantaila extends JPanel {

    private JLabel mezuaTxartela;
    private JButton btnBerriro;
    private JButton btnIrten;
    
    private BufferedImage irudiFondoa;
    private boolean irabaziDua = false;

    public GameOverPantaila() {
        this.setLayout(new BorderLayout());
        
        mezuaTxartela = new JLabel("", SwingConstants.CENTER);
        mezuaTxartela.setFont(new Font("Arial", Font.BOLD, 36));
        this.add(mezuaTxartela, BorderLayout.CENTER);

        JPanel botoiPanela = new JPanel();
        botoiPanela.setOpaque(false); 
        botoiPanela.setLayout(new FlowLayout());

        btnBerriro = new JButton("Jokatu Berriro");
        btnBerriro.setActionCommand("BERRIRO");
        
        btnIrten = new JButton("Irten");
        btnIrten.setActionCommand("IRTEN");

        botoiPanela.add(btnBerriro);
        botoiPanela.add(btnIrten);

        this.add(botoiPanela, BorderLayout.SOUTH);
    }

    private void cargarImagenFondo(String archivo) {
        try {
            URL imageURL = getClass().getResource(archivo);
            if (imageURL != null) {
                irudiFondoa = ImageIO.read(imageURL);
            } else {
                System.err.println("No se encontró el archivo: " + archivo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMezua(boolean irabazi, int puntuazioa) {
        this.irabaziDua = irabazi;
        
        if (irabazi) {
            cargarImagenFondo("Winner.png"); 
            mezuaTxartela.setText("WINNER! Puntuazioa: " + puntuazioa); 
        } else {
            cargarImagenFondo("GameOver.png");
            mezuaTxartela.setText("GAME OVER - Puntuazioa: " + puntuazioa); 
        }
        
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (irudiFondoa != null) {
            g.drawImage(irudiFondoa, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public void setActionListener(ActionListener al) {
        btnBerriro.addActionListener(al);
        btnIrten.addActionListener(al);
    }
}