import java.util.*;


class beWatched extends Observable {//Класс наблюдаемого объекта
    void notifyObs() {
        setChanged();
        notifyObservers(32);
    }
} class Watcher implements Observer {//Класс обозревателя
    public void update(Observable obs, Object arg) {
        System.out.println ("received " + arg);
    }
}

public class Main {
    public static void main(String[] args) {
        Watcher w = new Watcher();//Создать объект приемника
        beWatched bW = new beWatched();//Создать объект источника
        bW.addObserver(w);
        bW.notifyObs();//Уведомить
    }
}