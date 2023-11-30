package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Java1 {
    public static final int CELL_SIZE = 30;
    public static final int STARTING_LENGTH = 3;
    private static final boolean PLAYER_CONTROLS = false;
    private final int GRID_WIDTH = WIDTH / CELL_SIZE;
    private final int GRID_HEIGHT = HEIGHT / CELL_SIZE;
    private final GameState gameState;
    private final SnakeAi snakeAi;

    private ArrayList<Direction> pathToApple;
    
    public static void main(String[] args) {
        Main main = new Main();
    }
    public Main() {
        gameState = new GameState(GRID_WIDTH, GRID_HEIGHT);
        gameState.generateApple();
        snakeAi = new SnakeAi(gameState);
        initializeSnake();
        if (!PLAYER_CONTROLS) {
            pathToApple = (ArrayList<Direction>) snakeAi.aStar(gameState.SnakeParts.getLast(), gameState.Apple);
        }
        setDelay(30);
    }

    @Override
    protected void loop() {
        clear();
        if (gameState.GameOver) {
            drawPath();
            return;
        }
        if (gameState.ReachedApple) {
            gameState.generateApple();
            if (!PLAYER_CONTROLS) {
                pathToApple = (ArrayList<Direction>) snakeAi.aStar(gameState.SnakeParts.getLast(), gameState.Apple);
            }
            gameState.ReachedApple = false;
        }
        
        if (PLAYER_CONTROLS) {
            if (keyUp && gameState.SnakeDirection != Direction.SOUTH) {
                gameState.SnakeDirection = Direction.NORTH;
            }
            if (keyDown && gameState.SnakeDirection != Direction.NORTH) {
                gameState.SnakeDirection = Direction.SOUTH;
            }
            if (keyLeft && gameState.SnakeDirection != Direction.EAST) {
                gameState.SnakeDirection = Direction.WEST;
            }
            if (keyRight && gameState.SnakeDirection != Direction.WEST) {
                gameState.SnakeDirection = Direction.EAST;
            }
        } else {
            gameState.SnakeDirection = pathToApple.getLast();
            pathToApple.removeLast();
            drawPath();
        }
        
        gameState.update();
        
        fillRectangle(gameState.Apple.X * CELL_SIZE, gameState.Apple.Y * CELL_SIZE, CELL_SIZE, CELL_SIZE, Color.RED);
        
        for (Point snakePart : gameState.SnakeParts) {
            fillRectangle(snakePart.X * CELL_SIZE, snakePart.Y * CELL_SIZE, CELL_SIZE, CELL_SIZE, Color.BLUE);
        }
    }
    
    private void initializeSnake() {
        Point head = new Point(GRID_WIDTH / 2, GRID_HEIGHT / 2);
        for (int i = STARTING_LENGTH; i > 0; i--) {
            Point bodyPart = Point.subtract(head, new Point(i, 0));
            gameState.SnakeParts.add(bodyPart);
            gameState.Grid[bodyPart.X][bodyPart.Y] = Cell.SNAKE;
        }
        gameState.Grid[head.X][head.Y] = Cell.HEAD;
        gameState.SnakeParts.add(head);
        gameState.SnakeDirection = Direction.EAST;
    }
    
    private void drawPath() {
        for (Point point : snakeAi.pathPoints) {
            drawRectangle(point.X * CELL_SIZE, point.Y * CELL_SIZE, CELL_SIZE, CELL_SIZE, Color.GREEN);
        }
    }
}