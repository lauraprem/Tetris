/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import tetris.Vue.FenetresJeu.FenetreJeu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tetris.Controleur.ControleurPrincipal;
import tetris.Modele.JeuDeTetris;

/**
 *
 * @author leclerc
 */
public class FenetreAccueil extends JFrame
{

    private final int HAUTEUR_TOTAL = 300;
    private final int LARGEUR_TOTAL = 400;

    private final JButton commencer;
    private final JButton commencerAlea;
    private final JButton info;
    private JFormattedTextField nbligne;

    private JeuDeTetris tetris;

    public FenetreAccueil(JeuDeTetris t)
    {
        super();
        tetris = t;
        this.setTitle("Tetris");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        this.setResizable(false);
        this.setFocusable(false);
        this.setLocationRelativeTo(null);
        commencer = new JButton("Commencer à jouer mode A   ", new ImageIcon("src/Contenu/Images/Tetris.png"));
        info = new JButton("Voir les instructions   ", new ImageIcon("src/Contenu/Images/aide.png"));
        commencerAlea = new JButton("Commencer à jouer mode B   ", new ImageIcon("src/Contenu/Images/Tetris.png"));
        nbligne = new JFormattedTextField(NumberFormat.getIntegerInstance());

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

        commencer.setAlignmentX(CENTER_ALIGNMENT);
        commencer.setBackground(Color.red);
        commencer.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        commencer.addActionListener(new CommencerListener());

        //     JTextField nbligneuh = new JTextField("10");
        info.setAlignmentX(CENTER_ALIGNMENT);
        info.setBackground(Color.red);
        info.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        info.addActionListener(new AideListener());

        commencerAlea.setAlignmentX(CENTER_ALIGNMENT);
        commencerAlea.setBackground(Color.red);
        commencerAlea.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        commencerAlea.addActionListener(new CommencerAleaListener());

        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(commencer);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(commencerAlea);
        principalPanel.add(nbligne);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(info);
        this.add(principalPanel);
    }

    class CommencerListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            tetris.rejouer();
            FenetreJeu f = new FenetreJeu(tetris);
            f.setVisible(true);

            ControleurPrincipal controlleur = new ControleurPrincipal(tetris, f);
        }

    }

    class CommencerAleaListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            tetris.rejouer();
            int nb;
            if (nbligne.getText() != null && Integer.parseInt(nbligne.getText()) <20)
            {
                nb = Integer.parseInt(nbligne.getText());
            } else
            {
                nb = 10;
            }

            tetris.genererLignesAlea(nb);
            FenetreJeu f = new FenetreJeu(tetris);
            f.setVisible(true);

            ControleurPrincipal controlleur = new ControleurPrincipal(tetris, f);
        }

    }

    class AideListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            FenetreAide f = new FenetreAide();
            f.setVisible(true);

        }

    }

}
