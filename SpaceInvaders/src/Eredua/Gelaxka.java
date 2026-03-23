package Eredua;

import java.util.Observable;


public class Gelaxka extends Observable {
    private int zabalera;
    private int altuera;
    private Edukia edukia;
    // ownerId identifica la entidad (por ejemplo, un enemigo) a la que pertenece este pixel
    // -1 indica que no pertenece a ninguna entidad
    private int ownerId = -1;

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
    /**
     * Establece el contenido sin asociado a ninguna entidad (ownerId = -1)
     */
    public void setEdukia(Edukia edukia) {
        this.edukia = edukia;
        this.ownerId = -1;
    }

    /**
     * Establece el contenido y asigna un ownerId para agrupar pixeles pertenecientes
     * a la misma entidad (por ejemplo, un enemigo compuesto por varios pixeles).
     */
    public void setEdukia(Edukia edukia, int ownerId) {
        this.edukia = edukia;
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void notifikatu() {
        setChanged();
        // Mantener la notificación antigua (nombre del Edukia) para no romper la vista
        notifyObservers(edukia == null ? null : edukia.name()); // ? if else bezalako bat da, egia bada = nill, bestela edikia.name();
    }
}