package entity;

import java.util.Date;
import java.util.*;

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

// hashset geeft foutmelding bij ISBN-nr (int)

//public class HashSetBoek {
//    public static void main(String[] args) {
//        HashSet<Boek> set=new HashSet<>();
        //Boeken aanmaken
//        Boek b1=new Boek(101, 9789401611039L,"Zon","Lucinda Riley","Xander Uitgevers B.V.", "Nederlands", 732, 13-2-2020, 24.99, "RIRO",0, 2);
//        Boek b2=new Boek(102, 9789026343339L, "In hechtenis", "Nicci French", "Ambo|Anthos", "Nederlands", 437, 7-4-2020, 22.99, "FRRO", 1, 5);
//        //Boeken aan HashSet toevoegen
//        set.add(b1);
//        set.add(b2);
        //HashSet overnemen
//        for(Boek b:set){
//            System.out.println(b.getArtikelnummer()+" "+b.getISBN()+" "+b.getTitel()+" "+b.getAuteur()+" "+b.getUitgeverij()+""+b.getTaal()+" "+b.getPaginas()+" "+b.getAankoopdatum()+" "+b.getPrijs()+" "+b.getPlaatsInBib()+""+b.isGereserveerd()+" "+b.getAantalKeerUitgeleend());
//        }
//    }

//}