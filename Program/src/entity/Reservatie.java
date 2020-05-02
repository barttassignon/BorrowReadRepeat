package entity;

import java.time.LocalDate;

public class Reservatie extends Transactie{
    private int reservatieId;
    private LocalDate reservatieDatum;

    public int getReservatieId() { return reservatieId; }
    public void setReservatieId(int reservatieId){
        this.reservatieId = reservatieId;
    }

    public LocalDate getReservatieDatum() {
        return reservatieDatum;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", reservatieId = " + reservatieId
                +", reservatiedatum = " + reservatieDatum;
    }
}
