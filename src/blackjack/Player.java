package blackjack;

public class Player {

     public String name ;
     public int score = 0 ;
     private final Card[] cards = new Card[11] ;
     private int increase_card = 0 ;

     public void increasing_card(Card card_random)
     {
          if (increase_card < 11)
          {
               cards[increase_card] = card_random;
               increase_card++;
               score += card_random.getValue();
          }
     }

     public Card[] getCards() {
          return this.cards;
     }
}
