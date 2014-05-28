/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Modele.ModeleDeuxJoueurs;

import java.util.Observable;

/**
 *
 * @author leclerc
 */
public class JeuDeTetrisMultiJoueur extends Observable implements Runnable
{

    JeuDeTetris2Joueurs jeu1;
    JeuDeTetris2Joueurs jeu2;

    public JeuDeTetrisMultiJoueur(JeuDeTetris2Joueurs j1, JeuDeTetris2Joueurs j2)
    {
        jeu1 = j1;
        jeu2 = j2;
    }

    public JeuDeTetris2Joueurs getJeu1()
    {
        return jeu1;
    }

    public void setJeu1(JeuDeTetris2Joueurs jeu1)
    {
        this.jeu1 = jeu1;
    }

    public JeuDeTetris2Joueurs getJeu2()
    {
        return jeu2;
    }

    public void setJeu2(JeuDeTetris2Joueurs jeu2)
    {
        this.jeu2 = jeu2;
    }

    @Override
    public void run()
    {
        (new Thread(jeu1)).start();
        (new Thread(jeu2)).start();
        setChanged();
        notifyObservers();
        while (true)
        {

            if (jeu1.supprLigne)
            {
                jeu2.recevoirLigne(jeu1.getNbLigne());
                jeu1.setNbLigne(0);
                jeu1.setSupprLigne(false);
                setChanged();
                notifyObservers();
            }
            if (jeu2.supprLigne)
            {
                jeu1.recevoirLigne(jeu2.getNbLigne());
                jeu2.setNbLigne(0);
                jeu2.setSupprLigne(false);

                setChanged();
                notifyObservers();

            }
            if (jeu1.isTermine())
            {
                jeu2.setTermine(true);
                setChanged();
                notifyObservers();
            }
            if (jeu2.isTermine())
            {
                jeu1.setTermine(true);
                setChanged();
                notifyObservers();
            }
        }
    }

}
