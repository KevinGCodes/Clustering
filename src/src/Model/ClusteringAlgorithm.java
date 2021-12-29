package Model;

import java.util.List;
import java.util.Map;

public interface ClusteringAlgorithm {
    Map<Point, List<Point>> cluster();
}
