package Eredua;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class Soinua {
    private Clip clip;
    private static Soinua nireSoinua = new Soinua();
    public static Soinua getSoinua() { return nireSoinua; }

    // Nombre del recurso tal como está en src/Eredua
    private static final String JOKO_MUSIKA_RESOURCE = "/Eredua/freesound_community-8-bit-melody-loop-37872.wav";

    public void jokoMusika() {
        // Stop previous
        gelditu();

        // Try classpath resource first
        InputStream is = null;
        AudioInputStream audioStream = null;
        try {
            is = getClass().getResourceAsStream(JOKO_MUSIKA_RESOURCE);
            if (is != null) {
                audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            } else {
                // fallback: try relative file inside project
                File f = new File("Eredua/freesound_community-8-bit-melody-loop-37872.wav");
                if (f.exists()) {
                    audioStream = AudioSystem.getAudioInputStream(f);
                }
            }

            if (audioStream == null) {
                System.out.println("Soinu baliabidea ez da aurkitu: " + JOKO_MUSIKA_RESOURCE);
                return;
            }

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errorea soinua erreproduzitzean: " + e.getMessage());
        } finally {
            if (audioStream != null) {
                try { audioStream.close(); } catch (Exception ignored) {}
            } else if (is != null) {
                try { is.close(); } catch (Exception ignored) {}
            }
        }
    }

    public void erreproduzituBehin(String fitxategiIzena) {
        gelditu();
        try {
            AudioInputStream audioStream = null;
            // try classpath
            InputStream is = getClass().getResourceAsStream( fitxategiIzena);
            if (is != null) {
                audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            } else {
                File f = new File(fitxategiIzena);
                if (f.exists()) audioStream = AudioSystem.getAudioInputStream(f);
                else {
                    File f2 = new File("Eredua/" + fitxategiIzena);
                    if (f2.exists()) audioStream = AudioSystem.getAudioInputStream(f2);
                }
            }

            if (audioStream == null) {
                System.out.println("Soinu fitxategia ez da aurkitu: " + fitxategiIzena);
                return;
            }

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errorea soinua erreproduzitzean: " + e.getMessage());
        }
    }

    public void gelditu() {
        if (clip != null) {
            try {
                if (clip.isRunning()) clip.stop();
                clip.close();
            } catch (Exception ignored) {}
            clip = null;
        }
    }
}
