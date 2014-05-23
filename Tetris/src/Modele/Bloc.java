package Modele;

import java.awt.Color;

/**
 * Représente une <b>Case d'une Grille</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class Bloc
{

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
    public Bloc()
    {
        this(null, Color.GRAY);
    }

    /**
     * <b>Constructeur<\b> par la position
     *
     * @param position du Bloc
     */
    public Bloc(Position position)
    {
        this(position, null);
    }

    public Bloc(int x, int y, Color c)
    {
        this(new Position(x, y), c);
    }

    /**
     * <b>Constructeur<\b> par la position et la couleur
     *
     * @param position du Bloc
     * @param couleur du bloc
     */
    public Bloc(Position position, Color couleur)
    {
        this.position = position;
        this.couleur = couleur;
    }

    // ACCESSEURS
    public Position getPosition()
    {
        return position;
    }

    public Color getCouleur()
    {
        return couleur;
    }

    // MUTATEURS
    public void setPosition(Position position)
    {
        this.position.setPosition(position.getX(), position.getY());
    }

    public void setCouleur(Color couleur)
    {
        this.couleur = couleur;
    }

    public void setBloc(Position position, Color couleur)
    {
        if (position != null)
        {
            this.position = new Position(position.getX(), position.getY());
        }
        this.couleur = couleur;
    }
}
