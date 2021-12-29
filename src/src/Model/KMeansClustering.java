package Model;

import java.util.*;
import java.util.stream.Collectors;

public class KMeansClustering implements ClusteringAlgorithm{
    private List<Point> points;
    public KMeansClustering(List<Point> dataPoints){
        this.points = dataPoints;
    }

    @Override
    public Map<Point, List<Point>> cluster(int k){
        List<Point> referencePoints = getReferencePoints(k);
        HashMap<Point, List<Point>> clustering = Iteration(referencePoints);
        for(int i = 0; i < 50; i++){
            List<Point> tmp = new ArrayList<>();
            clustering.values().forEach(arr -> tmp.add(calculateMean(arr)));
            referencePoints = tmp;
            clustering = Iteration(referencePoints);
        }
        float minVariance = calculateVariance(clustering);
        HashMap<Point, List<Point>> bestClustering = clustering;
        for(int i = 0; i< 100; i++){
            List<Point> newReferencePoints = getReferencePoints(k);
            HashMap<Point, List<Point>> newClustering = Iteration(referencePoints);
            for(int j = 0; j < 50; j++){
                List<Point> newTmp = new ArrayList<>();
                newClustering.values().forEach(arr -> newTmp.add(calculateMean(arr)));
                newReferencePoints = newTmp;
                newClustering = Iteration(newReferencePoints);
            }
            float newVariance = calculateVariance(newClustering);
            if(newVariance < minVariance){
                minVariance = newVariance;
                bestClustering = newClustering;
            }
        }
        return bestClustering;
    }

    public List<Point> getReferencePoints(int k){
        ArrayList<Point> referencePoints = new ArrayList<Point>();
        Random rand = new Random();

        for(int i = 0; i < k; i++){
            int index = rand.nextInt(points.size());
            while(referencePoints.contains(points.get(index))) index = rand.nextInt(points.size());
            referencePoints.add(points.get(index));
        }
        return referencePoints;
    }

    public static float calculateVariance(Map<Point, List<Point>> clustering){
        double variance = 0;
        variance = clustering.values().stream().mapToDouble(arr -> {
            Point mean = calculateMean(arr);
            return arr.stream().mapToDouble(p -> (double)(p.getDiff(mean)*p.getDiff(mean))).sum();
        }).sum();

        return (float) variance;
    }

    public static Point calculateMean(List<Point> points){
        if(points == null || points.size() == 0) return new Point(0, 0);
        double sumX = points.stream().mapToDouble(p -> (double)p.getX()).sum();
        double sumY = points.stream().mapToDouble(p -> (double)p.getY()).sum();
        return new Point((float)sumX/points.size(), (float)sumY/points.size());
    }

    public HashMap<Point, List<Point>> Iteration(List<Point> referencePoints){
        int k = referencePoints.size();
        HashMap<Point, List<Point>> clustering = new HashMap<>();
        for(Point p : referencePoints) clustering.put(p, new ArrayList<Point>());
        for(Point p : points){
            float min = Float.MAX_VALUE;
            for(Point rp : referencePoints){
                float diff = p.getDiff(rp);
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
