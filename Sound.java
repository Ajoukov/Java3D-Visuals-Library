import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Sound {
    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Speed SUI Sound effect.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }
}
