package Modele.Pieces;

import Modele.GestionGrilleDeJeu.Bloc;
import Modele.GestionGrilleDeJeu.Position;
import java.awt.Color;

public class PieceJ extends PieceDeTetris {

    public PieceJ() {
        this.axeRotation = new Position(1, 1);

        listeBloc.add(new Bloc(1, 0, Color.YELLOW));
        listeBloc.add(new Bloc(1, 1, Color.YELLOW));
        listeBloc.add(new Bloc(1, 2, Color.YELLOW));
        listeBloc.add(new Bloc(0, 2, Color.YELLOW));
    }

    @Override
    public Color getColorDefault() {
        return Color.YELLOW;
    }
}
