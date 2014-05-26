/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris.Vue;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import tetris.Modele.JeuDeTetris;

/**
 *
 * @author leclerc
 */
public class FenetreAide extends JFrame
{
    
    private final int HAUTEUR_TOTAL = 700;
    private final int LARGEUR_TOTAL = 800;
    private final String aide;
 public FenetreAide()
    {
        super();
        aide = "Pour jouer ...\n qkjefbzv efjfuzrhguihzv zhzvu hz h uzhvuz h zofh ozuh zo zh ozu hzf of h\n pouet";
        
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
        JPanel fond = new JPanel();
        fond.setBackground(Color.BLACK);
        JTextArea text = new JTextArea(aide);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        fond.add(text);
        
        this.add(fond);
        
    }
}
