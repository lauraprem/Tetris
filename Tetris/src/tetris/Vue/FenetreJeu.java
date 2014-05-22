/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author leclerc
 */
public class FenetreJeu extends JFrame
{

    private final int HAUTEUR_TOTAL = 700;
    private final int LARGEUR_TOTAL = 800;
    private final int NB_CASE_LIGNE = 10;
    private final int NB_CASE_COLONE = 20;
    public FenetreJeu()
    {
        super();
        this.setTitle("Tetris");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        this.setResizable(false);
        this.setFocusable(false);
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

    public void build()
    {

        //Panel principal
        Border magentaline = BorderFactory.createLineBorder(Color.MAGENTA, 1);
        JPanel principalPanel = new JPanel(new BorderLayout());
        principalPanel.setBackground(Color.BLACK);
        this.add(principalPanel, BorderLayout.CENTER);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        principalPanel.add(menuBar, BorderLayout.PAGE_START);
        JMenu m = new JMenu("Jeu");
        menuBar.add(m);

        //Panel de score
        System.out.println(m.getBounds().getHeight());
        JComponent scorePanel = new JPanel(new BorderLayout());
        scorePanel.setOpaque(false);
        scorePanel.setBorder(magentaline);
        scorePanel.setPreferredSize(new Dimension(200, 600));
        principalPanel.add(scorePanel, BorderLayout.LINE_START);

        //Panel de grille
        JComponent grillePanel = new JPanel(new GridBagLayout());
        grillePanel.setOpaque(false);
        grillePanel.setBorder(magentaline);
        grillePanel.setPreferredSize(new Dimension(400, 600));
        principalPanel.add(grillePanel, BorderLayout.CENTER);

        // Panels de la grille
        for (int i = 1; i <= NB_CASE_COLONE * NB_CASE_LIGNE; i++)
        {
            JComponent macase = new JPanel();
            macase.setOpaque(false);
            macase.setBorder(magentaline);
            GridBagConstraints g = new GridBagConstraints();
            macase.setPreferredSize(new Dimension(32, 32));
            if (i % 10 == 0)
            {
                g.gridwidth = GridBagConstraints.REMAINDER;
            }

            grillePanel.add(macase, g);
        }

        //panel des pièces suivantes
        JComponent piecePanel = new JPanel(new BorderLayout());
        piecePanel.setOpaque(false);
        piecePanel.setBorder(magentaline);
        piecePanel.setPreferredSize(new Dimension(200, 600));
        principalPanel.add(piecePanel, BorderLayout.LINE_END);

    }

}
