import SecondLab.MainProgram;
import ThirdLab.*;



public class Main {


    public static void main(String[] args) {

        WorkWithConsole console = new WorkWithConsole();
        var result = console.UserInterface();

        var path = result.get("logFilePath");
        if (path.equals("")) {   path = WorkWithFile.defaultLogPath; }

        WorkWithFile file_printer = new WorkWithFile(path);
        Watcher watcherWhoWriteInFile = new Watcher(file_printer);
        file_printer.LogEvent(result.get("dialogBeforeLogOpen"));

        console.addObserver(watcherWhoWriteInFile);

        Watcher watcherWhoWrtieInConsole = new Watcher(console);

        MainProgram sum = new MainProgram(watcherWhoWrtieInConsole);

        var arr = result.get("array").split(" ");
        sum.process_sum(arr);
        console.OutputInConsole("Результат работы по поиску суммы: " + sum.GetResult());

        file_printer.EndWork();
    }
}