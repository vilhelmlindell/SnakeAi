package org.example;

import java.util.ArrayDeque;
import java.util.Random;

public class GameState {
    public Cell[][] Grid;
    public ArrayDeque<Point> SnakeParts;
    public Point Apple;
    public Direction SnakeDirection;
    public boolean GameOver = false;
    private final Random random;
    
    public GameState(int gridWidth, int gridHeight) {
        Grid = new Cell[gridWidth][gridHeight];
        SnakeParts = new ArrayDeque<>();
        SnakeDirection = Direction.NORTH;
        random = new Random(System.currentTimeMillis());
    }
    
    public void update() {
        Point newHead = new Point(SnakeParts.getLast().X, SnakeParts.getLast().Y);
        newHead.add(SnakeDirection.value());
        
        boolean collidedWithSelf = SnakeParts.contains(newHead);
        boolean outsideGrid = newHead.X < 0 || newHead.Y < 0 || newHead.X > gridWidth() || newHead.Y > gridHeight();
        
        if (collidedWithSelf || outsideGrid) {
            GameOver = true;
            return;
        }
        
        SnakeParts.add(newHead);
        
        if (newHead.equals(Apple)) {
            generateApple();
        }
        else {
            SnakeParts.remove();
        }
    }
    
    public int gridWidth() {
        return Grid.length;
    }
    public int gridHeight() {
        return Grid[0].length;
    }
    
    public void generateApple() {
        if (Apple != null) {
            Apple = new Point(0, 0);
        }
        
        int randomInt = random.nextInt(gridWidth() * gridHeight());
        for (Point excludedPoint : SnakeParts) {
            int excludedInt = excludedPoint.X + excludedPoint.Y * gridWidth();
            if (randomInt < excludedInt) {
                break;
            }
            randomInt++;
        }
        Apple.X = randomInt % gridWidth();
        Apple.Y = randomInt / gridWidth();
    }
}