package entity;

import java.time.LocalDate;

public class Boek {
    private int artikelnummer;
    private long ISBN;
    private String titel;
    private String auteur;
    private String uitgeverij;
    private String taal1;
    private String genre1;
    private int paginas;
    private LocalDate aankoopdatum;
    private double prijs;
    private String plaatsInBib;
    private boolean gereserveerd;
    private boolean uitgeleend;
    private boolean uitStock;
    private boolean kinderboek;

    // Constructors

    public Boek() { }

    public Boek(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public Boek(int artikelnummer, long ISBN, String titel, String auteur) {
        this.artikelnummer = artikelnummer;
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
    }

    // Constructor om boek te kunnen weergeven:

    public Boek(int artikelnummer, long ISBN, String titel, String auteur, String uitgeverij, int paginas, LocalDate aankoopdatum) {
        this.artikelnummer = artikelnummer;
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
        this.uitgeverij = uitgeverij;
        this.paginas = paginas;
        this.aankoopdatum = aankoopdatum;
    }

    // Constructor om info van boek op te halen:

    public Boek(int artikelnummer, long ISBN, String titel, String auteur, String uitgeverij, int paginas, LocalDate aankoopdatum, double prijs, String plaatsInBib, boolean gereserveerd, boolean uitgeleend, boolean uitStock, boolean kinderboek) {
        this.artikelnummer = artikelnummer;
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
        this.uitgeverij = uitgeverij;
        this.paginas = paginas;
        this.aankoopdatum = aankoopdatum;
        this.prijs = prijs;
        this.plaatsInBib = plaatsInBib;
        this.gereserveerd = gereserveerd;
        this.uitgeleend = uitgeleend;
        this.kinderboek = kinderboek;
        this.uitStock = uitStock;
    }

    // Constructor om boek te kunnen toevoegen:

    public Boek(long ISBN, String titel, String auteur, String uitgeverij, String taal1, String genre1, int paginas, LocalDate aankoopdatum, double prijs, String plaatsInBib, boolean kinderboek) {
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
        this.uitgeverij = uitgeverij;
        this.taal1 = taal1;
        this.genre1 = genre1;
        this.paginas = paginas;
        this.aankoopdatum = aankoopdatum;
        this.prijs = prijs;
        this.plaatsInBib = plaatsInBib;
        this.kinderboek = kinderboek;
        this.gereserveerd = false;
        this.uitgeleend = false;
        this.uitStock = false;
    }

    //Getters
    public int getArtikelnummer() {
        return artikelnummer;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getTitel() {
        return titel;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getUitgeverij() {
        return uitgeverij;
    }

    public String getTaal1() {
        return taal1;
    }

    public String getGenre1() {
        return genre1;
    }

    public int getPaginas() {
        return paginas;
    }

    public LocalDate getAankoopdatum() {
        return aankoopdatum;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getPlaatsInBib() {
        return plaatsInBib;
    }

    public boolean isGereserveerd() {
        return gereserveerd;
    }

    public boolean isUitgeleend() { return uitgeleend; }

    public boolean isKinderboek() { return kinderboek; }

    public boolean isUitStock() { return uitStock; }
}


