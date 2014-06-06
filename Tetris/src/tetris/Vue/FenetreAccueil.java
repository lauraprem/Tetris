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
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tetris.Controleur.ControleurPrincipal;
import tetris.Controleur.ControleursDeuxJoueurs.Controleur2Joueur;
import tetris.Modele.JeuDeTetris;
import tetris.Modele.ModeleDeuxJoueurs.JeuDeTetris2Joueurs;
import tetris.Vue.FenetresJeu.VueDeuxJoueurs.Fenetre2Joueur;

/**
 *
 * @author leclerc
 */
public class FenetreAccueil extends JFrame
{
    
    private final int HAUTEUR_TOTAL = 350;
    private final int LARGEUR_TOTAL = 400;
    
    private final JButton commencer;
    private final JButton commencerAlea;
    private final JButton commencer2joueur;
    private final JButton info;
    private JFormattedTextField nbligne;
    
    private JeuDeTetris tetris;
    
    public FenetreAccueil(JeuDeTetris t){
        super();
        tetris = t;
        this.setTitle("Tetris");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        this.setResizable(false);
        this.setFocusable(false);
        this.setLocationRelativeTo(null);
        commencer = new JButton("Commencer à jouer mode A   ",new ImageIcon(getClass().getResource("/Contenu/Images/Tetris.png")));
        info = new JButton("Voir les instructions   ", new ImageIcon(getClass().getResource("/Contenu/Images/aide.png")));
        commencerAlea = new JButton("Commencer à jouer mode B   ", new ImageIcon(getClass().getResource("/Contenu/Images/Tetris.png")));
        commencer2joueur = new JButton("Commencer à jouer à deux   ", new ImageIcon(getClass().getResource("/Contenu/Images/Tetris.png")));
  
        nbligne = new JFormattedTextField(NumberFormat.getIntegerInstance());
        nbligne.setBackground(Color.BLACK);
        nbligne.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        nbligne.setForeground(Color.white);
        
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

        //Commencer partie mode A
        commencer.setAlignmentX(CENTER_ALIGNMENT);
        commencer.setBackground(Color.red);
        commencer.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        commencer.addActionListener(new CommencerListener());
        
        //Commencer partie mode B
        commencerAlea.setAlignmentX(CENTER_ALIGNMENT);
        commencerAlea.setBackground(Color.red);
        commencerAlea.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        commencerAlea.addActionListener(new CommencerAleaListener());

        //Commencer partie 2 joueurs
        commencer2joueur.setAlignmentX(CENTER_ALIGNMENT);
        commencer2joueur.setBackground(Color.red);
        commencer2joueur.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        commencer2joueur.addActionListener(new Commencer2JoueurListener());
        
        //Voir l'aide
        info.setAlignmentX(CENTER_ALIGNMENT);
        info.setBackground(Color.red);
        info.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        info.addActionListener(new AideListener());
        
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(commencer);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(commencerAlea);
        principalPanel.add(nbligne);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(commencer2joueur);
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
            tetris = new JeuDeTetris();
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
            tetris= new JeuDeTetris();
            int nb;
            if (nbligne.getText().length()>0 && Integer.parseInt(nbligne.getText()) < 20)
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
    
      class Commencer2JoueurListener implements ActionListener
    {
        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
                JeuDeTetris2Joueurs modele1 = new JeuDeTetris2Joueurs();
                JeuDeTetris2Joueurs modele2 = new JeuDeTetris2Joueurs();
                FenetreJeu vue1 = new FenetreJeu(modele1);
                FenetreJeu vue2 = new FenetreJeu(modele2);
                Fenetre2Joueur vue = new Fenetre2Joueur(vue1, vue2);
                Controleur2Joueur controleur = new Controleur2Joueur(modele1,modele2,vue);
                vue.setVisible(true);
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
