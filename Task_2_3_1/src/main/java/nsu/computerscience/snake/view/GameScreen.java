package nsu.computerscience.snake.view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nsu.computerscience.snake.controller.GameController;

public class GameScreen extends Scene {
    private static final int CELL_WIDTH = 20;
    private static final int CELL_HEIGHT = 20;

    final GameController controller;

    final LabeledIntInput widthField;
    final LabeledIntInput heightField;
    final LabeledIntInput wallCount;
    final LabeledIntInput speedField;

    public GameScreen(Pane root, GameController controller) {
        super(root);

        this.controller = controller;
        addEventHandler(KeyEvent.KEY_PRESSED, controller);

        var rootHbox = new HBox();
        var canvas = new Canvas();
        var sideVbox = new VBox();

        var scoreLabel = new Text();
        scoreLabel.textProperty().bind(controller.score.asString());

        var gameOverVbox = new VBox();
        gameOverVbox.disableProperty().bind(controller.isGameOver.not());
        var gameOverLabel = new Text("Game over!");
        gameOverLabel.visibleProperty().bind(controller.isGameOver);

        var resetButton = new Button("Start");

        widthField = new LabeledIntInput("Width", 20);
        heightField = new LabeledIntInput("Height", 20);
        speedField = new LabeledIntInput("Speed", 1);
        wallCount = new LabeledIntInput("Walls", 20);

        resetButton.onActionProperty().setValue(event -> {
            controller.reset(widthField.valueProperty.get(), heightField.valueProperty.get(), wallCount.valueProperty.get(), speedField.valueProperty.get());
            canvas.setWidth(CELL_WIDTH * widthField.valueProperty.get());
            canvas.setHeight(CELL_HEIGHT * heightField.valueProperty.get());
            getWindow().sizeToScene();
        });


        rootHbox.getChildren().addAll(canvas, sideVbox);

        sideVbox.getChildren().addAll(scoreLabel, gameOverVbox);

        gameOverVbox.getChildren().addAll(gameOverLabel, widthField, heightField, speedField, wallCount, resetButton);

        root.getChildren().add(rootHbox);


        controller.field.addListener((observable, oldValue, field) -> {
            var width = field.get(0).size();
            var height = field.size();
            var context = canvas.getGraphicsContext2D();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    var cell = field.get(y).get(x);
                    Color color;
                    switch (cell) {
                        case EMPTY:
                            color = Color.BLACK;
                            break;
                        case FOOD:
                            color = Color.GREEN;
                            break;
                        case WALL:
                            color = Color.WHITE;
                            break;
                        case SNAKE:
                            color = Color.YELLOW;
                            break;
                        default:
                            throw new IllegalStateException();
                    }
                    context.setFill(color);
                    context.fillRect(CELL_WIDTH * x, CELL_HEIGHT * y, CELL_WIDTH, CELL_HEIGHT);
                }
            }
        });
    }
}
