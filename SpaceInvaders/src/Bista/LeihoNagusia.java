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
        JLabel title = new JLabel("SPACE INVADERS");
        title.setForeground(Color.GREEN);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        p.add(title);
        btnJolastu = new JButton("Jolastu");
        btnJolastu.setFocusable(false);
        btnJolastu.setActionCommand("JOLASTU");
        btnJolastu.addActionListener(kontroladorea);
        p.add(btnJolastu);
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