package Eredua;

import java.util.Observable;


public class Gelaxka extends Observable {
    private int zabalera;
    private int altuera;
    private Edukia edukia;

    public Gelaxka(int zabalera, int altuera) {
        this.zabalera = zabalera;
        this.altuera = altuera;
    }
    public int getZabalera() {
        return this.zabalera;
    }
    public int getAltuera() {
        return this.altuera;
    }
    public Edukia getEdukia() {
        return this.edukia;
    }
    public void setEdukia(Edukia edukia) {
        this.edukia = edukia;
    }

    public void notifikatu() {
        setChanged();
        notifyObservers(edukia == null ? null : edukia.kolorea);
    }
}