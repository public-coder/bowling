package bowlings;

import java.util.ArrayList;
import java.util.List;

public class Bowling 
{

    public static void main(String[] args) 
    {
        String text =args[0];
//        String text = "X7/9-X-88/-6XXX81";
//        String text = "XXXXXXXXXXXX";
//        String text = "9-9-9-9-9-9-9-9-9-9-";
//        String text = "5/5/5/5/5/5/5/5/5/5/5";


        // initialize rolls
        List<Integer> rolls = new ArrayList<>(30);
        for (int i = 0; i < 22; i++) {
            rolls.add(new Integer(0));
        }

        List<Turn> games = new ArrayList<>(11);

        char    STRIKE = 'X';
        char    SPARE = '/';
        char    MISS = '-';
        int     rollsIndex = 0;
        boolean first = true;
        Turn    turn = null;
        int lastStrike = 10;    //initial original value
        
        for (int i = 0; i < text.length() && lastStrike >0; i++) 
        {
            if (games.size()>= 10 && lastStrike != 10)
            {
                --lastStrike ;
            }
            char a = text.charAt(i);

            if (a == STRIKE) 
            {
                rolls.set(rollsIndex, 10);
                
                if (games.size() <10)
                {    
                    turn = new Turn(10, ScoreType.strike, rolls, rollsIndex +1);
                    games.add(turn);
     
                    first = true;      
                    if (games.size() == 9)
                    {
                        lastStrike = 2;
                    }
                }
                ++rollsIndex;
                
            }

            else if (a == SPARE) 
            {
                
                int thisRoll = 10 - rolls.get(rollsIndex -1);
                rolls.set(rollsIndex, thisRoll);
                
                if (games.size() < 10)
                {
                    turn = new Turn(10, ScoreType.spare, rolls, rollsIndex +1);
                    games.add(turn);
                    first = true;
                    if (games.size() == 10)
                    {
                        lastStrike = 1;
                    }
                }
                ++rollsIndex;
            }

            else if (isDigit(a))
            {
                int currentScore =  Integer.parseInt(""+a);
                rolls.set(rollsIndex, currentScore);
                
                // knock out normal logic after 10 turns
                if (games.size() >= 10)
                {
                    ++rollsIndex;
                    continue;
                }
                
                if (first)
                {
                    first = false;
                }
                else
                {

                    int total2 = rolls.get(rollsIndex)+ rolls.get(rollsIndex-1);

                    turn = new Turn(total2, ScoreType.regular);

                    games.add(turn);

                    first = true;

                    if (games.size() == 10)
                    {
                        lastStrike = 0;
                    }
                }
                
                ++rollsIndex;
            }
            else if (a == MISS)
            {
                if (games.size() >= 10)
                {
                    ++rollsIndex;
                    continue;
                }
                if (first)
                {

                    first = false;
                }
                else
                {

                    int total2 = rolls .get(rollsIndex-1);

                    turn = new Turn(total2, ScoreType.regular);
                    games.add(turn);

                    first = true;   
                    if (games.size() == 10)
                    {
                        lastStrike = 0;
                    }

                }
                ++rollsIndex;
            }
        
           
            if (games.size() >= 10 && lastStrike ==0)
                break;
        
        }// for
        
        System.out.println("After all");
        for (int j = 0; j < games.size(); j++) 
        {
            int k = j + 1;
            System.out.println("==== "+ k );

            System.out.println( "turn  "  + k +" " +games.get(j).getScore());
            games.get(j).toString();
            System.out.println("====\n");        

        }
        System.out.println("Total Score = " + totalScore(games));
    }
    public static boolean isDigit(char adigit) 
    {
        int a = (int)adigit;
        return a>= 48 && a <= 57;
    }
    
    public static int totalScore(List<Turn> turns)
    {
        int total = 0;
        for (Turn turn : turns) {
           total += turn.getScore();
        }
        return total;
    
    }
    
    
}
