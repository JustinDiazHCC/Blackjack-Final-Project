package edu.howardcc.javaii;

import edu.howardcc.javaii.jCards.*;

/**
 * Controls the Blackjack game logic
 */
public class BJLogic {
    // instance variables
    public static Player player;
    public static Player dealer = new Player("Dealer");
    public static int chips;
    public static Deck deck = new Deck();

    // game condition flags
    public static boolean roundEndConditionMet;
    public static boolean isBlackjack;
    public static boolean isDealerBlackjack;
    public static boolean isBust;
    public static boolean isDealerBust;
    public static boolean isTooBrokeToPlay;

    // begins a round of Blackjack
    public static void deal() {
        // instantiate a new deck
        deck = new Deck();

        // shuffle deck
        deck.shuffle();

        if (chips < 10) {
            isTooBrokeToPlay = true;
            getResult();
        } else {
            // deduct player bet from chip amount
            chips -= 10;

            // reset flags to false;
            roundEndConditionMet = false;
            isBlackjack = false;
            isDealerBlackjack = false;
            isBust = false;
            isDealerBust = false;
            isTooBrokeToPlay = false;

            // deal two cards to player and dealer
            player.addCard(deck.deal());
            dealer.addCard(deck.deal());
            player.addCard(deck.deal());
            dealer.addCard(deck.deal());

            /**
             * Print player and dealer hands
             */
            System.out.println("Action: deal");
            System.out.printf("Player hand: %s%nDealer hand: %s%n%n", player.getHand().toString(), dealer.getHand().toString());
            System.out.printf("Player hand value: %d%nDealer hand value: %d%n%n", getHandValue(player), getHandValue(dealer));
        }
    }

    // checks for player or dealer blackjack, sets flags accordingly
    public static void checkBlackjack() {
        // reset blackjack flags
        isBlackjack = false;
        isDealerBlackjack = false;

        // check player hand value
        int handValue = getHandValue(player);
        if (handValue == 21) {
            isBlackjack = true;
        }

        // check dealer hand value
        int dealerHandValue = getHandValue(dealer);
        if (dealerHandValue == 21) {
            isDealerBlackjack = true;
        }

        // automatically get results if player blackjack and not dealer blackjack
        if (isBlackjack && !isDealerBlackjack) {
            roundEndConditionMet = true;
        }
        // automatically get results if player and dealer blackjack
        if (isBlackjack && isDealerBlackjack) {
            roundEndConditionMet = true;
        }
        // automatically get results if dealer blackjack and not player blackjack
        if (isDealerBlackjack && !isBlackjack) {
            roundEndConditionMet = true;
        }
    }

    // adds a card to the player's hand
    public static void hit() {
        // deal player a card from the deck
        player.addCard(deck.deal());

        /**
         * Print player and dealer hands
         */
        System.out.println("Action: hit");
        System.out.printf("Player hand: %s%nDealer hand: %s%n%n", player.getHand().toString(), dealer.getHand().toString());
        System.out.printf("Player hand value: %d%nDealer hand value: %d%n%n", getHandValue(player), getHandValue(dealer));

        // check for player bust
        checkBust();
    }

    // checks for player bust (hand value exceeding 21)
    public static void checkBust() {
        // reset bust flag
        isBust = false;

        // check hand value
        int handValue = getHandValue(player);
        if (handValue > 21) {
            isBust = true;
            roundEndConditionMet = true;
        } else if (handValue == 21)
            // stand automatically is player hits to 21
            stand();
    }

    // after player stops drawing cards, the dealer then draws cards until they hit hard 17
    public static void stand() {
        // reset dealer bust flag
        isDealerBust = false;

        int dealerHandValue = getHandValue(dealer);
        while (dealerHandValue < 17) {
            // dealer draws cards until they have 17
            dealer.addCard(deck.deal());
            // get dealer hand value
            dealerHandValue = getHandValue(dealer);
        }
        if (dealerHandValue > 21) {
            isDealerBust = true;
        }
        roundEndConditionMet = true;

        /**
         * Print player and dealer hands
         */
        System.out.println("Action: stand");
        System.out.printf("Player hand: %s%nDealer hand: %s%n%n", player.getHand().toString(), dealer.getHand().toString());
        System.out.printf("Player hand value: %d%nDealer hand value: %d%n%n", getHandValue(player), getHandValue(dealer));
    }

    // determines round result and return win/lose/push string
    public static String getResult() {
        // first check if we got here because player is too broke to continue
        if (isTooBrokeToPlay) {
            return "broke";
        }

        // get player hand value
        int handValue = getHandValue(player);
        // get dealer hand value
        int dealerHandValue = getHandValue(dealer);

        // check win conditions
        // award player double their bet in chips if win, return bet if push, and do nothing if lose
        if (isBlackjack && !isDealerBlackjack) {
            chips += 25;
            clearHands();
            return "win";
        } else if (!isBlackjack && isDealerBlackjack) {
            clearHands();
            return "lose";
        } else if (isBlackjack && isDealerBlackjack) {
            chips += 10;
            clearHands();
            return "push";
        } else if (isBust) {
            clearHands();
            return "lose";
        } else if (isDealerBust) {
            chips += 20;
            clearHands();
            return "win";
        } else if (handValue > dealerHandValue) {
            chips += 20;
            clearHands();
            return "win";
        } else if (handValue < dealerHandValue) {
            clearHands();
            return "lose";
        } else {
            // hand values must be equal and non bust/blackjack if else condition is met
            chips += 10;
            clearHands();
            return "push";
        }
    }

    public static int getHandValue(Player player) {
        int handValue = 0;
        for (Card card : player.getHand().getCards()) {
            handValue = handValue + card.getRank().getValue();
        }
        // return handValue if 21 or under
        if (handValue <= 21) {
            return handValue;
        } else {
            // if hand value exceeds 21, iterate through cards to see if there is an ace in the hand
            for (Card card : player.getHand().getCards()) {
                if (card.getRank().getValue() == 11) {
                    // if there is an ace in the hand, change handValue from 11 to 1
                    handValue -= 10;
                    return handValue;
                }
            }
        }
        // otherwise, return busted hand value
        return handValue;
    }

    // clear user and dealer hands
    public static void clearHands() {
        player.getHand().getCards().clear();
        dealer.getHand().getCards().clear();
    }
}
