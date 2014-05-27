package tetris.Modele;

import java.awt.event.KeyEvent;
import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeTetris;
import tetris.Modele.Pieces.PieceTetris.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Random;
import tetris.Modele.Pieces.Position;
import tetris.Modele.Score.Score;

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
     * Pas de temps de la chute des blocs
     */
    private int pasTemps;
    /**
     * Nombre de lignes de la Grille
     */
    private int hauteur;

    /**
     * Nombre de colonnes de la Grille
     */
    private int largeur;
    
    private Score score;

    /**
     * Piece courante
     */
    private PieceDeTetris pieceCourante;

    /**
     * Piece pour détecter les collisions et mémoriser la derniere position de
     * la piece courante
     */
    private PieceDeTetris fantome;

    /**
     * les prochaines pièces à jouer
     */
    private ArrayList<PieceDeTetris> piecesSuivantes;

    /**
     * les blocs qui composaient les pièces qui ont déjà été jouées et qui sont
     * fixes
     */
    private ArrayList<Bloc> blocEnJeu;

    /**
     * permet de savoir si leu jeu est pause
     */
    private boolean mep;

    /**
     * permet de savoir si le jeu est en accéléré
     */
    private boolean accelerer;

    /**
     * permet de savoir si la partie est terminé
     */
    private boolean termine;

    /**
     * Permet de savoir si la piece rentre en collision avec les blocs
     */
    private boolean collision;
    
    

    // CONSTRUCTEURS
    /**
     * <b>Constructeur</b> permettant de créer une Grille de taille 20 sur 10
     */
    public JeuDeTetris()
    {
        this(10, 20);
    }

    /**
     * <b>Constructeur</b> permettant de créer une Grille de taille hauteur X
     * largeur
     *
     * @param largeur nombre de colonnes
     * @param hauteur nombre de lignes
     */
    public JeuDeTetris(int largeur, int hauteur)
    {
        this.hauteur = hauteur;
        this.largeur = largeur;
        score = new Score();
        pieceCourante = genererPiece();
        piecesSuivantes = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            piecesSuivantes.add(genererPiece());
        }
        blocEnJeu = new ArrayList<>();
        fantome = (PieceDeTetris) pieceCourante.clone();

        pasTemps = 1000;
        accelerer = false;
        mep = false;
        termine = false;
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

    public synchronized ArrayList<PieceDeTetris> getPiecesSuivantes()
    {
        return (ArrayList<PieceDeTetris>) piecesSuivantes.clone();
    }

    public synchronized PieceDeTetris getPieceCourante()
    {
        return (PieceDeTetris) pieceCourante.clone();
    }

    public synchronized ArrayList<Bloc> getBlocEnJeu()
    {
        return (ArrayList<Bloc>) blocEnJeu.clone();
    }

    public synchronized boolean isAccelerer()
    {
        return accelerer;
    }

    public synchronized boolean isTermine()
    {
        return termine;
    }

    public synchronized boolean isCollision()
    {
        return collision;
    }

    public synchronized PieceDeTetris getFantome()
    {
        return (PieceDeTetris) fantome.clone();
    }

    public void setMep(boolean mep)
    {
        this.mep = mep;
    }

    /**
     * <b>Mutateur</b> permettant de récupérer la pièce courante
     *
     * @return pieceCourante la pièce courante
     */
    public synchronized PieceDeTetris setPieceCourante()
    {
        return pieceCourante;
    }

    public Score getScore()
    {
        return score;
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
    public synchronized void setPieceCourante(PieceDeTetris pieceCourante)
    {
        this.pieceCourante = pieceCourante;
    }

    public synchronized void setPiecesSuivantes(ArrayList<PieceDeTetris> piecesSuivantes)
    {
        this.piecesSuivantes = piecesSuivantes;
    }

    public synchronized void setPasTemps(int pasTemps)
    {
        this.pasTemps = pasTemps;
    }

    public synchronized void setBlocEnJeu(ArrayList<Bloc> blocEnJeu)
    {
        this.blocEnJeu = blocEnJeu;
    }

    public synchronized void setTermine(boolean termine)
    {
        this.termine = termine;
    }

    public synchronized void setCollision(boolean collision)
    {
        this.collision = collision;
    }

    public synchronized void setFantome(PieceDeTetris fantome)
    {
        this.fantome = fantome;
    }

    public boolean isMep()
    {
        return mep;
    }

    public void setScore(Score score)
    {
        this.score = score;
    }
    
    

    // METHODES
    /**
     * Permet de passer à la pièce suivante
     */
    public void passerPieceSuivante()
    {
        pieceCourante = piecesSuivantes.get(0);
        piecesSuivantes.remove(0);
        piecesSuivantes.add(genererPiece());
    }

    /**
     * Permet de créer une pièce de tetris aléatoirement
     *
     * @return une piece de tetris
     */
    private PieceDeTetris genererPiece()
    {
        Random r = new Random();
        int forme = r.nextInt(7);
        PieceDeTetris piece;
        switch (forme)
        {
            case 0:
                piece = new PieceI(largeur, hauteur);
                break;
            case 1:
                piece = new PieceT(largeur, hauteur);
                break;
            case 2:
                piece = new PieceO(largeur, hauteur);
                break;
            case 3:
                piece = new PieceL(largeur, hauteur);
                break;
            case 4:
                piece = new PieceJ(largeur, hauteur);
                break;
            case 5:
                piece = new PieceZ(largeur, hauteur);
                break;
            case 6:
                piece = new PieceS(largeur, hauteur);
                break;
            default:
                piece = new PieceI(largeur, hauteur);
        }
        setFantome(piece);
        return piece;
    }

    /**
     * Permet de savoir si une piece est en collision avec les blocs déjà posés
     *
     * @return vrai s'il y a collision et et faux si c'est bon
     */
    public boolean verifCollision()
    {

        for (int j = 0; j < fantome.getlisteBlocs().size(); j++)
        {
            if ((fantome.getBlocPosX(j) == hauteur)
                    || (fantome.getBlocPosX(j) < 0)
                    || (fantome.getBlocPosY(j) < 0)
                    || (fantome.getBlocPosY(j) == largeur))
            {
                return true;
            }
        }
        for (int i = 0; i < blocEnJeu.size(); i++)
        {
            for (int j = 0; j < fantome.getlisteBlocs().size(); j++)
            {
                if (blocEnJeu.get(i).getPosition().getX() == fantome.getBlocPosX(j)
                        && blocEnJeu.get(i).getPosition().getY() == (fantome.getBlocPosY(j)))
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
    public synchronized boolean deplacerPiece(int sens)
    {

        fantome = (PieceDeTetris) pieceCourante.clone();

        
        
        // Recherche de l'action futur à réaliser
        switch (sens)
        {
            case KeyEvent.VK_Q: // déplacement à gauche
                fantome.deplacerGauche(1);
                break;
            case KeyEvent.VK_D: // déplacement à droite
                fantome.deplacerDroite(1);
                break;
            case KeyEvent.VK_K: //rotation vers le gauche
                fantome.rotationPiece(-90);
                break;
            case KeyEvent.VK_M: //rotation vers la droite
                fantome.rotationPiece(90);
                break;
            default: //deplacement par defaut
                fantome.deplacerBas(1);
                break;
        }

        // Verification de la colision
        collision = (verifCollision());
        if (!collision)
        {
            PieceDeTetris temp = (PieceDeTetris) fantome.clone();
            fantome = (PieceDeTetris) pieceCourante.clone();
            pieceCourante = temp;
            // indication de la modification à la vue
            setChanged();
            notifyObservers();

            return true;
        }

        //   }
        return false;
    }

    /**
     * permet de savoir si une ligne est pleine
     *
     * @return -1 si aucune ligne n'est a supprimer et le numéro de la ligne
     * sinon
     */
    public int verifLigne()
    {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < blocEnJeu.size(); i++)
        {
            if (pieceCourante.getBlocPosX(0) == blocEnJeu.get(i).getPosition().getX())
            {
                a++;
            } else if (pieceCourante.getBlocPosX(1) == blocEnJeu.get(i).getPosition().getX())
            {
                b++;
            } else if (pieceCourante.getBlocPosX(2) == blocEnJeu.get(i).getPosition().getX())
            {
                c++;
            } else if (pieceCourante.getBlocPosX(3) == blocEnJeu.get(i).getPosition().getX())
            {
                d++;
            }
        }

        if (a == largeur)
        {
            return pieceCourante.getlisteBlocs().get(0).getPosition().getX();
        } else if (b == largeur)
        {
            return pieceCourante.getlisteBlocs().get(1).getPosition().getX();
        } else if (c == largeur)
        {
            return pieceCourante.getlisteBlocs().get(2).getPosition().getX();
        } else if (d == largeur)
        {
            return pieceCourante.getlisteBlocs().get(3).getPosition().getX();
        }

        return -1;
    }

    /**
     * Permet de supprimer une ligne de de faire tomber les blocs au dessus
     *
     * @param ligne
     */
    public void supprimerLigne(int ligne)
    {
        ListIterator<Bloc> iterator = blocEnJeu.listIterator();
        while (iterator.hasNext())
        {
            Bloc bloc = iterator.next();

            //on supprime la ligne
            if (bloc.getX() == ligne)
            {
                iterator.remove();
            }
            //on fait descendre les autres 
            if (bloc.getX() <= ligne - 1)
            {
                Position p = new Position(bloc.getX() + 1, bloc.getY());
                bloc.setPosition(p);
                iterator.set(bloc);
            }
        }
    }

    /**
     * Permet de fixer la pièce courante au blocs déjà joués
     */
    public void fixerPiece()
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
    public synchronized void bougerPiece(int sens)
    {
        if (!deplacerPiece(sens))
        {
            fixerPiece();
            int ligne;
            int nbligne =0;
            while ((ligne = verifLigne()) != -1)
            {
                nbligne++;
                supprimerLigne(ligne);
            }
            score.calculerScore(nbligne);
            passerPieceSuivante();

            // Verifie si la partie est perdu
            if (verifCollision())
            {
                termine = true;
                mep = true;
            }

        }
    }

   
    @Override
    public void run()
    {
        setChanged();
        notifyObservers();
        try
        {
            // Thread.currentThread().sleep(pasTemps);

            while (true)
            {

                bougerPiece(-1);
                setChanged();
                notifyObservers();
                Thread.currentThread().sleep(getPasTemps());

                while (isPause())
                {//eviter que le thread soit réveillé par autre chose
                    synchronized (this)
                    {
                        wait();
                    }
                }
            }
        } catch (InterruptedException ex)
        {
            System.out.println("InterruptedException : " + ex.getMessage());
        }
    }

    /**
     * Permet de commencer une autre partie
     */
    public synchronized void rejouer()
    {
        this.accelerer = false;
        this.collision = false;
        this.mep = false;
        this.termine = false;
        this.pasTemps = 1000;
        this.score = new Score();
        this.blocEnJeu.clear();
        pieceCourante = genererPiece();
        piecesSuivantes.clear();
        for (int i = 0; i < 4; i++)
        {
            piecesSuivantes.add(genererPiece());
        }
    }

    /**
     * Permet d'accélérer la vitesse de la chute des blocs
     */
    public synchronized void decelerer()
    {
        if (mep != true && accelerer == true)
        {
            pasTemps = pasTemps * 8;
            accelerer = false;
        }
    }

    /**
     * Permet de ralentir la vitesse de la chute des blocs
     */
    public synchronized void accelerer()
    {
        if (accelerer == false && mep != true)
        {
            pasTemps = pasTemps / 8;
            accelerer = true;
        }
    }

    public synchronized int getPasTemps()
    {
        return pasTemps;
    }

    /**
     * Permet la pause lors du jeu
     */
    public void gestionEnPause()
    {
        synchronized (this)
        {
            if (mep == true)
            {
                mep = false;
                // synchronized (this) {
                notify();
                //}
            } else
            {
                mep = true;
            }
        }
    }

    public synchronized boolean isPause()
    { // pas changer pendant les if (type primitif)
        return mep;
    }
}
