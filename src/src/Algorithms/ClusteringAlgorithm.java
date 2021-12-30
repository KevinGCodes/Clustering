package Algorithms;

import Model.Point;

import java.util.List;
import java.util.Map;

public interface ClusteringAlgorithm {
    Map<Point, List<Point>> cluster();
}
