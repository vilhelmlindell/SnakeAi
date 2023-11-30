package org.example;

public enum Direction {
    NORTH,
    SOUTH,
    WEST,
    EAST;
    
    public Point value() {
        switch (this) {
            case NORTH -> {
                return new Point(0, -1);
            }
            case SOUTH ->  {
                return new Point(0, 1);
            }
            case EAST -> {
                return new Point(1, 0);
            } 
            case WEST -> {
                return new Point(-1, 0);
            }
        }
        return new Point(0, 0);
    }
    public static Direction fromPoints(Point a, Point b) {
        Point difference = Point.subtract(a, b);
        if (difference.Y == -1) {
            return NORTH;
        }
        else if (difference.Y == 1) {
            return SOUTH;
        }
        else if (difference.X == -1) {
            return WEST;
        }
        else {
            return EAST;
        }
    }
    public static boolean areOpposite(Direction a, Direction b) {
        switch (a) {
            case NORTH -> {
                return b == SOUTH;
            }
            case SOUTH -> {
                return b == NORTH;
            }
            case WEST -> {
                return b == EAST;
            }
            case EAST -> {
                return b == WEST;
            }
        }
        return false;
    }
}
