package Bista;

import javax.swing.*;
import Eredua.JokoKudeaketa;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class LeihoNagusia extends JFrame implements Observer, ActionListener, KeyListener {

    private JPanel kartaPanela;
    private CardLayout kartaDiseinua;
    private JokoPanela jokoPanelaAtala;
    
    // Botoi berriak hautaketarako
    private JButton btnBerdea;
    private JButton btnGorria;
    private JButton btnUrdina;
    
    private GameOverPantaila irabaziPantaila;
    private GameOverPantaila galduPantaila;


    public LeihoNagusia() {
        this.setTitle("Space Invaders - Aukeratu zure Ontzia");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        
        kartaDiseinua = new CardLayout();
        kartaPanela = new JPanel(kartaDiseinua);

        jokoPanelaAtala = JokoPanela.getJokoPanela();
        jokoPanelaAtala.setFocusable(false);
        
        // Hasierako panela kargatu
        kartaPanela.add(hasieraPanelaSortu(), "HASIERA");
        kartaPanela.add(jokoPanelaAtala, "JOKOA");
        
        irabaziPantaila = new GameOverPantaila();
        galduPantaila = new GameOverPantaila();
        irabaziPantaila.setActionListener(this);
        galduPantaila.setActionListener(this);
        kartaPanela.add(irabaziPantaila, "IRABAZI");
        kartaPanela.add(galduPantaila, "GAMEOVER");
        
        kartaPanela.setFocusable(false);

        this.add(kartaPanela);
        this.addKeyListener(this);
        
        JokoKudeaketa.getJokoKudeaketa().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(() -> {
            if ("MTRX_SORTUTA".equals(arg)) {
                jokoPanelaAtala.preparatuMatrizea(100, 60);
                Eredua.MatrizeEredua.getMatrizea().gelaxkaGuztiakNotifikatu();
                kartaDiseinua.show(kartaPanela, "JOKOA");
                LeihoNagusia.this.requestFocusInWindow();

            } else if ("IRABAZI".equals(arg)) {
                irabaziPantaila.setMezua(true);
                kartaDiseinua.show(kartaPanela, "IRABAZI");

            } else if ("GALDU".equals(arg)) {
                galduPantaila.setMezua(false);
                kartaDiseinua.show(kartaPanela, "GAMEOVER");
            }
        });
    }

    private JPanel hasieraPanelaSortu() {
        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setFocusable(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        // LOGOA EDO IRUDIA
        java.net.URL imgURL = getClass().getResource("space_invaders.jpg");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaled = icon.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaled));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(Box.createVerticalGlue());
            p.add(label);
        } else {
            JLabel label = new JLabel("SPACE INVADERS");
            label.setForeground(Color.GREEN);
            label.setFont(new Font("Arial", Font.BOLD, 48));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(Box.createVerticalGlue());
            p.add(label);
        }

        p.add(Box.createVerticalStrut(30));

        // TESTUA
        JLabel aukeratuText = new JLabel("AUKERATU ZURE ONTZIA JOLASTEKO:");
        aukeratuText.setForeground(Color.WHITE);
        aukeratuText.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(aukeratuText);
        p.add(Box.createVerticalStrut(20));

        // BOTOI PANELA (Strategy hautaketa)
        JPanel botoiPanela = new JPanel();
        botoiPanela.setOpaque(false);
        botoiPanela.setLayout(new FlowLayout());

        btnBerdea = sortuBotoia("Berdea", Color.GREEN);
        btnGorria = sortuBotoia("Gorria", Color.RED);
        btnUrdina = sortuBotoia("Urdina", Color.BLUE);

        botoiPanela.add(btnBerdea);
        botoiPanela.add(btnGorria);
        botoiPanela.add(btnUrdina);

        p.add(botoiPanela);
        p.add(Box.createVerticalGlue());
        
        return p;
    }

    // Botoiak sortzeko laguntzailea
    private JButton sortuBotoia(String izena, Color c) {
        JButton b = new JButton(izena);
        b.setFocusable(false);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setActionCommand("JOLASTU_" + izena.toUpperCase());
        b.addActionListener(this);
        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        // "JOLASTU_BERDEA", "JOLASTU_GORRIA", etab.
        if (cmd.startsWith("JOLASTU_")) {
            String koloreaRaw = cmd.substring(8); // "BERDEA", "GORRIA"...
            // Formatu egokia eman: "Berdea"
            String kolorea = koloreaRaw.substring(0, 1) + koloreaRaw.substring(1).toLowerCase();
            // Jokoa hasieratu aukeratutako kolorearekin
            JokoKudeaketa.getJokoKudeaketa().hasieratuJokoa(kolorea);
        } 
        else if (cmd.equals("BERRIRO")) {
            kartaDiseinua.show(kartaPanela, "HASIERA");
        } 
        else if (cmd.equals("IRTEN")) {
            System.exit(0);
        }
    }

    // TEKLATUAREN KONTROLA
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) JokoKudeaketa.getJokoKudeaketa().teklaSakatu("EZKERRA");
        else if (code == KeyEvent.VK_RIGHT) JokoKudeaketa.getJokoKudeaketa().teklaSakatu("ESKUINA");
        else if (code == KeyEvent.VK_UP) JokoKudeaketa.getJokoKudeaketa().teklaSakatu("GORA");
        else if (code == KeyEvent.VK_DOWN) JokoKudeaketa.getJokoKudeaketa().teklaSakatu("BEHERA");
        else if (code == KeyEvent.VK_SPACE) JokoKudeaketa.getJokoKudeaketa().teklaSakatu("TIROA");
        else if (code == KeyEvent.VK_M) JokoKudeaketa.getJokoKudeaketa().teklaSakatu("TIROA_ALDATU");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("EZKERRA");
        else if (code == KeyEvent.VK_RIGHT) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("ESKUINA");
        else if (code == KeyEvent.VK_UP) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("GORA");
        else if (code == KeyEvent.VK_DOWN) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("BEHERA");
        else if (code == KeyEvent.VK_SPACE) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("TIROA");
         else if (code == KeyEvent.VK_M) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("TIROA_ALDATU");
    }

    @Override public void keyTyped(KeyEvent e) {}
}