/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Controleur.ControleursDeuxJoueurs;

import tetris.Modele.ModeleDeuxJoueurs.JeuDeTetris2Joueurs;
import tetris.Modele.ModeleDeuxJoueurs.JeuDeTetrisMultiJoueur;
import tetris.Vue.FenetresJeu.VueDeuxJoueurs.Fenetre2Joueur;

/**
 *
 * @author leclerc
 */
public class Controleur2Joueur {

    JeuDeTetrisMultiJoueur jeu;
    Fenetre2Joueur vue;

    public Controleur2Joueur(JeuDeTetris2Joueurs _modele1, JeuDeTetris2Joueurs _modele2, Fenetre2Joueur _vue) {

        jeu = new JeuDeTetrisMultiJoueur(_modele1, _modele2);

        vue = _vue;

        vue.getPrincipalPanel().addKeyListener(new ControleurJoueurs(jeu.getJeu1(), jeu.getJeu2()));

        jeu.getJeu1().addObserver(vue.getJeu1());
        jeu.getJeu2().addObserver(vue.getJeu2());

        jeu.addObserver(vue);
        (new Thread(jeu)).start();

        // rend visible la fenêtre de la vue
        vue.setVisible(true);
    }
}
