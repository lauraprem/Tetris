package tetris.Controleur;

import tetris.Modele.JeuDeTetris;
import tetris.Vue.FenetresJeu.FenetreJeu;

/**
 * Représente le <b>Controleur Principale</b> qui permet de lancer la vue et le
 * modele
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class ControleurPrincipal extends Controleur {

    protected FenetreJeu vue;

    // CONSTRUCTEUR
    /**
     * <b>Constructeur<\b> qui permet d'obtenir le modele et la vue par
     * référence
     */
    public ControleurPrincipal(JeuDeTetris _modele, FenetreJeu _vue) {
        super(_modele);
        vue = _vue;

        //Listeners des vues
        vue.getPrincipalPanel().addKeyListener(new ControleurClavier(modele));

        // le modele pourra indiquer les mises à jours
        modele.addObserver(vue);
        (new Thread(modele)).start();

        // rend visible la fenêtre de la vue
        vue.setVisible(true);
    }
}
