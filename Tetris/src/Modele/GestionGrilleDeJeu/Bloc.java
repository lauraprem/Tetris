package Modele.GestionGrilleDeJeu;

import Modele.GestionGrilleDeJeu.Enums.Couleur;

/**
 * Représente une <b>Case d'une Grille</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class Bloc {

    /**
     * position du Bloc
     */
    private Position position;

    /**
     * couleur du Bloc
     */
    private int couleur;

    // CONSTRUCTEURS
    /**
     * <b>Constructeur<\b> par defaut
     */
    public Bloc() {
        this(null, Couleur.VIDE.valeur);
    }

    /**
     * <b>Constructeur<\b> par la position
     *
     * @param position du Bloc
     */
    public Bloc(Position position) {
        this(position, Couleur.VIDE.valeur);
    }

    /**
     * <b>Constructeur<\b> par la position et la couleur
     *
     * @param position du Bloc
     * @param couleur du bloc
     */
    public Bloc(Position position, int couleur) {
        this.position = position;
        this.couleur = couleur;
    }

    // ACCESSEURS
    public Position getPosition() {
        return position;
    }

    public int getCouleur() {
        return couleur;
    }

    // MUTATEURS
    public void setPosition(Position position) {
        this.position = position;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }
}
