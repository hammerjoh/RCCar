package de.hammer.rccar;

public class Datenbank {
    private long id;
    private String speichername;
    private String chassis;
    private String fahrer;
    private String veranstaltung;
    private String strecke;


    public Datenbank(long id, String speichername, String chassis, String fahrer, String veranstaltung, String strecke) {
        this.id = id;
        this.speichername = speichername;
        this.chassis = chassis;
        this.fahrer = fahrer;
        this.veranstaltung = veranstaltung;
        this.strecke = strecke;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpeichername() {
        return speichername;
    }


    public void setSpeichername(String speichername) {
        this.speichername = speichername;
    }

    public String getChassis() {
        return chassis;
    }

    public  void setChassis(String chassis){
        this.chassis = chassis;
    }

    public String getDriver() {
        return fahrer;
    }


    public void setDriver(String fahrer) {
        this.fahrer = fahrer;
    }


    @Override
    public String toString() {
        String output = speichername+" - "+chassis;

        return output;
    }
}
