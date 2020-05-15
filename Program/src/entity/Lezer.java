package entity;

import java.time.LocalDate;
import java.time.Period;

public class Lezer extends Persoon {

    private int id;
    private LocalDate geboortedatum;
    private Adres adres;
    private String email;
    private String telefoon;
    private String wachtwoord;
    private Uitlening uitlening;
    private Transactie transactie;
    private Schuld schuld;

    // Constructor nodig om schulden te kunnen weergeven:

    public Lezer(int id) {
        this.id = id;
    }

    // Constructor om lezer te kunnen weergeven:

    public Lezer(int id, String voornaam, String naam, LocalDate geboortedatum, String email, String telefoon) {
        super(voornaam, naam);
        this.id = id;
        this.geboortedatum = geboortedatum;
        this.email = email;
        this.telefoon = telefoon;
    }

    // Constructor om lezer te kunnen toevoegen:

    public Lezer(String voornaam, String naam, LocalDate geboortedatum, String email, String telefoon, String wachtwoord, Adres adres) {
        super(voornaam, naam);
        this.geboortedatum = geboortedatum;
        this.email = email;
        this.telefoon = telefoon;
        this.wachtwoord = wachtwoord;
        this.adres = adres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public String getWachtwoord() { return wachtwoord; }

    public Schuld getSchuld() { return schuld; }

    public int berekenLeeftijd() {
        int leeftijd = Period.between(geboortedatum, LocalDate.now()).getYears();
        return leeftijd;
    }
}

