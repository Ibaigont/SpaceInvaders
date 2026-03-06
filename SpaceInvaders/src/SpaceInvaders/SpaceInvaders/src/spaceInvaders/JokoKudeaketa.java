package spaceInvaders;

public class JokoKudeaketa {

    private static JokoKudeaketa nireKudeaketa = new JokoKudeaketa();
    private boolean jokoaHasita = false;

    private JokoKudeaketa() {}

    public static JokoKudeaketa getJokoKudeaketa() {
        return nireKudeaketa;
    }

    /**
     * Inicia el juego inicializando la matriz y reseteando el estado interno.
     */
    public void hasieratuJokoa(MatrizeEredua eredua) {
        if (eredua == null) return;
        jokoaHasita = true;
        eredua.matrizeaSortu();
    }

    /**
     * Comprueba condiciones de fin de juego:
     * - si un enemigo está en la misma posición que el jugador (EspazioOntzia)
     * - si un enemigo ha llegado al fondo jugable (fila altuera-2)
     * - si no queda ningún enemigo (victoria)
     * Si se detecta fin, marca el juego como terminado en el modelo y notifica vista.
     */
    public void egiaztatuAmaiera(MatrizeEredua eredua) {
        if (eredua == null) return;
        if (eredua.isJokoaAmaitua()) return;

        Gelaxka[][] gelaxka = eredua.getGelaxkak();
        int zabalera = eredua.getZabalera();
        int altuera = eredua.getAltuera();

        boolean anyEtsai = false;

        for (int x = 1; x < zabalera - 1; x++) {
            for (int y = 1; y < altuera - 1; y++) {
                Edukia e = gelaxka[x][y].getEdukia();
                if (e == Edukia.Etsaia) {
                    anyEtsai = true;
                    // Si un enemigo llega a la fila jugable más baja, terminar
                    if (y >= altuera - 2) {
                        eredua.amaituJokoa();
                        eredua.bistaEguneratu();
                        return;
                    }
                }
                // Colisión con el jugador (espacio ocupado por el jugador)
                if (e == Edukia.EspazioOntzia) {
                    // Revisar adyacentes por si un enemigo ha entrado en la casilla del jugador
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;
                            if (nx >= 1 && nx < zabalera - 1 && ny >= 1 && ny < altuera - 1) {
                                if (gelaxka[nx][ny].getEdukia() == Edukia.Etsaia && ny == y) {
                                    eredua.amaituJokoa();
                                    eredua.bistaEguneratu();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        // Si no hay enemigos, el jugador gana
        if (!anyEtsai) {
            eredua.amaituJokoa();
            eredua.bistaEguneratu();
            return;
        }
    }

    public boolean isJokoaHasita() { return jokoaHasita; }
}
