package gitlet.utils;

import gitlet.Repository;

import java.io.File;
import java.io.IOException;

public class Logger {
    private String name = "logs";
    private String content = "";


    private Logger() throws IOException {
        File log = Utils.join(Repository.GITLET_DIR, name);
        if (!log.exists()) {
            log.createNewFile();
        }
    }

    private static class LoggerHolder {
        private static final Logger instance;

        static {
            try {
                instance = new Logger();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
        File log = Utils.join(Repository.GITLET_DIR, name);
        if (!log.exists()) {
            log.createNewFile();
        }
        Utils.writeContents(log, content);
    }

    public void readFromFile() {
        File log = Utils.join(Repository.GITLET_DIR, name);
        if (!log.exists()) {
            System.err.println("Log file doesn't exist");
        }
        content = Utils.readContentsAsString(log);
    }
}
