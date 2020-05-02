package entity;

import java.util.Date;

public class Uitlening {

    private int lezerId;
    private String datumUitleen;
    private String datumEind;
    private String datumInlever;
    private boolean uitleningVerlengd;

    public int getLezerId() {
        return lezerId;
    }

    public void setLezerId(int lezerID) {
        this.lezerId = lezerId;
    }

    public String getDatumUitleen() {
        return datumUitleen;
    }

    public void setDatumUitleen(String datumUitleen) {
        this.datumUitleen = datumUitleen;
    }

    public String getDatumEind() {
        return datumEind;
    }

    public void setDatumEind(String datumEind) {
        this.datumEind = datumEind;
    }

    public String getInlever() {
        return datumInlever;
    }

    public void setDatumInlever(String datumInlever) {
        this.datumInlever = datumInlever;
    }

    public boolean getUitleveringVerlengd() {
        return uitleningVerlengd;
    }

    public void setUitleveringVerlengd(boolean uitleveringVerlengd) {
        this.uitleningVerlengd = uitleningVerlengd;
    }

    @Override
    public String toString() {
        return "Uitlening{" +
                "lezerId=" + lezerId +
                ", datum uitlening='" + datumUitleen + '\'' +
                ", datum in te leveren='" + datumEind + '\'' +
                ", datum ingeleverd='" + datumInlever + '\'' +
                ", verlegend='" + uitleningVerlengd + '\'' +
                '}';
    }
}