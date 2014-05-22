/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author p1307999
 */
public class Clavier implements KeyListener {

    public Clavier() {
        super();
    }

    @Override
    public void keyTyped(KeyEvent event) {
        System.out.println("Code touche pressée : " + event.getKeyCode()
                + " - caractère touche pressée : " + event.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent event) {
        System.out.println("Code touche pressée : " + event.getKeyCode()
                + " - caractère touche pressée : " + event.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        System.out.println("Code touche pressée : " + event.getKeyCode()
                + " - caractère touche pressée : " + event.getKeyChar());
    }

}
