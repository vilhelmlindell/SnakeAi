package org.example;

import java.awt.*;

public class Main extends Java1 {
    private static final int CELL_SIZE = 10;
    private final int GRID_WIDTH = WIDTH / CELL_SIZE;
    private final int GRID_HEIGHT = HEIGHT / CELL_SIZE;
    
    private GameState gameState;
    
    public static void main(String[] args) {
        Main main = new Main();
        main.gameState = new GameState(main.GRID_WIDTH, main.GRID_HEIGHT);
        main.gameState.generateApple();
        main.initializeSnake();
        
        main.setDelay(100);
    }

    @Override
    protected void loop() {
        if (gameState.GameOver) {
            return;
        }
        
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
        
        gameState.update();
        
        clear();
        drawRectangle(gameState.Apple.X * CELL_SIZE, gameState.Apple.Y * CELL_SIZE, CELL_SIZE, CELL_SIZE, Color.RED);
        
        for (Point snakePart : gameState.SnakeParts) {
            drawRectangle(snakePart.X * CELL_SIZE, snakePart.Y * CELL_SIZE, CELL_SIZE, CELL_SIZE, Color.BLUE);
        }
    }
    
    private void initializeSnake() {
        gameState.SnakeParts.add(new Point(10, 10));
        gameState.SnakeParts.add(new Point(11, 10));
        gameState.SnakeDirection = Direction.EAST;
    }
}