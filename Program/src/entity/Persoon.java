package entity;

public abstract class Persoon {

    private String voornaam;
    private String naam;

    public Persoon(String voornaam, String naam) {
        this.voornaam = voornaam;
        this.naam = naam;
    }

    public Persoon() {

    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return "Naam: " + getVoornaam() + " " + getNaam();
    }
}
