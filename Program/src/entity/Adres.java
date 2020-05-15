package entity;

import java.io.Serializable;

public class Adres implements Serializable {

    private int id;
    private String straatnaam;
    private int huisnummer;
    private String bus;
    private int postcode;
    private String woonplaats;

    public Adres(String straatnaam, int huisnummer, String bus, int postcode, String woonplaats) {
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.bus = bus;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
    }

    public int getId() {
        return id;
    }
    public String getStraatnaam() {
        return straatnaam;
    }
    public int getHuisnummer() {
        return huisnummer;
    }
    public String getBus() { return bus; }
    public int getPostcode() {
        return postcode;
    }
    public String getWoonplaats() {
        return woonplaats;
    }
}


