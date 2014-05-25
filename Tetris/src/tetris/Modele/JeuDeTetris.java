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

/**
 * représente une <b>Grille de Tetris</b>, qui étend une Grille de Jeu de
 * manière plus générale
 *
 * @author Corinne Fagno && Laura Prémillieu
 */
public class JeuDeTetris extends Observable implements Runnable {

    // ATTRIBUTS   
    private static int pasTemps;
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

    // CONSTRUCTEURS
    /**
     * <b>Constructeur</b> permettant de créer une Grille de taille 20 sur 10
     */
    public JeuDeTetris() {
        this(10, 20);
    }

    /**
     * <b>Constructeur</b> permettant de créer une Grille de taille hauteur X
     * largeur
     *
     * @param largeur nombre de colonnes
     * @param hauteur nombre de lignes
     */
    public JeuDeTetris(int largeur, int hauteur) {
        this.hauteur = hauteur;
        this.largeur = largeur;

        pieceCourante = genererPiece();
        piecesSuivantes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            piecesSuivantes.add(genererPiece());
        }
        blocEnJeu = new ArrayList<>();

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
    public int getHauteur() {
        return hauteur;
    }

    /**
     * <b>Accesseur</b> permettant de recuperer le nombre de colonnes de la
     * Grille
     *
     * @return nombre de colonnes de la Grille
     */
    public int getLargeur() {
        return largeur;
    }

    public ArrayList<PieceDeTetris> getPiecesSuivantes() {
        return piecesSuivantes;
    }

    public static int getPasTemps() {
        return pasTemps;
    }

    public PieceDeTetris getPieceCourante() {
        return pieceCourante;
    }

    public ArrayList<Bloc> getBlocEnJeu() {
        return blocEnJeu;
    }

    public boolean isAccelerer() {
        return accelerer;
    }

    public boolean isTermine() {
        return termine;
    }

    /**
     * <b>Mutateur</b> permettant de récupérer la pièce courante
     *
     * @return pieceCourante la pièce courante
     */
    public PieceDeTetris setPieceCourante() {
        return pieceCourante;
    }

    // MUTATEURS
    /**
     * <b>Mutateur</b> permettant de modifier le nombre de lignes de la zone de
     * jeu
     *
     * @param hauteur nouveau nombre de lignes de la zone de jeu
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * <b>Mutateur</b> permettant de modifier le nombre de colonnes de la zone
     * de jeu
     *
     * @param largeur nouveau nombre de colonnes de la zone de jeu
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     * <b>Mutateur</b> permettant de modifier la pièce courante
     *
     * @param pieceCourante nouvelle pièce courante
     */
    public void setPieceCourante(PieceDeTetris pieceCourante) {
        this.pieceCourante = pieceCourante;
    }

    public void setPiecesSuivantes(ArrayList<PieceDeTetris> piecesSuivantes) {
        this.piecesSuivantes = piecesSuivantes;
    }

    public static void setPasTemps(int pasTemps) {
        JeuDeTetris.pasTemps = pasTemps;
    }

    public void setBlocEnJeu(ArrayList<Bloc> blocEnJeu) {
        this.blocEnJeu = blocEnJeu;
    }

    public void setTermine(boolean termine) {
        this.termine = termine;
    }

    // METHODES
    /**
     * Permet de passer à la pièce suivante
     */
    public void passerPieceSuivante() {
        pieceCourante = piecesSuivantes.get(0);
        piecesSuivantes.remove(0);
        piecesSuivantes.add(genererPiece());
    }

    /**
     * Permet de créer une pièce de tetris aléatoirement
     *
     * @return une piece de tetris
     */
    private PieceDeTetris genererPiece() {
        Random r = new Random();
        int forme = r.nextInt(7);
        PieceDeTetris piece;
        switch (forme) {
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
        return piece;
    }

    /**
     * Permet de savoir si une piece est en collision avec les blocs déjà posés
     *
     * @param fantome liste de bloc reprénsant la position de la pièce à tester
     * @return vrai s'il y a collision et et faux si c'est bon
     */
    public boolean verifCollision(ArrayList<Bloc> fantome) {

        for (int j = 0; j < fantome.size(); j++) {
            if ((fantome.get(j).getPosition().getX() == hauteur)
                    || (fantome.get(j).getPosition().getX() < 0)
                    || (fantome.get(j).getPosition().getY() < 0)
                    || (fantome.get(j).getPosition().getY() == largeur)) {
                return true;
            }
        }
        for (int i = 0; i < blocEnJeu.size(); i++) {
            for (int j = 0; j < fantome.size(); j++) {
                if (blocEnJeu.get(i).getPosition().getX() == (fantome.get(j).getPosition()).getX()
                        && blocEnJeu.get(i).getPosition().getY() == (fantome.get(j).getPosition()).getY()) {
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
    public boolean deplacerPiece(int sens) {
        if (mep == false) {
            // cree un fantome pour verifier les position possibles de la piece
            PieceDeTetris fantome;
            fantome = (PieceDeTetris) pieceCourante.clone();

            // Recherche de l'action futur à réaliser
            switch (sens) {
                case KeyEvent.VK_Q: // déplacement à gauche
                    fantome.deplacerGauche(1);
                    break;
                case KeyEvent.VK_D: // déplacement à droite
                    fantome.deplacerDroite(1);
                    break;
                case KeyEvent.VK_LEFT: //rotation vers le gauche
                    fantome.rotationPiece(-90);
                    break;
                case KeyEvent.VK_RIGHT: //rotation vers la droite
                    fantome.rotationPiece(90);
                    break;
                default: //deplacement par defaut
                    fantome.deplacerBas(1);
                    break;
            }

            // Verification de la colision
            if (!verifCollision(fantome.getlisteBlocs())) {
                synchronized (this) {
                    pieceCourante = fantome;
                }
                // indication de la modification à la vue
                setChanged();
                notifyObservers();
                return true;
            }

        }
        return false;
    }

    /**
     * permet de savoir si une ligne est pleine
     *
     * @return -1 si aucune ligne n'est a supprimer et le numéro de la ligne
     * sinon
     */
    public int verifLigne() {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < blocEnJeu.size(); i++) {
            if (pieceCourante.getBlocPosX(0) == blocEnJeu.get(i).getPosition().getX()) {
                a++;
            } else if (pieceCourante.getBlocPosX(1) == blocEnJeu.get(i).getPosition().getX()) {
                b++;
            } else if (pieceCourante.getBlocPosX(2) == blocEnJeu.get(i).getPosition().getX()) {
                c++;
            } else if (pieceCourante.getBlocPosX(3) == blocEnJeu.get(i).getPosition().getX()) {
                d++;
            }
        }

        if (a == largeur) {
            return pieceCourante.getlisteBlocs().get(0).getPosition().getX();
        } else if (b == largeur) {
            return pieceCourante.getlisteBlocs().get(1).getPosition().getX();
        } else if (c == largeur) {
            return pieceCourante.getlisteBlocs().get(2).getPosition().getX();
        } else if (d == largeur) {
            return pieceCourante.getlisteBlocs().get(3).getPosition().getX();
        }

        return -1;
    }

    /**
     * Permet de supprimer une ligne de de faire tomber les blocs au dessus
     *
     * @param ligne
     */
    public void supprimerLigne(int ligne) {
        ListIterator<Bloc> iterator = blocEnJeu.listIterator();
        while (iterator.hasNext()) {
            Bloc bloc = iterator.next();

            if (bloc.getX() == ligne) {
                iterator.remove();
            }
            if (bloc.getX() == ligne - 1) {
                Position p = new Position(bloc.getX() + 1, bloc.getY());
                bloc.setPosition(p);
                iterator.set(bloc);
            }
        }
    }

    /**
     * Permet de fixer la pièce courante au blocs déjà joués
     */
    public void fixerPiece() {
        for (int i = 0; i < 4; i++) {
            blocEnJeu.add(pieceCourante.getlisteBlocs().get(i));
        }
    }

    /**
     * Permet de déplacer une pièce et de supprimer une ligne si besoin
     *
     * @param sens sens dans lequel on veut déplacer la pièce
     */
    public void bougerPiece(int sens) {
        if (!deplacerPiece(sens)) {
            fixerPiece();
            int ligne = verifLigne();
            if (ligne != -1) {
                supprimerLigne(ligne);
            }
            passerPieceSuivante();
            
            // Verifie si la partie est perdu
            if(verifCollision(pieceCourante.getlisteBlocs())){
                termine = true;
                setChanged();
                notifyObservers();
                mep = true;
            }
                
        }
    }

    @Override
    public void run() {
        setChanged();
        notifyObservers();
        try {
            // Thread.currentThread().sleep(pasTemps);

            while (true) {

                bougerPiece(-1);
                setChanged();
                notifyObservers();

                Thread.currentThread().sleep(pasTemps);

                if (mep) {
                    synchronized (this) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException : " + ex.getMessage());
        }
    }

    /**
     * Permet de gérer la vitesse des temps d'attentes du jeu
     */
    public void gestionVitesse() {
        if (mep != true) {
            if (accelerer == false) {
                pasTemps = pasTemps / 4;
                accelerer = true;
            } else {
                pasTemps = pasTemps * 4;
                accelerer = false;
            }
        }
    }

    /**
     * Permet la pause lors du jeu
     */
    public void gestionEnPause() {
        if (mep == true) {
            mep = false;
            synchronized (this) {
                notify();
            }
        } else {
            mep = true;
        }
    }
}
