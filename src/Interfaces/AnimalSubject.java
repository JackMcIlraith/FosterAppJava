package Interfaces;

public interface AnimalSubject {

    public void addObserver(AnimalObservers observer);
    public void removeObserver(AnimalObservers observer);
    public void notifyObserver();

}
