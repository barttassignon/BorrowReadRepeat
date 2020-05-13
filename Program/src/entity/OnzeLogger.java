/**
 * @Author Bart Tassignon
 */


package entity;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;


public class OnzeLogger {
    Logger OnzeLogger = Logger.getLogger(OnzeLogger.class.getName());
    FileHandler fh = null;

public OnzeLogger() {

    try {
        fh = new FileHandler("OnzeLog.log", true);

    } catch (SecurityException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    fh.setFormatter(new SimpleFormatter());
    OnzeLogger.addHandler(fh);

}

public void doLoggingInfo(String tekst){
    OnzeLogger.info(tekst);

}

public void doLoggingSevere(String tekst) {
    OnzeLogger.severe(tekst);
}
    public static void main(String[] args) throws IOException {

    }
}


