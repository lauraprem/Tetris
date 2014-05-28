/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tetris.Modele.JeuDeTetris2Joueurs;

/**
 *
 * @author leclerc
 */
public class Fenetre2Joueur extends JFrame implements Observer
{

    private FenetreJeu jeu1;
    private FenetreJeu jeu2;
    private JPanel principalPanel;

    public Fenetre2Joueur(FenetreJeu vue1, FenetreJeu vue2)
    {
        super();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(1200, 700));
        this.setResizable(false);
        this.setFocusable(false);
        jeu1 = vue1;
        jeu2 = vue2;
        jeu1.getPrincipalPanel().setFocusable(false);
        jeu2.getPrincipalPanel().setFocusable(false);
        principalPanel = new JPanel(new BorderLayout());
        principalPanel.setFocusable(true);
        this.add(principalPanel);
        build();
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

    }

    private void build()
    {
        jeu1.setTaille(600, 700);
        jeu2.setTaille(600, 700);

        jeu1.getScorePanel().setTaillePolice(15, 15);
        jeu2.getScorePanel().setTaillePolice(15, 15);

        jeu1.getGrillePanel().setTaille(350, 700);
        jeu1.getPieceSuivantePanel().setTaille(150, 700);
        jeu1.getScorePanel().setTaille(100, 700);

        jeu2.getGrillePanel().setTaille(350, 700);
        jeu2.getPieceSuivantePanel().setTaille(150, 700);
        jeu2.getScorePanel().setTaille(100, 700);

        principalPanel.add(jeu1.getPrincipalPanel(), BorderLayout.LINE_START);
        principalPanel.add(jeu2.getPrincipalPanel(), BorderLayout.CENTER);
        principalPanel.add(new JPanel(), BorderLayout.LINE_END);
    }

    public FenetreJeu getJeu1()
    {
        return jeu1;
    }

    public void setJeu1(FenetreJeu jeu1)
    {
        this.jeu1 = jeu1;
    }

    public FenetreJeu getJeu2()
    {
        return jeu2;
    }

    public void setJeu2(FenetreJeu jeu2)
    {
        this.jeu2 = jeu2;
    }

    public JPanel getPrincipalPanel()
    {
        return principalPanel;
    }

    public void setPrincipalPanel(JPanel principalPanel)
    {
        this.principalPanel = principalPanel;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        synchronized (this)
        {
            boolean sup = ((JeuDeTetris2Joueurs) jeu2.getTetris()).isSupprLigne();
            if (sup)
            {

                jeu1.rafraichirAffichage(jeu1.getTetris().getBlocEnJeu(), jeu1.getTetris().getPiecesSuivantes(), jeu1.getTetris().getScore().getNiveau(), jeu1.getTetris().getScore().getPoint());
            }
            sup = ((JeuDeTetris2Joueurs) jeu1.getTetris()).isSupprLigne();
            if (sup)
            {
                jeu2.rafraichirAffichage(jeu2.getTetris().getBlocEnJeu(), jeu2.getTetris().getPiecesSuivantes(), jeu2.getTetris().getScore().getNiveau(), jeu2.getTetris().getScore().getPoint());
            }
        }
    }

}
