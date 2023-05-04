module edu.howardcc.javaii {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.howardcc.javaii to javafx.fxml;
    exports edu.howardcc.javaii;
}
