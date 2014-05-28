/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Modele.ModeleDeuxJoueurs;

import java.awt.Color;
import java.util.ArrayList;
import tetris.Modele.Pieces.ActionBloc;
import tetris.Modele.JeuDeTetris;
import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeJeu;
import tetris.Modele.Pieces.Position;

/**
 *
 * @author leclerc
 */
public class JeuDeTetris2Joueurs extends JeuDeTetris implements Runnable
{
    
    int nbLigne;
    boolean supprLigne;
    
    public JeuDeTetris2Joueurs()
    {
        super();
        nbLigne = 0;
        supprLigne = false;
    }
    
    public synchronized int getNbLigne()
    {
        return nbLigne;
    }
    
    public synchronized void setNbLigne(int nbLigne)
    {
        this.nbLigne = nbLigne;
    }
    
    public synchronized boolean isSupprLigne()
    {
        return supprLigne;
    }
    
    public synchronized void setSupprLigne(boolean supprLigne)
    {
        this.supprLigne = supprLigne;
    }
    
    public void recevoirLigne(int nb)
    {
        ArrayList<Bloc> bloc = this.getBlocEnJeu();
        PieceDeJeu piece = this.getPieceCourante();
        for (int i = 0; i < bloc.size(); i++)
        {
            
                bloc.get(i).setX(bloc.get(i).getX()-1);
           
        }
        for (int i = 0; i < largeur; i++)
        {
            bloc.add(new Bloc(new Position(19, i), Color.DARK_GRAY));
        }
        this.setBlocEnJeu(bloc);
    }
    
    @Override
    public void bougerPiece(ActionBloc sens)
    {
        
        if (!deplacerPiece(sens))
        {
            fixerPiece();
            int ligne;
            int nb = 0;
            while ((ligne = verifLigne()) != -1)
            {
                nb++;
                supprimerLigne(ligne);
            }
            score.calculerScore(nb);
            if (nb > 0)
            {
                nbLigne = nb;
                setSupprLigne(true);
            }
            passerPieceSuivante();

            // Verifie si la partie est perdu
            if (verifCollision())
            {
                setTermine(true);
                setMep(true);
            }
            
        } //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
