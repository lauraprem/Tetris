package Modele.GestionGrilleDeJeu;

import java.util.ArrayList;

/**
 * représente un ensemble de <b>cases</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class PieceDeJeu {
    private Position position;
    private ArrayList<Bloc> listeBloc;

    public PieceDeJeu() {
        listeBloc = new ArrayList<Bloc>();
        listeBloc.add(new Bloc());
    }

    public PieceDeJeu(Position position) {
        this.position = position;
    }

    public PieceDeJeu(Position position, ArrayList<Bloc> listeBloc) {
        this.position = position;
        this.listeBloc = listeBloc;
    }

    
    public Position getPosition() {
        return position;
    }

    public ArrayList<Bloc> getlisteBlocs() {
        return listeBloc;
    }
    
    
}
