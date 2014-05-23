package Modele;

/**
 * Représente une dimension
 *
 * @author Fagno && Premillieu
 */
public class Position {

    /**
     * Position sur la colonne
     */
    private int x;

    /**
     * Position sur la ligne
     */
    private int y;

    // CONSTRUCTEURS
    /**
     * <b>Constructeur<\b> par defaut
     */
    public Position() {
        this(0, 0);
    }

    /**
     * <b>Constructeur<\b> construit une position
     *
     * @param x paramètre x
     * @param y paramètre y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // ACCESSEURS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // MUTATEURS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
