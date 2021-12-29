package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataSet implements Subject {
    private List<Observer> observers = new CopyOnWriteArrayList<>();
    private List<Point> data = new ArrayList<>();

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
        this.data.add(new Point(x, y));
        notifyObservers();
    }
}
