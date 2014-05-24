package tetris.Modele.Pieces;

import java.awt.Color;
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
        this.listeBloc.removeAll(this.listeBloc);
        
        for(int i =0;i<listeBloc.size();i++){
            Bloc bloc = new Bloc();
            bloc.setBloc(listeBloc.get(i).getPosition(), listeBloc.get(i).getCouleur());
            this.listeBloc.add(bloc);
        }
    }
    
}
