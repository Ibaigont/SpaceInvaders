package Eredua;

import java.awt.EventQueue;

import Bista.LeihoNagusia;
import Kontroladorea.Kontroladorea;


public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	MatrizeEredua eredua = MatrizeEredua.getMatrizea();
                	Kontroladorea kontroladorea = new Kontroladorea(eredua);
                    LeihoNagusia frame = new LeihoNagusia();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}