package entity;

/**
 * @author Katrien Persoons
 */

public class Beheerder extends Persoon{

    private int id;
    private String gebruikersnaam;
    private String wachtwoord;

    public Beheerder(String voornaam, String naam, String gebruikersnaam, String wachtwoord) {
        super(voornaam, naam);
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public Beheerder(int id, String voornaam, String naam, String gebruikersnaam) {
        super(voornaam, naam);
        this.gebruikersnaam = gebruikersnaam;
        this.id = id;
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
