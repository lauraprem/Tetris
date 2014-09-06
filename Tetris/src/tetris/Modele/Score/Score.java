package tetris.Modele.Score;

/**
 * Représente un <b>Score</b>
 *
 * @author Corinne && Laura Prémillieu
 */
public class Score {
    private int point;
    private int niveau;
    private int nbLigne;
    
    public Score()
    {
        point=0;
        niveau=1;
        nbLigne=0;
    }
    
    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    public int getNiveau()
    {
        return niveau;
    }

    public void setNiveau(int niveau)
    {
        this.niveau = niveau;
    }

    public int getNbLigne()
    {
        return nbLigne;
    }

    public void setNbLigne(int nbLigne)
    {
        this.nbLigne = nbLigne;
    }
    
     public void calculerScore(int nblignesup)
    {
        nbLigne += nblignesup;
        if(nbLigne % 10 == 0 && nbLigne !=0)
        {
            niveau++;
            nbLigne = 0;
        }
        
        switch (nblignesup)
        {
            case 1: point += 40*niveau;break;
            case 2 : point += 100*niveau;break;
            case 3 : point += 300*niveau;break;
            case 4 : point += 1200*niveau;break;
        }
        
        
    }

    @Override
    public String toString()
    {
        return "nbligne : " +nbLigne +"\n" + "points : " + point + "\nniveau : " + niveau +"\n\n";
    }
    
    
}
