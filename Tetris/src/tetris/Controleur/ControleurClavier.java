package tetris.Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.Modele.JeuDeTetris;

/**
 * Représente un <b>Controleur</b> des touches clavier
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class ControleurClavier extends Controleur implements KeyListener {
    // CONSTRUCTEUR
    /**
     * <b>Constructeur<\b> par defaut
     */
    public ControleurClavier(JeuDeTetris modele) {
        super(modele);
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

        switch (event.getKeyCode()) {
            case KeyEvent.VK_P:
                modele.gestionEnPause();
                break;
            case KeyEvent.VK_DOWN: //déplacement vers le bas
                modele.gestionVitesse();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        System.out.println("Code touche tapée : " + event.getKeyCode()
                + " - caractère touche tapée : " + event.getKeyChar());

        switch (event.getKeyCode()) {
            case KeyEvent.VK_Q: // déplacement à gauche
                modele.deplacerPiece(event.getKeyCode());
                break;
            case KeyEvent.VK_D: // déplacement à droite
                modele.deplacerPiece(event.getKeyCode());
                break;
            case KeyEvent.VK_LEFT: //rotation vers le gauche
                modele.deplacerPiece(event.getKeyCode());
                break;
            case KeyEvent.VK_RIGHT: //rotation vers la droite
                modele.deplacerPiece(event.getKeyCode());
                break;
            default:
                break;
        }
    }

}
