/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Modele.Pieces;

import java.awt.Color;

/**
 *
 * @author Corinne
 */
public abstract class PieceDeTetris extends PieceDeJeu {
    protected Position centreRotation;
    protected Color couleurDefaut;
    
    public PieceDeTetris(int largeur){ // position centre rotation ???
        super();
        centreRotation = new Position(1, largeur/2);
        
    }
    
    public abstract Color getCouleurDefaut();
}
