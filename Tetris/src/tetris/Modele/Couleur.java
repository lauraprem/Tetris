package tetris.Modele;

/**
 * Représente les couleurs possibles
 *
 * @author Fagno && Premillieu
 */
public enum Couleur {

    VIDE(0),
    JAUNE(1),
    ORANGE(2),
    VIOLET(3),
    ROUGE(4),
    VERT(5),
    BLEUC(6),
    BLEUF(7);

    public final int val;

    Couleur(int val) {
        this.val = val;
    }
}
