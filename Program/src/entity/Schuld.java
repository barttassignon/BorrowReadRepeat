package entity;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
// bevat methode DAYS.between. Static toevoegen om de leesbaarheid te verhogen (anders moet je telkens ook ChronoUnit zelf toevoegen)

public class Schuld {

    public enum Oorsprong {OVERTIJD, VERLIES, BESCHADIGING, RESERVATIE}

    private Oorsprong oorsprong;
    private double bedrag;
    private LocalDate datumAangemaakt;
    private LocalDate datumBetaald;

    public Schuld() {
    }

    public Schuld(Oorsprong oorsprong, double bedrag, LocalDate datumAangemaakt) {
        this.oorsprong = oorsprong;
        this.bedrag = bedrag;
        this.datumAangemaakt = datumAangemaakt;
    }

    public Oorsprong getOorsprong() {
        return oorsprong;
    }

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

    public double bepalenSchuld(Boek b, Uitlening u, Oorsprong o) {

        switch (o) {
            case BESCHADIGING:
                return b.getPrijs()/ 2.0;
            case VERLIES:
                return b.getPrijs();
            case RESERVATIE:
                return 0.5;
            case OVERTIJD: {
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
            default:
                throw new IllegalStateException("Unexpected value: " + o);
        }
    }

    public static void main(String[] args) {
        Uitlening u = new Uitlening(LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 22), LocalDate.of(2020, 5, 30));
        Oorsprong o = Oorsprong.OVERTIJD;
        Boek b = new Boek(123456789L, "Hallo", "Man", "Pelckmans", "Frans", 145, LocalDate.of(2015, 10, 12), 24, "COE 138");
        Schuld s = new Schuld();
        System.out.println(u.getDatumIngeleverd());
        System.out.println(u.getDatumVerlengd().plusDays(21));
        System.out.println(String.format("%.2f", s.bepalenSchuld(b, u, o)));
    }
}
