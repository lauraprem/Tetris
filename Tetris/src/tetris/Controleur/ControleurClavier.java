package tetris.Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static tetris.Modele.Action.*;
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
     *
     * @param modele
     */
    public ControleurClavier(JeuDeTetris modele) {
        super(modele);
    }

    @Override
    public void keyTyped(KeyEvent event) {
        System.out.println("Caractère touché : " + event.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent event) {
        System.out.println("Code touche pressée : " + event.getKeyCode());
        switch (event.getKeyCode()) {
            case KeyEvent.VK_Q: // déplacement à gauche
                modele.deplacerPiece(DEPLACEMENT_G);
                break;
            case KeyEvent.VK_D: // déplacement à droite
                modele.deplacerPiece(DEPLACEMENT_D);
                break;
            case KeyEvent.VK_SPACE: // met en pause le jeu
                modele.gestionEnPause();
                break;
            case KeyEvent.VK_L: // accélerer la vitesse de la chute des blocs
                modele.accelerer();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        System.out.println("Code touche relaché : " + event.getKeyCode());

        switch (event.getKeyCode()) {
            case KeyEvent.VK_K: //rotation vers le gauche
                modele.deplacerPiece(ROTATION_G);
                break;
            case KeyEvent.VK_M: //rotation vers la droite
                modele.deplacerPiece(ROTATION_D);
                break;
            case KeyEvent.VK_L:  // ralentir la vitesse de la chute des blocs
                modele.decelerer();
            default:
                break;
        }
    }

}
        
  
