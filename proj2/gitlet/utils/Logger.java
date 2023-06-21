package gitlet.utils;

import java.io.File;
import java.io.IOException;

public class Logger {
    private String path = ".gitlet/logs";
    private String content = "";


    private Logger() throws IOException {
        File log = Utils.join(path);
        if (!log.exists()) {
            log.createNewFile();
        }
    }

    private static class LoggerHolder {
        private static final Logger instance = new Logger();
    }

    public static Logger getInstance() {
        return LoggerHolder.instance;
    }

    public void writeLog(String log) throws IOException {
        readFromFile();
        content = content + System.lineSeparator() + log;
        write2File();
    }

    private void write2File() throws IOException {
        File log = Utils.join(path);
        if (!log.exists()) {
            log.createNewFile();
        }
        Utils.writeContents(log, content);
    }

    public void readFromFile() {
        File log = Utils.join(path);
        if (!log.exists()) {
            System.err.println("Log file doesn't exist");
        }
        content = Utils.readContentsAsString(log);
    }
}
