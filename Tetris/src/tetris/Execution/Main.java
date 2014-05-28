package tetris.Execution;

import tetris.Modele.JeuDeTetris;
import javax.swing.SwingUtilities;
import tetris.Controleur.ControleurPrincipal;
import tetris.Vue.FenetreAccueil;
import tetris.Vue.FenetresJeu.FenetreJeu;

/**
 *
 * @author Laura Prémillieu && Corinne Fagno
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                JeuDeTetris modele = new JeuDeTetris();
                FenetreAccueil vue = new FenetreAccueil(modele);
                vue.setVisible(true);
                
//FenetreJeu(modele);

            }
        });
    }

}
