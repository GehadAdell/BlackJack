package blackjack;

import java.util.Random;
import java.util.Scanner;

public class Game {

    public Player[] players = new Player[4] ;
    public Card[] cardDeck = new Card[52] ;
    public int[] highScores = new int[4] ;

    public void generate_card()
    {
        int index = 0 ;
        for (int i = 0 ; i <= 3 ; i++) {
            for (int j = 0; j <= 12; j++) {
                if (j > 9)
                {
                    Card cards = new Card(i , j , 10) ;
                    cardDeck[index] = cards;
                    index++;
                }
                else
                {
                    Card cards = new Card(i , j , j + 1) ;
                    cardDeck[index] = cards;
                    index++;
                }
            }
        }
    }

    public Card drawn_Card()
    {
        Random random = new Random() ;
        Card card ;
        while (true)
        {
            int randomchoice = random.nextInt(52) ;
            card = cardDeck[randomchoice] ;
            if (card != null)
            {
                cardDeck[randomchoice] = null ;
                break ;
            }
        }
        return card;
    }

    public void set_information()
    {
        Scanner in = new Scanner(System.in) ;
        for (int i = 0 ; i < 3 ; i++)
        {
            System.out.print("Enter Name Of " + (i + 1) + " Player : ");
            players[i] = new Player();
            players[i].name = in.next();
            players[i].increasing_card(this.drawn_Card());
            players[i].increasing_card(this.drawn_Card());
        }
        for (int k = 3 ; k < 4 ; k++) {
            players[k] = new Player();
            players[k].name = "Dealer";
            players[k].increasing_card(this.drawn_Card());
            players[k].increasing_card(this.drawn_Card());
        }
    }

    public void update_score()
    {
        for (int i = 0 ; i < 4 ; i++)
        {
            if (players[i].score <= 21)
            {
                highScores[i] = players[i].score ;
            }
            else
            {
                highScores[i] = 0 ;
            }
        }
    }

}
