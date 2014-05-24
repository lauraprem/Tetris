package tetris.Modele.Pieces.PieceTetris;

import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeTetris;
import java.awt.Color;

public class PieceI extends PieceDeTetris {

    public PieceI(int largeur) {
        super(largeur);

        for (int i = 0; i < 4; i++) {
            listeBloc.add(new Bloc(centreRotation.getX(), centreRotation.getY() + (i - 1), Color.BLUE));
        }
    }

    @Override
    public Color getCouleurDefaut() {
        return Color.BLUE;
    }
}
