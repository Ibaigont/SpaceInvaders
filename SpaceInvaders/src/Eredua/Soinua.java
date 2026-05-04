package Eredua;
import javax.sound.sampled.*;
import java.io.File;
public class Soinua {
	private Clip clip;

	    public void erreproduzituLoop(String fitxategiIzena) {
	        try {
	            File audioFitxategia = new File(fitxategiIzena);
	            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFitxategia);
	            
	            clip = AudioSystem.getClip();
	            clip.open(audioStream);

	            // Hau da gakoa: soinua behin eta berriz erreproduzitzeko
	            clip.loop(Clip.LOOP_CONTINUOUSLY);
	            
	            clip.start();
	        } catch (Exception e) {
	            System.out.println("Errorea soinua erreproduzitzean: " + e.getMessage());
	        }
	    }
	    
	    public void erreproduzituBehin(String fitxategiIzena) {
	        try {
	            File audioFitxategia = new File(fitxategiIzena);
	            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFitxategia);
	            
	            clip = AudioSystem.getClip();
	            clip.open(audioStream);
   
	            clip.start();
	        } catch (Exception e) {
	            System.out.println("Errorea soinua erreproduzitzean: " + e.getMessage());
	        }
	    }
	    public void gelditu() {
	        if (clip != null) {
	            clip.stop();
	        }
	    }
	}
