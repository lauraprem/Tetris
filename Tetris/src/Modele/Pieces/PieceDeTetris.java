/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Pieces;

import Modele.Bloc;
import Modele.Position;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Corinne
 */
public abstract class PieceDeTetris extends PieceDeJeu {
    protected Position centreRotation;
    protected Color couleurDefaut;
    
    public PieceDeTetris(int largeur){ // position centre rotation ???
        centreRotation.setPosition(1, largeur/2);
    }
    
    public abstract Color getCouleurDefaut();
}
