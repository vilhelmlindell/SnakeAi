package org.example;

public class Point {
    public int X;
    public int Y;
    
    public Point(int x, int y) {
        X = x;
        Y = y;
    }
    
    public void add(Point point) {
        X += point.X;
        Y += point.Y;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point otherPoint = (Point) obj;
        return X == otherPoint.X && Y == otherPoint.Y;
    } 
}
