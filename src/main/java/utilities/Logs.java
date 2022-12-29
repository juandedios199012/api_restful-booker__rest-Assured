package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {
    private final Logger log;

    public Logs() {
        log = LogManager.getLogger("AUTOMATION");
    }

    private void printSeparator() {
        log.info("------------------------------------------------------------------------------------------");
    }

    private void printNewLine() {
        log.info("");
    }

    public void startTest(String testName) {
        printSeparator();
        log.info("Test: " + testName);
        printSeparator();
    }

    public void endTest(String status) {
        printSeparator();
        log.info(status);
        printSeparator();
        printNewLine();
        printNewLine();
    }

    public void startSuite(String suiteName) {
        printNewLine();
        printSeparator();
        printSeparator();
        log.info("Beginning: " + suiteName);
        printSeparator();
        printSeparator();
        printNewLine();
    }

    public void info(String message) {
        log.info(message);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void error(String message) {
        log.error(message);
    }

    public void debug(String message) {
        log.debug(message);
    }
}
