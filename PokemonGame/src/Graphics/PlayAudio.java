package Graphics;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayAudio {

    public static void play(String filePath, boolean loop) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            if (loop) {
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            audioClip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
