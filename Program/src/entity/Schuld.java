package entity;

import java.time.LocalDate;

public class Schuld {
    public enum Oorsprong {OVERTIJD, VERLIES, BESCHADIGING, RESERVATIE}
    private Oorsprong oorsprong;
    private double bedrag;
    private LocalDate datumAangemaakt;
    private LocalDate datumBetaald;

public Schuld(LocalDate datumAangemaakt, Oorsprong oorsprong, double bedrag){
    this.datumAangemaakt = datumAangemaakt;
    this.oorsprong = oorsprong;
    this.bedrag = bedrag;
}

public Oorsprong getOorsprong() {
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
