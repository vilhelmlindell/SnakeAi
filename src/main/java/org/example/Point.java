package org.example;

import java.util.Objects;

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
    public static Point add(Point a, Point b) {
        return new Point(a.X + b.X, a.Y + b.Y);
    }
    public static Point subtract(Point a, Point b) {
        return new Point(a.X - b.X, a.Y - b.Y);
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
    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    } 
}
