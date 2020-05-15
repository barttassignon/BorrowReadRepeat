package entity;

public class Beheerder extends Persoon{

    private String gebruikersnaam;
    private String wachtwoord;

    public Beheerder(String voornaam, String naam, String gebruikersnaam, String wachtwoord) {
        super(voornaam, naam);
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public Beheerder(String voornaam, String naam, String gebruikersnaam) {
        super(voornaam, naam);
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
    public String getWachtwoord() {
        return wachtwoord;
    }

}
