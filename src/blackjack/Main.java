package blackjack;

import java.util.Scanner;

public class Main {

    static Game game = new Game();

    public static void main(String[] args) {
	// write your code here
         GUI gui = new GUI() ;

        game.generate_card();

        game.set_information();

        gui.runGUI(game.cardDeck, game.players[0].getCards(), game.players[1].getCards(), game.players[2].getCards(), game.players[3].getCards());

         playersturn(gui);

         game.update_score();

         dealersTurn(gui);

         game.update_score();

         GameDetails();

    }

    public static void playersturn(GUI gui)
    {
        Scanner in = new Scanner(System.in) ;
        for (int i = 0 ; i < 3 ; i++)
        {
            int stat = 0 ;
            System.out.println("----------------------------------------------");
            while (stat != 2)
            {
                System.out.println("Current Score Of " + (i + 1) + " Player is : " + game.players[i].score);
                if (game.players[i].score == 21)
                {
                    System.out.println(game.players[i].name + " has BlackJack ");
                    break;
                }
                else if (game.players[i].score > 21)
                {
                    System.out.println(game.players[i].name + " Is Busted ");
                    break;
                }
                else {
                    System.out.println(game.players[i].name + " Turn ... Press 1 To Hit Or 2 To Stand : ");
                    stat = in.nextInt();
                }
                if(stat == 1)
                {
                        Card card = game.drawn_Card();
                        game.players[i].increasing_card(card);
                        gui.updatePlayerHand(card, i);

                }
            }
        }
    }

    public static void dealersTurn(GUI gui)
    {

        int dwins = 0 ;
        int highScore = 0 ;
        System.out.println("----------------------------------------------");
        System.out.println("Dealer's Turn ... ");
        System.out.println("Current Score of Dealer's is : " + game.players[3].score);
        for (int i = 0 ; i < 3 ; i++)
        {
            if (game.highScores[i] >= game.players[3].score)
            {
                dwins++ ;
            }
            if (game.highScores[i] > highScore)
            {
                highScore = game.highScores[i];
            }
        }
        if (dwins != 0)
        {
             while (game.players[3].score < highScore)
              {
                   Card card = game.drawn_Card();
                   game.players[3].increasing_card(card);
                   gui.updateDealerHand(card , game.cardDeck);
                   System.out.println("----------------------------------------------");
                   System.out.println("Dealer's Turn ... ");
                   System.out.println("Current Score of Dealer's is : " + game.players[3].score);
                   if (game.players[3].score > 21)
                   {
                       System.out.println(game.players[3].name + " Is Busted ");
                   }
                   else if (game.players[3].score == 21)
                   {
                       System.out.println(game.players[3].name + " has BlackJack ");
                   }
              }
        }
        else
        {
            return;
        }
    }

    public static void GameDetails()
    {
        int highScore = 0 ;
        int winner = -1 ;
        for (int i = 0 ; i < 4 ; i++)
        {
            if(game.highScores[i] > highScore)
            {
                highScore = game.highScores[i];
                winner = i ;
            }
            else if (game.highScores[i] == highScore)
            {
                winner = -3 ;
            }
        }
        if(winner >= 0)
        {
            System.out.println("***********************************************");
            System.out.println("Details Of The Game...");
            System.out.println(game.players[winner].name + " Is Winner has score : " + highScore);
        }
        else if (winner == -3)
        {
            System.out.println("***********************************************\n");
            System.out.println("The Game Is Push With  Score : " + highScore );
        }
    }

}








