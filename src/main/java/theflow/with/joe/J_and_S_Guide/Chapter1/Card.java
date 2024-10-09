package theflow.with.joe.J_and_S_Guide.Chapter1;

// This was created by me just to practice creating a more complex enum
public enum Card {

    DIAMOND(1, "diamond") {
        public String blackOrRed(){ return "red";}
        public String colour() { return "red"; }
    }, HEART(2, "heart") {
        @Override       // Can use @Override or not
        public String blackOrRed() {
            return "red";
        }
        @Override
        public String colour() {
            return "red";
        }
    }, SPADE(2, "spade") {
        @Override
        public String blackOrRed() {
            return "black";
        }
        @Override
        public String colour() {
            return super.colour();  // NOTE you can refer to the overridden super colour() method
        }
    }, CLUB(1, "club") {
        @Override
        public String blackOrRed() {
            return "black";
        }
        // NOTE you don't need to override colour() if you are happy with the default
    };  // NOTE the semi-colon is essential here

    public final int value; // Can mark as final
    private String suit;    // NOTE that even though set to PRIVATE, it is actually PUBLIC

    // PRIVATE Constructor
    Card(int value, String suit) { // NOTE implicitly or explicitly private, but CANNOT be marked as public, protected
        this.value = value;
        this.suit = suit;
    }

    // ABSTRACT method must be overridden by every enum value
    public abstract String blackOrRed();

    // DEFAULT method
    public String colour() {
        return "black";
    }

    // GETTER for private suit
    public String getSuit() {
        return this.suit;
    }

    public static void main(String[] args) {
        for(Card card : Card.values()) {
            System.out.println("Card name: " + card.name() +
                    ", Card.DIAMOND: " + Card.DIAMOND +      // specify the Card.name()
                    ", Card.values()" + Card.values() +
                    ", Card.valueOf(card.name()):" + Card.valueOf(card.name()) +
                    ", Card ordinal: " + card.ordinal() +
                    ", Card value: " + card);    // NOTE that this gives the same as the .name() as there are 2 values
                                                // if was only one value, e.g. number, then number would be printed
        }
        // Produces:
//        Card name: DIAMOND, Card.DIAMOND: DIAMOND, Card.values()[Ltheflow.with.joe.J_and_S_Guide.Chapter1.Card;@75b84c92,
//        Card.valueOf(card.name()):DIAMOND, Card ordinal: 0, Card value: DIAMOND
//        Card name: HEART, Card.DIAMOND: DIAMOND, Card.values()[Ltheflow.with.joe.J_and_S_Guide.Chapter1.Card;@6bc7c054,
//        Card.valueOf(card.name()):HEART, Card ordinal: 1, Card value: HEART
//        Card name: SPADE, Card.DIAMOND: DIAMOND, Card.values()[Ltheflow.with.joe.J_and_S_Guide.Chapter1.Card;@232204a1,
//        Card.valueOf(card.name()):SPADE, Card ordinal: 2, Card value: SPADE
//        Card name: CLUB, Card.DIAMOND: DIAMOND, Card.values()[Ltheflow.with.joe.J_and_S_Guide.Chapter1.Card;@4aa298b7,
//        Card.valueOf(card.name()):CLUB, Card ordinal: 3, Card value: CLUB

        for(Card card : Card.values()) {
            System.out.println("Card name: " + card.name() +
                    ", Card ordinal: " + card.ordinal() +
                    ", Card value: " + card.value +  // Print the public 'value' variable
                    ", Card suit: " + card.suit + // NOTE that even though set to PRIVATE, it is actually PUBLIC
                    ", Card suit: " + card.getSuit() +
                    ", Card blackOrRed: " + card.blackOrRed() +
                    ", Card colour: " + card.colour());
        }
        // Produces:
//        Card name: DIAMOND, Card ordinal: 0, Card value: 1, Card suit: diamond, Card suit: diamond, Card blackOrRed: red, Card colour: red
//        Card name: HEART, Card ordinal: 1, Card value: 2, Card suit: heart, Card suit: heart, Card blackOrRed: red, Card colour: red
//        Card name: SPADE, Card ordinal: 2, Card value: 2, Card suit: spade, Card suit: spade, Card blackOrRed: black, Card colour: black
//        Card name: CLUB, Card ordinal: 3, Card value: 1, Card suit: club, Card suit: club, Card blackOrRed: black, Card colour: black

        System.out.println("Card.HEART.blackOrRed(): " + Card.HEART.blackOrRed() +
                ", Card.HEART.colour: " + Card.HEART.colour() +
                ", Card.HEART.suit: " + Card.HEART.suit +
                ", Card.HEART.getSuit: " + Card.HEART.getSuit() +
                ", Card.HEART.ordinal(): " + Card.HEART.ordinal() +
                ", Card.HEART.value: " + Card.HEART.value +
                ", Card.HEART.name(): " + Card.HEART.name() +
                ", Card.HEART: " + Card.HEART);

        // Produces:
//        Card.HEART.blackOrRed(): red, Card.HEART.colour: red, Card.HEART.suit: heart, Card.HEART.getSuit: heart,
//        Card.HEART.ordinal(): 1, Card.HEART.value: 2, Card.HEART.name(): HEART, Card.HEART: HEART
    }
}


