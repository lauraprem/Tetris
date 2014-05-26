package tetris.Modele.Pieces;

/**
 * Représente une <b>Position</b>
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class Position implements Cloneable {
    // ATTRIBUTS
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

    /**
     * <b>Clone<\b> creé un clone de Position
     *
     * @return clone de Position
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Position p = (Position) super.clone();
        p.x = x;
        p.y = y;
        return p;
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

    @Override
    public boolean equals(Object o) {
        // Vérification du type du paramètre
        if (o instanceof Position) {
            // Vérification des valeurs des attributs
            Position other = (Position) o;

            // Pour les attributs de type primitif
            // on compare directement les valeurs :
            if ((this.x != other.x) || (this.y != other.y)) {
                return false; // les attributs sont différents
            }
            return true;
        }
        return false;
    }
}
