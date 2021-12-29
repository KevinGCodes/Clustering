package Model;

import Model.Observer;

public interface Subject {
    void attachObserver(Observer obs);
    void detachObserver(Observer obs);
    void notifyObservers();
}
