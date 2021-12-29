package tests;

import Model.Point;
import org.junit.Test;
import Model.KMeansClustering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class KMeansClusteringTests {

    private List<Point> sampleList = List.of(new Point(0.5f, 0.67f),
                                             new Point(0.33f, 0.54f),
                                             new Point(0.11f, 0.48f),
                                             new Point(0.66f, 0.98f),
                                             new Point(0.88f, 0.91f),
                                             new Point(0.42f, 0.33f),
                                             new Point(1, 0.44f));
    private List<Point> generateDataset(){
        return List.of(new Point((float)Math.random(), (float)Math.random()),
                                          new Point((float)Math.random(), (float)Math.random()),
                                          new Point((float)Math.random(), (float)Math.random()),
                                          new Point((float)Math.random(), (float)Math.random()));
    }

    private Map<Point, List<Point>> generateClustering(){
        List<Point> listOne = generateDataset();
        List<Point> listTwo = generateDataset();
        List<Point> listThree = generateDataset();
        List<Point> listFour = generateDataset();

        return Map.of(
                listOne.get(0), listOne,
                listTwo.get(1), listTwo,
                listThree.get(2), listThree,
                listFour.get(3), listFour
        );

    }


    @Test
    public void calculateMeanTest(){
        List<Point> dataset = generateDataset();
        KMeansClustering kmeans= new KMeansClustering(dataset);
        Point mean = kmeans.calculateMean(dataset);
        System.out.println(mean);
        double expectedX = dataset.get(0).getX() + dataset.get(1).getX() + dataset.get(2).getX() + dataset.get(3). getX();
        double expectedY = dataset.get(0).getY() + dataset.get(1).getY() + dataset.get(2).getY() + dataset.get(3). getY();
        System.out.println(expectedX/4);
        System.out.println(expectedY/4);
        assertEquals(mean.getX(), expectedX/4, 0.005 );
        assertEquals(mean.getY(), expectedY/4, 0.005);
    }
    @Test
    public void calculateVarianceTest(){
        Map<Point, List<Point>> mapping = generateClustering();
        double variance = KMeansClustering.calculateVariance(mapping);
        double expectedVariance = 0;
        for(List<Point> list : mapping.values()){
            Point mean = KMeansClustering.calculateMean(list);
            for(int i = 0; i < 4; i++) expectedVariance += list.get(i).getDiff(mean)* list.get(i).getDiff(mean);
        }
        System.out.print(variance + " + " + expectedVariance);
        assertEquals(variance, expectedVariance, 0.0005 );
    }

    @Test
    public void getReferencePointsTest(){
        KMeansClustering kmeans = new KMeansClustering(sampleList);
        int k = 7;
        var points = kmeans.getReferencePoints(k);
        assertEquals(points.size(), k);
        for(int i = 0; i < k - 1; i++){
            for(int j = i + 1; j < k; j++){
                assertFalse(points.get(i).equals(points.get(j)));
            }
        }
        System.out.println(points);
    }

    @Test
    public void IterationTest(){
        var dataset = generateDataset();
        System.out.println(dataset);
        KMeansClustering kmeans = new KMeansClustering(sampleList);
        int k = 2;
        var referencePoints = kmeans.getReferencePoints(k);
        System.out.println(referencePoints);
        var clustering = kmeans.cluster(2);
        System.out.println(clustering);
    }

}
