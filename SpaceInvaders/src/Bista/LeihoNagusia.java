package Bista;

import javax.swing.*;
import Eredua.JokoKudeaketa;
import Eredua.MatrizeEredua;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class LeihoNagusia extends JFrame implements Observer, ActionListener, KeyListener {
    private JPanel kartaPanela;
    private CardLayout kartaDiseinua;
    private JokoPanela jokoPanelaAtala;
    private KargatzenPantaila kargatzenPantailaAtala; 
    
    private JButton btnBerdea;
    private JButton btnGorria;
    private JButton btnUrdina;
    private JButton btnPartidaNormala;
    private JButton btnMugagabe;
    private JLabel modoSeleccionadoLabel;
    
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
        kargatzenPantailaAtala = new KargatzenPantaila();
        jokoPanelaAtala.setFocusable(false);
        
        jokoPanelaAtala.preparatuMatrizea(100, 60);
        
        kartaPanela.add(hasieraPanelaSortu(), "HASIERA");
        kartaPanela.add(kargatzenPantailaAtala, "KARGATZEN");
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
        if ("MTRX_SORTUTA".equals(arg)) {
            Timer pausaEstetikoa = new Timer(2000, e -> {
                SwingUtilities.invokeLater(() -> {
                    Eredua.MatrizeEredua.getMatrizea().gelaxkaGuztiakNotifikatu();
                    kartaDiseinua.show(kartaPanela, "JOKOA");
                    LeihoNagusia.this.requestFocusInWindow();
                });
            });
            pausaEstetikoa.setRepeats(false);
            pausaEstetikoa.start();
        } else if ("IRABAZI".equals(arg)) {
            SwingUtilities.invokeLater(() -> {
                int puntu = MatrizeEredua.getMatrizea().getPuntuazioa();
                irabaziPantaila.setMezua(true, puntu);
                kartaDiseinua.show(kartaPanela, "IRABAZI");
            });
        } else if ("GALDU".equals(arg)) {
            SwingUtilities.invokeLater(() -> {
                int puntu = MatrizeEredua.getMatrizea().getPuntuazioa();
                galduPantaila.setMezua(false, puntu);
                kartaDiseinua.show(kartaPanela, "GAMEOVER");
            });
        }
    }

    private JPanel hasieraPanelaSortu() {
        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setFocusable(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

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

        JPanel modoPanela = new JPanel();
        modoPanela.setOpaque(false);
        modoPanela.setLayout(new FlowLayout());
        btnPartidaNormala = new JButton("Partida 1");
        btnPartidaNormala.setFocusable(false);
        btnPartidaNormala.setBackground(Color.GRAY);
        btnPartidaNormala.setForeground(Color.WHITE);
        btnPartidaNormala.setActionCommand("NORMALA");
        btnPartidaNormala.addActionListener(this);
        btnMugagabe = new JButton("Mugagabea");
        btnMugagabe.setFocusable(false);
        btnMugagabe.setBackground(Color.GRAY);
        btnMugagabe.setForeground(Color.WHITE);
        btnMugagabe.setActionCommand("MUGAGABE");
        btnMugagabe.addActionListener(this);
        modoPanela.add(btnPartidaNormala);
        modoPanela.add(btnMugagabe);
        p.add(modoPanela);
        
        modoSeleccionadoLabel = new JLabel("AUKERATU JOKATZEKO MODOA");
        modoSeleccionadoLabel.setForeground(Color.YELLOW);
        modoSeleccionadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(modoSeleccionadoLabel);
        p.add(Box.createVerticalStrut(20));

        JLabel aukeratuText = new JLabel("AUKERATU ZURE ONTZIA JOLASTEKO:");
        aukeratuText.setForeground(Color.WHITE);
        aukeratuText.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(aukeratuText);
        p.add(Box.createVerticalStrut(20));

        JPanel botoiPanela = new JPanel();
        botoiPanela.setOpaque(false);
        botoiPanela.setLayout(new FlowLayout());

        btnBerdea = sortuBotoia("Berdea", Color.GREEN);
        btnGorria = sortuBotoia("Gorria", Color.RED);
        btnUrdina = sortuBotoia("Urdina", Color.BLUE);
        btnBerdea.setEnabled(false);
        btnGorria.setEnabled(false);
        btnUrdina.setEnabled(false);

        botoiPanela.add(btnBerdea);
        botoiPanela.add(btnGorria);
        botoiPanela.add(btnUrdina);
        p.add(botoiPanela);
        p.add(Box.createVerticalGlue());
        
        return p;
    }

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
        
        if (cmd.equals("NORMALA")) {
            JokoKudeaketa.getJokoKudeaketa().setModoJuego(false);
            modoSeleccionadoLabel.setText("Modua: Partida 1 (bizitza extra barik)");
            btnBerdea.setEnabled(true);
            btnGorria.setEnabled(true);
            btnUrdina.setEnabled(true);
        } else if (cmd.equals("MUGAGABE")) {
            JokoKudeaketa.getJokoKudeaketa().setModoJuego(true);
            modoSeleccionadoLabel.setText("Modua: Mugagabea (3 bizitza, bolada infinituak)");
            btnBerdea.setEnabled(true);
            btnGorria.setEnabled(true);
            btnUrdina.setEnabled(true);
        } else if (cmd.startsWith("JOLASTU_")) {
            String koloreaRaw = cmd.substring(8); 
            String kolorea = koloreaRaw.substring(0, 1) + koloreaRaw.substring(1).toLowerCase();
            
            kartaDiseinua.show(kartaPanela, "KARGATZEN");
            
            Timer t = new Timer(100, evt -> JokoKudeaketa.getJokoKudeaketa().hasieratuJokoa(kolorea));
            t.setRepeats(false);
            t.start();
        } 
        else if (cmd.equals("BERRIRO")) {
            kartaDiseinua.show(kartaPanela, "HASIERA");
        } 
        else if (cmd.equals("IRTEN")) {
            System.exit(0);
        }
    }

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