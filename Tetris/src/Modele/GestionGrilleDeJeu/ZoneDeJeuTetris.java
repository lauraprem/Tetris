package Modele.GestionGrilleDeJeu;

import Modele.Pieces.PieceDeJeu;
import Modele.Pieces.PieceDeTetris;

/**
 * représente une <b>Grille de Tetris</b>, qui étend une Grille de Jeu de
 * manière plus générale
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class ZoneDeJeuTetris {

    // ATTRIBUTS   
    /**
     * Nombre de lignes de la Grille
     */
    protected int hauteur;

    /**
     * Nombre de colonnes de la Grille
     */
    protected int largeur;

    /**
     * Piece courante
     */
    protected PieceDeTetris pieceCourante;

    /**
     * <b>Constructeur</b> permettant de créer une Grille de taille 20 sur 10
     */
    public ZoneDeJeuTetris() {
        hauteur = 20;
        largeur = 10;
        pieceCourante = null;
    }
    
    // ACCESSEURS
    /**
     * <b>Accesseur</b> permettant de recuperer le nombre de lignes de la Grille
     *
     * @return nombre de lignes de la Grille
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * <b>Accesseur</b> permettant de recuperer le nombre de colonnes de la
     * Grille
     *
     * @return nombre de colonnes de la Grille
     */
    public int getLargeur() {
        return largeur;
    }
    
    /**
     * <b>Mutateur</b> permettant de récupérer la pièce courante
     *
     * @return pieceCourante la pièce courante
     */
    public PieceDeTetris setPieceCourante() {
        return pieceCourante;
    }

    // MUTATEURS
    /**
     * <b>Mutateur</b> permettant de modifier le nombre de lignes de la zone de
     *  jeu
     *
     * @param hauteur nouveau nombre de lignes de la zone de jeu
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * <b>Mutateur</b> permettant de modifier le nombre de colonnes de la zone
     *  de jeu
     *
     * @param largeur nouveau nombre de colonnes de la zone de jeu
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    
    /**
     * <b>Mutateur</b> permettant de modifier la pièce courante
     *
     * @param pieceCourante nouvelle pièce courante
     */
    public void setPieceCourante(PieceDeTetris pieceCourante) {
        this.pieceCourante = pieceCourante;
    }
    
    // METHODES
    public boolean GenererPiece() {
        return false;
    }
    
    public boolean VerifCollision() {
        return false;
    }
    
    public boolean DeplacerPiece() {
        return false;
    }

    public boolean VerifLigne() {
        return false;
    }
    
    public boolean SupprimerLigne() {
        return false;
    }
}
