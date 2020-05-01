package entity;

/**
 * @author Katrien Persoons
 */

import java.time.LocalDate;
import java.time.Period;

public class Lezer {

    private int id;
    private String voornaam;
    private String naam;
    private LocalDate geboortedatum;
    // toegevoegd door Bart na aanmaak klasse Adres
    private Adres adres;
    private String email;
    private int telefoon;
    private String gebruikersnaam;
    private String wachtwoord;

    public int berekenLeeftijd()
    {
        int leeftijd = Period.between(geboortedatum, LocalDate.now()).getYears();
        return leeftijd;
    }
}

