/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris.Multimedia;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Représente un <b>lecteur de son</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class LecteurSon {
    protected AudioClip bach;
    
    public LecteurSon() {
        bach = null;
    }
    
    public LecteurSon(String f) {
        try {
              File file = new File(f);
            bach = Applet.newAudioClip(file.toURI().toURL());
        } catch (Exception ex) { //MalformedURLException
            bach = null;
            Logger.getLogger(LecteurSon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void jouer(){
        if(bach != null)
        bach.play();
    }
    
    public void stopper(){
        if(bach != null)
            bach.stop();
    }
    
    public void jouerAvecRepetition(){
        if(bach != null)
        bach.loop();
    }
}
