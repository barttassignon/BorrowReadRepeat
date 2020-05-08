package entity;

/**
 * @author Katrien Persoons
 */

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Objects;

public class Lezer extends Persoon {

    private int id;
    private LocalDate geboortedatum;
    // toegevoegd door Bart na aanmaak klasse Adres
    private Adres adres;
    private String email;
    private String telefoon;
    private String wachtwoord;
    private Uitlening uitlening;
    private Transactie transactie;
    private Schuld schuld;
    // kolomnamen om lezers weer te geven bij opzoekenlezer
    private String iD, voornaam, naam;

    public Lezer(String voornaam, String naam, LocalDate geboortedatum, String email, String telefoon, String wachtwoord, Adres adres) {
        super(voornaam, naam);
        this.geboortedatum = geboortedatum;
        this.email = email;
        this.telefoon = telefoon;
        this.wachtwoord = wachtwoord;
        this.adres = adres;
    }

    public Lezer(String voornaam, String naam, int id, LocalDate geboortedatum, String email) {
        super(voornaam, naam);
        this.id = id;
        this.geboortedatum = geboortedatum;
        this.email = email;
    }

    public Lezer(String voornaam, String naam, int id, LocalDate geboortedatum, String email, String telefoon) {
        super(voornaam, naam);
        this.id = id;
        this.geboortedatum = geboortedatum;
        this.email = email;
        this.telefoon = telefoon;
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

    public void setGeboortedatum(LocalDate geboortedatum)
    {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Schuld getSchuld() {
        return schuld;
    }

    public void setSchuld(Schuld schuld) {
        this.schuld = schuld;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lezer)) return false;
        Lezer lezer = (Lezer) o;
        return id == lezer.id &&
                Objects.equals(geboortedatum, lezer.geboortedatum) &&
                Objects.equals(adres, lezer.adres) &&
                Objects.equals(email, lezer.email) &&
                Objects.equals(telefoon, lezer.telefoon) &&
                Objects.equals(wachtwoord, lezer.wachtwoord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, geboortedatum, adres, email, telefoon, wachtwoord);
    }

    public int berekenLeeftijd() {
        int leeftijd = Period.between(geboortedatum, LocalDate.now()).getYears();
        return leeftijd;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", " + super.toString() + ", geboortedatum: " + getGeboortedatum().getDayOfMonth() + "/" + getGeboortedatum().getMonth().getValue() + "/" + getGeboortedatum().getYear() +
                ", email: " + getEmail();
    }
}

