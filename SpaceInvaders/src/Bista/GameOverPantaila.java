package Bista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GameOverPantaila extends JPanel {
    private JLabel mezuaTxartela;

    public GameOverPantaila() {
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        mezuaTxartela = new JLabel("", SwingConstants.CENTER);
        mezuaTxartela.setFont(new Font("Arial", Font.BOLD, 36));
        this.add(mezuaTxartela, BorderLayout.CENTER);
    }

    public void setMezua(boolean irabazi) {
        if (irabazi) {
            mezuaTxartela.setText("IRABAZI DUZU!");
            mezuaTxartela.setForeground(Color.GREEN);
        } else {
            mezuaTxartela.setText("GALDU DUZU!");
            mezuaTxartela.setForeground(Color.RED);
        }
    }
}
