package tetris.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.Border;
import tetris.Modele.JeuDeTetris;
import tetris.Modele.Pieces.PieceTetris.PieceI;

/**
 *
 * @author leclerc
 */
public class FenetreJeu extends JFrame implements Observer
{

    private final int HAUTEUR_TOTAL = 700;
    private final int LARGEUR_TOTAL = 800;
    private final int NB_CASE_LIGNE = 10;
    private final int NB_CASE_COLONE = 20;

    JPanel[][] cases;
    JeuDeTetris tetris;
    JPanel principalPanel;

    JPanel piecesuivante1;
    JPanel piecesuivante2;
    JPanel piecesuivante3;
    JPanel piecesuivante4;
    JPanel[][] casesuivante1;
    JPanel[][] casesuivante2;
    JPanel[][] casesuivante3;
    JPanel[][] casesuivante4;

    public FenetreJeu(JeuDeTetris t)
    {
        super();
        tetris = t;
        cases = new JPanel[NB_CASE_COLONE][NB_CASE_LIGNE];
        piecesuivante1 = new JPanel(new GridBagLayout());
        piecesuivante2 = new JPanel(new GridBagLayout());
        piecesuivante3 = new JPanel(new GridBagLayout());
        piecesuivante4 = new JPanel(new GridBagLayout());
        casesuivante1 = new JPanel[4][4];
        casesuivante2 = new JPanel[4][4];
        casesuivante3 = new JPanel[4][4];
        casesuivante4 = new JPanel[4][4];
        this.setTitle("Tetris");
        this.setSize(LARGEUR_TOTAL, HAUTEUR_TOTAL);
        this.setResizable(false);
        this.setFocusable(false);
        build();

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                super.windowClosing(arg0);
                // System.exit(0);
            }
        });
    }

    private void build()
    {

        //Panel principal
        Border whiteline = BorderFactory.createLineBorder(Color.WHITE, 1);
        principalPanel = new JPanel(new BorderLayout());
        principalPanel.setBackground(Color.BLACK);
        this.add(principalPanel, BorderLayout.CENTER);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        principalPanel.add(menuBar, BorderLayout.PAGE_START);
        JMenu m = new JMenu("Jeu");
        menuBar.add(m);

        //Panel de score
        System.out.println(m.getBounds().getHeight());
        JComponent scorePanel = new JPanel(new BorderLayout());
        scorePanel.setOpaque(false);
        scorePanel.setBorder(whiteline);
        scorePanel.setPreferredSize(new Dimension(200, 600));
        principalPanel.add(scorePanel, BorderLayout.LINE_START);

        //Panel de grille
        JComponent grillePanel = new JPanel(new GridBagLayout());
        grillePanel.setOpaque(false);
        grillePanel.setBorder(whiteline);
        grillePanel.setPreferredSize(new Dimension(400, 600));
        principalPanel.add(grillePanel, BorderLayout.CENTER);

        // Panels de la grille
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

                grillePanel.add(cases[i][j], g);
            }

        }

        //panel des pièces suivantes
        JPanel piecePanel = new JPanel();
        piecePanel.setLayout(new BoxLayout(piecePanel, BoxLayout.Y_AXIS));
        piecePanel.setOpaque(false);
        piecePanel.setBorder(whiteline);
        piecePanel.setPreferredSize(new Dimension(200, 600));
        principalPanel.add(piecePanel, BorderLayout.LINE_END);
        principalPanel.setFocusable(true);

        
         Border blackline = BorderFactory.createLineBorder(Color.BLACK, 1);
        //Panel piece suivante 1
        JPanel piece1 = new JPanel(new GridBagLayout());
        piece1.setBackground(Color.BLACK);
        piecePanel.add(piece1);
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)

            {
                casesuivante1[i][j] = new JPanel();
                casesuivante1[i][j].setBackground(Color.BLACK);
                casesuivante1[i][j].setBorder(blackline);
                GridBagConstraints g = new GridBagConstraints();
                casesuivante1[i][j].setPreferredSize(new Dimension(32, 32));
                if ((i * 4 + j + 1) % 4 == 0)
                {
                    g.gridwidth = GridBagConstraints.REMAINDER;
                }

                piece1.add(casesuivante1[i][j], g);
            }
        }

        //Panel piece suivante 2
        JPanel piece2 = new JPanel(new GridBagLayout());
        piece2.setBackground(Color.BLACK);
        piecePanel.add(piece2);
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)

            {
                casesuivante2[i][j] = new JPanel();
                casesuivante2[i][j].setBackground(Color.BLACK);
                casesuivante2[i][j].setBorder(blackline);
                GridBagConstraints g = new GridBagConstraints();
                casesuivante2[i][j].setPreferredSize(new Dimension(32, 32));
                if ((i * 4 + j + 1) % 4 == 0)
                {
                    g.gridwidth = GridBagConstraints.REMAINDER;
                }

                piece2.add(casesuivante2[i][j], g);
            }
        }

        //Panel piece suivante 3
        JPanel piece3 = new JPanel(new GridBagLayout());
        piece3.setBackground(Color.BLACK);
        piecePanel.add(piece3);
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)

            {
                casesuivante3[i][j] = new JPanel();
                casesuivante3[i][j].setBackground(Color.BLACK);
                casesuivante3[i][j].setBorder(blackline);
                GridBagConstraints g = new GridBagConstraints();
                casesuivante3[i][j].setPreferredSize(new Dimension(32, 32));
                if ((i * 4 + j + 1) % 4 == 0)
                {
                    g.gridwidth = GridBagConstraints.REMAINDER;
                }

                piece3.add(casesuivante3[i][j], g);
            }
        }

        //Panel piece suivante 4
        JPanel piece4 = new JPanel(new GridBagLayout());
        piece4.setBackground(Color.BLACK);
        piecePanel.add(piece4);
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)

            {
                casesuivante4[i][j] = new JPanel();
                casesuivante4[i][j].setBackground(Color.BLACK);
                casesuivante4[i][j].setBorder(blackline);
                GridBagConstraints g = new GridBagConstraints();
                casesuivante4[i][j].setPreferredSize(new Dimension(32, 32));
                if ((i * 4 + j + 1) % 4 == 0)
                {
                    g.gridwidth = GridBagConstraints.REMAINDER;
                }

                piece4.add(casesuivante4[i][j], g);
            }
        }

        //principalPanel.addKeyListener(new ControlleurClavier(tetris));
    }

    public JPanel getPrincipalPanel()
    {
        return principalPanel;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        //effacer les anciennes cases
        for (int i = 0; i < NB_CASE_COLONE; i++)
        {
            for (int j = 0; j < NB_CASE_LIGNE; j++)
            {
                cases[i][j].setBackground(Color.BLACK);
            }

        }
        //afficher les blocs fixes
        for (int i = 0; i < tetris.getBlocEnJeu().size(); i++)
        {
            cases[tetris.getBlocEnJeu().get(i).getPosition().getX()][tetris.getBlocEnJeu().get(i).getPosition().getY()].setOpaque(true);

            cases[tetris.getBlocEnJeu().get(i).getPosition().getX()][tetris.getBlocEnJeu().get(i).getPosition().getY()].setBackground(tetris.getBlocEnJeu().get(i).getCouleur());

        }
        //afficher la piece courante
        for (int i = 0; i < tetris.getPieceCourante().getlisteBlocs().size(); i++)
        {
            cases[tetris.getPieceCourante().getlisteBlocs().get(i).getPosition().getX()][tetris.getPieceCourante().getlisteBlocs().get(i).getPosition().getY()].setOpaque(true);

            cases[tetris.getPieceCourante().getlisteBlocs().get(i).getPosition().getX()][tetris.getPieceCourante().getlisteBlocs().get(i).getPosition().getY()].setBackground(tetris.getPieceCourante().getlisteBlocs().get(i).getCouleur());

        }
        //effacer les cases des pieces suivantes
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                casesuivante1[i][j].setBackground(Color.BLACK);
                casesuivante2[i][j].setBackground(Color.BLACK);
                casesuivante3[i][j].setBackground(Color.BLACK);
                casesuivante4[i][j].setBackground(Color.BLACK);
            }
        }
        //afficher la piece suivante 1
        for (int i = 0; i < tetris.getPiecesSuivantes().get(0).getlisteBlocs().size(); i++)
        {
            int k = tetris.getPiecesSuivantes().get(0).getlisteBlocs().get(0).getY() - 1;
            int l = tetris.getPiecesSuivantes().get(0).getlisteBlocs().get(0).getX();
            if (tetris.getPiecesSuivantes().get(0).getClass() == PieceI.class)
            {
                k++;
            //    l++;
            }
            casesuivante1[tetris.getPiecesSuivantes().get(0).getlisteBlocs().get(i).getX() - l][tetris.getPiecesSuivantes().get(0).getlisteBlocs().get(i).getY() - k].setBackground(tetris.getPiecesSuivantes().get(0).getlisteBlocs().get(i).getCouleur());

        }
        //afficher la piece suivante 2
        for (int i = 0; i < tetris.getPiecesSuivantes().get(1).getlisteBlocs().size(); i++)
        {
            int k = tetris.getPiecesSuivantes().get(1).getlisteBlocs().get(0).getY() - 1;
            int l = tetris.getPiecesSuivantes().get(1).getlisteBlocs().get(0).getX();
            if (tetris.getPiecesSuivantes().get(1).getClass() == PieceI.class)
            {
                k++;
            //    l++;
            }
            casesuivante2[tetris.getPiecesSuivantes().get(1).getlisteBlocs().get(i).getX() - l][tetris.getPiecesSuivantes().get(1).getlisteBlocs().get(i).getY() - k].setBackground(tetris.getPiecesSuivantes().get(1).getlisteBlocs().get(i).getCouleur());

        }
        //afficher la piece suivante 3
        for (int i = 0; i < tetris.getPiecesSuivantes().get(2).getlisteBlocs().size(); i++)
        {
            int k = tetris.getPiecesSuivantes().get(2).getlisteBlocs().get(0).getY() - 1;
            int l = tetris.getPiecesSuivantes().get(2).getlisteBlocs().get(0).getX();
            if (tetris.getPiecesSuivantes().get(2).getClass() == PieceI.class)
            {
                k++;
            //    l++;
            }
            casesuivante3[tetris.getPiecesSuivantes().get(2).getlisteBlocs().get(i).getX() - l][tetris.getPiecesSuivantes().get(2).getlisteBlocs().get(i).getY() - k].setBackground(tetris.getPiecesSuivantes().get(2).getlisteBlocs().get(i).getCouleur());

        }
        //afficher la piece suivante 4
        for (int i = 0; i < tetris.getPiecesSuivantes().get(3).getlisteBlocs().size(); i++)
        {
            int k = tetris.getPiecesSuivantes().get(3).getlisteBlocs().get(0).getY() - 1;
            int l = tetris.getPiecesSuivantes().get(3).getlisteBlocs().get(0).getX();
            if (tetris.getPiecesSuivantes().get(3).getClass() == PieceI.class)
            {
                k++;
           //     l++;
            }
            casesuivante4[tetris.getPiecesSuivantes().get(3).getlisteBlocs().get(i).getX() - l][tetris.getPiecesSuivantes().get(3).getlisteBlocs().get(i).getY() - k].setBackground(tetris.getPiecesSuivantes().get(3).getlisteBlocs().get(i).getCouleur());

        }
    }
    

}
