import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class SoundEffect {

    // Um mapa para armazenar e reutilizar sons carregados
    private static HashMap<String, Clip> soundEffects = new HashMap<>();

    // Método para carregar um som
    public static void loadSound(String soundName, String filePath, float volume) {
        try {
            URL soundURL = SoundEffect.class.getClassLoader().getResource(filePath);
            if (soundURL == null) {
                System.out.println("Arquivo de som não encontrado: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            // O volume deve estar entre os limites permitidos pelo controlador
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            
            // Define o volume, garantindo que esteja dentro dos limites
            volumeControl.setValue(Math.max(min, Math.min(volume, max)));

            // Armazena o som no mapa
            soundEffects.put(soundName, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Método para tocar o som
    public static void playSound(String soundName) {
        Clip clip = soundEffects.get(soundName);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop(); // Para o som anterior se estiver tocando
            }
            clip.setFramePosition(0); // Reposiciona para o início
            clip.start(); // Toca o som
        } else {
            System.out.println("Som não encontrado: " + soundName);
        }
    }

    // Método para parar o som
    public static void stopSound(String soundName) {
        Clip clip = soundEffects.get(soundName);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
