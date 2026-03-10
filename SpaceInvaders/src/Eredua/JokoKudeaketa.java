package Eredua;

public class JokoKudeaketa {

    private static JokoKudeaketa nireKudeaketa = new JokoKudeaketa();
    private boolean jokoaHasita = false;
    private Boolean irabazi = false;
    	
    private JokoKudeaketa() {}

    public static JokoKudeaketa getJokoKudeaketa() {
        return nireKudeaketa;
    }

   
    public void hasieratuJokoa(MatrizeEredua eredua) {
        if (eredua == null) return;
        jokoaHasita = true;
        eredua.matrizeaSortu();
    }

    
    public void egiaztatuAmaiera() {
    	MatrizeEredua eredua = MatrizeEredua.getMatrizea();
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
                    if (y >= altuera - 2) {
                        eredua.amaituJokoa(false);
                        eredua.bistaEguneratu();
                        return;
                    }
                }
                if (e == Edukia.EspazioOntzia) {
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;
                            if (nx >= 1 && nx < zabalera - 1 && ny >= 1 && ny < altuera - 1) {
                                if (gelaxka[nx][ny].getEdukia() == Edukia.Etsaia && ny == y) {
                                    eredua.amaituJokoa(false);
                                    eredua.bistaEguneratu();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!anyEtsai) {
        	irabazi = true;
            eredua.amaituJokoa(true);
            eredua.bistaEguneratu();
            return;
        }
    }
    
    public boolean isJokoaHasita() { return jokoaHasita; }
}
