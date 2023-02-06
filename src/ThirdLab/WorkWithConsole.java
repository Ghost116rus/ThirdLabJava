package ThirdLab;

import java.util.*;

public class WorkWithConsole extends Observable implements ILogger {
    private final Scanner sc;
    public WorkWithConsole()
    {
        sc = new Scanner(System.in);
    }
    public String getPathFromConsole()
    {
        System.out.print("Введите путь к файлу: ");
        String s1;
        s1 = sc.nextLine();

        return s1;
    }
    public void outputInConsole(Object obj)
    {
        setChanged();
        notifyObservers(obj);
        System.out.println(obj);
    }

    @Override
    public void logEvent(String data) {
        outputInConsole(data);
    }
}
