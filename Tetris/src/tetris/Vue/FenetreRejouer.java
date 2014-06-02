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
import java.awt.Font;
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
public class FenetreRejouer extends JFrame {

    private final int HAUTEUR_TOTAL = 120;
    private final int LARGEUR_TOTAL = 250;
    private String partieTermine;

    private JButton ButtonOk;

    public FenetreRejouer(JeuDeTetris t, FenetreJeu j) {
        this.setTitle("Tetris : Fin partie");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        this.setFocusable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        partieTermine = "Partie terminée !!!!";
        ButtonOk = new JButton("Retour au menu  ", new ImageIcon("src/Contenu/Images/Tetris.png"));
        build();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                dispose();
            }
        });
    }

    private void build() {
        // Panel principal
        JPanel principalPanel = new JPanel();
        principalPanel.setLayout(new BoxLayout(principalPanel, BoxLayout.Y_AXIS));
        principalPanel.setBackground(Color.BLACK);
        this.add(principalPanel);

        // Message
        JLabel text = new JLabel(partieTermine);
        text.setFont(new Font("Arial", Font.BOLD, 20));
        text.setPreferredSize(new Dimension(100, 200));
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setAlignmentY(TOP_ALIGNMENT);
        text.setAlignmentX(CENTER_ALIGNMENT);
        principalPanel.add(text);

        // Bouton OK
        ButtonOk.setBackground(Color.red);
        ButtonOk.setAlignmentY(BOTTOM_ALIGNMENT);
        ButtonOk.setAlignmentX(CENTER_ALIGNMENT);
        ButtonOk.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 5));
        ButtonOk.addActionListener(new QuitterListener());
        principalPanel.add(ButtonOk);
    }

    class QuitterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            dispose();
        }
    }
}
