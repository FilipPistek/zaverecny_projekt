package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/damage.wav");
        soundURL[1] = getClass().getResource("/sound/healing.wav");
        soundURL[2] = getClass().getResource("/sound/level_up.wav");
        soundURL[3] = getClass().getResource("/sound/swing.wav");
        soundURL[4] = getClass().getResource("/sound/spell.wav");
    }

    public void setFileSound(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
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