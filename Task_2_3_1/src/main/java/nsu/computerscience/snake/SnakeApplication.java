package nsu.computerscience.snake;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nsu.computerscience.snake.controller.GameController;
import nsu.computerscience.snake.view.GameScreen;

public class SnakeApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        var controller = new GameController();
        var gameScreen = new GameScreen(new StackPane(), controller);
        stage.setTitle("Snake");
        stage.setScene(gameScreen);
        stage.setResizable(false);
        stage.show();
    }
}