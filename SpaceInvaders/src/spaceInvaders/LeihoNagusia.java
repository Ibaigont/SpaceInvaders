package spaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

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
        
        kartaDiseinua = new CardLayout();
        kartaPanela = new JPanel(kartaDiseinua);
        
        JPanel hasieraPanela = hasieraPanelaSortu();
        jokoPanelaAtala = new JokoPanela(); 

        kartaPanela.add(hasieraPanela, "HASIERA");
        kartaPanela.add(jokoPanelaAtala, "JOKOA");
        
        this.add(kartaPanela);
        this.addKeyListener(getKontroladorea()); 
    }

    @Override
    public void update(Observable o, Object arg) {
        jokoPanelaAtala.egoeraEguneratu(eredua.getGelaxkak(), eredua.getZabalera(), eredua.getAltuera());
    }

    private JPanel hasieraPanelaSortu() {
        JPanel p = new JPanel();
        p.add(new JLabel("SPACE INVADERS"));
        btnJolastu = new JButton("Jolastu");
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
        
        private Timer jokoBegizta; 
        private int tickKontagailua = 0; 

        public Kontroladorea() {
            jokoBegizta = new Timer(50, this); 
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnJolastu) {
                eredua.matrizeaSortu();
                kartaDiseinua.show(kartaPanela, "JOKOA");
                LeihoNagusia.this.requestFocus(); 
                jokoBegizta.start(); 
                
            } else if (e.getSource() == jokoBegizta) {
                
                eredua.jokoZikloaEguneratu();
                
                // 50x4=200ms bakoitzean etsaiak mugitzeko
                tickKontagailua++;
                if (tickKontagailua >= 4) {
                    eredua.etsaiakMugitu();
                    tickKontagailua = 0; 
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int tekla = e.getKeyCode();
            if (tekla == KeyEvent.VK_LEFT) eredua.ontziaMugitu("EZKERRA");
            else if (tekla == KeyEvent.VK_RIGHT) eredua.ontziaMugitu("ESKUINA");
            else if (tekla == KeyEvent.VK_UP) eredua.ontziaMugitu("GORA");
            else if (tekla == KeyEvent.VK_DOWN) eredua.ontziaMugitu("BEHERA");
            else if (tekla == KeyEvent.VK_SPACE) eredua.tirokatu();
        }
    }
}