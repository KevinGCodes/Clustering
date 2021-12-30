package Algorithms;

import Model.Point;

public class ElbowPointCalculator {
    public static int findElbow(double[] values){
        Point p = new Point(1, values[1]);
        double slope = (values[values.length - 1] - values[1])/(values.length - 2);
        double orthSlope = -1/slope;
        double magnitude = Math.sqrt(slope*slope + 1);
        Point normalVector = new Point(1/magnitude, orthSlope/magnitude);

        double max = 0;
        int best = 1;
        for(int k = 1; k < values.length; k++){
            Point diff = new Point(k - p.getX(), values[k] - p.getY());
            double dist = Math.abs(diff.getX()*normalVector.getX() + diff.getY()*normalVector.getY());
            if(dist > max){
                max = dist;
                best = k;
            }
        }
        return best;
    }
}
