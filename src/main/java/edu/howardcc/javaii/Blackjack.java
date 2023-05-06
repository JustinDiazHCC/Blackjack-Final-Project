package edu.howardcc.javaii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Blackjack.java
 * A JavaFX app that runs a GUI Blackjack game
 *
 * @author Justin Diaz
 * @version 1.0
 */
public class Blackjack extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("title"), 640, 480);
        stage.setTitle("Blackjack!");
        stage.getIcons().add(new Image(Objects.requireNonNull(Blackjack.class.getResourceAsStream("black-jack-icon.png"))));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Blackjack.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}