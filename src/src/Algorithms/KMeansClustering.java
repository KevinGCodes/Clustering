package Algorithms;

import Model.Point;

import java.util.*;

public class KMeansClustering implements ClusteringAlgorithm {
    private List<Point> points;
    public KMeansClustering(List<Point> dataPoints){
        this.points = dataPoints;
    }

    @Override
    public Map<Point, List<Point>> cluster(int k){
        System.out.println("neeiiiin");
        k = Math.min(k, getPoints().size());
        List<Point> referencePoints = getReferencePoints(k);
        HashMap<Point, List<Point>> clustering = Iteration(referencePoints);
        for(int i = 0; i < 15; i++){
            List<Point> tmp = new ArrayList<>();
            clustering.values().forEach(arr -> tmp.add(ClusteringAlgorithm.calculateMean(arr)));
            referencePoints = tmp;
            clustering = Iteration(referencePoints);
        }
        double minVariance = ClusteringAlgorithm.calculateVariance(clustering);
        HashMap<Point, List<Point>> bestClustering = clustering;
        for(int i = 0; i< 100; i++){
            List<Point> newReferencePoints = getReferencePoints(k);
            HashMap<Point, List<Point>> newClustering = Iteration(newReferencePoints);
            for(int j = 0; j < 15; j++){
                List<Point> newTmp = new ArrayList<>();
                newClustering.values().forEach(arr -> newTmp.add(ClusteringAlgorithm.calculateMean(arr)));
                newReferencePoints = newTmp;
                newClustering = Iteration(newReferencePoints);
            }
            double newVariance = ClusteringAlgorithm.calculateVariance(newClustering);
            if(newVariance < minVariance){
                minVariance = newVariance;
                bestClustering = newClustering;
            }
        }
        return bestClustering;
    }

    @Override
    public List<Point> getPoints() {
        return this.points;
    }

}
