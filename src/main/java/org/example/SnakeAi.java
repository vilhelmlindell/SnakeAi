package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SnakeAi {
    private final GameState gameState;
    
    public SnakeAi(GameState gameState) {
        this.gameState = gameState;
    }
    
    public ArrayList<Direction> PathfindToApple(Point snakeHead, Point apple) {
        PriorityQueue<Point> openSet = new PriorityQueue<>();
        openSet.add(snakeHead);
        
        for (Direction direction : Direction.values()) {
            Point point = 
                    snakeHead.add(direction.value());
        }
        
        HashMap<int, Direction> cameFrom = new HashMap<>();
        
        ArrayList fScore = new ArrayList();
        ArrayList gScore = new ArrayList();
        
        return new ArrayList<>();
    }
    public ArrayList(ArrayList cameFrom, )
    public int HeuristicValue(Point point) {
        return Math.abs(gameState.Apple.X - point.X) + Math.abs(gameState.Apple.Y - point.Y);
    }
}
