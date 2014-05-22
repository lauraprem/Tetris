package Modele.GestionGrilleDeJeu.Enums;

/**
 * Représente les couleurs possibles
 *
 * @author Corinne Fagno && Laura Prémillieu
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

    public final int valeur;

    Couleur(int valeur) {
        this.valeur = valeur;
    }
}
