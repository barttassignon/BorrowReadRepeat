package entity;

/**
 * @author Katrien Persoons
 */

import java.util.Date;

public class Beheerder extends Persoon{

    private int id;
    private String gebruikersnaam;
    private String wachtwoord;

    // wachtwoord moet worden gehasht (verschil encryptie-hashing opzoeken) - of salting toepassen

    public Beheerder(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public Beheerder(int id, String gebruikersnaam, String wachtwoord) {
        this.id = id;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}
