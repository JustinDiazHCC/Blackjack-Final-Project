package edu.howardcc.javaii;

import java.io.IOException;
import javafx.fxml.FXML;

public class AboutController {

    @FXML
    private void backToTitle() throws IOException {
        App.setRoot("title");
    }
}