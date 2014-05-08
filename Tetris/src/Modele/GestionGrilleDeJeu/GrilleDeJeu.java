package Modele.GestionGrilleDeJeu;

import Modele.GestionGrilleDeJeu.Enums.Couleur;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Représente d'une <b>Grille de jeu</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class GrilleDeJeu {
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
     * Etat de la Grille
     */
    protected int[][] etatGrille;

    /**
     * historique de la Grille
     */
    protected ArrayList<PieceDeJeu> historique;

    // CONSTRUCTEURS
    /**
     * <b>Constructeur</b> par défaut permettant de faire une grille de 10 sur
     * 10
     */
    public GrilleDeJeu() {
        this(10, 10);
    }

    /**
     * <b>Constructeur</b> permettant de créer une grille à la dimension voulue
     *
     * @param hauteur
     * @param largeur
     */
    public GrilleDeJeu(int hauteur, int largeur) {
        this(hauteur, largeur, null);
    }

    /**
     * <b>Constructeur</b> permettant de créer une grille à partir d'une partie
     * existante
     *
     * @param hauteur
     * @param largeur
     * @param historique
     */
    public GrilleDeJeu(int hauteur, int largeur, ArrayList<PieceDeJeu> historique) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.historique = historique;
        Initialiser();
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
     * <b>Accesseur</b> permettant de recuperer l'état de la Grille
     *
     * @return la liste des cases de la Grille
     */
    public int[][] getEtatGrille() {
        return etatGrille;
    }

    public ArrayList<PieceDeJeu> getHistorique() {
        return historique;
    }

    // MUTATEURS
    /**
     * <b>Mutateur</b> permettant de modifier le nombre de lignes de la Grille
     *
     * @param hauteur nouveau nombre de lignes de la Grille
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * <b>Mutateur</b> permettant de modifier le nombre de colonnes de la Grille
     *
     * @param largeur nouveau nombre de colonnes de la Grille
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     * <b>Mutateur</b> permettant de modifier l'état de la grille
     *
     * @param etatGrille nouvelle liste de blocs de la grille
     */
    public void setEtatGrille(int[][] etatGrille) {
        this.etatGrille = etatGrille;
    }

    /**
     * <b>Mutateur</b> permettant de modifier l'état de la grille
     *
     * @param historique nouvelle liste de blocs de la grille
     */
    public void setHistorique(ArrayList<PieceDeJeu> historique) {
        this.historique = historique;
    }

    // METHODES
    /**
     * <b>Méthode</b> permettant d'initialiser la grille.<br>
     * Elle initialise la position des cases et elle met leur couleur à vide.
     */
    public void Initialiser() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                etatGrille[i][j] = Couleur.VIDE.valeur;
            }
        }
        historique = new ArrayList<PieceDeJeu>();
    }

    /**
     * <b>Méthode</b> permettant de modifier l'état de la case où le joueur a
     * joué. L'état de la case prendra l'entier de la couleur donnees
     *
     * @param pieceJoue est la piece qui est joué
     * @return 'true' si le coup à bin été joué sinon 'false'
     * @see Position
     */
    public boolean Jouer(PieceDeJeu pieceJoue) {
        if (pieceJoue == null && pieceJoue.getlisteBlocs() == null) {
            return false;
        }

        Iterator<Bloc> it = pieceJoue.getlisteBlocs().iterator();
        while (it.hasNext()) {
            Bloc bloc = (Bloc) it.next();
            if (bloc.getPosition() != null) {
                int x = bloc.getPosition().getX();
                int y = bloc.getPosition().getY();

                if (x <= hauteur && x > 0
                        && y <= largeur && y > 0
                        && etatGrille[x][y] == Couleur.VIDE.valeur) {
                    etatGrille[x][y] = bloc.getCouleur();
                } else {
                    this.Annuler(pieceJoue);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * <b>Méthode</b> permettant d'annuler un coup qui a été joué
     *
     * @param pieceJoue est la piece qui a joué
     * @return 'true' si le coup à bin été joué sinon 'false'
     * @see Position
     */
    public boolean Annuler(PieceDeJeu pieceJoue) {
        if (pieceJoue == null && pieceJoue.getlisteBlocs() == null) {
            return false;
        }

        Iterator<Bloc> it = pieceJoue.getlisteBlocs().iterator();
        while (it.hasNext()) {
            Bloc bloc = (Bloc) it.next();
            if (bloc.getPosition() != null) {
                int x = bloc.getPosition().getX();
                int y = bloc.getPosition().getY();

                if (x <= hauteur && x > 0
                        && y <= largeur && y > 0
                        && etatGrille[x][y] != Couleur.VIDE.valeur) {
                    etatGrille[x][y] = Couleur.VIDE.valeur;
                }
            }
        }
        return true;
    }

    /**
     * <b>Méthode</b> permettant d'annuler un coup qui a été joué
     *
     * @return 'true' si le coup à bin été joué sinon 'false'
     * @see Position
     */
    public boolean AnnulerDernierCoup() {
        if (historique == null && historique.get(historique.size() - 1)
                .getlisteBlocs() == null) {
            return false;
        }

        Iterator<Bloc> it = historique.get(historique.size() - 1).getlisteBlocs()
                .iterator();
        while (it.hasNext()) {
            Bloc bloc = (Bloc) it.next();
            if (bloc.getPosition() != null) {
                int x = bloc.getPosition().getX();
                int y = bloc.getPosition().getY();

                if (x <= hauteur && x > 0
                        && y <= largeur && y > 0
                        && etatGrille[x][y] != Couleur.VIDE.valeur) {
                    etatGrille[x][y] = Couleur.VIDE.valeur;
                }
            }
        }
        return true;
    }

    /**
     * <b>Méthode</b> permettant l'affichage d'une Grille
     *
     * @return Affichage de la Grille
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("     ");
        for (int i = 1; i <= hauteur; i++) {
            sb.append(i).append("  ");
        }
        sb.append("\n\n");
        for (int i = 0; i < hauteur; i++) {
            sb.append(i + 1);
            sb.append("    ");
            for (int j = 0; j < largeur; j++) {

                sb.append(etatGrille[i][j]);
                sb.append("  ");
            }
            sb.append("\n");
        }
        String fullString = sb.toString();

        return fullString;
    }
}
