import SecondLab.MainProgram;
import ThirdLab.*;



public class Main {
    public static void main(String[] args) {

        WorkWithConsole console = new WorkWithConsole();
        var path = console.getPathFromConsole();

        WorkWithFile reader = new WorkWithFile(path);
        Watcher watcherWhoWriteInFile = new Watcher(reader);

        console.addObserver(watcherWhoWriteInFile);

        Watcher watcherWhoWrtieInConsole = new Watcher(console);

        MainProgram sum = new MainProgram(watcherWhoWrtieInConsole);

        var arr = reader.getData().split(" ");
        for (String x : arr)
            console.outputInConsole(x);

        console.outputInConsole(" " + arr.length);
        if (arr.length > 1)
            sum.process_sum(arr);
    }
}