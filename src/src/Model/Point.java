package Model;

import java.util.Objects;

public class Point {
    private double x;
    private double y;
    private int col;

    public Point(double x, double y, int col){
        this.col = col;
        this.x = x;
        this.y = y;
    }

    public Point(float x, float y){
        this.col = 1;
        this.x = x;
        this.y = y;
    }
    public int getCol(){
        return this.col;
    }

    public double getDiff(Point other){
        double xDiff = this.x - other.x;
        double yDiff = this.y - other.y;
        return (float)Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }

    public double getX() {
        return x;
    }

    public double getY(){
        return y;
    }

    public void setCol(int n){
        this.col = n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0 && Objects.equals(col, point.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, col);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", col='" + col + '\'' +
                '}';
    }
}
