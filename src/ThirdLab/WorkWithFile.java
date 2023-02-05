package ThirdLab;

import java.io.*;
import java.util.*;


public class WorkWithFile extends Observable implements ILogger {
    private String _path = "";
    private File file;
    private File log;
    public void setPath(String path)
    {
        if (!Objects.equals(_path, ""))
        {
            setChanged(); notifyObservers("Путь к файлу изменился, вместо " + _path + " стал: " + path);
        }
        _path = path;
    }
    public String getPath() {return _path; }

    public WorkWithFile(String path)
    {
        _path = path; prepareFile(path);
        openLogFile();
    }


    public String getData()
    {
        //Чтение из файла -------------------------------------------------------
        StringBuilder sb = new StringBuilder();
        if(file.exists()){
            try{
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                try{
                    String s;
                    while((s = br.readLine())!=null){//построчное чтение
                        sb.append(s);
                        sb.append("\n");
                    }
                }finally{br.close();}
            }catch(IOException e){throw new RuntimeException();}
        }
        return (sb.toString());//в sb.toString() хранится текст файла
    }
    public String getData(String path)
    {
        _path = path; prepareFile(_path);
        return getData();
    }


    public void writeData(String data)
    {
        try{
            PrintWriter pw = new PrintWriter(file.getAbsoluteFile());
            try{
                pw.println(data);
            }finally{pw.close();}
        }catch(IOException e){throw new RuntimeException();}
    }


    public void writeData(String data, String path) {
        _path = path; prepareFile(_path);
        writeData(data);
    }


    private void prepareFile(String path)
    {
        setPath(path);
        file = new File(path);
        try{
            if(!file.exists()) file.createNewFile();
        }catch(IOException e){throw new RuntimeException();}
    }
    private void openLogFile()
    {
        String logPath = defaultLogPath;;
        if(file.exists()){
            try{
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                try
                {
                    String s;
                    while((s = br.readLine())!=null){//построчное чтение
                        if (s.contains("LogFilePath:"))
                        {
                            logPath = s.replace("LogFilePath:","");
                            br.close(); break;
                        }
                    }
                }
                finally{br.close();}
            }catch(IOException e){throw new RuntimeException();}
        }

        log = new File(logPath);
        try{
            if(!log.exists()) log.createNewFile();
        }catch(IOException e){throw new RuntimeException();}
    }

    @Override
    public void logEvent(String data) {
        try{
            PrintWriter pw = new PrintWriter(log.getAbsoluteFile());
            try{
                pw.println(data);
            }finally{pw.close();}
        }catch(IOException e){throw new RuntimeException();}
    }
}
