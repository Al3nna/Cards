/*Alenna - alenna.castaneda@oneidaihla.org
for CTE software development 1
instructor Mr. Gross*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    //creating attribute
    int deckSize;

    //constructor if value is empty
    public Deck() {
        deckSize = 52;
    }
    
    //constructor if value is filled
    public Deck(int deckSize) {
        this.deckSize = deckSize;
    }
    
    //creating deck arraylist
    private static ArrayList<Card> deck = new ArrayList<>();
    //creating function to get deck
    public static List<Card> getDeck() {return (deck);};

    //creating main deck object
    static Deck main = new Deck();
    //creating function to get main deck
    public static Deck getMainDeck() {
        return main;
    }

    //creating new deck
    void deckCreation(int deckSize) {
        if (deckSize != 52) {
            System.out.println("invalid deck size, please do not include jokers");
        } else {
            //int d counts each new card in the deck starting from 0
            int id = 0;
            //creates sequence of 13 cards
            for (int sequenceNum = 0; sequenceNum < 13; sequenceNum++) {
                
                //making 4 sequences
                for (int i = 0; i < 4; i++) {
                    //creating new card
                    deck.add(new Card(id, sequenceNum+1, "basic", "suit"));

                    //repeating suit every 4 cards
                    if (i == 0) {
                        deck.get(id).suit = "diamonds";
                    } else if (i == 1) {
                        deck.get(id).suit = "hearts";
                    } else if (i == 2) {
                        deck.get(id).suit = "clubs";
                    } else if (i == 3) {
                        deck.get(id).suit = "spades";
                    }

                    //checking if sequence number = 1
                    if (deck.get(id).sequenceNumber == 1) {
                    //setting court to ace
                        deck.get(id).court = "Ace";
                    } else if (deck.get(id).sequenceNumber == 11) { //setting jack court
                        deck.get(id).court = "Jack";
                    } else if (deck.get(id).sequenceNumber == 12) { //setting queen court
                        deck.get(id).court = "Queen";
                    } else if (deck.get(id).sequenceNumber == 13) { //setting king court
                        deck.get(id).court = "King";
                    }
                    //recognising a card has been added
                    id++;
                }
            }        
        }
    }

    //printing card names in deck
    public void printDeck() {
        System.out.println("Current cards in the deck:");
        //looping through deck
        for (int i = 0; i < deck.size(); i++) {
            //if i is not the last card in the deck
            if (i != deck.size()-1) {
                System.out.print(deck.get(i)+", ");
                //if i is the last card in the deck
            } else {
                System.out.print(deck.get(i));
            }
        }
    }

    //shuffling deck
    public void shuffle() {
        Collections.shuffle(deck);
    }

    //printing number of cards in deck
    public void printDeckCardsNum() {
        System.out.println(deck.size()+" cards are left in the deck.");
    }
}
class DiscardPile extends Card{
    
    //constructor for card info
    public DiscardPile(int cardIdNumber,int sequenceNumber,String court,String suit) {
        this.cardIdNumber = cardIdNumber;
        this.sequenceNumber = sequenceNumber;
        this.court = court;
        this.suit = suit;
    }
    
    //constructor if values are empty
    public DiscardPile() {}

    //getting deck arraylist
    List<Card> deck = Deck.getDeck();

    //creating discard pile arraylist
    private static ArrayList<DiscardPile> discardPile = new ArrayList<>();
    //creating function to get discard pile
    public static ArrayList<DiscardPile> getDiscardPile() {return (discardPile);};

    //creating discard object
    static DiscardPile discard = new DiscardPile();
    //creating function to get discard
    public static DiscardPile getDiscard() {
        return discard;
    }

    //shuffling and transferring discard pile to main deck
    public void circulate() {
        //shuffling discard pile
        Collections.shuffle(discardPile);

        //looping through discard pile
        for (int i = 0; i < discardPile.size(); i++) {
            //adding card to deck
            deck.add(new Hand(discardPile.get(i).cardIdNumber, discardPile.get(i).sequenceNumber, discardPile.get(i).court, discardPile.get(i).suit));
            //removing card from discard pile
            discardPile.remove(discardPile.get(i));
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println("The discard pile has been shuffled and added back into the deck.");
        System.out.println("----------------------------------------------------------------");
        System.out.println();
    }

    //printing card names in discard pile
    public void printDiscardPile() {
        System.out.println("Current card(s) in the discard pile:");
        //looping through discard pile
        for (int i = 0; i < discardPile.size(); i++) {
            //if i is the last in the pile
            if (i == discardPile.size()-1) {
                System.out.println(discardPile.get(i));
                //if i is not the last in the pile
            } else {
                System.out.println(discardPile.get(i)+", ");
            }
        }
    }

    //printing number of cards in discard pile
    public void printDiscardPileCardsNum() {
        System.out.println(discardPile.size()+" cards are in the discard pile.");
    }
}