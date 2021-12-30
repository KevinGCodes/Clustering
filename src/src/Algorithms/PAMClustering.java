package Algorithms;

import Model.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PAMClustering implements ClusteringAlgorithm{
    private List<Point> points;

    public PAMClustering(List<Point> points){
        this.points = points;
    }

    @Override
    public Map<Point, List<Point>> cluster(int k) {
        k = Math.min(k, getPoints().size());
        var referencePoints = getReferencePoints(k);
        var initialClustering = Iteration(referencePoints);
        var initialCost = ClusteringAlgorithm.calculateVariance(initialClustering);
        double allTimeMin = initialCost;
        var allTimeBestClustering = initialClustering;

        while(true){
            double min = Double.MAX_VALUE;
            HashMap<Point, List<Point>> currentBest = null;
            for (Point m : referencePoints) {
                for (Point o : points) {
                    if (referencePoints.contains(o)) continue;
                    var swappedPoints = new ArrayList<>(referencePoints);
                    swappedPoints.remove(m);
                    swappedPoints.add(o);
                    var newClustering = Iteration(swappedPoints);
                    var newCost = ClusteringAlgorithm.calculateVariance(newClustering);
                    if (newCost < min) {
                        min = newCost;
                        currentBest = newClustering;
                    }
                }
            }
            if(min < allTimeMin){
                referencePoints = currentBest.keySet().stream().collect(Collectors.toList());
                allTimeMin = min;
                allTimeBestClustering = currentBest;
            }else break;
        }
        return allTimeBestClustering;
    }

    @Override
    public List<Point> getPoints() {
        return this.points;
    }
}
