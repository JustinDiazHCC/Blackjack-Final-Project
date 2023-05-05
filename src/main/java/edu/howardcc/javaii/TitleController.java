package edu.howardcc.javaii;

import edu.howardcc.javaii.jCards.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class TitleController {

    @FXML
    private Button aboutButton;

    @FXML
    private ImageView blackjackIcon;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button playButton;

    @FXML
    private Label subtitle;

    @FXML
    private Label title;

    @FXML
    void startGame(ActionEvent event) throws IOException {
        if (!nameTextField.getText().equals("")) {
            BlackjackLogic.player = new Player(nameTextField.getText());
            App.setRoot("game");
        }
    }

    public void aboutPage(ActionEvent actionEvent) throws IOException {
        App.setRoot("about");
    }
}
