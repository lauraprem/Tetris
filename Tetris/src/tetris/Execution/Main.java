package tetris.Execution;

import tetris.Modele.JeuDeTetris;
import javax.swing.SwingUtilities;
import tetris.Controleur.ControleurPrincipale;
import tetris.Vue.FenetreJeu;

/**
 *
 * @author Laura Prémillieu && Corinne Fagno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JeuDeTetris modele = new JeuDeTetris();
                FenetreJeu vue = new FenetreJeu(modele);
                ControleurPrincipale controlleur = new ControleurPrincipale(modele, vue);
            }
        });
    }

}
