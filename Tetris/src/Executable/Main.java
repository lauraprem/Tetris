package Executable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.SwingUtilities;
import tetris.Vue.FenetreJeu;

/**
 *
 * @author frederic
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
                //On crée une nouvelle instance de notre JDialog
                FenetreJeu fenetre = new FenetreJeu();
                fenetre.setVisible(true);//On la rend visible
            }
        });

    }

}
