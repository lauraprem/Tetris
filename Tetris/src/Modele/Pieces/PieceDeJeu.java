package Modele.Pieces;

import Modele.Bloc;
import Modele.Bloc;
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
    }

    public PieceDeJeu(ArrayList<Bloc> listeBloc) {
        this.listeBloc = listeBloc;
    }

    public ArrayList<Bloc> getlisteBlocs() {
        return listeBloc;
    }
    
    public void setlisteBlocs(ArrayList<Bloc> listeBloc) {
        this.listeBloc = listeBloc;
    }
    
}
