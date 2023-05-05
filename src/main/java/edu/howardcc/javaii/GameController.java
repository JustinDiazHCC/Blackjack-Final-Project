package edu.howardcc.javaii;

import edu.howardcc.javaii.jCards.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameController {

    // labels
    @FXML
    private Label nameLabel;
    @FXML
    private Label currentChipsLabel;
    @FXML
    private Label winConditionLabel;

    // Blackjack control buttons
    @FXML
    private Button resetChipsButton;
    @FXML
    private Button dealButton;
    @FXML
    private Button hitButton;
    @FXML
    private Button standButton;

    // initialize method
    public void initialize() {
        currentChipsLabel.setVisible(false);
        dealButton.setVisible(false);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        nameLabel.setText("Hi, " + BJLogic.player.getName() + "!");
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
        BJLogic.chips = 150;
        currentChipsLabel.setText("Chips: " + BJLogic.chips);

        // make currentChips visible if this is the first chip reset
        currentChipsLabel.setVisible(true);
        dealButton.setVisible(true);
    }

    // Blackjack control methods
    @FXML
    void deal(ActionEvent event) {
        // hide deal button, win condition label, and reset chips button when round begins
        dealButton.setVisible(false);
        winConditionLabel.setVisible(false);
        resetChipsButton.setVisible(false);

        // redisplay hit and stand buttons now that round is beginning
        hitButton.setVisible(true);
        standButton.setVisible(true);

        // deal cards
        BJLogic.deal();

        // display chip amount to reflect bet deduction
        currentChipsLabel.setText("Chips: " + BJLogic.chips);

        // check for blackjacks
        BJLogic.checkBlackjack();
        if (BJLogic.roundEndConditionMet) {
            getResult();
        }
    }

    @FXML
    void hit(ActionEvent event) {
        BJLogic.hit();
        if (BJLogic.roundEndConditionMet) {
            getResult();
        }
    }

    @FXML
    void stand(ActionEvent event) {
        BJLogic.stand();
        if (BJLogic.roundEndConditionMet) {
            getResult();
        }
    }

    public void getResult() {
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
}
