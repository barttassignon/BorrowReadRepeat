/**
 * @Author Bart Tassignon
 */

package model;

import entity.Adres;
import entity.Schuld;
import entity.Transactie;
import entity.Uitlening;

import java.time.LocalDate;

public class Lezer {

    private int id;
    private LocalDate geboortedatum;
    private Adres adres;
    private String email;
    private String telefoon;
    private String wachtwoord;
    private Uitlening uitlening;
    private Transactie transactie;
    private Schuld schuld;

}


