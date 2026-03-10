package Kontroladorea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Timer;

import Eredua.JokoKudeaketa;
import Eredua.MatrizeEredua;

public class Kontroladorea extends KeyAdapter implements ActionListener {

    private Timer jokoBegizta;
    private Set<Integer> teclasPresionadas = new HashSet<>();
    private MatrizeEredua eredua;

    public Kontroladorea(MatrizeEredua eredua) {
        this.eredua = eredua;
        jokoBegizta = new Timer(50, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("JOLASTU".equals(e.getActionCommand())) {
            JokoKudeaketa.getJokoKudeaketa().hasieratuJokoa(eredua);
            jokoBegizta.start();
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

            eredua.tick();
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
