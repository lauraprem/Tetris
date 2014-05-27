package tetris.Modele.Pieces.PieceTetris;

import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeTetris;
import java.awt.Color;
import tetris.Modele.Pieces.Position;

/**
 * Représente la <b>Piece de Tetris T</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class PieceT extends PieceDeTetris {
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
    public PieceT(int largeur, int hauteur) {
        super(largeur, hauteur);

        // positionnement du centre de rotation sur le repere
        Position p = new Position(0, largeur / 2);

        // Indication de l'index du bloc de rotation
        numBlocRotation = 1;

        // Fabrication des Bloc de la piece S
        listeBloc.add(new Bloc(p.getX(), p.getY() - 1, getCouleurDefaut()));
        listeBloc.add(new Bloc(p.getX(), p.getY(), getCouleurDefaut()));
        listeBloc.add(new Bloc(p.getX(), p.getY() + 1, getCouleurDefaut()));
        listeBloc.add(new Bloc(p.getX() + 1, p.getY(), getCouleurDefaut()));
        
        //posRotation = new Position(p.getX(), p.getY());// enlever
    }

    // METHODE
    /**
     * <b>Méthode<\b> permettant de retourner la couleur originale de la piece
     *
     * @return couleur originale de la pièce
     */
    @Override
    public Color getCouleurDefaut() {
        return Color.PINK;
    }
}
