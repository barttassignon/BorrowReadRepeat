package entity;

import java.time.LocalDate;

public class Schuld {
    private String oorsprong;
    private double bedrag;
    private LocalDate datumAangemaakt;
    private LocalDate datumBetaald;

public Schuld(LocalDate datumAangemaakt, String oorsprong, double bedrag){
    this.datumAangemaakt = datumAangemaakt;
    this.oorsprong = oorsprong;
    this.bedrag = bedrag;
}

public String getOorsprong() {
    return oorsprong;
}

public LocalDate getDatumAangemaakt(){
    return datumAangemaakt;
}

public LocalDate getDatumBetaald(){
    return datumBetaald;
}

public double getBedrag(){
    return bedrag;
}

}
