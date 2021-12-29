package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class DataSet implements Subject {
    private List<Observer> observers = new CopyOnWriteArrayList<>();
    private List<Point> data = new ArrayList<>();
    private ClusteringAlgorithm clusteringAlg;
    public int clusterCount = 1;

    public DataSet(){
        this.clusteringAlg = new KMeansClustering(this.data);
    }

    public DataSet(List<Point> dataset){
        data = dataset;
        this.clusteringAlg = new KMeansClustering(this.data);
    }


    public List<Point> getData(){
        return this.data;
    }
    @Override
    public void attachObserver(Observer obs) {
        if(obs == null) return;
        if(!observers.contains(obs)) observers.add(obs);
    }

    @Override
    public void detachObserver(Observer obs) {
        if(obs == null) return;
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for(Observer obs : this.observers){
            obs.updateObserver();
        }
    }

    public void addPoint(float x, float y){
        for(Point p : data)
            if(p.getX() == x && p.getY() == y) return;
        this.data.add(new Point(x, y));
        applyClustering();
        notifyObservers();
    }

    public void applyClustering(){
        var clustering = ((KMeansClustering)clusteringAlg).cluster(4);
        this.clusterCount = clustering.keySet().size();
        var lists = clustering.values().stream().collect(Collectors.toList());
        for(int i = 0; i < lists.size(); i++){
            for(int j = 0; j < lists.get(i).size(); j++){
                lists.get(i).get(j).setCol(i + 1);
            }
        }
        notifyObservers();
    }
}
