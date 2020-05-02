package entity;

import java.util.Date;

public class Reservatie extends Transactie{
    private int reservatieId;
    private String reservatieDatum;

    public int getReservatieId() {
        return reservatieId;
    }
    public void setReservatieId(int reservatieId){
        this.reservatieId = reservatieId;
    }

    public String getReservatieDatum() {
        return reservatieDatum;
    }
    public void setReservatieDatum(String reservatieDatum){
        this.reservatieDatum = reservatieDatum;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", reservatieId = " + reservatieId
                +", reservatiedatum = " + reservatieDatum;
    }
}
