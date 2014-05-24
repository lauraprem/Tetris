package tetris.Controlleur;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import tetris.Modele.GestionJeuDeTetris.JeuDeTetris;
import tetris.Modele.GestionnaireTetris;
import javax.swing.SwingUtilities;
import tetris.Vue.FenetreJeu;

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
                JeuDeTetris tetris = new JeuDeTetris();
                
                
                
                
                //On crée une nouvelle instance de notre JDialog*/
                FenetreJeu fenetre = new FenetreJeu(tetris);
                fenetre.setVisible(true);//On la rend visible
                
                tetris.addObserver(fenetre);
                (new Thread(tetris)).start();
            }
        });
        
        //GestionnaireTetris tetris = new GestionnaireTetris();
        

    }

}
