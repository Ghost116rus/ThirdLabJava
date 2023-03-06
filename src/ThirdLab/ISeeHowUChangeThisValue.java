package ThirdLab;

import java.util.Observable;

public class ISeeHowUChangeThisValue extends Observable implements ILogger
{
    @Override
    public void LogEvent(String data) {
        setChanged();
        notifyObservers("Наблюдатель за переменной отработал: " + data);
    }
}
