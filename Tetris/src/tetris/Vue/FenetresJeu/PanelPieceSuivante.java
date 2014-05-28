/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue.FenetresJeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import tetris.Modele.Pieces.PieceDeTetris;
import tetris.Modele.Pieces.PieceTetris.PieceI;

/**
 *
 * @author leclerc
 */
public class PanelPieceSuivante extends JPanel
{

    private ArrayList<JPanel[][]> casesSuivantes;

    public PanelPieceSuivante()
    {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        this.setPreferredSize(new Dimension(200, 600));
        casesSuivantes = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            casesSuivantes.add(new JPanel[4][4]);
        }
        build();

    }

     public void setTaille(int largeur, int hauteur)
    {this.setPreferredSize(new Dimension(largeur,hauteur));
    }
    
    private void build()
    {
        //Panels contenant les grilles
        JPanel[] pieces = new JPanel[4];
        for (int i = 0; i < 4; i++)
        {
            pieces[i] = new JPanel(new GridBagLayout());
        }


        //Fabriquer les grilles
        for (int i = 0; i < casesSuivantes.size(); i++)
        {
            creerPieceSuivantes(casesSuivantes.get(i), pieces[0], new Dimension(32, 32));
        }

        //ajout des panel contenant les grilles au panel principal
        for (int i = 0; i < 4; i++)
        {
            pieces[i].setBackground(Color.BLACK);
            this.add(pieces[i]);
        }

    }

    public void effacerGrilles()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                for (int k = 0; k < casesSuivantes.size(); k++)
                {
                    casesSuivantes.get(k)[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }

    public void afficherPiecesSuivantes(ArrayList<PieceDeTetris> piecesSuivantes, int nbPiece)
    {
        for (int j = 0; j < nbPiece; j++)
        {
            for (int i = 0; i < piecesSuivantes.get(j).getlisteBlocs().size(); i++)
            {
                int k = piecesSuivantes.get(j).getBlocPosY(0) - 1;
                int l = piecesSuivantes.get(j).getBlocPosX(0);
                if (piecesSuivantes.get(j).getClass() == PieceI.class)
                {
                    k++;
                }
                casesSuivantes.get(j)[piecesSuivantes.get(j).getBlocPosX(i) - l][piecesSuivantes.get(j).getBlocPosY(i) - k].setBackground(piecesSuivantes.get(j).getBloc(i).getCouleur());
            }
        }
    }

    public void creerPieceSuivantes(JPanel[][] casesuivante, JPanel parent,  Dimension dim)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)

            {
                casesuivante[i][j] = new JPanel();
                casesuivante[i][j].setBackground(Color.BLACK);
                casesuivante[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                GridBagConstraints g = new GridBagConstraints();
                casesuivante[i][j].setPreferredSize(dim);
                if ((i * 4 + j + 1) % 4 == 0)
                {
                    g.gridwidth = GridBagConstraints.REMAINDER;
                }

                parent.add(casesuivante[i][j], g);
            }
        }
    }

}
