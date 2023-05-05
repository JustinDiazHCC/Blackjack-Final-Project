package edu.howardcc.javaii;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class GameController {

    @FXML
    private Button quitButton;

    @FXML
    void quitToTitle(ActionEvent event) throws IOException {
        App.setRoot("title");
    }

}
