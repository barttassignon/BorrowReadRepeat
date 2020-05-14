package entity;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
// bevat methode DAYS.between. Static toevoegen om de leesbaarheid te verhogen (anders moet je telkens ook ChronoUnit zelf toevoegen)

public class Schuld {

    public String oorsprong;
    private int id;
    private Lezer lezer;
    private double bedrag;
    private LocalDate datumAangemaakt;
    private LocalDate datumBetaald;

    public Schuld() {
    }

    public Schuld(int id, double bedrag, LocalDate datumAangemaakt, LocalDate datumBetaald) {
        this.id = id;
        this.bedrag = bedrag;
        this.datumAangemaakt = datumAangemaakt;
        this.datumBetaald = datumBetaald;
    }

    public Schuld(Lezer lezer, int id, String oorsprong, double bedrag, LocalDate datumAangemaakt, LocalDate datumBetaald) {
        this.oorsprong = oorsprong;
        this.id = id;
        this.lezer = lezer;
        this.bedrag = bedrag;
        this.datumAangemaakt = datumAangemaakt;
        this.datumBetaald = datumBetaald;
    }

    public int getId() {
        return id;
    }

    public String getOorsprong() { return oorsprong; }

    public LocalDate getDatumAangemaakt() {
        return datumAangemaakt;
    }

    public LocalDate getDatumBetaald() {
        return datumBetaald;
    }

    public void setDatumBetaald(LocalDate datumBetaald) {
        if (datumBetaald.isAfter(datumAangemaakt))
        this.datumBetaald = datumBetaald;
    }

    public double getBedrag() {
        return bedrag;
    }

    public Lezer getLezer() { return lezer; }

    public static boolean overtijd(Uitlening u){
        if (u.getDatumVerlengd() != null) {
            LocalDate einddatum = u.getDatumVerlengd().plusDays(21);
            if (u.getDatumIngeleverd().isAfter(einddatum)) return true;
            else return false;}
        else {
            LocalDate einddatum = u.getDatumUitgeleend().plusDays(21);
            if (u.getDatumIngeleverd().isAfter(einddatum)) return true;
            else return false;
        }
    }

    public static double overtijdSchuld(Uitlening u, Boek b){
        if (u.getDatumVerlengd() != null) {
            LocalDate einddatum = u.getDatumVerlengd().plusDays(21);
            if (u.getDatumIngeleverd().isAfter(einddatum)) {
                long dagenTeLaat = DAYS.between(einddatum, u.getDatumIngeleverd());
                if (dagenTeLaat * 0.1 > b.getPrijs()) {
                    return b.getPrijs();
                } else {
                    return dagenTeLaat * 0.1;
                }
            }
            else return 0;
        } else {
            LocalDate einddatum = u.getDatumUitgeleend().plusDays(21);
            if (u.getDatumIngeleverd().isAfter(einddatum)) {
                long dagenTeLaat = DAYS.between(einddatum, u.getDatumIngeleverd());
                if (dagenTeLaat * 0.1 > b.getPrijs()) {
                    return b.getPrijs();
                } else {
                    return dagenTeLaat * 0.1;
                }
            }
            else return 0;
        }
    }


/*
            case BESCHADIGING:
                return b.getPrijs()/ 2.0;
            case VERLIES:
                return b.getPrijs();

*/
}
