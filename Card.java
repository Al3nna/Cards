/*Alenna - alenna.castaneda@oneidaihla.org
for CTE software development 1
instructor Mr. Gross*/

public class Card {
    //creating attributes
    int cardIdNumber;
    int sequenceNumber;   // 1 to 13/
    String court;   // king, queen, jack, ace, basic
    String suit;   // diamonds, hearts, clubs, spades
    String name;
    String fullId;

    //constructor if no input variables
    public Card() {
        cardIdNumber = 0;
        sequenceNumber = 0;
        court = "empty";
        suit = "empty";
    }

    //constructor if given input variables
    public Card(int cardIdNumber,int sequenceNumber,String court,String suit) {
        this.cardIdNumber = cardIdNumber;
        this.sequenceNumber = sequenceNumber;
        this.court = court;
        this.suit = suit;
    }

    //putting attributes into a string
    public String toString() {
        if (sequenceNumber != 1 && sequenceNumber != 11 && sequenceNumber != 12 && sequenceNumber != 13) {
            //setting name
            name = (sequenceNumber+" of "+suit);
            //setting Id
            fullId = (name+", "+cardIdNumber);
            return (name);
        } else {
            //setting name
            name = (court+" of "+suit);
            //setting Id
            fullId = (name+", "+cardIdNumber);
            return (name);
        }
    }
}