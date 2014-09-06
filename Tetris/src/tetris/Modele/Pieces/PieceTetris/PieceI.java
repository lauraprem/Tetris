package tetris.Modele.Pieces.PieceTetris;

import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeTetris;
import java.awt.Color;
import tetris.Modele.Pieces.Position;

/**
 * Représente la <b>Piece de Tetris I</b>
 *
 * @author Corinne && Laura Prémillieu
 */
public class PieceI extends PieceDeTetris {
    //ATTRIBUT
    /**
     * indique si la barre est horizontal
     */
    protected boolean horizontal;

    // CONSTRUCTEUR
    /**
     * <b>Constructeur<\b> en fonction de la taille d'un repère, cela permet que
     * l'on puissent construire les Blocs avec leur positions. Cela sert aussi
     * prendre en compte le changement de repère lors de la rotation.
     *
     * @param largeur taille maximum de l'abscisse
     * @param hauteur taille maximum de l'ordonnées
     * @see Bloc
     */
    public PieceI(int largeur, int hauteur) {
        super();

        // positionnement du centre de rotation sur le repere
        Position p = new Position(0, (largeur / 2)-1);

        // Indication de l'index du bloc de rotation
        numBlocRotation = 1;

        // Fabrication des Bloc de la piece I
        for (int i = 0; i < 4; i++) {
            listeBloc.add(new Bloc(p.getX(), p.getY() + (i - 1), getCouleurDefaut()));
        }
        horizontal = true;
    }

    // METHODE
    /**
     * <b>Méthode<\b> permettant de retourner la couleur originale de la piece
     *
     * @return couleur originale de la pièce
     *
     */
    @Override
    public final Color getCouleurDefaut() {
        return Color.CYAN;
    }

    /**
     * <b>Méthode<\b> permettant de tourner la piece I. Il n'y a que deux
     * positions possible, une horizontale et une vertical
     *
     * @param degre est un entier qui correspond au degré de rotation
     */
    @Override
    public void rotationPiece(int degre) {
        super.rotationPiece(degrePieceTourne(degre));
    }

    /**
     * <b>Méthode<\b> permet de connaitre le degre de rotation pour que la piece
     * n'est que deux mouvement de rotation possible
     *
     * @param degre de rotation de la piece
     * @return couleur originale de la pièce
     *
     */
    private int degrePieceTourne(int degre) {
        if (horizontal == true) {
            horizontal = false;
            return (-Math.abs(degre));
        } else {
            horizontal = true;
            return Math.abs(degre);
        }
    }

}
