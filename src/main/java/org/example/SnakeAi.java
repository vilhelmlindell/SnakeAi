package org.example;

import java.util.*;
import java.util.List;

public class SnakeAi {
    public ArrayList<Point> pathPoints = new ArrayList<>();
    private final GameState gameState;
    
    public SnakeAi(GameState gameState) {
        this.gameState = gameState;
    }
    
    private List<Direction> reconstructPath(Map<Point, Point> cameFrom, Point current) {
        List<Direction> path = new ArrayList<>();
        pathPoints.clear();
        pathPoints.add(current);
        while (cameFrom.containsKey(current)) {
            Point previous = cameFrom.get(current);
            pathPoints.add(previous);
            path.add(Direction.fromPoints(current, previous));
            current = previous;
        }
        return path;
    }

    public List<Direction> aStar(Point start, Point goal) {
        Map<Point, Point> cameFrom = new HashMap<>();

        Map<Point, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        Map<Point, Integer> fScore = new HashMap<>();
        fScore.put(start, heuristic(start, goal));
        
        PriorityQueue<Point> openSet = new PriorityQueue<>(Comparator.comparingInt(point -> fScore.getOrDefault(point, Integer.MAX_VALUE)));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Point current = openSet.poll();
            
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            for (Direction direction : Direction.values()) {
                Point neighbor = Point.add(current, direction.value());
                
                if (gameState.pointOutsideGrid(neighbor)) {
                    continue;
                }
                
                boolean isSnakeOrHead = gameState.Grid[neighbor.X][neighbor.Y] == Cell.SNAKE || gameState.Grid[neighbor.X][neighbor.Y] == Cell.HEAD;
                
                if (isSnakeOrHead) {
                    continue;
                }
                
                int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;
                if (tentativeGScore <= gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, goal));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    private static int heuristic(Point start, Point end) {
        return Math.abs(start.X - end.X) + Math.abs(start.Y - end.Y);
    }
}


