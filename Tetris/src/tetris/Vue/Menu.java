/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.Vue;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author leclerc
 */
public class Menu extends JMenuBar
{

    private JMenu menu; 
    private ButtonGroup choix;
    private JRadioButtonMenuItem sousmenu0;
    private JRadioButtonMenuItem sousmenu1;
    private JRadioButtonMenuItem sousmenu2;
    private JRadioButtonMenuItem sousmenu3;
    private JRadioButtonMenuItem sousmenu4;

    public Menu()
    {
        super();
        menu = new JMenu("Jeu");
        choix = new ButtonGroup();
        sousmenu0 = new JRadioButtonMenuItem("N'afficher aucune piece en avance");
        sousmenu1 = new JRadioButtonMenuItem("Afficher une piece en avance");
        sousmenu1.setSelected(true);
        sousmenu2 = new JRadioButtonMenuItem("Afficher deux piece en avance");
        sousmenu3 = new JRadioButtonMenuItem("Afficher trois piece en avance");
        sousmenu4 = new JRadioButtonMenuItem("Afficher quatre piece en avance");
        build();
    }

    public JMenu getMenu()
    {
        return menu;
    }

    public ButtonGroup getChoix()
    {
        return choix;
    }

    public JRadioButtonMenuItem getSousmenu0()
    {
        return sousmenu0;
    }

    public JRadioButtonMenuItem getSousmenu1()
    {
        return sousmenu1;
    }

    public JRadioButtonMenuItem getSousmenu2()
    {
        return sousmenu2;
    }

    public JRadioButtonMenuItem getSousmenu3()
    {
        return sousmenu3;
    }

    public JRadioButtonMenuItem getSousmenu4()
    {
        return sousmenu4;
    }

    
    
    public int getNombrePieceAfficher()
    {
        if(sousmenu0.isSelected())
            return 0;
        else if(sousmenu1.isSelected())
            return 1;
        else if(sousmenu2.isSelected())
            return 2;
        else if(sousmenu3.isSelected())
            return 3;
        else return 4;
    }



    private void build()
    {
        

        choix.add(sousmenu0);
        choix.add(sousmenu1);
        choix.add(sousmenu2);
        choix.add(sousmenu3);
        choix.add(sousmenu4);
        
        menu.add(sousmenu0);
        menu.add(sousmenu1);
        menu.add(sousmenu2);
        menu.add(sousmenu3);
        menu.add(sousmenu4);

        this.add(menu);
    }
}
