package SpaceInvaders;

import java.awt.EventQueue;

import Bista.LeihoNagusia;


public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LeihoNagusia frame = new LeihoNagusia();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}