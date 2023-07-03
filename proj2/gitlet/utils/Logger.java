package gitlet.utils;

import gitlet.Config;

import java.io.IOException;

public class Logger {
    private String content = "";


    private Logger() throws IOException {
        if (!Config.LOG_FILE.exists()) {
            if (!Config.LOG_FILE.createNewFile()) {
                throw new IOException("Create logs file fail");
            }
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
        if (!Config.LOG_FILE.exists()) {
            if (!Config.LOG_FILE.createNewFile()) {
                throw new IOException("Create logs file fail");
            }
        }
        Utils.writeContents(Config.LOG_FILE, content);
    }

    public void readFromFile() {
        if (!Config.LOG_FILE.exists()) {
            System.err.println("Log file doesn't exist");
        }
        content = Utils.readContentsAsString(Config.LOG_FILE);
    }
}
