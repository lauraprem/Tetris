package Modele.Pieces;

import Modele.GestionGrilleDeJeu.Bloc;
import java.util.ArrayList;

/**
 * représente un ensemble de <b>cases</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class PieceDeJeu {
    protected ArrayList<Bloc> listeBloc;

    public PieceDeJeu() {
        listeBloc = new ArrayList<Bloc>();
        listeBloc.add(new Bloc());
    }

    public PieceDeJeu(ArrayList<Bloc> listeBloc) {
        this.listeBloc = listeBloc;
    }

    public ArrayList<Bloc> getlisteBlocs() {
        return listeBloc;
    }
    
}
