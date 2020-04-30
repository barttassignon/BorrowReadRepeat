/**
 * @Author Bart Tassignon
 */

package entity;

public class Adres {

    private String straatnaam;
    private int huisnummer;
    private int bus;
    private int postcode;
    private String woonplaats;

    public Adres(String straatnaam, int huisnummer, int bus, int postcode, String woonplaats) {
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.bus = bus;
        if (postcode > 999 && postcode < 10000) {
            this.postcode = postcode;
        } else this.postcode = 1000;
        this.woonplaats = woonplaats;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " +
                "straatnaam = '" + straatnaam + '\'' +
                ", huisnummer = '" + huisnummer +  '\'' +
                ", bus = '" + bus + '\'' +
                ", postcode = '" + postcode +  '\'' +
                ", woonplaats = '" + woonplaats + '\'';
    }

    public static void main(String[] args) {
        /* Test Output
        Adres a = new Adres("Floralaan", 14, 1, 1501, "Buizingen");
        System.out.println(a);
        */
    }
}


