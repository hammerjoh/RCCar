package de.hammer.rccar;

public class Datenbank {
    private long id;
    /**Kopf**/
    private String speichername;
    private String chassis;
    private String fahrer;
    private String veranstaltung;
    private String datum;
    private String strecke;
    private String tq;
    private String qualifizierung;
    private String haupt;
    private String ziel;
    private String besterunde;
    /**Front Suspension**/
    private String fahrhoehe_vorne;
    private String sturz_vorne;
    private String spitze;
    private String armtyp;
    private String turmtyp;
    private String rolleneinsatz;
    private String schotttyp;
    private String stosswinkel;
    private String reifenmitnehmer;
    private String fsnotizen;


    public Datenbank(long id, String speichername, String chassis, String fahrer, String veranstaltung,
                     String datum, String strecke, String tq, String qualifizierung, String haupt,
                     String ziel, String besterunde, String fahrhoehe_vorne, String sturz_vorne,
                     String spitze, String armtyp, String turmtyp, String rolleneinsatz, String schotttyp,
                     String stosswinkel, String reifenmitnehmer, String fsnotizen) {
        this.id = id;
        this.speichername = speichername;
        this.chassis = chassis;
        this.fahrer = fahrer;
        this.veranstaltung = veranstaltung;
        this.datum = datum;
        this.strecke = strecke;
        this.tq = tq;
        this.qualifizierung = qualifizierung;
        this.haupt = haupt;
        this.ziel = ziel;
        this.besterunde = besterunde;
        this.fahrhoehe_vorne = fahrhoehe_vorne;
        this.sturz_vorne = sturz_vorne;
        this.spitze = spitze;
        this.armtyp = armtyp;
        this.turmtyp = turmtyp;
        this.rolleneinsatz = rolleneinsatz;
        this.schotttyp = schotttyp;
        this.stosswinkel = stosswinkel;
        this.reifenmitnehmer = reifenmitnehmer;
        this.fsnotizen = fsnotizen;
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

    public String getFahrer() {
        return fahrer;
    }
    public void setFahrer(String fahrer) {
        this.fahrer = fahrer;
    }

    public String getVeranstaltung() {
        return veranstaltung;
    }
    public void setVeranstaltung(String veranstaltung) {
        this.veranstaltung = veranstaltung;
    }

    public String getStrecke() {
        return strecke;
    }
    public void setStrecke(String strecke) {
        this.strecke = strecke;
    }

    public String getTq() {
        return tq;
    }
    public void setTq(String tq) {
        this.tq = tq;
    }

    public String getQualifizierung() {
        return qualifizierung;
    }
    public void setQualifizierung(String qualifizierung) {
        this.qualifizierung = qualifizierung;
    }

    public String getHaupt() {
        return haupt;
    }
    public void setHaupt(String haupt) {
        this.haupt = haupt;
    }

    public String getZiel() {
        return ziel;
    }
    public void setZiel(String ziel) {
        this.ziel = ziel;
    }

    public String getBesterunde() {
        return besterunde;
    }
    public void setBesterunde(String besterunde) {
        this.besterunde = besterunde;
    }

    public String getDatum() {
        return datum;
    }
    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getFahrhoehe_vorne() {
        return fahrhoehe_vorne;
    }
    public void setFahrhoehe_vorne(String fahrhoehe_vorne) {
        this.fahrhoehe_vorne = fahrhoehe_vorne;
    }

    public String getSturz_vorne() {
        return sturz_vorne;
    }
    public void setSturz_vorne(String sturz_vorne) {
        this.sturz_vorne = sturz_vorne;
    }

    public String getSpitze() {
        return spitze;
    }
    public void setSpitze(String spitze) {
        this.spitze = spitze;
    }

    public String getArmtyp() {
        return armtyp;
    }
    public void setArmtyp(String armtyp) {
        this.armtyp = armtyp;
    }

    public String getTurmtyp() {
        return turmtyp;
    }
    public void setTurmtyp(String turmtyp) {
        this.turmtyp = turmtyp;
    }

    public String getRolleneinsatz() {
        return rolleneinsatz;
    }
    public void setRolleneinsatz(String rolleneinsatz) {
        this.rolleneinsatz = rolleneinsatz;
    }

    public String getSchotttyp() {
        return schotttyp;
    }
    public void setSchotttyp(String schotttyp) {
        this.schotttyp = schotttyp;
    }

    public String getStosswinkel() {
        return stosswinkel;
    }
    public void setStosswinkel(String stosswinkel) {
        this.stosswinkel = stosswinkel;
    }

    public String getReifenmitnehmer() {
        return reifenmitnehmer;
    }
    public void setReifenmitnehmer(String reifenmitnehmer) {
        this.reifenmitnehmer = reifenmitnehmer;
    }

    public String getFsnotizen() {
        return fsnotizen;
    }
    public void setFsnotizen(String fsnotizen) {
        this.fsnotizen = fsnotizen;
    }

/**

    public String getZ() {
        return z;
    }
    public void setZ(String z) {
        this.z = z;
    }
 **/



    @Override
    public String toString() {
        String output = speichername+" - "+chassis;

        return output;
    }
}
