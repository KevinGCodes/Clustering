package Model;

import io.CSVLoader;

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

    public void loadData(String fileName){
        String[][] data = CSVLoader.load(fileName);
        if(data == null) return;
        reset();
        double max = 0;
        for(String[] line : data){
            double x = Float.valueOf(line[0].strip());
            double y = Float.valueOf(line[1].strip());
            max = Math.max(max, x);
            max = Math.max(max, y);
        }
        for(String[] line : data){
            double x = Integer.valueOf(line[0].strip());
            double y = Integer.valueOf(line[1].strip());
            addPoint(x/max, y/max);
        }
        System.out.print(this.getData());
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

    public void addPoint(double x, double y){
        for(Point p : data)
            if(p.getX() == x && p.getY() == y) return;
        this.data.add(new Point(x, y));
        this.notifyObservers();
    }
    @Override
    public void reset(){
        this.data = new ArrayList<>();
        this.clusteringAlg = new KMeansClustering(this.data);
        this.notifyObservers();
    }
    @Override
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
