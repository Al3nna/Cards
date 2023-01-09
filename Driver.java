/*Alenna - alenna.castaneda@oneidaihla.org
for CTE software development 1
instructor Mr. Gross*/
import java.util.List;

public class Driver {
    
    public static void main(String[] args) {
        
        //getting players arraylist
        List<Hand> players = Hand.getPlayers();

        //getting main deck, base, and discard
        Deck main = Deck.getMainDeck();
        DiscardPile discard = DiscardPile.getDiscard();
        Hand base = Hand.getBase();

        //adding cards to deck and shuffling
        main.deckCreation(52);
        main.shuffle();     

        //creating players
        Hand Oreo = new Hand("Oreo", 1);
        Hand Mochi = new Hand("Mochi", 2);
        Hand Halia = new Hand("Halia", 3);
        Hand Daisy = new Hand("Daisy", 4);
        Hand Snuggles = new Hand("Snuggles", 5);

        //adding players to arraylist
        players.add(Oreo);
        players.add(Mochi);
        players.add(Halia);
        players.add(Daisy);
        players.add(Snuggles);

        //printing deck and discard pile card numbers
        main.printDeckCardsNum();
        discard.printDiscardPileCardsNum();

        //looping play function until game is over or max # of rounds has been reached
        // whichever comes first
        do {
            base.play();
        } while (base.gameOver == 0 && base.round < base.maxRounds);

        System.out.println();
        System.out.println();
        //printing deck and discard pile card numbers
        main.printDeckCardsNum();
        discard.printDiscardPileCardsNum();

        //printing total # cards players have
        
        int total = 0;
        //looping through players
        for (int i = 0; i < players.size(); i++) {
            int p = players.get(i).hand.size();
            //adding current player's # of cards to total
            total += p;
            //printing total after "players" has been looped through
            if (i == players.size() - 1) {
                System.out.println(total+" cards are being occupied by players.");
            }
        }
    }
}