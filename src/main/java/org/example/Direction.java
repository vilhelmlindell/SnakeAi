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
}
