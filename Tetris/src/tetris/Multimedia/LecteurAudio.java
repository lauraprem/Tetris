/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Multimedia;

import java.applet.AudioClip;
import java.io.IOException;
import java.io.FileInputStream;
import java.net.URL;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Corinne
 */
public class LecteurAudio implements AudioClip {

    private AudioData audiodata;

    private AudioDataStream audiostream;

    private ContinuousAudioDataStream continuousaudiostream;

    static int length;

    public LecteurAudio(URL url) {
        try {
            audiodata = new AudioStream(url.openStream()).getData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        audiostream = null;
        continuousaudiostream = null;
    }

    public LecteurAudio(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            AudioStream audioStream = new AudioStream(fis);
            audiodata = audioStream.getData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        audiostream = null;
        continuousaudiostream = null;
    }

    @Override
    public void play() {
        audiostream = new AudioDataStream(audiodata);
        sun.audio.AudioPlayer.player.start(audiostream);
    }

    @Override
    public void loop() {
        continuousaudiostream = new ContinuousAudioDataStream(audiodata);
        sun.audio.AudioPlayer.player.start(continuousaudiostream);
    }

    @Override
    public void stop() {
        if (audiostream != null) {
            sun.audio.AudioPlayer.player.stop(audiostream);
        }
        if (continuousaudiostream != null) {
            sun.audio.AudioPlayer.player.stop(continuousaudiostream);
        }
    }

}
