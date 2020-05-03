package entity;

/**
 * @author Katrien Persoons
 */

import java.time.LocalDate;
import java.time.Period;

public class Lezer extends Persoon{

    private final int id;
    private final LocalDate geboortedatum;
    // toegevoegd door Bart na aanmaak klasse Adres
    private Adres adres;
    private String email;
    private int telefoon;
    private String gebruikersnaam;
    private String wachtwoord;
    private Uitlening uitlening;
    private Reservatie reservatie;
    private Schuld schuld;

    public Lezer(int id, LocalDate geboortedatum) {
        this.id = id;
        this.geboortedatum = geboortedatum;
    }

    public int berekenLeeftijd()
    {
        int leeftijd = Period.between(geboortedatum, LocalDate.now()).getYears();
        return leeftijd;
    }

}

