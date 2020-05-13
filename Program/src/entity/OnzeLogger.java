/**
 * @Author Bart Tassignon
 */


package entity;

import java.io.IOException;
import java.util.logging.*;


public class OnzeLogger {
    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(OnzeLogger.class.getName());


    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.INFO);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("myLogger.log");
            fh.setLevel(Level.FINE);
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            //ignore
            LOGGER.log(Level.SEVERE, "File logger werkt niet.", e);
        }
    }


    public static void main(String[] args) throws IOException {



    }
}


