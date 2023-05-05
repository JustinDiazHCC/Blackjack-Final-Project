package edu.howardcc.javaii;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class TitleController {

    @FXML
    private Button startButton;

    @FXML
    void startGame(ActionEvent event) throws IOException {
        App.setRoot("game");
    }

    public void aboutPage(ActionEvent actionEvent) throws IOException {
        App.setRoot("about");
    }
}
