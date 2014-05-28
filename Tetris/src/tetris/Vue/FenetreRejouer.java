/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import tetris.Vue.FenetresJeu.FenetreJeu;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.Modele.JeuDeTetris;

/**
 *
 * @author leclerc
 */
public class FenetreRejouer extends JFrame
{

    private final int HAUTEUR_TOTAL = 300;
    private final int LARGEUR_TOTAL = 400;
    private String partieTermine;

    private JButton rejouerButtonModeA;
    private JButton rejouerButtonModeB;
    private JButton quitterButton;

    private JFormattedTextField nbligne;

    private JeuDeTetris tetris;
    private FenetreJeu jeu;

    private boolean rejouer;

    public FenetreRejouer(JeuDeTetris t, FenetreJeu j)
    {
        super();
        if (t != null)
        {
            tetris = t;
        }
        if (j != null)
        {
            jeu = j;
        }
        this.setTitle("Tetris");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        this.setFocusable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        partieTermine = "Partie terminée !! Voulez-vous rejouer ?";
        rejouerButtonModeA = new JButton("Rejouer en mode A  ", new ImageIcon("src/Contenu/Images/Tetris.png"));
        rejouerButtonModeB = new JButton("Rejouer en mode B  ", new ImageIcon("src/Contenu/Images/Tetris.png"));
        quitterButton = new JButton("Retour au menu  ", new ImageIcon("src/Contenu/Images/Tetris.png"));
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

        rejouerButtonModeA.setAlignmentX(CENTER_ALIGNMENT);
        rejouerButtonModeA.setBackground(Color.red);
        rejouerButtonModeA.setAlignmentX(CENTER_ALIGNMENT);
        rejouerButtonModeA.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        rejouerButtonModeA.addActionListener(new RejouerAListener());

        rejouerButtonModeB.setAlignmentX(CENTER_ALIGNMENT);
        rejouerButtonModeB.setBackground(Color.red);
        rejouerButtonModeB.setAlignmentX(CENTER_ALIGNMENT);
        rejouerButtonModeB.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        rejouerButtonModeB.addActionListener(new RejouerBListener());

        quitterButton.setAlignmentX(CENTER_ALIGNMENT);
        quitterButton.setBackground(Color.red);
        quitterButton.setAlignmentX(CENTER_ALIGNMENT);
        quitterButton.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        quitterButton.addActionListener(new QuitterListener());

        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(text);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(rejouerButtonModeA);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(rejouerButtonModeB);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(nbligne);
        principalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        principalPanel.add(quitterButton);

    }

    class RejouerAListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            if (tetris != null || jeu != null)
            {
                tetris.rejouer();
                rejouer = true;
                jeu.reveil();
                dispose();
            }

        }

    }

    class RejouerBListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            if (tetris != null || jeu != null)
            {
                tetris.rejouer();
                int nb;
                if (nbligne.getText().length()>0 && Integer.parseInt(nbligne.getText()) < 20)
                {
                    nb = Integer.parseInt(nbligne.getText());
                } else
                {
                    nb = 10;
                }
                tetris.genererLignesAlea(nb);
                rejouer = true;
                jeu.reveil();
                dispose();
            }
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
