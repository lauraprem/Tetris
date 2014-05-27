package tetris.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import tetris.Modele.JeuDeTetris;
import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeTetris;

/**
 *
 * @author leclerc
 */
public class FenetreJeu extends JFrame implements Observer
{

    private final int HAUTEUR_TOTAL;
    private final int LARGEUR_TOTAL;

    JeuDeTetris tetris;

    JPanel principalPanel;

    PanelScore scorePanel;
    PanelGrille grillePanel;
    PanelPieceSuivante pieceSuivantePanel;

    public FenetreJeu(JeuDeTetris t)
    {
        super();
        scorePanel = new PanelScore();
        grillePanel = new PanelGrille();
        pieceSuivantePanel = new PanelPieceSuivante();
        tetris = t;
        
        HAUTEUR_TOTAL = Toolkit.getDefaultToolkit().getScreenSize().height - 45;
        LARGEUR_TOTAL = HAUTEUR_TOTAL + (Toolkit.getDefaultToolkit().getScreenSize().width / 4);
        
        this.setTitle("Tetris");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        this.setResizable(false);
        this.setFocusable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        build();

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                super.windowClosing(arg0);
            }
        });
    }

    private void build()
    {

        //Panel principal
        principalPanel = new JPanel(new BorderLayout());
        principalPanel.setBackground(Color.BLACK);

        principalPanel.add(scorePanel, BorderLayout.LINE_START);
        principalPanel.add(grillePanel, BorderLayout.CENTER);
        this.add(principalPanel, BorderLayout.CENTER);

        principalPanel.add(pieceSuivantePanel, BorderLayout.LINE_END);
        principalPanel.setFocusable(true);

        pieceSuivantePanel.afficherPiecesSuivantes(tetris.getPiecesSuivantes());

        //Menu
        JMenuBar menuBar = new JMenuBar();
        principalPanel.add(menuBar, BorderLayout.PAGE_START);
        JMenu m = new JMenu("Jeu");
        menuBar.add(m);

    }

    public JPanel getPrincipalPanel()
    {
        return principalPanel;
    }

    public synchronized void reveil()
    {
        this.notify();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        PieceDeTetris piecePrec = tetris.getFantome();
        PieceDeTetris pieceCour = tetris.getPieceCourante();
        ArrayList<Bloc> blocsEnJeu = tetris.getBlocEnJeu();
        int score = tetris.getScore().getPoint();
        int niveau = tetris.getScore().getNiveau();

        if (tetris.isTermine())
        {

            FenetreRejouer f = new FenetreRejouer(tetris, this);
            f.setVisible(true);
            synchronized (this)
            {
                try
                {
                    this.wait();
                } catch (InterruptedException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }
            if (!f.getRejouer())
            {
                dispose();
            }
            rafraichirAffichage(blocsEnJeu, niveau, score);
        }
        if (tetris.isCollision())
        {
            rafraichirAffichage(blocsEnJeu, niveau, score);
        }

        //Effacer la trace
        grillePanel.effacerPiece(piecePrec);

        //afficher la piece courante
        grillePanel.afficherPiece(pieceCour);

    }

    public void rafraichirAffichage(ArrayList<Bloc> blocsEnJeu, int niveau, int score)
    {
        //effacer les cases
        grillePanel.effacerCases();

        //afficher les blocs fixes
        grillePanel.afficherBlocsFixes(blocsEnJeu);

        //effacer les cases des pieces suivantes
        pieceSuivantePanel.effacerGrilles();
        pieceSuivantePanel.afficherPiecesSuivantes(tetris.getPiecesSuivantes());

        scorePanel.setNiveau(niveau);
        scorePanel.setScore(score);

    }

}
