package entity;


import java.time.LocalDate;

public class Uitlening extends Transactie{

    private LocalDate datumUitleen;
    private LocalDate datumEind;
    private LocalDate datumInlever;
    private boolean uitleningVerlengd;

    public LocalDate getDatumUitleen() {
        return datumUitleen;
    }

    public LocalDate getDatumEind() {
        return datumEind;
    }

    public LocalDate getInlever() {
        return datumInlever;
    }

    public boolean getUitleveringVerlengd() {
        return uitleningVerlengd;
    }

    @Override
    public String toString() {
        return "Uitlening{" +
                ", datum uitlening='" + datumUitleen + '\'' +
                ", datum in te leveren='" + datumEind + '\'' +
                ", datum ingeleverd='" + datumInlever + '\'' +
                ", verlegend='" + uitleningVerlengd + '\'' +
                '}';
    }
}