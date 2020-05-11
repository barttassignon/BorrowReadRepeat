/**
 * @Author Bart Tassignon
 */

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
    private int aantalKeerUitgeleend;
    private boolean kinderboek;

    // Constructors


    public Boek() {
    }

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

    // Constructor om alle nuttige info van een boek op te halen m.b.t. status:

    public Boek(int artikelnummer, long ISBN, String titel, String auteur, String uitgeverij, int paginas, LocalDate aankoopdatum, double prijs, String plaatsInBib, boolean gereserveerd, boolean uitgeleend, int aantalKeerUitgeleend, boolean kinderboek) {
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
        this.aantalKeerUitgeleend = aantalKeerUitgeleend;
        this.kinderboek = kinderboek;
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
        this.aantalKeerUitgeleend = 0;
    }

    //Getters en Setters
    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getUitgeverij() {
        return uitgeverij;
    }

    public void setUitgeverij(String uitgeverij) {
        this.uitgeverij = uitgeverij;
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

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public LocalDate getAankoopdatum() {
        return aankoopdatum;
    }

    public void setAankoopdatum(LocalDate aankoopdatum) {
        this.aankoopdatum = aankoopdatum;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getPlaatsInBib() {
        return plaatsInBib;
    }

    public void setPlaatsInBib(String plaatsInBib) {
        this.plaatsInBib = plaatsInBib;
    }

    public boolean isGereserveerd() {
        return gereserveerd;
    }

    public void setGereserveerd(boolean gereserveerd) {
        this.gereserveerd = gereserveerd;
    }

    public int getAantalKeerUitgeleend() {
        return aantalKeerUitgeleend;
    }

    public void setAantalKeerUitgeleend(int aantalKeerUitgeleend) {
        this.aantalKeerUitgeleend = aantalKeerUitgeleend;
    }

    public boolean isUitgeleend() { return uitgeleend; }

    public void setUitgeleend(boolean uitgeleend) { this.uitgeleend = uitgeleend; }

    public boolean isKinderboek() { return kinderboek; }

    public void setKinderboek(boolean kinderboek) { this.kinderboek = kinderboek; }

    @Override
    public String toString() {
        return "Boek{" +
                "ISBN=" + ISBN +
                ", titel='" + titel + '\'' +
                ", auteur='" + auteur + '\'' +
                '}';
    }
}


