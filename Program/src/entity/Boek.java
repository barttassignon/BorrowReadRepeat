/**
 * @Author Bart Tassignon
 */

package entity;

import java.time.LocalDate;

public class Boek {
    private int artikelnummer;
    private long ISBN;
    // ISBN telt 13 cijfers en dat is te groot voor een int
    private String titel;
    private String auteur;
    private String uitgeverij;
    private String taal;
    // Genre varGenre = Genre.BIOGRAFIE (om op te roepen)
    enum Genre {BIOGRAFIE, FANTASY, GESCHIEDENIS, GEZONDHEID, KINDERBOEK, KOOKBOEK, ROMANS, THRILLER, TECHNOLOGIE };
    private int paginas;
    private LocalDate aankoopdatum;
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

    public Boek(long ISBN, String titel, String auteur, String uitgeverij, String taal, int paginas, LocalDate aankoopdatum, double prijs, String plaatsInBib) {
        this();
        this.artikelnummer = AantalBoekenInBib;
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
        this.uitgeverij = uitgeverij;
        this.taal = taal;
        this.paginas = paginas;
        this.aankoopdatum = aankoopdatum;
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


