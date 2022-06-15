package nsu.computerscience.snake.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import nsu.computerscience.snake.model.Cell;
import nsu.computerscience.snake.model.Game;
import nsu.computerscience.snake.model.SnakeDirection;

import java.util.List;

public class GameController implements EventHandler<KeyEvent> {
    public final SimpleIntegerProperty score = new SimpleIntegerProperty(0);
    public final SimpleBooleanProperty isGameOver = new SimpleBooleanProperty(true);
    public final SimpleObjectProperty<List<List<Cell>>> field = new SimpleObjectProperty<>();
    AnimationTimer gameLogicTimer;
    Game game;
    int tickDelayMs;

    public void reset(int width, int height, int wallCount, float speed) {
        if (gameLogicTimer != null) gameLogicTimer.stop();


        game = new Game(width, height, wallCount);
        tickDelayMs = (int) (1000.0 / speed);

        gameLogicTimer = new GameLogicTimer();
        gameLogicTimer.start();

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

    class GameLogicTimer extends AnimationTimer {
        long latestUpdate = 0;

        @Override
        public void handle(long now) {
            var delayMs = 100 + (tickDelayMs / (game.getScore() + 1));

            if (latestUpdate + delayMs * 1000000L > now) {
                return;
            }
            latestUpdate = now;

            game.tick();

            score.setValue(game.getScore());
            isGameOver.setValue(game.isGameOver());
            field.setValue(game.getField());
        }
    }
}
