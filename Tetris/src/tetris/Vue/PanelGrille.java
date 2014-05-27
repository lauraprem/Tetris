/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeTetris;

/**
 *
 * @author leclerc
 */
public class PanelGrille extends JPanel
{

    private final int NB_CASE_LIGNE = 10;
    private final int NB_CASE_COLONE = 20;

    private JPanel[][] cases;

    public JPanel[][] getCases()
    {
        return cases;
    }

    public void setCases(JPanel[][] cases)
    {
        this.cases = cases;
    }

    public void setCase(int x, int y, JPanel p)
    {
        if (x >= 0 && y >= 0 && p != null)
        {
            cases[x][y] = p;
        }
    }

    public PanelGrille()
    {
        super(new GridBagLayout());
        Border whiteline = BorderFactory.createLineBorder(Color.WHITE, 1);
        this.setOpaque(false);
        this.setBorder(whiteline);
        this.setPreferredSize(new Dimension(400, 600));
        cases = new JPanel[NB_CASE_COLONE][NB_CASE_LIGNE];
        build();

    }

    private void build()
    {
        Border whiteline = BorderFactory.createLineBorder(Color.WHITE, 1);
        for (int i = 0; i < NB_CASE_COLONE; i++)
        {
            for (int j = 0; j < NB_CASE_LIGNE; j++)
            {
                cases[i][j] = new JPanel();
                cases[i][j].setOpaque(false);
                cases[i][j].setBorder(whiteline);
                GridBagConstraints g = new GridBagConstraints();
                cases[i][j].setPreferredSize(new Dimension(32, 32));
                if ((i * NB_CASE_LIGNE + j + 1) % NB_CASE_LIGNE == 0)
                {
                    g.gridwidth = GridBagConstraints.REMAINDER;
                }

                this.add(cases[i][j], g);
            }

        }
    }
    
    public void effacerCases()
    {
        for (int i = 0; i < NB_CASE_COLONE; i++)
        {
            for (int j = 0; j < NB_CASE_LIGNE; j++)
            {
                cases[i][j].setBackground(Color.BLACK);
            }
        }
    }
    
    public void effacerPiece(PieceDeTetris piecePrec ){
     for (int i = 0; i < piecePrec.getlisteBlocs().size(); i++)
        {
            if (piecePrec.getBlocPosX(i) >= 0 && piecePrec.getBlocPosY(i) >= 0 && piecePrec.getBlocPosX(i) < NB_CASE_COLONE && piecePrec.getBlocPosY(i) < NB_CASE_LIGNE)
            {
                cases[piecePrec.getBlocPosX(i)][piecePrec.getBlocPosY(i)].setBackground(Color.BLACK);
            }
        }
    }
    
    public void afficherPiece(PieceDeTetris pieceCourante)
    {
         for (int i = 0; i < pieceCourante.getlisteBlocs().size(); i++)
        {
            cases[pieceCourante.getlisteBlocs().get(i).getPosition().getX()][pieceCourante.getlisteBlocs().get(i).getPosition().getY()].setOpaque(true);

            cases[pieceCourante.getlisteBlocs().get(i).getPosition().getX()][pieceCourante.getlisteBlocs().get(i).getPosition().getY()].setBackground(pieceCourante.getlisteBlocs().get(i).getCouleur());

        }
    }
    
    public void afficherBlocsFixes(ArrayList<Bloc> blocEnJEu)
    {
         for (int i = 0; i <blocEnJEu.size(); i++)
        {
            cases[blocEnJEu.get(i).getPosition().getX()][blocEnJEu.get(i).getPosition().getY()].setOpaque(true);
            cases[blocEnJEu.get(i).getPosition().getX()][blocEnJEu.get(i).getPosition().getY()].setBackground(blocEnJEu.get(i).getCouleur());
        }
    }
}
