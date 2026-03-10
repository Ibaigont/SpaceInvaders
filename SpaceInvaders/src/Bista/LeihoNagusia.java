package Bista;

import javax.swing.*;
import Eredua.JokoKudeaketa;
import Eredua.MatrizeEredua;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

@SuppressWarnings("deprecation")
public class LeihoNagusia extends JFrame implements Observer {

    private MatrizeEredua eredua;
    private Kontroladorea kontroladorea = null;
    private JPanel kartaPanela;
    private CardLayout kartaDiseinua;
    private JokoPanela jokoPanelaAtala;
    private JButton btnJolastu;

    public LeihoNagusia() {
        this.eredua = MatrizeEredua.getMatrizea();
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
        this.addKeyListener(getKontroladorea());
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
                getKontroladorea().jokoBegizta.start();
                LeihoNagusia.this.requestFocus();
        	} else if ("EGUNERATU".equals(arg)) {
        		
        	}
        	else if (eredua.isJokoaAmaitua()) {
                kontroladorea.jokoBegizta.stop();
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
        btnJolastu.setFocusable(false);
        btnJolastu.addActionListener(getKontroladorea());
        p.add(btnJolastu);
        return p;
    }

    private Kontroladorea getKontroladorea() {
        if (kontroladorea == null) {
            kontroladorea = new Kontroladorea();
        }
        return kontroladorea;
    }

    private class Kontroladorea extends KeyAdapter implements ActionListener {

        Timer jokoBegizta;
        private int tickKontagailua = 0;
        private Set<Integer> teclasPresionadas = new HashSet<>();

        public Kontroladorea() {
            jokoBegizta = new Timer(50, this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnJolastu) {
                JokoKudeaketa.getJokoKudeaketa().hasieratuJokoa(eredua);
                kartaDiseinua.show(kartaPanela, "JOKOA");
                jokoBegizta.start();
                LeihoNagusia.this.requestFocus();

            } else if (e.getSource() == jokoBegizta) {
                if (eredua.isJokoaAmaitua()) {
                    jokoBegizta.stop();
                    return;
                }

                if (teclasPresionadas.contains(KeyEvent.VK_LEFT)) eredua.ontziaMugitu("EZKERRA");
                if (teclasPresionadas.contains(KeyEvent.VK_RIGHT)) eredua.ontziaMugitu("ESKUINA");
                if (teclasPresionadas.contains(KeyEvent.VK_UP)) eredua.ontziaMugitu("GORA");
                if (teclasPresionadas.contains(KeyEvent.VK_DOWN)) eredua.ontziaMugitu("BEHERA");
                if (teclasPresionadas.contains(KeyEvent.VK_SPACE)) eredua.tirokatu();

                eredua.jokoZikloaEguneratu();

                tickKontagailua++;
                if (tickKontagailua >= 4) {
                    eredua.etsaiakMugitu();
                    JokoKudeaketa.getJokoKudeaketa().egiaztatuAmaiera();
                    tickKontagailua = 0;
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            teclasPresionadas.add(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            teclasPresionadas.remove(e.getKeyCode());
        }
    }
}