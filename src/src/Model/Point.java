package Model;

public class Point {
    private float x;
    private float y;
    private String col;

    public Point(float x, float y, String col){
        this.col = col;
        this.x = x;
        this.y = y;
    }

    public Point(float x, float y){
        this.col = "w";
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY(){
        return y;
    }
}
