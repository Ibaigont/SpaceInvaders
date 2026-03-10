package Bista;

import javax.swing.*;
import Eredua.MatrizeEredua;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import Kontroladorea.Kontroladorea;

@SuppressWarnings("deprecation")
public class LeihoNagusia extends JFrame implements Observer {

    private MatrizeEredua eredua;
    private Kontroladorea kontroladorea;
    private JPanel kartaPanela;
    private CardLayout kartaDiseinua;
    private JokoPanela jokoPanelaAtala;
    private JButton btnJolastu;

    public LeihoNagusia(Kontroladorea kontroladorea, MatrizeEredua eredua) {
        this.eredua = eredua;
        this.kontroladorea = kontroladorea;
        this.eredua.addObserver(this);
        this.setTitle("Space Invaders");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);

        kartaDiseinua = new CardLayout();
        kartaPanela = new JPanel(kartaDiseinua);

        JPanel hasieraPanela = hasieraPanelaSortu();
        jokoPanelaAtala = JokoPanela.getJokoPanela();
        jokoPanelaAtala.setFocusable(false);

        kartaPanela.add(hasieraPanela, "HASIERA");
        kartaPanela.add(jokoPanelaAtala, "JOKOA");
        kartaPanela.setFocusable(false);

        this.add(kartaPanela);
        this.addKeyListener(kontroladorea);
    }

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(() -> {
            if ("MTRX_SORTUTA".equals(arg)) {
                jokoPanelaAtala.inicializatuEtaKonektatu(
                        eredua.getGelaxkak(),
                        eredua.getZabalera(),
                        eredua.getAltuera());
                kartaDiseinua.show(kartaPanela, "JOKOA");
                LeihoNagusia.this.requestFocus();
            } else if ("EGUNERATU".equals(arg)) {

            } else if (eredua.isJokoaAmaitua()) {
                // kartaPanela.add(new GameOverPantaila(), "GAMEOVER"); KLASEA GEHITU BEHAR DA!!
                kartaDiseinua.show(kartaPanela, "GAMEOVER");
            }
        });
    }

    private JPanel hasieraPanelaSortu() {
        JPanel p = new JPanel();
        p.setFocusable(false);
        p.add(new JLabel("SPACE INVADERS"));
        btnJolastu = new JButton("Jolastu");
        btnJolastu.setActionCommand("JOLASTU");
        btnJolastu.setFocusable(false);
        btnJolastu.addActionListener(kontroladorea);
        p.add(btnJolastu);
        return p;
    }
}