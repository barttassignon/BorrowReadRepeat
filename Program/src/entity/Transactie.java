package entity;

public abstract class Transactie {
    private Lezer lezer;
    private Boek boek;

    public Transactie() { }

    public Transactie(Lezer lezer, Boek boek) {
        this.lezer = lezer;
        this.boek = boek;
    }

    public Lezer getLezer() {
        return lezer;
    }

    public void setLezer(Lezer lezer) {
        this.lezer = lezer;
    }

    public Boek getBoek() {
        return boek;
    }

    public void setBoek(Boek boek) { this.boek = boek; }
}
