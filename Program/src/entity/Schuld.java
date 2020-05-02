package entity;

public class Schuld {
    private String oorsprong;
    private double bedrag;
    private String datumAangemaakt;
    private String datumBetaald;

public Schuld(String datumAangemaakt, String datumBetaald, String oorsprong, double bedrag){
    this.datumAangemaakt = datumAangemaakt;
    this.datumBetaald = datumAangemaakt;
    this.oorsprong = oorsprong;
    this.bedrag = bedrag;
}

public String getOorsprong() {
    return oorsprong;
}

public String getDatumAangemaakt(){
    return datumAangemaakt;
}

public String getDatumBetaald(){
    return datumBetaald;
}

public double getBedrag(){
    return bedrag;
}

}
