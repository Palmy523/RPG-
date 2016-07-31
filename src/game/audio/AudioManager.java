/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.audio;

import com.jme3.audio.AudioNode;

/**
 *
 * @author Dave
 */
public class AudioManager {
    
    private static AudioManager instance;
    private AudioNode backgroundMusic;

    public void playBackgroundMusic(AudioNode node) {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
        this.backgroundMusic = node;
        node.play();
    }

    public void setBackgroundMusic(AudioNode backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }
    
    
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }
    
}
