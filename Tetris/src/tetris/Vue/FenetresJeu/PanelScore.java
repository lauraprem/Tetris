/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue.FenetresJeu;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author leclerc
 */
public class PanelScore extends JPanel
{

    private JLabel score;
    private JLabel niveau;
    private JLabel s;
    private JLabel n;

    public PanelScore()
    {
        super();
        score = new JLabel("0");
        niveau = new JLabel("1");
        s = new JLabel("Score\n");
        n = new JLabel("Niveau\n");
        Border whiteline = BorderFactory.createLineBorder(Color.WHITE, 1);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
        this.setBorder(whiteline);
        this.setPreferredSize(new Dimension(200, 600));

        build();
    }

    public void setTaille(int largeur, int hauteur)
    {
        this.setPreferredSize(new Dimension(largeur, hauteur));
    }

    public JLabel getScore()
    {
        return score;
    }

    public JLabel getNiveau()
    {
        return niveau;
    }

    public void setNiveau(int niveau)
    {
        this.niveau.setText(Integer.toString(niveau));

    }

    public void setScore(int score)
    {
        this.score.setText(Integer.toString(score));
    }

    public void setTaillePolice(int intitule, int nombre)
    {
        s.setFont(new Font("Arial", Font.BOLD, intitule));
        n.setFont(new Font("Arial", Font.BOLD, intitule));
        score.setFont(new Font("Arial", Font.BOLD, nombre));
        niveau.setFont(new Font("Arial", Font.BOLD, nombre));
    }
    
    private void build()
    {

        //Panel affichant "score"
        s.setFont(new Font("Arial", Font.BOLD, 40));
        s.setForeground(Color.WHITE);
        s.setAlignmentX(CENTER_ALIGNMENT);

        //Panel affichant le score
        score.setFont(new Font("Arial", Font.BOLD, 35));
        score.setForeground(Color.WHITE);
        score.setAlignmentX(CENTER_ALIGNMENT);

        //Panel affichant "niveau"
        n.setFont(new Font("Arial", Font.BOLD, 40));
        n.setForeground(Color.WHITE);
        n.setAlignmentX(CENTER_ALIGNMENT);

        //Panel affichant le niveau
        niveau.setFont(new Font("Arial", Font.BOLD, 35));
        niveau.setForeground(Color.WHITE);
        niveau.setAlignmentX(CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(s);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(score);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(n);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(niveau);
    }
}
