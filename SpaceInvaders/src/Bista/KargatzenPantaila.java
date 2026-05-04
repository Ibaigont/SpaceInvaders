package Bista;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class KargatzenPantaila extends JPanel {

    private Image bgImage;

    public KargatzenPantaila() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        URL imgURL = getClass().getResource("kargatu.png");
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
}