package edu.howardcc.javaii;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    // labels
    @FXML
    private Label nameLabel;
    @FXML
    private Label currentChipsLabel;
    @FXML
    private Label winConditionLabel;
    @FXML
    private Label handValuesLabel;
    @FXML
    private Label playerHandLabel;
    @FXML
    private Label dealerHandLabel;

    // Blackjack control buttons
    @FXML
    private Button resetChipsButton;
    @FXML
    private Button dealButton;
    @FXML
    private Button hitButton;
    @FXML
    private Button standButton;

    // player card image view objects
    @FXML
    private ImageView playerCard1;

    @FXML
    private ImageView playerCard2;

    @FXML
    private ImageView playerCard3;

    @FXML
    private ImageView playerCard4;

    @FXML
    private ImageView playerCard5;

    @FXML
    private ImageView playerCard6;

    @FXML
    private ImageView playerCard7;

    // dealer card image view objects
    @FXML
    private ImageView dealerCard1;

    @FXML
    private ImageView dealerCard2;

    @FXML
    private ImageView dealerCard3;

    @FXML
    private ImageView dealerCard4;

    @FXML
    private ImageView dealerCard5;

    @FXML
    private ImageView dealerCard6;

    @FXML
    private ImageView dealerCard7;

    // initialize method
    public void initialize() {
        currentChipsLabel.setVisible(false);
        dealButton.setVisible(false);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        playerHandLabel.setVisible(false);
        dealerHandLabel.setVisible(false);
        nameLabel.setText("Hi, " + BJLogic.player.getName() + "!");

        // set and display chips amount
        BJLogic.chips = 100;
        currentChipsLabel.setText("Chips: " + BJLogic.chips);
        currentChipsLabel.setVisible(true);
        dealButton.setVisible(true);
    }

    // quit back to title screen
    @FXML
    void quitToTitle(ActionEvent event) throws IOException {
        Blackjack.setRoot("title");
    }

    // reset chips back to default value of 150
    @FXML
    void resetChips(ActionEvent event) {
        // set chips value and set chips label text according to the value
        BJLogic.chips = 100;
        currentChipsLabel.setText("Chips: " + BJLogic.chips);
    }

    // Blackjack control methods
    @FXML
    void deal(ActionEvent event) {
        // clear all cards that may currently be on display
        // dealer cards
        dealerCard1.setImage(null);
        dealerCard2.setImage(null);
        dealerCard3.setImage(null);
        dealerCard4.setImage(null);
        dealerCard5.setImage(null);
        dealerCard6.setImage(null);
        dealerCard7.setImage(null);
        // player cards
        playerCard1.setImage(null);
        playerCard2.setImage(null);
        playerCard3.setImage(null);
        playerCard4.setImage(null);
        playerCard5.setImage(null);
        playerCard6.setImage(null);
        playerCard7.setImage(null);

        // hide deal button, win condition label, and reset chips button when round begins
        dealButton.setVisible(false);
        winConditionLabel.setVisible(false);
        resetChipsButton.setVisible(false);

        // redisplay hit and stand buttons now that round is beginning
        hitButton.setVisible(true);
        standButton.setVisible(true);

        // display dealer hand and player hand labels now that round is beginning
        playerHandLabel.setVisible(true);
        dealerHandLabel.setVisible(true);

        // deal cards
        BJLogic.deal();

        // update card table display
        updateCardDisplayDealerFaceDown();

        // display chip amount to reflect bet deduction
        currentChipsLabel.setText("Chips: " + BJLogic.chips);

        // display hand value amounts
        handValuesLabel.setText("Dealer hand value: ?\nYour hand value: " + BJLogic.getHandValue(BJLogic.player));

        // check for blackjacks
        BJLogic.checkBlackjack();

        // get results if round end condition is met
        if (BJLogic.roundEndConditionMet) {
            // update card table display
            updateCardDisplayRoundOver();
            getResult();
        }
    }

    @FXML
    void hit(ActionEvent event) {
        BJLogic.hit();

        // update card table display
        updateCardDisplayDealerFaceDown();

        // display hand value amounts
        handValuesLabel.setText("Dealer hand value: ?\nYour hand value: " + BJLogic.getHandValue(BJLogic.player));

        // get result if round end condition is met
        if (BJLogic.roundEndConditionMet) {
            // update card table display
            updateCardDisplayRoundOver();
            getResult();
        }
    }

    @FXML
    void stand(ActionEvent event) {
        BJLogic.stand();

        // update card table display
        updateCardDisplayRoundOver();

        // display hand value amounts
        handValuesLabel.setText("Dealer hand value: " + BJLogic.getHandValue(BJLogic.dealer)
                + "\nYour hand value: " + BJLogic.getHandValue(BJLogic.player));

        // get result if round end condition is met
        if (BJLogic.roundEndConditionMet) {
            // update card table display
            updateCardDisplayRoundOver();
            getResult();
        }
    }

    public void getResult() {
        // display hand value amounts
        handValuesLabel.setText("Dealer hand value: " + BJLogic.getHandValue(BJLogic.dealer)
                + "\nYour hand value: " + BJLogic.getHandValue(BJLogic.player));

        // call getResult method
        String result = BJLogic.getResult();

        // set win/lose/push label text based on round result
        switch (result) {
            case "win":
                if (BJLogic.isBlackjack) {
                    winConditionLabel.setText("Blackjack! So proud.");
                } else {
                    winConditionLabel.setText("You win! Play again?");
                }
                currentChipsLabel.setText("Chips: " + BJLogic.chips);
                break;
            case "lose":
                winConditionLabel.setText("You lose. Try again?");
                currentChipsLabel.setText("Chips: " + BJLogic.chips);
                break;
            case "push":
                winConditionLabel.setText("Push. One more hand?");
                currentChipsLabel.setText("Chips: " + BJLogic.chips);
                break;
            case "broke":
                winConditionLabel.setText("Too broke to play...");
                currentChipsLabel.setText("Chips: " + BJLogic.chips);
        }

        // hide hit and stand buttons
        hitButton.setVisible(false);
        standButton.setVisible(false);

        // display results
        dealButton.setVisible(true);
        winConditionLabel.setVisible(true);

        // redisplay reset chips button now that round has ended
        resetChipsButton.setVisible(true);
    }

    // display card pngs on virtual blackjack table (first dealer card face down)
    public void updateCardDisplayDealerFaceDown() {
        // dealer only has two cards before round over, no loop needed
        dealerCard1.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/card-back.png"))));
        dealerCard2.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.dealer.getHand().getCards().get(1).toString() + ".png"))));

        // display the two guaranteed cards in player hand
        playerCard1.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(0).toString() + ".png"))));
        playerCard2.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(1).toString() + ".png"))));

        // display any other player cards up through a potential seven if they exist
        if (BJLogic.player.getHand().getCards().size() > 2) {
            playerCard3.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(2).toString() + ".png"))));
        }
        if (BJLogic.player.getHand().getCards().size() > 3) {
            playerCard4.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(3).toString() + ".png"))));
        }
        if (BJLogic.player.getHand().getCards().size() > 4) {
            playerCard5.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(4).toString() + ".png"))));
        }
        if (BJLogic.player.getHand().getCards().size() > 5) {
            playerCard6.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(5).toString() + ".png"))));
        }
        if (BJLogic.player.getHand().getCards().size() > 6) {
            playerCard7.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(6).toString() + ".png"))));
        }
    }

    // display card pngs on virtual blackjack table (when all cards are revealed)
    public void updateCardDisplayRoundOver() {
        // display the two guaranteed cards in dealer hand
        dealerCard1.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.dealer.getHand().getCards().get(0).toString() + ".png"))));
        dealerCard2.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.dealer.getHand().getCards().get(1).toString() + ".png"))));

        // display any other dealer cards up through a potential seven if they exist
        if (BJLogic.dealer.getHand().getCards().size() > 2) {
            dealerCard3.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.dealer.getHand().getCards().get(2).toString() + ".png"))));
        }
        if (BJLogic.dealer.getHand().getCards().size() > 3) {
            dealerCard4.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.dealer.getHand().getCards().get(3).toString() + ".png"))));
        }
        if (BJLogic.dealer.getHand().getCards().size() > 4) {
            dealerCard5.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.dealer.getHand().getCards().get(4).toString() + ".png"))));
        }
        if (BJLogic.dealer.getHand().getCards().size() > 5) {
            dealerCard6.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.dealer.getHand().getCards().get(5).toString() + ".png"))));
        }
        if (BJLogic.dealer.getHand().getCards().size() > 6) {
            dealerCard7.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.dealer.getHand().getCards().get(6).toString() + ".png"))));
        }

        // display the two guaranteed cards in player hand
        playerCard1.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(0).toString() + ".png"))));
        playerCard2.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(1).toString() + ".png"))));

        // display any other player cards up through a potential seven if they exist
        if (BJLogic.player.getHand().getCards().size() > 2) {
            playerCard3.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(2).toString() + ".png"))));
        }
        if (BJLogic.player.getHand().getCards().size() > 3) {
            playerCard4.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(3).toString() + ".png"))));
        }
        if (BJLogic.player.getHand().getCards().size() > 4) {
            playerCard5.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(4).toString() + ".png"))));
        }
        if (BJLogic.player.getHand().getCards().size() > 5) {
            playerCard6.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(5).toString() + ".png"))));
        }
        if (BJLogic.player.getHand().getCards().size() > 6) {
            playerCard7.setImage(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("playing-cards/" + BJLogic.player.getHand().getCards().get(6).toString() + ".png"))));
        }
    }
}
