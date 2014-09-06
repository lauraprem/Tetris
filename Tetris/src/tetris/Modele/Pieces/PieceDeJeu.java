package tetris.Modele.Pieces;

import java.util.ArrayList;

/**
 * Représente une <b>Piece de jeu</b>
 *
 * @author Corinne && Laura Prémillieu
 */
public class PieceDeJeu implements Cloneable {
    // ATTRIBUTS
    /**
     * liste des Bloc qui composent la piece
     *
     * @see Bloc
     */
    protected ArrayList<Bloc> listeBloc;

    // CONSTRUCTEURS
    /**
     * <b>Constructeur<\b> par defaut
     */
    public PieceDeJeu() {
        listeBloc = new ArrayList<>();
    }

    /**
     * <b>Constructeur<\b> en fonction d'une liste de Bloc donnée
     *
     * @param listeBloc de la piece
     * @see Bloc
     */
    public PieceDeJeu(ArrayList<Bloc> listeBloc) {
        this.listeBloc = listeBloc;
    }

    /**
     * <b>Clone<\b> creé un clone Piece du jeu
     *
     * @return clone de la Piece du jeu
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        PieceDeJeu p = (PieceDeJeu) super.clone();

        p.listeBloc = new ArrayList<>();
        for (int i = 0; i < this.listeBloc.size(); i++) {
            p.listeBloc.add((Bloc) this.listeBloc.get(i).clone());
        }

        return p;
    }

    // ACCESSEURS
    public ArrayList<Bloc> getlisteBlocs() {
        return listeBloc;
    }

    public Bloc getBloc(int i) {
        return listeBloc.get(i);
    }

    public int getBlocPosX(int i) {
        return listeBloc.get(i).getX();
    }

    public int getBlocPosY(int i) {
        return listeBloc.get(i).getY();
    }

    // MUTATEURS
    public void setlisteBlocs(ArrayList<Bloc> listeBloc) {
        this.listeBloc.clear();//.removeAll(this.listeBloc);

        for (int i = 0; i < listeBloc.size(); i++) {
            Bloc bloc = new Bloc();
            bloc.setBloc(listeBloc.get(i).getPosition(), listeBloc.get(i).getCouleur());
            this.listeBloc.add(bloc);
        }
    }

    public void setPosBloc(int i, int x, int y) {
        listeBloc.get(i).setPosBloc(x, y);
    }

    // METHODES
    /**
     * <b>Méthode<\b> permettant de déplacer la piece vers la gauche
     *
     * @param pasDep correspond au pas du déplacement
     */
    public void deplacerGauche(int pasDep) {
        if (listeBloc != null) {
            for (int i = 0; i < listeBloc.size(); i++) {
                this.setPosBloc(i, this.getBlocPosX(i), this.getBlocPosY(i) - pasDep);
            }
        }
    }

    /**
     * <b>Méthode<\b> permettant de déplacer la piece vers la droite
     *
     * @param pasDep correspond au pas du déplacement
     */
    public void deplacerDroite(int pasDep) {
        if (listeBloc != null) {
            for (int i = 0; i < listeBloc.size(); i++) {
                this.setPosBloc(i, this.getBlocPosX(i), this.getBlocPosY(i) + pasDep);
            }
        }
    }

    /**
     * <b>Méthode<\b> permettant de déplacer la piece vers le bas
     *
     * @param pasDep correspond au pas du déplacement
     */
    public void deplacerBas(int pasDep) {
        if (listeBloc != null) {
            for (int i = 0; i < listeBloc.size(); i++) {

                this.setPosBloc(i, this.getBlocPosX(i) + pasDep, this.getBlocPosY(i));
            }
        }
    }

}
