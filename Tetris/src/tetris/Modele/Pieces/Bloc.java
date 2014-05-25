package tetris.Modele.Pieces;

import java.awt.Color;

/**
 * Représente un <b>Bloc</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class Bloc implements Cloneable {
    // ATTRIBUTS
    /**
     * position du Bloc
     */
    private Position position;

    /**
     * couleur du Bloc
     */
    private Color couleur;

    // CONSTRUCTEURS
    /**
     * <b>Constructeur<\b> par defaut
     */
    public Bloc() {
        this(null, Color.GRAY);
    }

    /**
     * <b>Constructeur<\b> par la position
     *
     * @param position du Bloc
     */
    public Bloc(Position position) {
        this(position, null);
    }

    public Bloc(int x, int y, Color c) {
        this(new Position(x, y), c);
    }

    /**
     * <b>Constructeur<\b> par la position et la couleur
     *
     * @param position du Bloc
     * @param couleur du bloc
     */
    public Bloc(Position position, Color couleur) {
        this.position = position;
        this.couleur = couleur;
    }

    /**
     * <b>Clone<\b> creé un clone de bloc
     *
     * @return clone du bloc
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Bloc b = (Bloc) super.clone();
        b.position = (Position) this.position.clone();
        b.couleur = couleur;

        return b;
    }

    // ACCESSEURS
    public Position getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Color getCouleur() {
        return couleur;
    }

    // MUTATEURS
    public void setPosition(Position position) {
        this.position.setPosition(position.getX(), position.getY());
    }

    public void setX(int x) {
        this.position.setX(x);
    }

    public void setY(int y) {
        this.position.setY(y);
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setBloc(Position position, Color couleur) {
        if (position != null) {
            this.position = new Position(position.getX(), position.getY());
        }
        this.couleur = couleur;
    }

    public void setPosBloc(int x, int y) {
        position.setPosition(x, y);
    }
}
