/*
De logger die we hier hebben aangemaakt gebruiken we in onze DatabaseSingleton om fouten te loggen.
Aangezien een logger niet vereist was voor ons project, ook niet als extra,
hebben we hem nergens ander in de code geimplementeerd maar het is dus wel mogelijk dit te doen.
*/

package entity;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

        //formatter voor de tekst in logfile
        fh.setFormatter(new SimpleFormatter());
        OnzeLogger.addHandler(fh);
    }

    //logger voor info log
    public void doLoggingInfo(String tekst) {
        OnzeLogger.info(tekst);
        // geen bericht in console voor info berichten
        OnzeLogger.setUseParentHandlers(false);
    }

    //logger voor warning log
    public void doLoggingWarning(String tekst) {
        OnzeLogger.warning(tekst);
    }

    //logger voor severe log
    public void doLoggingSevere(String tekst) {
        OnzeLogger.severe(tekst);
    }
}


