package Modele.Pieces;

import Modele.GestionGrilleDeJeu.Bloc;
import Modele.GestionGrilleDeJeu.Position;
import java.awt.Color;

public class PieceL extends PieceDeTetris {

   public PieceL() {
        this.axeRotation = new Position(0, 1);

        listeBloc.add(new Bloc(0, 0, Color.GREEN));
        listeBloc.add(new Bloc(0, 1, Color.GREEN));
        listeBloc.add(new Bloc(0, 2, Color.GREEN));
        listeBloc.add(new Bloc(1, 2, Color.GREEN));
   }

   //@Override
   public Color getColorDefault() {
      return Color.GREEN;
   }
}
