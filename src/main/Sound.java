package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound() {

        soundURL[0] = getClass().getResource("");
        soundURL[1] = getClass().getResource("");
        soundURL[2] = getClass().getResource("");
        soundURL[3] = getClass().getResource("");
        soundURL[4] = getClass().getResource("");
    }

    public void setFileSound(int i) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound() {

        clip.start();
    }

    public void loopSound() {

        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stopSound() {

        clip.stop();
    }
}
