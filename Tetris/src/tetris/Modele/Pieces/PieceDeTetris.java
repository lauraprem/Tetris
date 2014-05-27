package tetris.Modele.Pieces;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Représente une <b>Piece de Tetris</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class PieceDeTetris extends PieceDeJeu implements Cloneable {

    // ATTRIBUTS
    /**
     * correspond au numéro de l'index du Bloc qui permet la rotation de la
     * piece
     */
    protected int numBlocRotation;
    
    /**
     * 
     */
    protected int hauteur;
    protected int largeur;
    
    // CONSTRUCTEURS
    /**
     * <b>Constructeur<\b> par defaut
     */
    public PieceDeTetris() {
        this(0, 0);
    }

    /**
     * <b>Constructeur<\b> en fonction de la taille d'un repère, cela permet que
     * l'on puissent construire les Blocs avec leur positions. Cela sert aussi
     * prendre en compte le changement de repère lors de la rotation.
     *
     * @param largeur taille maximum de l'abscisse
     * @param hauteur taille maximum de l'ordonnées
     * @see Bloc
     */
    public PieceDeTetris(int largeur, int hauteur) {
        super();
        numBlocRotation = 0;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    /**
     * <b>Clone<\b> creé un clone de la Piece de Tetris
     *
     * @return clone de la Piece de Tetris
     */
    @Override
    public Object clone() {
        try {
            PieceDeTetris p = (PieceDeTetris) super.clone();
            p.numBlocRotation = this.numBlocRotation;
            return p;
        } catch (CloneNotSupportedException ex) {
            System.out.println("Erreur clonage profondeur PieceDeTetris :" + ex.getMessage());
        }
        return null;
    }

    // ACCESSEUR
    public int getNumBlocRotation() {
        return numBlocRotation;
    }

    // MUTATEUR
    public void setNumBlocRotation(int numBlocRotation) {
        this.numBlocRotation = numBlocRotation;
    }

    // METHODES
    /**
     * <b>Méthode<\b> permettant de retourner la couleur originale de la piece
     *
     * @return couleur originale de la pièce
     */
    public Color getCouleurDefaut() {
        return Color.GRAY;
    }

    /**
     * <b>Méthode<\b> permettant de tourner la piece de Tetris
     *
     * @param degre est un entier qui correspond au degré de rotation
     */
    public void rotationPiece(int degre) {
        // Rotation de la piece
        if (numBlocRotation != -1 && listeBloc != null) { // s'il existe un point de rotation
            // cas avant centre
            for (int i = 0; i < listeBloc.size(); i++) {
                if (!listeBloc.get(numBlocRotation).equals(listeBloc.get(i))) {
                    int x = listeBloc.get(i).getPosition().getX();
                    int y = listeBloc.get(i).getPosition().getY();
                    int deltax = listeBloc.get(numBlocRotation).getX() - x;
                    int deltay = listeBloc.get(numBlocRotation).getY() - y;
                    int deltaLarg = Math.abs(largeur - (largeur - listeBloc.get(numBlocRotation).getX()));
                    int deltaLong = Math.abs(hauteur - (hauteur - listeBloc.get(numBlocRotation).getY()));

                    Position p = new Position(deltax * ((int) Math.round(Math.cos(degre))) - deltay * ((int) Math.round(Math.sin(degre))) + deltaLarg,
                            deltax * ((int) Math.round(Math.sin(degre))) + deltay * ((int) Math.round(Math.cos(degre))) + deltaLong);

                    listeBloc.get(i).setPosition(p);
                }
            }
        }
    }
    
    /**
     *  <b>Méthode<\b> place la pièce la plus haute à gauche en x = 0 et y = 0
     * @return 
     */
    public ArrayList<Bloc> getPieceAOrigine(){////////////////////////////////////////mettre dans classe + generale
        ArrayList<Bloc> listeBloc2 = (ArrayList<Bloc>) listeBloc.clone();
        if(listeBloc2 != null) {
            int numBloc = getBlocPosPlusHautGauche();
            for (int i = 0; i < listeBloc2.size(); i++) {
                if (!listeBloc2.get(numBloc).equals(listeBloc2.get(i))) {
                    int x = listeBloc2.get(i).getPosition().getX();
                    int y = listeBloc2.get(i).getPosition().getY();
                    int deltax = Math.abs(listeBloc2.get(numBloc).getX() - x);
                    int deltay = Math.abs(listeBloc2.get(numBloc).getY() - y);

                    Position p = new Position(deltax,deltay);
                    listeBloc2.get(i).setPosition(p);
                }
            }
            // cas bloc qui se trouve le plus en haut a gauche
            Position p = new Position(0,0);
            listeBloc2.get(numBloc).setPosition(p);
        }
        return listeBloc2;
    }
    private int getBlocPosPlusHautGauche(){
        int numBloc = -1;
        if(listeBloc.size()!=0){
            numBloc = 0;
            int x = listeBloc.get(0).getX();
            int y = listeBloc.get(0).getY();
            for(int i = 1; i< listeBloc.size();i++){
                if((listeBloc.get(i).getX()<x) &&  (listeBloc.get(i).getY()<y)){
                    numBloc = i;
                }
            }
        }
        return numBloc;
    }
}
