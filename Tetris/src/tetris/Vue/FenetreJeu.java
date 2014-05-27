package tetris.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import tetris.Modele.JeuDeTetris;
import tetris.Modele.Pieces.PieceDeTetris;
import tetris.Modele.Pieces.PieceTetris.PieceI;

/**
 *
 * @author leclerc
 */
public class FenetreJeu extends JFrame implements Observer
{

    private final int HAUTEUR_TOTAL;// = 700;
    private final int LARGEUR_TOTAL;// = 800;
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

    JPanel piece1;
    JPanel piece2;
    JPanel piece3;
    JPanel piece4;

    JPanel scorePanel;
    
    JLabel score;
    JLabel niveau;

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
        piece1 = new JPanel(new GridBagLayout());
        piece2 = new JPanel(new GridBagLayout());
        piece3 = new JPanel(new GridBagLayout());
        piece4 = new JPanel(new GridBagLayout());
        scorePanel = new JPanel();
        score = new JLabel("0");
        niveau = new JLabel("1");
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
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setOpaque(false);
        scorePanel.setBorder(whiteline);
        scorePanel.setPreferredSize(new Dimension(200, 600));
        principalPanel.add(scorePanel, BorderLayout.LINE_START);
        
        JLabel s = new JLabel("Score\n");
        JLabel n = new JLabel("Niveau\n");
        s.setFont(new Font("Arial", Font.BOLD, 40));
        s.setForeground(Color.WHITE);
        s.setAlignmentX(CENTER_ALIGNMENT);
        score.setFont(new Font("Arial", Font.BOLD, 35));
        score.setForeground(Color.WHITE);
        score.setAlignmentX(CENTER_ALIGNMENT);
        n.setFont(new Font("Arial", Font.BOLD, 40));
        n.setForeground(Color.WHITE);
        n.setAlignmentX(CENTER_ALIGNMENT);
        niveau.setFont(new Font("Arial", Font.BOLD, 35));
        niveau.setForeground(Color.WHITE);
        niveau.setAlignmentX(CENTER_ALIGNMENT);
        
        scorePanel.add(Box.createRigidArea(new Dimension(0, 50)));
        scorePanel.add(s);
        scorePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        scorePanel.add(score);
        scorePanel.add(Box.createRigidArea(new Dimension(0, 100)));
        scorePanel.add(n);
        scorePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        scorePanel.add(niveau);

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
        creerPieceSuivantes(casesuivante1, piece1, blackline, new Dimension(32, 32));
        creerPieceSuivantes(casesuivante2, piece2, blackline, new Dimension(32, 32));
        creerPieceSuivantes(casesuivante3, piece3, blackline, new Dimension(32, 32));
        creerPieceSuivantes(casesuivante4, piece4, blackline, new Dimension(32, 32));
        piece1.setBackground(Color.BLACK);
        piecePanel.add(piece1);

        piece2.setBackground(Color.BLACK);
        piecePanel.add(piece2);

        piece3.setBackground(Color.BLACK);
        piecePanel.add(piece3);

        piece4.setBackground(Color.BLACK);
        piecePanel.add(piece4);

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
        //Afficher les pieces suivantes
        afficherPiecesSuivantes(casesuivante1, 0);
        afficherPiecesSuivantes(casesuivante2, 1);
        afficherPiecesSuivantes(casesuivante3, 2);
        afficherPiecesSuivantes(casesuivante4, 3);

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
            rafraichirAffichage();
        }
        if (tetris.isCollision())
        {
            rafraichirAffichage();
        }

        PieceDeTetris piecePrec = tetris.getFantome();
        for (int i = 0; i < piecePrec.getlisteBlocs().size(); i++)
        {
            if (piecePrec.getBlocPosX(i) >= 0 && piecePrec.getBlocPosY(i) >= 0 && piecePrec.getBlocPosX(i) < NB_CASE_COLONE && piecePrec.getBlocPosY(i) < NB_CASE_LIGNE)
            {
                cases[piecePrec.getBlocPosX(i)][piecePrec.getBlocPosY(i)].setBackground(Color.BLACK);
            }
        }

        //afficher la piece courante
        for (int i = 0; i < tetris.getPieceCourante().getlisteBlocs().size(); i++)
        {
            cases[tetris.getPieceCourante().getlisteBlocs().get(i).getPosition().getX()][tetris.getPieceCourante().getlisteBlocs().get(i).getPosition().getY()].setOpaque(true);

            cases[tetris.getPieceCourante().getlisteBlocs().get(i).getPosition().getX()][tetris.getPieceCourante().getlisteBlocs().get(i).getPosition().getY()].setBackground(tetris.getPieceCourante().getlisteBlocs().get(i).getCouleur());

        }
    }

    public void afficherPiecesSuivantes(JPanel[][] casesuivante, int piece)
    {
        for (int i = 0; i < tetris.getPiecesSuivantes().get(piece).getlisteBlocs().size(); i++)
        {
            int k = tetris.getPiecesSuivantes().get(piece).getBlocPosY(0) - 1;
            int l = tetris.getPiecesSuivantes().get(piece).getBlocPosX(0);
            if (tetris.getPiecesSuivantes().get(piece).getClass() == PieceI.class)
            {
                k++;
            }
            casesuivante[tetris.getPiecesSuivantes().get(piece).getBlocPosX(i) - l][tetris.getPiecesSuivantes().get(piece).getBlocPosY(i) - k].setBackground(tetris.getPiecesSuivantes().get(piece).getBloc(i).getCouleur());
        }
    }

    public void rafraichirAffichage()
    {
        //effacer les cases
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
        //Afficher les pieces suivantes
        afficherPiecesSuivantes(casesuivante1, 0);
        afficherPiecesSuivantes(casesuivante2, 1);
        afficherPiecesSuivantes(casesuivante3, 2);
        afficherPiecesSuivantes(casesuivante4, 3);
        
        score.setText( Integer.toString(tetris.getScore().getPoint()));
        niveau.setText(Integer.toString(tetris.getScore().getNiveau()));
    }

    public void creerPieceSuivantes(JPanel[][] casesuivante, JPanel parent, Border bordure, Dimension dim)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)

            {
                casesuivante[i][j] = new JPanel();
                casesuivante[i][j].setBackground(Color.BLACK);
                casesuivante[i][j].setBorder(bordure);
                GridBagConstraints g = new GridBagConstraints();
                casesuivante[i][j].setPreferredSize(dim);
                if ((i * 4 + j + 1) % 4 == 0)
                {
                    g.gridwidth = GridBagConstraints.REMAINDER;
                }

                parent.add(casesuivante[i][j], g);
            }
        }
    }

   
}
