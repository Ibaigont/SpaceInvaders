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
    private JButton btnJolastu;

    private GelaxkaBista[][] bistaMatrizea = null;

    public LeihoNagusia() {
        JokoKudeaketa.getJokoKudeaketa().addObserver(this);
     

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
        // Listen to key events on this frame
        this.addKeyListener(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(() -> {
            if ("MTRX_SORTUTA".equals(arg)) {
                jokoPanelaAtala.preparatuMatrizea(100, 60);
                bistaMatrizea = jokoPanelaAtala.getBistaMatrizea();
                for (int y = 0; y < 60; y++) {
                    for (int x = 0; x < 100; x++) {
                    	GelaxkaBista Gelaxka = bistaMatrizea[x][y];
                     	if (Gelaxka != null) {
                    		MatrizeEredua.getMatrizea().getGelaxka(x, y).addObserver(Gelaxka);
                 
                    	}
                    }         
                }
                MatrizeEredua.getMatrizea().gelaxkaGuztiakNotifikatu();
                kartaDiseinua.show(kartaPanela, "JOKOA");
                LeihoNagusia.this.requestFocusInWindow();

            } else if ("IRABAZI".equals(arg)) {
                kartaDiseinua.show(kartaPanela, "IRABAZI");

            } else if ("GALDU".equals(arg)) {
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
        // Use this as ActionListener
        btnJolastu.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("JOLASTU".equals(e.getActionCommand())) {
            // Mostrar inmediatamente la tarjeta de juego antes de inicializar el modelo
            // así el usuario ve la transición de forma instantánea.
            kartaDiseinua.show(kartaPanela, "JOKOA");
            System.out.println("[LeihoNagusia] Boton JOLASTU pulsado: mostrando JOKOA y lanzando inicializacion en background");
            // Inicializamos el modelo en un hilo de fondo para no bloquear la EDT.
            new Thread(() -> {
                JokoKudeaketa.getJokoKudeaketa().hasieratuJokoa();
            }, "Inicializa-Jokoa-Thread").start();
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("EZKERRA");
        else if (code == KeyEvent.VK_RIGHT) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("ESKUINA");
        else if (code == KeyEvent.VK_UP) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("GORA");
        else if (code == KeyEvent.VK_DOWN) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("BEHERA");
        else if (code == KeyEvent.VK_SPACE) JokoKudeaketa.getJokoKudeaketa().teklaAskatu("TIROA");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }
}