package entity;

import java.time.LocalDate;

public class Boek {
    private int artikelnummer;
    private long ISBN;
    private String titel;
    private String auteur;
    private String uitgeverij;
    private String taal;
    private String genre;
    private int paginas;
    private LocalDate aankoopdatum;
    private double prijs;
    private String plaatsInBib;
    private boolean gereserveerd;
    private boolean uitgeleend;
    private boolean uitStock;
    private boolean kinderboek;

    // Constructor nodig voor het weergeven van uitleningen:

    public Boek(int artikelnummer) {
        this.artikelnummer = artikelnummer;
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

    // Constructor om boek te kunnen toevoegen:

    public Boek(long ISBN, String titel, String auteur, String uitgeverij, String taal, String genre, int paginas, LocalDate aankoopdatum, double prijs, String plaatsInBib, boolean kinderboek) {
        this.ISBN = ISBN;
        this.titel = titel;
        this.auteur = auteur;
        this.uitgeverij = uitgeverij;
        this.taal = taal;
        this.genre = genre;
        this.paginas = paginas;
        this.aankoopdatum = aankoopdatum;
        this.prijs = prijs;
        this.plaatsInBib = plaatsInBib;
        this.kinderboek = kinderboek;
        this.gereserveerd = false;
        this.uitgeleend = false;
        this.uitStock = false;
    }

    // Constructor om alle nuttige info van boek op te halen:

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

    public String getTaal() {
        return taal;
    }

    public String getGenre() {
        return genre;
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

    public static boolean validateISBN10(long ISBN) {

        // Van de 9 eerste cijfers wordt het 1e met 10 vermenigvuldigd, het 2e met 9, het 3e met 8, ...
        // Bij de som van de producten wordt een getal opgeteld, zodanig dat een veelvoud van 11 ontstaat.
        // Dit toegevoegde getal is het controlecijfer.
        // Als het controlecijfer 10 is, wordt in plaats van een cijfer een X op de laatste positie gezet.

        int totaal = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Integer.parseInt(String.valueOf(ISBN).substring(i, i + 1));
            totaal += ((10 - i) * digit);
        }

        String checksom = Integer.toString((11 - (totaal % 11)) % 11);
        if ("10".equals(checksom)) {
            checksom = "X";
        }

        return checksom.equals(String.valueOf(ISBN).substring(9));
    }

    public static boolean validateISBN13(long ISBN) {

        // Alle cijfers op een even positie worden bij elkaar opgeteld en met 3 vermenigvuldigd.
        // Alle cijfers op een oneven positie worden hier ook bij opgeteld.
        // Het controlecijfer is het cijfer dat hier nog bijgeteld moet worden om op een veelvoud van 10 te komen.

        int totaal = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Integer.parseInt(String.valueOf(ISBN).substring(i, i + 1));
            totaal += (i % 2 == 0) ? digit * 1 : digit * 3;
        }

        int checksom = 10 - (totaal % 10);
        if (checksom == 10) {
            checksom = 0;
        }
        return checksom == Integer.parseInt(String.valueOf(ISBN).substring(12));
    }
}


