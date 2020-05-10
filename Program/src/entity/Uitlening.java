package entity;

import java.time.LocalDate;
import java.util.HashSet;

public class Uitlening extends Transactie{

    private int uitleen_ID;
    private LocalDate datumUitgeleend;
    private LocalDate datumVerlengd;
    private LocalDate datumIngeleverd;

    public Uitlening() {
    }

    // Constructor om nieuwe uitlening toe te voegen:

    public Uitlening(Lezer lezer, Boek boek) {
        super(lezer, boek);
        this.datumUitgeleend = LocalDate.now();
    }

    public Uitlening(LocalDate datumUitgeleend, LocalDate datumVerlengd, LocalDate datumIngeleverd) {
        this.datumUitgeleend = datumUitgeleend;
        if(datumVerlengd.isAfter(datumUitgeleend) && datumVerlengd.isBefore(datumUitgeleend.plusDays(22)))
        this.datumVerlengd = datumVerlengd;
        else System.out.println("Foutieve invoer");
        if(datumIngeleverd.isAfter(datumUitgeleend))
        this.datumIngeleverd = datumIngeleverd;
        else System.out.println("Foutieve invoer");
    }

    public int getUitleen_ID() {
        return uitleen_ID;
    }


    public LocalDate getDatumUitgeleend() {
        return datumUitgeleend;
    }

    public void setDatumUitgeleend(LocalDate datumUitgeleend) {
        this.datumUitgeleend = datumUitgeleend;
    }

    public LocalDate getDatumVerlengd() {
        return datumVerlengd;
    }

    public void setDatumVerlengd(LocalDate datumVerlengd) {
        if(datumVerlengd.isAfter(datumUitgeleend) && datumVerlengd.isBefore(datumUitgeleend.plusDays(22)))
            this.datumVerlengd = datumVerlengd;
        else System.out.println("Foutieve invoer");
    }

    public LocalDate getDatumIngeleverd() {
        return datumIngeleverd;
    }

    public void setDatumIngeleverd(LocalDate datumIngeleverd) {
        if(datumIngeleverd.isAfter(datumUitgeleend))
            this.datumIngeleverd = datumIngeleverd;
        else System.out.println("Foutieve invoer");
    }

    @Override
    public String toString() {
        return "Uitlening: " +
                ", datum uitgeleend: " + datumUitgeleend +
                ", datum verlengd: " + datumVerlengd +
                ", datum ingeleverd: " + datumIngeleverd;
    }
}