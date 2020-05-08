package entity;

import java.time.LocalDate;

public class Uitlening extends Transactie{

    private LocalDate datumUitgeleend;
    private LocalDate datumVerlengd;
    private LocalDate datumIngeleverd;

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
        this.datumVerlengd = datumVerlengd;
    }

    public LocalDate getDatumIngeleverd() {
        return datumIngeleverd;
    }

    public void setDatumIngeleverd(LocalDate datumIngeleverd) {
        this.datumIngeleverd = datumIngeleverd;
    }

    @Override
    public String toString() {
        return "Uitlening: " +
                ", datum uitgeleend: " + datumUitgeleend +
                ", datum verlengd: " + datumVerlengd +
                ", datum ingeleverd: " + datumIngeleverd;
    }
}