package Bista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class GameOverPantaila extends JPanel {

    private JLabel mezuaTxartela;
    private JButton btnBerriro;
    private JButton btnIrten;

    public GameOverPantaila() {
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        mezuaTxartela = new JLabel("", SwingConstants.CENTER);
        mezuaTxartela.setFont(new Font("Arial", Font.BOLD, 36));
        this.add(mezuaTxartela, BorderLayout.CENTER);

        JPanel botoiPanela = new JPanel();
        botoiPanela.setBackground(Color.BLACK);
        botoiPanela.setLayout(new FlowLayout());

        btnBerriro = new JButton("Jokatu Berriro");
        btnBerriro.setActionCommand("BERRIRO");
        
        btnIrten = new JButton("Irten");
        btnIrten.setActionCommand("IRTEN");

        botoiPanela.add(btnBerriro);
        botoiPanela.add(btnIrten);

        this.add(botoiPanela, BorderLayout.SOUTH);
    }

    public void setMezua(boolean irabazi) {
        if (irabazi) {
            mezuaTxartela.setText("WINNER WINNER CHICKEN DINNER");
            mezuaTxartela.setForeground(Color.GREEN);
        } else {
            mezuaTxartela.setText("GALDU... Saiatu berriro!");
            mezuaTxartela.setForeground(Color.RED);
        }
    }

    public void setActionListener(ActionListener al) {
        btnBerriro.addActionListener(al);
        btnIrten.addActionListener(al);
    }
}