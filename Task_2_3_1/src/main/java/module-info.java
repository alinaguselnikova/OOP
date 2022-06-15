module nsu.computerscience.snake {
    requires javafx.controls;
    requires javafx.base;


    opens nsu.computerscience.snake to javafx.fxml;
    exports nsu.computerscience.snake;
}