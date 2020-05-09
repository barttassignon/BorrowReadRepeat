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
    public enum Taal {NEDERLANDS, FRANS, ENGELS};
    private Taal taal;
    public enum Genre {BIOGRAFIE, FANTASY, GESCHIEDENIS, GEZONDHEID, PRENTENBOEK, KOOKBOEK, ROMAN, THRILLER, TECHNOLOGIE };
    private Genre genre;
    private int paginas;
    private LocalDate aankoopdatum;
    private LocalDate aankoopdatumDB;
    private double prijs;
    private String plaatsInBib;
    private boolean gereserveerd;
    private boolean uitgeleend;
    private int aantalKeerUitgeleend;

    // bijhouden totaal aantal boeken in de Bib
    private static int AantalBoekenInBib = 0;

    // Constructors
    public Boek() {
        AantalBoekenInBib++;
    }

    public Boek(int artikelnummer, long ISBN, String titel, String auteur) {
        this.artikelnummer = artikelnummer;
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
    }

    public Boek(long ISBN, String titel, String auteur, String uitgeverij, Taal taal, Genre genre, int paginas, LocalDate aankoopdatum, double prijs, String plaatsInBib) {
        this();
        this.artikelnummer = AantalBoekenInBib;
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
        this.uitgeverij = uitgeverij;
        this.taal = taal;
        this.genre = genre;
        this.paginas = paginas;
        this.aankoopdatum = aankoopdatum;
        this.aankoopdatumDB = aankoopdatum.plusDays(1);
        this.prijs = prijs;
        this.plaatsInBib = plaatsInBib;
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

    public Taal getTaal() {
        return taal;
    }

    public void setTaal(Taal taal) {
        this.taal = taal;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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

    public LocalDate getAankoopdatumDB() { return aankoopdatumDB; }

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

    public static int getAantalBoekenInBib() {
        return AantalBoekenInBib;
    }

    public static void setAantalBoekenInBib(int aantalBoekenInBib) { AantalBoekenInBib = aantalBoekenInBib; }

    @Override
    public String toString() {
        return "Boek{" +
                "ISBN=" + ISBN +
                ", titel='" + titel + '\'' +
                ", auteur='" + auteur + '\'' +
                '}';
    }
}


