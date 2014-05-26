package tetris.Modele.Pieces;

import java.awt.Color;

/**
 * Représente une <b>Piece de Tetris</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class PieceDeTetris extends PieceDeJeu implements Cloneable {

    // ATTRIBUTS
    //protected Position posRotation;
    /**
     * correspond au numéro de l'index du Bloc qui permet la rotation de la
     * piece
     */
    protected int numBlocRotation;

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
        //posRotation = null;
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
            //p.posRotation = (Position) this.posRotation.clone();
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
    public void rotationPiece(int degre) { // peut mettre degre en parametre
        if (numBlocRotation != -1 && listeBloc != null) { // s'il existe un point de rotation
            // cas avant centre
            for (int i = 0; i < listeBloc.size(); i++) {
                if (!listeBloc.get(numBlocRotation).equals(listeBloc.get(i))) {
                    int x = listeBloc.get(i).getPosition().getX();
                    int y = listeBloc.get(i).getPosition().getY();
                    int deltax = listeBloc.get(numBlocRotation).getX() - x;
                    int deltay = listeBloc.get(numBlocRotation).getY() - y;
                    int deltaLarg = Math.abs(20 - (20 - listeBloc.get(numBlocRotation).getX()));
                    int deltaLong = Math.abs(10 - (10 - listeBloc.get(numBlocRotation).getY()));

                    /*if (x < listeBloc.get(numBlocRotation).getPosition().getX() || y > listeBloc.get(numBlocRotation).getPosition().getY()) {
                     degre = degre * (-1);
                     }else{*/
                    // }
                    Position p = new Position(deltax * ((int) Math.round(Math.cos(degre))) - deltay * ((int) Math.round(Math.sin(degre))) + deltaLarg,
                            deltax * ((int) Math.round(Math.sin(degre))) + deltay * ((int) Math.round(Math.cos(degre))) + deltaLong);

                    listeBloc.get(i).setPosition(p);
                }
            }
        } else {
            // inverser les lignes avec les collonnes
        }
    }
}
