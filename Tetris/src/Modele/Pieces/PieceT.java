package Modele.Pieces;

import Modele.GestionGrilleDeJeu.Bloc;
import Modele.GestionGrilleDeJeu.Position;
import java.awt.Color;

public class PieceT extends PieceDeTetris {

   public PieceT() {
        this.axeRotation = new Position(1, 0);

        listeBloc.add(new Bloc(0, 0, Color.PINK));
        listeBloc.add(new Bloc(1, 0, Color.PINK));
        listeBloc.add(new Bloc(2, 0, Color.PINK));
        listeBloc.add(new Bloc(1, 1, Color.PINK));
   }

   @Override
   public Color getColorDefault() {
      return Color.PINK;
   }

}
