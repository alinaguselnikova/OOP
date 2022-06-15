package nsu.computerscience.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    final int width;
    final int height;
    final int wallCount;
    final Cell[][] field;
    final Random random;
    final List<Pos> snake;
    int score;
    Pos snakeDirection;
    boolean gameOver;

    public Game(int width, int height, int wallCount) {
        this.width = width;
        this.height = height;
        this.wallCount = wallCount;

        snake = new ArrayList<>();

        random = new Random();

        field = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                field[y][x] = Cell.EMPTY;
            }
        }

        for (int i = 0; i < wallCount; i++) {
            setCell(getRandomFreeCell(), Cell.WALL);
        }

        snake.add(getRandomFreeCell());
        snakeDirection = new Pos(1, 0);
        gameOver = false;
        placeFood();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<List<Cell>> getField() {
        var field = new ArrayList<List<Cell>>(height);
        for (int y = 0; y < height; y++) {
            field.add(new ArrayList<>(width));
            for (int x = 0; x < width; x++) {
                field.get(y).add(this.field[y][x]);
            }
        }
        return field;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void tick() {
        if (gameOver) return;
        var nextPos = new Pos(snake.get(0).x + snakeDirection.x, snake.get(0).y + snakeDirection.y);
        if (nextPos.x < 0 || nextPos.x >= width || nextPos.y < 0 || nextPos.y >= height) {
            gameOver = true;
            return;
        }
        var nextCell = getCell(nextPos);
        if (nextCell == Cell.WALL || nextCell == Cell.SNAKE) {
            gameOver = true;
            return;
        }
        snake.add(0, nextPos);
        setCell(nextPos, Cell.SNAKE);
        if (nextCell != Cell.FOOD) {
            var lastCell = snake.remove(snake.size() - 1);
            setCell(lastCell, Cell.EMPTY);
        } else {
            score++;
        }

        if (nextCell == Cell.FOOD) {
            placeFood();
        }
    }

    public void setDirection(SnakeDirection direction) {
        switch (direction) {
            case UP:
                snakeDirection = new Pos(0, -1);
                break;
            case DOWN:
                snakeDirection = new Pos(0, 1);
                break;
            case LEFT:
                snakeDirection = new Pos(-1, 0);
                break;
            case RIGHT:
                snakeDirection = new Pos(1, 0);
                break;
        }
    }

    Cell getCell(Pos pos) {
        return field[pos.y][pos.x];
    }

    void setCell(Pos pos, Cell value) {
        field[pos.y][pos.x] = value;
    }

    void placeFood() {
        setCell(getRandomFreeCell(), Cell.FOOD);
    }

    private Pos getRandomFreeCell() {
        int x;
        int y;
        do {
            y = random.nextInt(height);
            x = random.nextInt(width);
        } while (field[y][x] != Cell.EMPTY);
        return new Pos(x, y);
    }

    static class Pos {
        final int x;
        final int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
