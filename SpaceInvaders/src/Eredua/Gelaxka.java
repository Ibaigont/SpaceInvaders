
package Eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
    private int zabalera;
    private int altuera;
    
    private EdukiaEgoera edukia; 

    public Gelaxka(int zabalera, int altuera) {
        this.zabalera = zabalera;
        this.altuera = altuera;
        this.edukia = new HutsaEgoera(); 
    }
    
    public int getZabalera() {
        return this.zabalera;
    }
    
    public int getAltuera() {
        return this.altuera;
    }
    
    public EdukiaEgoera getEdukia() {
        return this.edukia;
    }
    
    public void setEdukia(EdukiaEgoera edukia) {
        this.edukia = edukia;
    }

    public void notifikatu() {
        setChanged();
        notifyObservers(this.edukia); 
    }
}
