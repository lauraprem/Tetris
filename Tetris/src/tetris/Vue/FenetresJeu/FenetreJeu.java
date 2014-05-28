package tetris.Vue.FenetresJeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import tetris.Modele.JeuDeTetris;
import tetris.Modele.Pieces.Bloc;
import tetris.Modele.Pieces.PieceDeTetris;
import tetris.Multimedia.LecteurSon;
import tetris.Vue.FenetreRejouer;
import tetris.Vue.Menu;

/**
 *
 * @author leclerc
 */
public class FenetreJeu extends JFrame implements Observer
{

    private int HAUTEUR_TOTAL;
    private int LARGEUR_TOTAL;

    private JeuDeTetris tetris;

    private JPanel principalPanel;

    private PanelScore scorePanel;
    private PanelGrille grillePanel;
    private PanelPieceSuivante pieceSuivantePanel;
    private Menu menu;

    LecteurSon l;
    public FenetreJeu(JeuDeTetris t)
    {
        super();
        scorePanel = new PanelScore();
        grillePanel = new PanelGrille();
        pieceSuivantePanel = new PanelPieceSuivante();
        menu = new Menu();
         l = new LecteurSon("./src/Contenu/Musiques/tetris.wav");
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
                tetris.gestionEnPause();
                 if (l != null)
                 {
                 l.stopper();
                 }
                dispose();
            }
        });
    }

    public void setTaille(int largeur, int hauteur)
    {
        HAUTEUR_TOTAL = hauteur;
        LARGEUR_TOTAL = largeur;
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
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

        pieceSuivantePanel.afficherPiecesSuivantes(tetris.getPiecesSuivantes(), menu.getNombrePieceAfficher());

        menu.getSousmenu0().addActionListener(new MyRadioButtonMenuItem());
        menu.getSousmenu0().addItemListener(new MyRadioButtonMenuItem());
        menu.getSousmenu1().addActionListener(new MyRadioButtonMenuItem());
        menu.getSousmenu1().addItemListener(new MyRadioButtonMenuItem());
        menu.getSousmenu2().addActionListener(new MyRadioButtonMenuItem());
        menu.getSousmenu2().addItemListener(new MyRadioButtonMenuItem());
        menu.getSousmenu3().addActionListener(new MyRadioButtonMenuItem());
        menu.getSousmenu3().addItemListener(new MyRadioButtonMenuItem());
        menu.getSousmenu4().addActionListener(new MyRadioButtonMenuItem());
        menu.getSousmenu4().addItemListener(new MyRadioButtonMenuItem());
        principalPanel.add(menu, BorderLayout.PAGE_START);
        if (!tetris.getBlocEnJeu().isEmpty())
        {
            grillePanel.afficherBlocsFixes(tetris.getBlocEnJeu());
        }

         l.jouerAvecRepetition();
         
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
        synchronized (this)
        {
            PieceDeTetris piecePrec = tetris.getFantome();
            PieceDeTetris pieceCour = tetris.getPieceCourante();
            ArrayList<Bloc> blocsEnJeu = tetris.getBlocEnJeu();
            ArrayList<PieceDeTetris> piecesSuivantes = tetris.getPiecesSuivantes();

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

                    if (!f.getRejouer())
                    {
                         if (l != null)
                         {
                         l.stopper();
                         }
                        dispose();
                    }

                    piecePrec = tetris.getFantome();
                    pieceCour = tetris.getPieceCourante();
                    blocsEnJeu = tetris.getBlocEnJeu();
                    piecesSuivantes = tetris.getPiecesSuivantes();

                    score = tetris.getScore().getPoint();
                    niveau = tetris.getScore().getNiveau();
                    rafraichirAffichage(blocsEnJeu, piecesSuivantes, niveau, score);
                }
            }
            if (tetris.isCollision() )//|| (( tetris.getClass() == JeuDeTetris2Joueurs.class ) &&((JeuDeTetris2Joueurs)tetris.isSupprLigne())))
            {
                rafraichirAffichage(blocsEnJeu, piecesSuivantes, niveau, score);
            }

            //Effacer la trace
            grillePanel.effacerPiece(piecePrec);

            //afficher la piece courante
            grillePanel.afficherPiece(pieceCour);
        }

    }

    public synchronized void rafraichirAffichage(ArrayList<Bloc> blocsEnJeu, ArrayList<PieceDeTetris> piecesSuivantes, int niveau, int score)
    {
        //effacer les cases
        grillePanel.effacerCases();

        //afficher les blocs fixes
        grillePanel.afficherBlocsFixes(blocsEnJeu);

        //effacer les cases des pieces suivantes
        pieceSuivantePanel.effacerGrilles();
        pieceSuivantePanel.afficherPiecesSuivantes(piecesSuivantes, menu.getNombrePieceAfficher());

        //afficher le score
        scorePanel.setNiveau(niveau);
        scorePanel.setScore(score);

    }

    class MenuListener implements ActionListener
    {

        //Redéfinition de la méthode actionPerformed()
        @Override
        public synchronized void actionPerformed(ActionEvent arg0)
        {
            pieceSuivantePanel.afficherPiecesSuivantes(tetris.getPiecesSuivantes(), menu.getNombrePieceAfficher());
        }

    }

    class MyRadioButtonMenuItem extends JRadioButtonMenuItem
            implements ActionListener, ItemListener
    {

        @Override
        public synchronized void actionPerformed(ActionEvent e)
        {
            int nb;
            if ("N'afficher aucune piece en avance".equals(e.getActionCommand()))
            {
                nb = 0;
            } /*else if (e.getItem().equals(menu.getSousmenu1()))
             {
             nb = 1;
             } else if (e.getItem().equals(menu.getSousmenu2()))
             {
             nb = 2;
             } else if (e.getItem().equals(menu.getSousmenu3()))
             {
             nb = 3;
             } else
             {
             nb = 4;
             }*/

            // pieceSuivantePanel.afficherPiecesSuivantes(tetris.getPiecesSuivantes(), nb);
            pieceSuivantePanel.afficherPiecesSuivantes(tetris.getPiecesSuivantes(), menu.getNombrePieceAfficher());
            System.out.println("clic ");
        }

        @Override
        public void itemStateChanged(ItemEvent e)
        {
            System.out.println("State changed: ");
            /*  int nb;
             if (e.getItem().equals(menu.getSousmenu0()))
             {
             nb = 0;
             } else if (e.getItem().equals(menu.getSousmenu1()))
             {
             nb = 1;
             } else if (e.getItem().equals(menu.getSousmenu2()))
             {
             nb = 2;
             } else if (e.getItem().equals(menu.getSousmenu3()))
             {
             nb = 3;
             } else
             {
             nb = 4;
             }
             pieceSuivantePanel.afficherPiecesSuivantes(tetris.getPiecesSuivantes(), nb);*/

        }

    }

    public PanelScore getScorePanel()
    {
        return scorePanel;
    }

    public void setScorePanel(PanelScore scorePanel)
    {
        this.scorePanel = scorePanel;
    }

    public PanelGrille getGrillePanel()
    {
        return grillePanel;
    }

    public void setGrillePanel(PanelGrille grillePanel)
    {
        this.grillePanel = grillePanel;
    }

    public PanelPieceSuivante getPieceSuivantePanel()
    {
        return pieceSuivantePanel;
    }

    public void setPieceSuivantePanel(PanelPieceSuivante pieceSuivantePanel)
    {
        this.pieceSuivantePanel = pieceSuivantePanel;
    }

    public JeuDeTetris getTetris()
    {
        return tetris;
    }
    
    

}
