package entity;

public class Beheerder extends Persoon{

    private String gebruikersnaam;
    private String wachtwoord;

    // Constructor om een beheerder toe te voegen aan de DB:

    public Beheerder(String voornaam, String naam, String gebruikersnaam, String wachtwoord) {
        super(voornaam, naam);
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
    public String getWachtwoord() {
        return wachtwoord;
    }

}
