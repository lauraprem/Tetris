package Modele.GestionGrilleDeJeu;

/**
 * représente une <b>Grille de Tetris</b>, qui étend une Grille de Jeu de
 * manière plus générale
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class GrilleDeTetris extends GrilleDeJeu{
    
    /**
     * <b>Constructeur</b> permettant de créer une Grille de taille 20,11
     */
    public GrilleDeTetris()
    {
        super(20, 11);
    }

    public boolean CheckBlocLigne(Position pos, int n, int id)
    {
        return false;
    }

    public boolean PartieTermine(int n, int id, Position p)
    {
        return false;
    }
}