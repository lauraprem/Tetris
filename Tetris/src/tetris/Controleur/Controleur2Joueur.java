/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris.Controleur;

import sun.management.Agent;
import tetris.Modele.JeuDeTetris;
import tetris.Modele.JeuDeTetris2Joueurs;
import tetris.Modele.JeuDeTetrisMultiJoueur;
import tetris.Vue.Fenetre2Joueur;
import tetris.Vue.FenetreJeu;

/**
 *
 * @author leclerc
 */



public class Controleur2Joueur
{
    JeuDeTetris2Joueurs jeu1;
    JeuDeTetris2Joueurs jeu2;
    JeuDeTetrisMultiJoueur jeu;
    Fenetre2Joueur vue;
    
     public Controleur2Joueur(JeuDeTetris2Joueurs _modele1,JeuDeTetris2Joueurs _modele2, Fenetre2Joueur _vue) {
         
         jeu1 = _modele1;
         jeu2 = _modele2;
         jeu = new JeuDeTetrisMultiJoueur(jeu1,jeu2);
         
         vue = _vue;
         
         vue.getPrincipalPanel().addKeyListener(new ControleurJoueurs(jeu.getJeu1(),jeu.getJeu2()));
         //vue.getJeu1().getPrincipalPanel().addKeyListener(new ControleurJoueurs(jeu1));
         
         
         jeu1.addObserver(vue.getJeu1());
         jeu2.addObserver(vue.getJeu2());
             
         jeu.addObserver(vue.getJeu1());
         jeu.addObserver(vue.getJeu2());
         jeu.addObserver(vue);
         (new Thread (jeu)).start();
         
         //(new Thread (jeu2)).start();*/
         


        // rend visible la fenêtre de la vue
        vue.setVisible(true);
    }
}
