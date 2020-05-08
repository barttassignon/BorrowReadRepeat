package entity;

import java.time.LocalDate;
import java.time.Period;

public class Schuld {

    public enum Oorsprong {OVERTIJD, VERLIES, BESCHADIGING, RESERVATIE}

    private Oorsprong oorsprong;
    private double bedrag;
    private LocalDate datumAangemaakt;
    private LocalDate datumBetaald;

    public Schuld() {
    }

    public Schuld(LocalDate datumAangemaakt, Oorsprong oorsprong, double bedrag) {
        this.datumAangemaakt = datumAangemaakt;
        this.oorsprong = oorsprong;
        this.bedrag = bedrag;
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

    public double getBedrag() {
        return bedrag;
    }

 /*   public double bepalenSchuld(Boek b, Uitlening u, Oorsprong o) {
        if (o.equals("beschadiging")) {
            return b.getPrijs() / 2;
        }

        if (o.equals("verlies")) {
            return b.getPrijs();
        }
        if (o.equals("reservatie"))
            return 0.5;

        if (o.equals("overtijd")) {

            if (u.getDatumVerlengd() != null) {
                LocalDate einddatum = u.getDatumVerlengd().plusDays(21);
                if (einddatum.isAfter(u.getDatumIngeleverd())) {
                    int dagenTeLaat = Period.between(u.getDatumIngeleverd(), einddatum).getDays();
                    if (dagenTeLaat * 0.1 > b.getPrijs()) {
                        return b.getPrijs();
                    } else {
                        return dagenTeLaat * 0.1;
                    }
                } else
                    return 0;
            }
            else {
                LocalDate einddatum = u.getDatumIngeleverd().plusDays(21);
                if (einddatum.isAfter(u.getDatumIngeleverd())) {
                    int dagenTeLaat = Period.between(u.getDatumIngeleverd(), einddatum).getDays();
                    if (dagenTeLaat * 0.1 > b.getPrijs()) {
                        return b.getPrijs();
                    } else {
                        return dagenTeLaat * 0.1;
                    }
                } else
                    return 0;
            }
        }

}*/

    public static void main(String[] args) {
        Uitlening u = new Uitlening();
       Oorsprong o = Oorsprong.BESCHADIGING;
        Boek b = new Boek(123456789L, "Hallo", "Man", "Pelckmans", "Frans", 145, LocalDate.of(2015, 10, 12), 24, "COE 138");
        Schuld s = new Schuld();
        //System.out.println(s.bepalenSchuld(b, u, o));
    }
}
