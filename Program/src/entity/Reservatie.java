package entity;

import java.time.LocalDate;

public class Reservatie extends Transactie{
    private int reservatieId;
    private LocalDate reservatieDatum;
    private LocalDate einddatum;

    // Constructor om nieuwe reservatie toe te voegen:

    public Reservatie(Lezer lezer, Boek boek) {
        super(lezer, boek);
        reservatieDatum = LocalDate.now();
    }

    public int getReservatieId() {
        return reservatieId;
    }

    public LocalDate getReservatieDatum() {
        return reservatieDatum;
    }

    public LocalDate getEinddatum() { return einddatum; }
}