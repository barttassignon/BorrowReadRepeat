/**
 * @Author Bart Tassignon
 */


package entity;

import java.io.IOException;
import java.util.logging.*;

public class OnzeLogger {
    Logger OnzeLogger = Logger.getLogger(OnzeLogger.class.getName());
    FileHandler fh = null;

public OnzeLogger() {

    try {
        //schrijf log weg naar logfile
        fh = new FileHandler("OnzeLog.log", true);

    } catch (SecurityException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    //formatter voor de teks in logfile
    fh.setFormatter(new SimpleFormatter());
    OnzeLogger.addHandler(fh);

}

//logger voor info log
public void doLoggingInfo(String tekst){
    OnzeLogger.info(tekst);

}

//logger voor severe log
public void doLoggingSevere(String tekst) {
    OnzeLogger.severe(tekst);
}

}


