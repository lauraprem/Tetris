/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris.Execution;

import javax.swing.SwingUtilities;
import tetris.Controleur.Controleur2Joueur;
import tetris.Modele.JeuDeTetris2Joueurs;
import tetris.Modele.JeuDeTetrisMultiJoueur;
import tetris.Vue.Fenetre2Joueur;
import tetris.Vue.FenetreJeu;

/**
 *
 * @author leclerc
 */
public class Main2joueurs
{
     public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                JeuDeTetris2Joueurs modele1 = new JeuDeTetris2Joueurs();
                JeuDeTetris2Joueurs modele2 = new JeuDeTetris2Joueurs();
                FenetreJeu vue1 = new FenetreJeu(modele1);
                FenetreJeu vue2 = new FenetreJeu(modele2);
                Fenetre2Joueur vue = new Fenetre2Joueur(vue1, vue2);
                Controleur2Joueur controleur = new Controleur2Joueur(modele1,modele2,vue);
                /*Fenetre2Joueur vue = new Fenetre2Joueur(modele1, modele2);*/
                vue.setVisible(true);

            }
        });
    }
}
