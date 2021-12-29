package Model;

import Model.Observer;

import java.util.List;

public interface Subject {
    void attachObserver(Observer obs);
    void detachObserver(Observer obs);
    void notifyObservers();
    List<Point> getData();
    void addPoint(float x, float y);
}
