package entity;

public abstract class Persoon {

    private String voornaam;
    private String naam;

    public Persoon() { }

    public Persoon(String voornaam, String naam) {
        this.voornaam = voornaam;
        this.naam = naam;
    }

    public String getVoornaam() { return voornaam; }
    public String getNaam() {
        return naam;
    }
}
