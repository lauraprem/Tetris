/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Controlleur;

import tetris.Modele.GestionJeuDeTetris.JeuDeTetris;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author p1307999
 */
public class ControlleurClavier implements KeyListener {

    JeuDeTetris tetris;

    public ControlleurClavier(JeuDeTetris _t) {
        super();
        tetris = _t;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        System.out.println("Code touche pressée : " + event.getKeyCode()
                + " - caractère touche pressée : " + event.getKeyChar());

    }

    @Override
    public void keyPressed(KeyEvent event) {

        System.out.println("Code touche relâchée : " + event.getKeyCode()
                + " - caractère touche relâchée : " + event.getKeyChar());
        if (event.getKeyCode() == KeyEvent.VK_P) {
            tetris.gestionEnPause();
        }
        if (event.getKeyCode() == KeyEvent.VK_Q) {
            tetris.DeplacerPiece(event.getKeyCode());
        }
        if (event.getKeyChar() == KeyEvent.VK_D) {
            tetris.DeplacerPiece(event.getKeyCode());

        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        System.out.println("Code touche tapée : " + event.getKeyCode()
                + " - caractère touche tapée : " + event.getKeyChar());
    }

}
