package edu.howardcc.javaii;

import edu.howardcc.javaii.jCards.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TitleController {

    // name text field
    @FXML
    private TextField nameTextField;

    // to game screen
    @FXML
    void startGame(ActionEvent event) throws IOException {
        // only start if there is an entry in the name field
        if (!nameTextField.getText().equals("")) {
            // instantiate Player object
            BJLogic.player = new Player(nameTextField.getText());
            Blackjack.setRoot("game");
        }
    }

    // to about screen
    public void aboutPage(ActionEvent actionEvent) throws IOException {
        Blackjack.setRoot("about");
    }
}
