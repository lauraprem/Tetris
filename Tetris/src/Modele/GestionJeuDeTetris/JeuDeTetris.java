package Modele.GestionJeuDeTetris;

import Modele.Bloc;
import Modele.Pieces.PieceDeTetris;
import Modele.Pieces.PieceTetris.PieceI;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * représente une <b>Grille de Tetris</b>, qui étend une Grille de Jeu de
 * manière plus générale
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class JeuDeTetris extends Observable implements Runnable
{

    // ATTRIBUTS   
    /**
     * Nombre de lignes de la Grille
     */
    private int hauteur;

    /**
     * Nombre de colonnes de la Grille
     */
    private int largeur;

    /**
     * Piece courante
     */
    private PieceDeTetris pieceCourante;

    public PieceDeTetris getPieceCourante()
    {
        return pieceCourante;
    }

    /**
     * les prochaines pièces à jouer
     */
    private ArrayList<PieceDeTetris> piecesSuivantes;

    /**
     * les blocs qui composaient les pièces qui ont déjà été jouées et qui sont
     * fixes
     */
    private ArrayList<Bloc> blocEnJeu;

    public ArrayList<PieceDeTetris> getPiecesSuivantes()
    {
        return piecesSuivantes;
    }

    public void setPiecesSuivantes(ArrayList<PieceDeTetris> piecesSuivantes)
    {
        this.piecesSuivantes = piecesSuivantes;
    }

    public ArrayList<Bloc> getBlocEnJeu()
    {
        return blocEnJeu;
    }

    public void setBlocEnJeu(ArrayList<Bloc> blocEnJeu)
    {
        this.blocEnJeu = blocEnJeu;
    }

    public boolean isMep()
    {
        return mep;
    }

    public void setMep(boolean mep)
    {
        this.mep = mep;
    }

    /**
     * permet de savoir si leu jeu est pause
     */
    private boolean mep;

    /**
     * <b>Constructeur</b> permettant de créer une Grille de taille 20 sur 10
     */
    public JeuDeTetris()
    {
        hauteur = 20;
        largeur = 10;
        pieceCourante = GenererPiece();
        piecesSuivantes = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            piecesSuivantes.add(GenererPiece());
        }
        blocEnJeu = new ArrayList<>();
        mep = false;
    }

    // ACCESSEURS
    /**
     * <b>Accesseur</b> permettant de recuperer le nombre de lignes de la Grille
     *
     * @return nombre de lignes de la Grille
     */
    public int getHauteur()
    {
        return hauteur;
    }

    /**
     * <b>Accesseur</b> permettant de recuperer le nombre de colonnes de la
     * Grille
     *
     * @return nombre de colonnes de la Grille
     */
    public int getLargeur()
    {
        return largeur;
    }

    /**
     * <b>Mutateur</b> permettant de récupérer la pièce courante
     *
     * @return pieceCourante la pièce courante
     */
    public PieceDeTetris setPieceCourante()
    {
        return pieceCourante;
    }

    // MUTATEURS
    /**
     * <b>Mutateur</b> permettant de modifier le nombre de lignes de la zone de
     * jeu
     *
     * @param hauteur nouveau nombre de lignes de la zone de jeu
     */
    public void setHauteur(int hauteur)
    {
        this.hauteur = hauteur;
    }

    /**
     * <b>Mutateur</b> permettant de modifier le nombre de colonnes de la zone
     * de jeu
     *
     * @param largeur nouveau nombre de colonnes de la zone de jeu
     */
    public void setLargeur(int largeur)
    {
        this.largeur = largeur;
    }

    /**
     * <b>Mutateur</b> permettant de modifier la pièce courante
     *
     * @param pieceCourante nouvelle pièce courante
     */
    public void setPieceCourante(PieceDeTetris pieceCourante)
    {
        this.pieceCourante = pieceCourante;
    }

    // METHODES
    /**
     * Permet de passer à la pièce suivante
     */
    public void PasserPieceSuivante()
    {
        pieceCourante = piecesSuivantes.get(0);
        piecesSuivantes.remove(0);
        piecesSuivantes.add(GenererPiece());
    }

    /**
     * Permet de créer une pièce de tetris aléatoirement
     *
     * @return une piece de tetris
     */
    private PieceDeTetris GenererPiece()
    {
        Random r = new Random();
        int forme = r.nextInt(7);
        PieceDeTetris piece;
        switch (forme)
        {
            case 0:
                piece = new PieceI(largeur);
                break;
            /* case 1 : piece = new PieceT();break;
             case 2 : piece = new PieceO();break;
             case 3 : piece = new PieceL();break;
             case 4 : piece = new PieceJ();break;
             case 5 : piece = new PieceZ();break;
             case 6 : piece = new PieceS();break;*/
            default:
                piece = new PieceI(largeur);
        }
        return piece;
    }

    /**
     * Permet de savoir si une piece est en collision avec les blocs déjà posés
     *
     * @param fantome liste de bloc reprénsant la position de la pièce à tester
     * @return vrai s'il y a collision et et faux si c'est bon
     */
    public boolean VerifCollision(ArrayList<Bloc> fantome)
    {
        for (int i = 0; i < blocEnJeu.size(); i++)
        {
            for (int j = 0; i < fantome.size(); j++)
            {
                if (blocEnJeu.get(i).getPosition() == fantome.get(j).getPosition())
                {
                    return true;
                }
                if (fantome.get(j).getPosition().getX() == hauteur - 1)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Permet de déplacer une pièce à droite, en bas ou en haut
     *
     * @param sens 81 pour droite (code clavier) 68 pour gauche (code clavier)
     * -1 pour en bas
     * @return vrai si la pièce a pu etre déplacée et faux sinon
     */
    public boolean DeplacerPiece(int sens)
    {
        ArrayList<Bloc> fantome = new ArrayList<>(pieceCourante.getlisteBlocs());
        // déplacement à gauche
        if (sens == 81)
        {
            for (int i = 0; i < 4; i++)
            {
                fantome.get(i).getPosition().setY(fantome.get(i).getPosition().getY() - 1);
            }
        } // déplacement à droite
        else if (sens == 68)
        {
            for (int i = 0; i < 4; i++)
            {
                fantome.get(i).getPosition().setY(fantome.get(i).getPosition().getY() + 1);
            }
        } //déplacement vers le bas
        else if (sens == -1)
        {
            for (int i = 0; i < 4; i++)
            {
                fantome.get(i).getPosition().setX(fantome.get(i).getPosition().getX() + 1);
            }
        }

        if (!VerifCollision(fantome))
        {
            pieceCourante.setlisteBlocs(fantome);
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * permet de savoir si une ligne est pleine
     *
     * @return -1 si aucune ligne n'est a supprimer et le numéro de la ligne
     * sinon
     */
    public int VerifLigne()
    {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < blocEnJeu.size(); i++)
        {
            if (pieceCourante.getlisteBlocs().get(0).getPosition().getY() == blocEnJeu.get(i).getPosition().getY())
            {
                a++;
            } else if (pieceCourante.getlisteBlocs().get(1).getPosition().getY() == blocEnJeu.get(i).getPosition().getY())
            {
                b++;
            } else if (pieceCourante.getlisteBlocs().get(2).getPosition().getY() == blocEnJeu.get(i).getPosition().getY())
            {
                c++;
            } else if (pieceCourante.getlisteBlocs().get(3).getPosition().getY() == blocEnJeu.get(i).getPosition().getY())
            {
                d++;
            }
        }

        if (a == largeur - 1)
        {
            return pieceCourante.getlisteBlocs().get(0).getPosition().getY();
        } else if (b == largeur - 1)
        {
            return pieceCourante.getlisteBlocs().get(1).getPosition().getY();
        } else if (c == largeur - 1)
        {
            return pieceCourante.getlisteBlocs().get(2).getPosition().getY();
        } else if (d == largeur - 1)
        {
            return pieceCourante.getlisteBlocs().get(3).getPosition().getY();
        }

        return -1;
    }

    /**
     * Permet de supprimer une ligne de de faire tomber les blocs au dessus
     *
     * @param ligne
     */
    public void SupprimerLigne(int ligne)
    {
        for (int i = 0; i < blocEnJeu.size(); i++)
        {
            if (blocEnJeu.get(i).getPosition().getX() == ligne)
            {
                blocEnJeu.remove(i);
            }
            if (blocEnJeu.get(i).getPosition().getX() > ligne)
            {
                blocEnJeu.get(i).getPosition().setX(blocEnJeu.get(i).getPosition().getX() + 1);
            }
        }
    }

    /**
     * Permet de fixer la pièce courante au blocs déjà joués
     */
    public void FixerPiece()
    {
        for (int i = 0; i < 4; i++)
        {
            blocEnJeu.add(pieceCourante.getlisteBlocs().get(i));
        }
    }

    /**
     * Permet de déplacer une pièce et de supprimer une ligne si besoin
     *
     * @param sens sens dans lequel on veut déplacer la pièce
     */
    public void BougerPiece(int sens)
    {
        if (!DeplacerPiece(sens))
        {
            int ligne = VerifLigne();
            FixerPiece();
            if (ligne != -1)
            {
                SupprimerLigne(ligne);
            }
            PasserPieceSuivante();
        }
    }

    @Override
    public void run()
    {
        while (true)
        {

            BougerPiece(-1);
            setChanged();
            notifyObservers();
            try
            {
                Thread.currentThread().sleep(1000);
                if (mep)
                {
                    synchronized (this)
                    {
                        wait();
                    }
                }
            } catch (InterruptedException ex)
            {
                Logger.getLogger(JeuDeTetris.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void gestionEnPause()
    {
        if (mep == true)
        {
            mep = false;
            synchronized (this)
            {
                notify();
            }
        } else
        {
            mep = true;
        }

    }



}
