package Modele.Pieces;

import Modele.GestionGrilleDeJeu.Bloc;
import Modele.GestionGrilleDeJeu.Position;
import java.awt.Color;

public class PieceI extends PieceDeTetris {

    public PieceI() {
        this.axeRotation = new Position(0, 2);
        
        listeBloc.add(new Bloc(0, 0, Color.blue));
        listeBloc.add(new Bloc(0, 1, Color.blue));
        listeBloc.add(new Bloc(0, 2, Color.blue));
        listeBloc.add(new Bloc(0, 3, Color.blue));
    }

    @Override
    public Color getColorDefault() {
        return Color.blue;
    }
}
