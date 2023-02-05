package ThirdLab;

import java.io.*;


public interface ILogger {
    String defaultLogPath = "log.txt";
    void logEvent(String data);
}
