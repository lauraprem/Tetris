package tetris.Modele.Pieces.PieceTetris;

import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeTetris;
import java.awt.Color;
import tetris.Modele.Pieces.Position;

/**
 * Représente la <b>Piece de Tetris I</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class PieceI extends PieceDeTetris {
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
        super(largeur, hauteur);

        // positionnement du centre de rotation sur le repere
        //Position p = new Position(0, largeur / 2);
        Position p = new Position(0, largeur / 2);

        // Indication de l'index du bloc de rotation
        numBlocRotation = 1;

        // Fabrication des Bloc de la piece I
        for (int i = 0; i < 4; i++) {
            Bloc b = new Bloc(p.getX(), p.getY() + (i - 1), getCouleurDefaut());
            /*if(i==1)
                posRotation = b.getPosition();*/
            
            listeBloc.add(b);
        }
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
    
    @Override
    public void deplacerBas(int pasDep) {
        super.deplacerBas(pasDep);
            //posRotation = listeBloc.get(numBlocRotation).getPosition();
    }
}
