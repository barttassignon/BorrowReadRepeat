/**
 * @Author Bart Tassignon
 */

package entity;

import java.util.Objects;

public abstract class Boek {
    private int artikelnummer;
    private int ISBN;
    private String titel;
    private String auteur;
    private String Uitgeverij;
    private String taal;
    private int paginas;
    private String aankoopdatum;
    private double prijs;
    private String plaatsInBib;
    private boolean gereserveerd;
    private int aantalKeerUitgeleend;

    // bijhouden totaal aantal boeken in de Bib
    private static int AantalBoekenInBib = 0;

    // Constructors
    public Boek() {
        AantalBoekenInBib += 1;
    }

    public Boek(int artikelnummer, int ISBN, String titel, String auteur, String uitgeverij, String taal, int paginas, String aankoopdatum, double prijs, String plaatsInBib) {
        this.artikelnummer = artikelnummer;
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
        Uitgeverij = uitgeverij;
        this.taal = taal;
        this.paginas = paginas;
        this.aankoopdatum = aankoopdatum;
        this.prijs = prijs;
        this.plaatsInBib = plaatsInBib;
    }

    //Getters en Setters
    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
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
        return Uitgeverij;
    }

    public void setUitgeverij(String uitgeverij) {
        Uitgeverij = uitgeverij;
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        this.taal = taal;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getAankoopdatum() {
        return aankoopdatum;
    }

    public void setAankoopdatum(String aankoopdatum) {
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

    public static int getAantalBoekenInBib() {
        return AantalBoekenInBib;
    }

    public static void setAantalBoekenInBib(int aantalBoekenInBib) {
        AantalBoekenInBib = aantalBoekenInBib;
    }

    @Override
    public String toString() {
        return "Boek{" +
                "ISBN=" + ISBN +
                ", titel='" + titel + '\'' +
                ", auteur='" + auteur + '\'' +
                '}';
    }
}


