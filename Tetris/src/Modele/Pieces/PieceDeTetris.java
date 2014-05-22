/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.Pieces;

import Modele.GestionGrilleDeJeu.Bloc;
import Modele.GestionGrilleDeJeu.Position;
import java.awt.Color;

/**
 *
 * @author Corinne
 */
public class PieceDeTetris extends PieceDeJeu {
    protected Position axeRotation;

    public PieceDeTetris() {
    }
    
    public void setPosition(Position axeRotation){
        this.axeRotation = axeRotation;
    }

    public Color getColor() {
        return Color.WHITE;
    }
    public void setColor() {
    }
    public Color getColorDefault() {
        return Color.blue;
    }
}
