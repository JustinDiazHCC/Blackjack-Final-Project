package edu.howardcc.javaii;

import javafx.fxml.FXML;

import java.io.IOException;

public class AboutController {

    // back to title screen
    @FXML
    private void backToTitle() throws IOException {
        Blackjack.setRoot("title");
    }
}