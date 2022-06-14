package nsu.computerscience.snake.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import nsu.computerscience.snake.model.Cell;
import nsu.computerscience.snake.model.Game;
import nsu.computerscience.snake.model.SnakeDirection;

import java.util.ArrayList;
import java.util.List;

public class GameController implements EventHandler<KeyEvent> {
    public final SimpleIntegerProperty score = new SimpleIntegerProperty(0);
    public final SimpleBooleanProperty isGameOver = new SimpleBooleanProperty(true);
    public final SimpleObjectProperty<List<List<Cell>>> field = new SimpleObjectProperty<>();
    Thread gameThread;
    Game game;
    int tickDelayMs;

    public void reset(int width, int height, int wallCount, float speed) {
        game = new Game(width, height, wallCount);
        tickDelayMs = (int) (1000.0 / speed);

        if (gameThread != null) {
            gameThread.interrupt();
        }
            gameThread = new Thread(this::runGame);
            gameThread.setDaemon(true);
            gameThread.start();
    }

    private void runGame() {
        while (!Thread.interrupted()) {
            game.tick();

            var field = game.getField();

            int score = game.getScore();
            boolean over = game.isGameOver();
            // This will run in UI thread
            // So we copy data for thread safety
            Platform.runLater(() -> {
                this.score.setValue(score);
                isGameOver.setValue(over);
                this.field.setValue(field);
            });
            try {
                Thread.sleep(100 + (tickDelayMs / (score + 1)));
                // we use interrupted exception to signal that new game hase begun
            } catch (InterruptedException ignored) {
                break;
            }
        }
    }

    @Override
    public void handle(KeyEvent event) {
        if (game == null) return;
        if (event.getEventType() != KeyEvent.KEY_PRESSED) return;
        switch (event.getCode()) {
            case A:
                game.setDirection(SnakeDirection.LEFT);
                break;
            case D:
                game.setDirection(SnakeDirection.RIGHT);
                break;
            case W:
                game.setDirection(SnakeDirection.UP);
                break;
            case S:
                game.setDirection(SnakeDirection.DOWN);
                break;
        }
    }
}
