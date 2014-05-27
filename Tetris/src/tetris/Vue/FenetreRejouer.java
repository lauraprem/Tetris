/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.Modele.JeuDeTetris;

/**
 *
 * @author leclerc
 */
public class FenetreRejouer extends JFrame
{

    private final int HAUTEUR_TOTAL = 200;
    private final int LARGEUR_TOTAL = 400;
    private String partieTermine;

    private JButton rejouerButton;
    private JButton quitterButton;

    private JeuDeTetris tetris;
    private FenetreJeu jeu;

    private boolean rejouer;

    public FenetreRejouer(JeuDeTetris t, FenetreJeu j)
    {
        super();
        tetris = t;
        jeu = j;
        this.setTitle("Tetris");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        //  this.setResizable(false);
        this.setFocusable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        partieTermine = "Vous avez perdu !! Voulez-vous rejouer ?";
        rejouerButton = new JButton("Rejouer  ", new ImageIcon("src/Contenu/Images/Tetris.png"));
        quitterButton =  new JButton("Retour au menu  ", new ImageIcon("src/Contenu/Images/Tetris.png"));
        build();

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                super.windowClosing(arg0);
                // System.exit(0);
            }
        });
    }

    private void build()
    {
        JPanel principalPanel = new JPanel();
        principalPanel.setLayout(new BoxLayout(principalPanel, BoxLayout.Y_AXIS));
        principalPanel.setBackground(Color.BLACK);
        this.add(principalPanel);

        JLabel text = new JLabel(partieTermine);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setAlignmentX(CENTER_ALIGNMENT);

        rejouerButton.setAlignmentX(CENTER_ALIGNMENT);
        rejouerButton.setBackground(Color.red);
        rejouerButton.setAlignmentX(CENTER_ALIGNMENT);
        rejouerButton.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        rejouerButton.addActionListener(new RejouerListener());

        quitterButton.setAlignmentX(CENTER_ALIGNMENT);
        quitterButton.setBackground(Color.red);
        quitterButton.setAlignmentX(CENTER_ALIGNMENT);
        quitterButton.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        quitterButton.addActionListener(new QuitterListener());

        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(text);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(rejouerButton);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(quitterButton);

    }

    class RejouerListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            tetris.rejouer();
            rejouer = true;
            jeu.reveil();
            dispose();

        }

    }

    class QuitterListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            tetris.setMep(true);
            rejouer = false;
            jeu.reveil();
            dispose();
        }

    }

    public boolean getRejouer()
    {
        return rejouer;
    }
}
