package entity;

import java.time.LocalDate;

public class Uitlening extends Transactie{

    private int uitleen_ID;
    private LocalDate datumUitgeleend;
    private LocalDate datumVerlengd;
    private LocalDate datumIngeleverd;

    // Constructor om nieuwe uitlening toe te voegen:

    public Uitlening(Lezer lezer, Boek boek) {
        super(lezer, boek);
        this.datumUitgeleend = LocalDate.now();
    }

    // Constructor om uitleningen te kunnen weergeven:

    public Uitlening(Lezer lezer, Boek boek, int uitleen_ID, LocalDate datumUitgeleend, LocalDate datumVerlengd, LocalDate datumIngeleverd) {
        super(lezer, boek);
        this.uitleen_ID = uitleen_ID;
        this.datumUitgeleend = datumUitgeleend;
        this.datumVerlengd = datumVerlengd;
        this.datumIngeleverd = datumIngeleverd;
    }

    public int getUitleen_ID() {
        return uitleen_ID;
    }
    public LocalDate getDatumUitgeleend() {
        return datumUitgeleend;
    }
    public LocalDate getDatumVerlengd() {
        return datumVerlengd;
    }
    public LocalDate getDatumIngeleverd() {
        return datumIngeleverd;
    }
}