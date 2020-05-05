package entity;

/**
 * @author Katrien Persoons
 */

import java.time.LocalDate;
import java.time.Period;

public class Lezer extends Persoon{

    private int id;
    private LocalDate geboortedatum;
    // toegevoegd door Bart na aanmaak klasse Adres
    private Adres adres;
    private String email;
    private int telefoon;
    private String wachtwoord;
    private Uitlening uitlening;
    private Reservatie reservatie;
    private Schuld schuld;

    public Lezer(String voornaam, String naam, int id, LocalDate geboortedatum) {
        super(voornaam, naam);
        this.id = id;
        this.geboortedatum = geboortedatum;
    }

    public int berekenLeeftijd()
    {
        int leeftijd = Period.between(geboortedatum, LocalDate.now()).getYears();
        return leeftijd;
    }

}

