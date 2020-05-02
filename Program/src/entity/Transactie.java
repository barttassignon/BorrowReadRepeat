package entity;

import java.util.Date;

public class Transactie {
    private int transactieId;
    private int lezerId;
    private int artikelNummer;

    public int getTransactieId() {return transactieId;}
    public void setTransactieId (int transactieId) { this.transactieId = transactieId;}

    public int getLezerId() {return lezerId;}
    public void setLezerId (int lezerId) { this.lezerId = lezerId;}

    public int getArtikelNummer() {return artikelNummer;}
    public void setArtikelNummer (int artikelNummer) { this.artikelNummer = artikelNummer;}

@Override
    public String toString() {
        return super.toString() +
                ", transactieId = " + transactieId
                +", lezerId = " + lezerId
                +", artikelnummer = " + artikelNummer;
}
}
