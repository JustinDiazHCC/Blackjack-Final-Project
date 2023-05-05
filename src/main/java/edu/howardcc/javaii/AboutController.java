package edu.howardcc.javaii;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class AboutController {

    @FXML
    private Label aboutText;

    @FXML
    private Button backButton;

    @FXML
    private ScrollPane scrollBox;

    @FXML
    private Label sourcesLabel;

    @FXML
    private void backToTitle() throws IOException {
        App.setRoot("title");
    }
}