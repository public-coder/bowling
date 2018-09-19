/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowlings;

import java.util.List;

/**
 *
 * @author web
 */
public class Turn 
{
    private ScoreType scoreType = ScoreType.regular;
    private List<Integer> rolls ;
    private int nextRollIndex = 0;
    private int ownScore = 0;
    private int total = getScore();
    private String text = "";
    
    public Turn(ScoreType type, List<Integer> scoreSeries) {
        this.scoreType = type;
        this.rolls = scoreSeries;
    }
 
    public Turn(int ownScore, ScoreType type, List<Integer> rolls, int nextRollIndex) {
        this.ownScore         = ownScore;
        this.nextRollIndex    = nextRollIndex;
        this.scoreType        = type;
        this.rolls            = rolls;
    }

    Turn(int total2, ScoreType scoreType) {
        this.ownScore   = total2;
        this.scoreType  = scoreType;
    }
 

    
    public int getScore() {
        
        
        if (scoreType.equals(ScoreType.strike))
        {
            total = ownScore + rolls.get(nextRollIndex) + rolls.get(nextRollIndex + 1);        
            text = "total "+ total + "("+ ownScore +" + " + rolls.get(nextRollIndex) + " + " + rolls.get(nextRollIndex + 1) + ")";        
        }
        
        
        else if (scoreType.equals(ScoreType.spare))
        {
            total = ownScore + rolls.get(nextRollIndex);
            text = "total "+ total + "("+ ownScore +" + " + rolls.get(nextRollIndex)  + ")";        
        }
        
        else {
            total = ownScore;
            text = "total "+ total + "("+ ownScore +") ";
        }
        return total; 
    }
    
    
    @Override
    public String toString() 
    {
        System.out.println(text);
        return text;
    }
    
    
    
}
