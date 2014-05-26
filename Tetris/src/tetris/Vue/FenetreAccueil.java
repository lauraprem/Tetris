/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tetris.Controleur.ControleurPrincipale;
import tetris.Modele.JeuDeTetris;

/**
 *
 * @author leclerc
 */
public class FenetreAccueil extends JFrame
{

    private final int HAUTEUR_TOTAL = 200;
    private final int LARGEUR_TOTAL = 400;

    private JButton commencer;
    private JButton info;

    private JeuDeTetris tetris;

    public FenetreAccueil(JeuDeTetris t)
    {
        super();
        tetris = t;
        this.setTitle("Tetris");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        this.setResizable(false);
        this.setFocusable(false);
        commencer = new JButton("Commencer à jouer", new ImageIcon("src/Contenu/Tetris.png"));
        info = new JButton("Voir les instructions", new ImageIcon("src/Contenu/aide.png"));
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
        JPanel principalPanel = new JPanel();
        principalPanel.setBackground(Color.BLACK);
        principalPanel.setLayout(new BoxLayout(principalPanel, BoxLayout.Y_AXIS));

// (new BorderLayout());
        commencer.setAlignmentX(CENTER_ALIGNMENT);
        commencer.setBackground(Color.red);
        commencer.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        commencer.addActionListener(new CommencerListener());

//commencer.setIcon();
        info.setAlignmentX(CENTER_ALIGNMENT);
        info.setBackground(Color.red);
        info.addActionListener(new AideListener());

        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(commencer);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(info);
        this.add(principalPanel);
    }

    class CommencerListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent arg0)
        {

            FenetreJeu f = new FenetreJeu(tetris);
            f.setVisible(true);

            ControleurPrincipale controlleur = new ControleurPrincipale(tetris, f);
        }

    }

    class AideListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent arg0)
        {
            FenetreAide f = new FenetreAide();
            f.setVisible(true);

        }

    }

}
