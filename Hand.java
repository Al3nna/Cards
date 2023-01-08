/*Alenna - alenna.castaneda@oneidaihla.org
for CTE software development 1
instructor Mr. Gross*/
import java.util.ArrayList;
import java.util.List;

public class Hand extends Card {
    //creating attribute
    int handSize = 6;
    int playerId;
    String playerName;
    int gameOver; //creating "gameOver" attribute
    int round;
    int winIndex = 99;
    int index;
    int maxRounds;
    int pointGoal;
    int card = 0;
    int sum = 0;
    int sum1;

    //base constructor (custom game rules)
    public Hand(int maxRounds,int pointGoal) {
        gameOver = 0;
        round = 0;
        this.maxRounds = maxRounds;
        this.pointGoal = pointGoal;
    }
    
    //constructor if values are empty
    public Hand() {
        playerId = 0;
        playerName = "empty";
        cardIdNumber = 0;
        sequenceNumber = 0;
        court = "empty";
        suit = "empty";
    }

    //constructor for player info only
    public Hand(String playerName,int playerId) {
        this.playerName = playerName;
        this.playerId = playerId;
    }
    
    //constructor for card info only
    public Hand(int cardIdNumber,int sequenceNumber,String court,String suit) {
        this.cardIdNumber = cardIdNumber;
        this.sequenceNumber = sequenceNumber;
        this.court = court;
        this.suit = suit;
    }

    //putting card information in a string
    public String cardInfo() {
        //if card is not ace, jack, queen, or king
        if (sequenceNumber != 1 && sequenceNumber != 11 && sequenceNumber != 12 && sequenceNumber != 13) {
            //setting name
            name = (sequenceNumber+" of "+suit);
            fullId = (playerName+" "+name+", "+cardIdNumber);
            return (name);
            //if card is ace, jack, queen, or king
        } else {
            //setting name
            name = (court+" of "+suit);
            fullId = (playerName+" "+name+", "+cardIdNumber);
            return (name);
        }
    }

    //putting player information into string
    public String playerInfo() {
        return (playerName+", "+playerId);
    }
    
    //creating base object (game rules and operation)
    static Hand base = new Hand(5,66);

    //creating method to get base
    public static Hand getBase() {
        return base;
    }
    
    //getting deck, and discardPile arraylist
    List<Card> deck = Deck.getDeck();
    List<DiscardPile> discardPile = DiscardPile.getDiscardPile();

    //creating hand arraylist
    ArrayList<Card> hand = new ArrayList<>();
    //creating function to get Hand
    public List<Card> getHand() {return (hand);};

    //creating players arraylist
    private static ArrayList<Hand> players = new ArrayList<>();
    //creating function to get players
    public static List<Hand> getPlayers() {return (players);};

    //getting main deck and discard
    DiscardPile discard = DiscardPile.getDiscard();

    //dealing cards
    public void deal() {
        //dealing only if player has no cards
        if (hand.size() == 0) {
            System.out.println();
            //looping through hand
            for (int i = 0; i < handSize; i++) {
                //checking if deck is empty
                if (deck.size() == 0) {
                    //adding discarded cards to deck
                    discard.circulate();
                }
                //adding card to hand
                hand.add(new Hand(deck.get(i).cardIdNumber, deck.get(i).sequenceNumber, deck.get(i).court, deck.get(i).suit));
                //removing card from deck
                deck.remove(deck.get(i));
            }
            System.out.println();
            System.out.println(playerName+" was dealt "+handSize+" cards");
        }
    }

    //drawing cards
    public void draw() {
        System.out.println();
        System.out.println(playerName+" had to draw.");
        //if player needs to draw more than 1 card
        if (handSize - 1 != hand.size()) {
            System.out.println("cards drawn:");
            //looping until hand has correct number of cards
            for (int i = hand.size(); i < handSize; i++) {
                //checking if deck is empty
                if (deck.size() == 0) {
                    //adding discarded cards to deck
                    discard.circulate();
                }
                //adding card to hand
                hand.add(new Hand(deck.get(i).cardIdNumber, deck.get(i).sequenceNumber, deck.get(i).court, deck.get(i).suit));
                System.out.println(deck.get(i));
                //removing card from deck
                deck.remove(deck.get(i));
            }
        }
        //if player only needs to draw 1 card
        else {
            //checking if deck is empty
            if (deck.size() == 0) {
                //adding discarded cards to deck
                discard.circulate();
            }
            //adding card to hand
            hand.add(new Hand(deck.get(0).cardIdNumber, deck.get(0).sequenceNumber, deck.get(0).court, deck.get(0).suit));
            if (deck.get(0).court == "Ace") {
                System.out.println(playerName+" drew an "+deck.get(0));
            } else {
                System.out.println(playerName+" drew a "+deck.get(0));
            }
            //removing card from deck
            deck.remove(deck.get(0));
        }
    }

    //playinng turn
    public void turn() {
        //attributes for discard function
        int min = 0;

        //if hand.size does not have correct number of cards
        if (hand.size() != handSize) {
            draw();
        }

        //looping through hand
            for (int i = 1; i < hand.size(); i++) {
                //checking if next value is less than current "min"
                if (hand.get(i).sequenceNumber < hand.get(min).sequenceNumber) {
                    //setting min to the lesser value
                    min = i;
                    
                }

                //if loop has gone through the hand
                if (i == hand.size()-1) {
                    //discarding "min" card
                    discard(min, hand.get(min).cardIdNumber, hand.get(min).sequenceNumber, hand.get(min).court, hand.get(min).suit);
                    }
            }
            
    }

    //discarding cards
    public void discard(int min,int cardIdNumber,int sequenceNumber,String court,String suit) {
        //adding card to discard pile
        discardPile.add(new DiscardPile(cardIdNumber, sequenceNumber, court, suit));
        System.out.println();
        System.out.println();
        if (court == "Ace") {
            System.out.println(playerName+" discarded an "+discardPile.get(discardPile.size()-1));
        } else {
            System.out.println(playerName+" discarded a "+discardPile.get(discardPile.size()-1));
        }
        //removing card from hand
        hand.remove(hand.get(min));
        
        //running draw function
        draw();
    }
    
    //printing hand
    public void printHand() {
        System.out.println(playerName+"'s hand:");
        //looping through hand
        for (int i = 0; i < handSize; i++) {
            //if i is not the last card
            if (i != handSize-1) {
                System.out.print(hand.get(i)+", ");
                //if i is the last card
            } else {
                System.out.print("& "+hand.get(i));
            }
        }
    }

    //printing number of cards in hand
    public void printHandCardsNum() {
        System.out.println(playerName+" has "+hand.size()+" cards.");
    }

    //checking if anybody won
    public void winCheck(int index) {
        //checking if their hand is full
        if (players.get(index).hand.size() == handSize) {
            //looping through hand
            card = 0;
            sum = 0;
            for (int j = 0; j < players.get(index).hand.size(); j++) {
                card = players.get(index).hand.get(j).sequenceNumber;
                //adding card sequence numbers together
                sum += card;
            }
            //if player hits minimum winning card sum
            if (sum >= base.pointGoal) {
                base.gameOver = 1;
                winIndex = index;
                //running win function for winner
                win(winIndex, sum);
            }
        }
    }
    
    //printing winner(s)
    public void win(int winIndex,int sum) {
        //checking if their hand is full
        if (players.get(winIndex).hand.size() == handSize) {
            System.out.println();
            System.out.println("-------------------------------------------");
            System.out.println(players.get(winIndex).playerName+" won the game!");
            System.out.println(players.get(winIndex).playerName+"'s Points: "+sum+" of the "+base.pointGoal+" minimum points");
            System.out.println("-------------------------------------------");
            System.out.println("Winning Hand:");
            //looping through hand
            for (int i = 0; i < players.get(winIndex).hand.size(); i++) {
                //if i is not the last card
                if (i != handSize-1) {
                    System.out.print(players.get(winIndex).hand.get(i)+", ");
                    //if i is the last card
                } else {
                    System.out.print("& "+players.get(winIndex).hand.get(i));
                }
            }
            System.out.println();
            //base.rankPlayers();
        }
    }
    
    public void first() {
        int top = 0;

        for (int i = 0; i < players.size(); i++) {
            if (i >= 1) {
                if (players.get(i).sum > players.get(top).sum) {
                    top = i;
                }

                if (i == players.size() - 1) {
                    win(top, players.get(top).sum);
                }
            }
        }
    }
    
    //playing game
    public void play() {
        if (round <= maxRounds && base.gameOver == 0) {
            //dealing cards
            //looping through players
            for (int i = 0; i < players.size(); i++) {
                if (gameOver == 0) {
                    //checking if there are 0 cards in hand
                    if (players.get(i).hand.size() == 0 ) {
                        //dealing cards to player
                        players.get(i).deal();
                        //printing # of cards in hand
                        players.get(i).printHandCardsNum();
                        System.out.println();
                        //printing cards in hand
                        players.get(i).printHand();

                        //checking if current player won
                        players.get(i).winCheck(i);
                        
                        if (winIndex != 99) {
                            gameOver = 1;
                            //doesn't loop again because "i" is no longer inside the qualifications to loop
                            i = players.size();
                        }
                    }
                }
            }
            //making sure game is not over
            if (base.gameOver == 0) {
                //main.printDeckCardsNum();
                for (int i = 0; i < players.size(); i++) {
                    if (base.gameOver == 0) {
                        //running turn for current player
                        players.get(i).turn();
                        //checking if current player won
                        players.get(i).winCheck(i);
                        
                        //exiting loop if current player won
                        if (winIndex != 99) {
                            base.gameOver = 1;
                            //doesn't loop again because "i" is no longer inside the qualifications to loop
                            i = players.size();
                        }
                        if (i == players.size() - 1 && base.gameOver == 0) {
                            round++;
                            System.out.println("--------------------------");
                            System.out.println("Round "+round+" is over");
                            System.out.println("--------------------------");
                        }
                    }
                }
            }
        }
        if (round == maxRounds && base.gameOver == 0) {
            System.out.println();
            System.out.println("------------------------------------------------------------------------");
            System.out.println("This game has reached the set maximum rounds");
            System.out.println("No player reached the point goal so the player with the most points wins");
            first();       
            System.out.println("------------------------------------------------------------------------");
        }
    }
}