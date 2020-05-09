package entity;

import java.time.LocalDate;

public class Kinderboek extends Boek {
    public Kinderboek(long ISBN, String titel, String auteur, String uitgeverij, String taal, String genre, int paginas, LocalDate aankoopdatum, double prijs, String plaatsInBib) {
        super(ISBN, titel, auteur, uitgeverij, taal, genre, paginas, aankoopdatum, prijs, plaatsInBib);
    }
}
