package org.example;

import java.util.ArrayDeque;
import java.util.Random;

public class GameState {
    public Cell[][] Grid;
    public ArrayDeque<Point> SnakeParts;
    public Point Apple;
    public Direction SnakeDirection;
    public boolean GameOver = false;
    public boolean ReachedApple = false;
    private final Random random;
    
    public GameState(int gridWidth, int gridHeight) {
        Grid = new Cell[gridWidth][gridHeight];
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                Grid[x][y] = Cell.EMPTY;
            }
        }
        SnakeParts = new ArrayDeque<>();
        SnakeDirection = Direction.NORTH;
        random = new Random(System.currentTimeMillis());
    }
    
    public void update() {
        Point newHead = new Point(SnakeParts.getLast().X, SnakeParts.getLast().Y);
        if (SnakeParts.size() > 1) {
            Grid[newHead.X][newHead.Y] = Cell.SNAKE;
        }
        newHead.add(SnakeDirection.value());
        
        boolean collidedWithSelf = Grid[newHead.X][newHead.Y] == Cell.SNAKE;
        
        if (collidedWithSelf || pointOutsideGrid(newHead)) {
            GameOver = true;
            return;
        }
        
        SnakeParts.add(newHead);
        Grid[newHead.X][newHead.Y] = Cell.HEAD;

        Point tail = SnakeParts.peek();
        assert tail != null;
        if (newHead.equals(Apple)) {
            ReachedApple = true;
            Grid[tail.X][tail.Y] = Cell.SNAKE;
        }
        else {
            Grid[tail.X][tail.Y] = Cell.EMPTY;
            SnakeParts.remove();
        }
    }
    
    public int gridWidth() {
        return Grid.length;
    }
    public int gridHeight() {
        return Grid[0].length;
    }
    public boolean pointOutsideGrid(Point point) {
        return point.X < 0 || point.Y < 0 || point.X >= gridWidth() || point.Y >= gridHeight();
    }
    
    public void generateApple() {
        if (Apple == null) {
            Apple = new Point(0, 0);
        }
        Grid[Apple.X][Apple.Y] = Cell.EMPTY;
        int randomInt = random.nextInt(gridWidth() * gridHeight() - SnakeParts.size());
        for (Point excludedPoint : SnakeParts) {
            int excludedInt = excludedPoint.X + excludedPoint.Y * gridWidth();
            if (randomInt < excludedInt) {
                break;
            }
            randomInt++;
        }
        int x = randomInt % gridWidth();
        int y = randomInt / gridWidth();
        Apple = new Point(x, y);
        Grid[x][y] = Cell.APPLE;
    }
}