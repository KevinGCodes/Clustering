package Algorithms;

import Model.Point;

import java.util.*;

public interface ClusteringAlgorithm {
    Map<Point, List<Point>> cluster(int k);
    List<Point> getPoints();

    static double calculateVariance(Map<Point, List<Point>> clustering){
        double variance = 0;
        variance = clustering.values().stream().mapToDouble(arr -> {
            Point mean = calculateMean(arr);
            return arr.stream().mapToDouble(p -> (p.getDiff(mean)*p.getDiff(mean))).sum();
        }).sum();

        return variance;
    }

    static Point calculateMean(List<Point> points){
        if(points == null || points.size() == 0) return new Point(0, 0);
        double sumX = points.stream().mapToDouble(p -> p.getX()).sum();
        double sumY = points.stream().mapToDouble(p -> p.getY()).sum();
        return new Point(sumX/points.size(), sumY/points.size());
    }

    default Map<Point, List<Point>> cluster(){
        int len = getPoints().size();
        double[] variances = new double[len + 1];
        for(int k = 1; k <= len; k++){
            var clustering = cluster(k);
            variances[k] = ClusteringAlgorithm.calculateVariance(clustering);
        }
        int bestK = ElbowPointCalculator.findElbow(variances);
        return cluster(bestK);
    }

    default List<Point> getReferencePoints(int k){
        ArrayList<Point> referencePoints = new ArrayList<Point>();
        Random rand = new Random();

        for(int i = 0; i < k; i++){
            int index = rand.nextInt(getPoints().size());
            while(referencePoints.contains(getPoints().get(index))) index = rand.nextInt(getPoints().size());
            referencePoints.add(getPoints().get(index));
        }
        return referencePoints;
    }

    default HashMap<Point, List<Point>> Iteration(List<Point> referencePoints){
        int k = referencePoints.size();
        HashMap<Point, List<Point>> clustering = new HashMap<>();
        for(Point p : referencePoints) clustering.put(p, new ArrayList<>());
        for(Point p : getPoints()){
            double min = Double.MAX_VALUE;
            for(Point rp : referencePoints){
                double diff = p.getDiff(rp);
                if(diff < min){
                    min = diff;
                    for(List<Point> arr : clustering.values()) arr.remove(p);
                    clustering.get(rp).add(p);
                }
            }
        }
        return clustering;
    }
}
