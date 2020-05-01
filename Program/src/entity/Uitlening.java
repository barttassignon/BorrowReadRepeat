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

    public void setDatumUitleen(int datumUitleen) {
        this.datumUitleen = datumUitleen;
    }

    public String getDatumEind() {
        return datumEind;
    }

    public void setDatumEind(int datumEind) {
        this.datumEind = datumEind;
    }

    public String getInlever() {
        return datumInlever;
    }

    public void setDatumInlever(int datumInlever) {
        this.datumInlever = datumInlever;
    }

    public boolean getUitleveringVerlengd() {
        return uitleningVerlengd;
    }

    public void setUitleveringVerlengd(int uitleveringVerlengd) {
        this.uitleningVerlengd = uitleningVerlengd;
    }

    public String toString() {
        return "Uitlener [lezerID=" + lezerID + ", datumUitleen=" + datumUitleen + ", datumEind=" + datumEind
                + ", datumInlever=" + datumInlever + ", uitleningVerlengd= " + uitleningVerlengd + "]";
    }
}