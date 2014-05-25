package tetris.Controleur;

import tetris.Modele.JeuDeTetris;

/**
 * Représente un <b>Controleur</b> : permet de regrouper les points communs avec
 * les tout les Controleurs
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class Controleur {
    // ATTRIBUT
    /**
     * modele du jeu Tetris
     */
    protected JeuDeTetris modele;

    // CONSTRUCTEUR
    /**
     * <b>Constructeur<\b> qui permet d'obtenir le modele par référence
     */
    public Controleur(JeuDeTetris modele) {
        this.modele = modele;
    }
}
