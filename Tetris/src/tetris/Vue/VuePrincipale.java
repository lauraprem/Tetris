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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author p1307999
 */
public class VuePrincipale extends JFrame {

    private final int HAUTEUR = 900;
    private final int LARGEUR = 1000;
    private final int LARGEUR_PANEL_COTE = 250;
    private final int LARGEUR_PANEL_CENTRE = 500;
    private final int NB_CASE_LIGNE = 10;
    private final int NB_CASE_COLONE = 20;

    public VuePrincipale() {
        super();

        this.setSize(new Dimension(LARGEUR, HAUTEUR));
        this.setResizable(false);
        this.setFocusable(false);

        build();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

    }

    private void build() {
        JComponent panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.BLACK);
        panelPrincipal.setFocusable(true);
        this.add(panelPrincipal);

        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        jm.add(m);
        panelPrincipal.add(jm, BorderLayout.PAGE_START);
        setJMenuBar(jm);

        //Panel de score
        JComponent panelScore = new JPanel(new BorderLayout());
        panelScore.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));
        panelScore.setPreferredSize(new Dimension(LARGEUR_PANEL_COTE, HAUTEUR));
        panelScore.setOpaque(false);
        panelPrincipal.add(panelScore, BorderLayout.LINE_START);

        //Panel contenant la grille
        JComponent panelGrille = new JPanel(new GridBagLayout());
        panelGrille.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));
        panelGrille.setPreferredSize(new Dimension(LARGEUR_PANEL_CENTRE, HAUTEUR));
        panelGrille.setOpaque(false);
        panelPrincipal.add(panelGrille, BorderLayout.CENTER);

        //Cases de la grille
        for (int i = 1; i <= NB_CASE_COLONE * NB_CASE_LIGNE; i++) {
            JPanel macase = new JPanel();
            macase.setOpaque(false);
            macase.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));
            macase.setPreferredSize(new Dimension(42, 42));
            GridBagConstraints c = new GridBagConstraints();
            if (i % 10 == 0) {
                c.gridwidth = GridBagConstraints.REMAINDER;
            }
            //macase.set
            panelGrille.add(macase, c);

        }

        //panel des pièces suivantes
        JComponent panelPieceSuivante = new JPanel(new BorderLayout());
        panelPieceSuivante.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));
        panelPieceSuivante.setPreferredSize(new Dimension(LARGEUR_PANEL_COTE, HAUTEUR));
        panelPieceSuivante.setOpaque(false);
        panelPrincipal.add(panelPieceSuivante, BorderLayout.LINE_END);

        panelPrincipal.addKeyListener(new Clavier());

    }


}
