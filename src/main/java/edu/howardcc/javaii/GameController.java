package edu.howardcc.javaii;

import edu.howardcc.javaii.jCards.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameController {

    @FXML
    private Label nameLabel;

    @FXML
    private Button quitButton;

    @FXML
    private Button resetChipsButton;

    @FXML
    void setGreetingLabelText () {
        nameLabel.setText("Hi, " + BlackjackLogic.player.getName() + "!");
    }

    @FXML
    void quitToTitle(ActionEvent event) throws IOException {
        App.setRoot("title");
    }

    @FXML
    void resetChips(ActionEvent event) {
        BlackjackLogic.chips = 150;
    }

}
