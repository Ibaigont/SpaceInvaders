package Bista;

import javax.swing.*;
import Eredua.JokoKudeaketa;
import Eredua.MatrizeEredua;
import Kontroladorea.Kontroladorea;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class LeihoNagusia extends JFrame implements Observer {

    private MatrizeEredua eredua;
    private Kontroladorea kontroladorea;
    private JPanel kartaPanela;
    private CardLayout kartaDiseinua;
    private JokoPanela jokoPanelaAtala;
    private JButton btnJolastu;

    public LeihoNagusia() {
        this.eredua = MatrizeEredua.getMatrizea();
        JokoKudeaketa.getJokoKudeaketa().addObserver(this);
        this.kontroladorea = new Kontroladorea(eredua);

        this.setTitle("Space Invaders");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);

        kartaDiseinua = new CardLayout();
        kartaPanela = new JPanel(kartaDiseinua);

        jokoPanelaAtala = JokoPanela.getJokoPanela();
        jokoPanelaAtala.setFocusable(false);

        kartaPanela.add(hasieraPanelaSortu(), "HASIERA");
        kartaPanela.add(jokoPanelaAtala, "JOKOA");
        kartaPanela.add(amaieraPanelaSortu("WINNER WINNER CHIKEN DINNER", Color.GREEN), "IRABAZI");
        kartaPanela.add(amaieraPanelaSortu("GALDU... Saiatu berriro!", Color.RED), "GAMEOVER");
        kartaPanela.setFocusable(false);

        this.add(kartaPanela);
        this.addKeyListener(kontroladorea);
    }

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(() -> {
            if ("MTRX_SORTUTA".equals(arg)) {
                kartaDiseinua.show(kartaPanela, "JOKOA");
                kontroladorea.getJokoBegizta().start();
                LeihoNagusia.this.requestFocus();

            } else if ("IRABAZI".equals(arg)) {
                kontroladorea.getJokoBegizta().stop();
                kartaDiseinua.show(kartaPanela, "IRABAZI");

            } else if ("GALDU".equals(arg)) {
                kontroladorea.getJokoBegizta().stop();
                kartaDiseinua.show(kartaPanela, "GAMEOVER");
            }
        });
    }

    private JPanel hasieraPanelaSortu() {
        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setFocusable(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        java.net.URL imgURL = getClass().getResource("space_invaders.jpg");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            int targetWidth = 350; 
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            if (w > 0) {
                int scaledHeight = (int) ((double) targetWidth * h / w);
                Image scaled = icon.getImage().getScaledInstance(targetWidth, scaledHeight, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaled);
            }
            JLabel label = new JLabel(icon);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(Box.createVerticalGlue());
            p.add(label);
            p.add(Box.createVerticalStrut(10));
        } else {
            // Irudiaren kargatzean arazoa  badago
            JLabel label = new JLabel("SPACE INVADERS");
            label.setForeground(Color.GREEN);
            label.setFont(new Font("Arial", Font.BOLD, 36));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(Box.createVerticalGlue());
            p.add(label);
            p.add(Box.createVerticalStrut(10));
        }

        btnJolastu = new JButton("Jolastu");
        btnJolastu.setFocusable(false);
        btnJolastu.setActionCommand("JOLASTU");
        btnJolastu.addActionListener(kontroladorea);
        btnJolastu.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(btnJolastu);
        p.add(Box.createVerticalGlue());
        return p;
    }

    private JPanel amaieraPanelaSortu(String mezua, Color kolorea) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.BLACK);
        JLabel label = new JLabel(mezua);
        label.setForeground(kolorea);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        p.add(label);
        return p;
    }
}