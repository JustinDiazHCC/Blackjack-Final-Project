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

    // Blackjack control buttons
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
        // make all elements visible if this is the first chip reset
        currentChipsLabel.setVisible(true);
        dealButton.setVisible(true);
        hitButton.setVisible(true);
        standButton.setVisible(true);

        BJLogic.chips = 150;
        currentChipsLabel.setText("Chips: " + BJLogic.chips);

    }

    // Blackjack control methods
    @FXML
    void deal(ActionEvent event) {

    }

    @FXML
    void hit(ActionEvent event) {

    }

    @FXML
    void stand(ActionEvent event) {

    }
}
