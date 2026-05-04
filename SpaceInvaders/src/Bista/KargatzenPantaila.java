package Bista;

import javax.swing.*;
import java.awt.*;

public class KargatzenPantaila extends JPanel {

    public KargatzenPantaila() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JLabel lbl = new JLabel("KARGATZEN...", SwingConstants.CENTER);
        lbl.setForeground(Color.GREEN);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 40));

        JLabel subLbl = new JLabel("Prestatzen etsaiak urruntzeko...", SwingConstants.CENTER);
        subLbl.setForeground(Color.DARK_GRAY);
        subLbl.setFont(new Font("Arial", Font.ITALIC, 14));

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setOpaque(false);
        centerPanel.add(lbl);
        centerPanel.add(subLbl);

        this.add(centerPanel, BorderLayout.CENTER);
    }
}