/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.Modele.Action;
import tetris.Modele.JeuDeTetris;
import tetris.Modele.JeuDeTetris2Joueurs;

/**
 *
 * @author leclerc
 */
public class ControleurJoueurs implements KeyListener
{
    // CONSTRUCTEUR
    /**
     * <b>Constructeur<\b> par defaut
     *
     * @param modele
     */

    JeuDeTetris2Joueurs modele1;
    JeuDeTetris2Joueurs modele2;

    public ControleurJoueurs(JeuDeTetris2Joueurs _modele1, JeuDeTetris2Joueurs _modele2)
    {
        modele1 = _modele1;
        modele2 = _modele2;
    }

    @Override
    public void keyTyped(KeyEvent event)
    {
        //  System.out.println("Caractère touché : " + event.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        // System.out.println("Code touche pressée : " + event.getKeyCode());
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_SPACE: // met en pause le jeu
                modele1.gestionEnPause();
                modele2.gestionEnPause();
                break;
            case KeyEvent.VK_A: // accélerer la vitesse de la chute des blocs joueur 1
                modele1.accelerer();
                break;
            case KeyEvent.VK_P: // accélerer la vitesse de la chute des blocs joueur 2
                modele2.accelerer();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
     //   System.out.println("Code touche relaché : " + event.getKeyCode());

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_Q: // déplacement à gauche
                modele1.deplacerPiece(Action.DEPLACEMENT_G);
                break;
            case KeyEvent.VK_D: // déplacement à droite
                modele1.deplacerPiece(Action.DEPLACEMENT_D);
                break;
            case KeyEvent.VK_Z: //rotation vers le gauche
                modele1.deplacerPiece(Action.ROTATION_G);
                break;
            case KeyEvent.VK_S: //rotation vers la droite
                modele1.deplacerPiece(Action.ROTATION_D);
                break;
            case KeyEvent.VK_A:  // ralentir la vitesse de la chute des blocs
                modele1.decelerer();
                break;
            case KeyEvent.VK_K: // déplacement à gauche
                modele2.deplacerPiece(Action.DEPLACEMENT_G);
                break;
            case KeyEvent.VK_M: // déplacement à droite
                modele2.deplacerPiece(Action.DEPLACEMENT_G);
                break;
            case KeyEvent.VK_O: //rotation vers le gauche
                modele2.deplacerPiece(Action.ROTATION_G);
                break;
            case KeyEvent.VK_L: //rotation vers la droite
                modele2.deplacerPiece(Action.ROTATION_D);
                break;
            case KeyEvent.VK_P:  // ralentir la vitesse de la chute des blocs
                modele2.decelerer();
            default:
                break;
        }
    }

}
