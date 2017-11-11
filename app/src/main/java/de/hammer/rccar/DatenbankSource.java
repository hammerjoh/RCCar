package de.hammer.rccar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static de.hammer.rccar.StartActivity.LOG_TAG;


public class DatenbankSource {
    private static final String LOG_TAG = DatenbankSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatenbankHelper dbHelper;

    private String[] spalten = {
            DatenbankHelper.SPALTE_ID,
            DatenbankHelper.SPALTE_SPEICHERNAME,
            DatenbankHelper.SPALTE_CHASSIS,
            DatenbankHelper.SPALTE_DRIVER,
            DatenbankHelper.SPALTE_EVENT,
            DatenbankHelper.SPALTE_DATE,
            DatenbankHelper.SPALTE_TRACK,
            DatenbankHelper.SPALTE_TQ,
            DatenbankHelper.SPALTE_QUALIFY,
            DatenbankHelper.SPALTE_MAIN,
            DatenbankHelper.SPALTE_FINISH,
            DatenbankHelper.SPALTE_BESTLAP,
            DatenbankHelper.SPALTE_RIDE_HEIGHT_V,
            DatenbankHelper.SPALTE_CAMBER_V,
            DatenbankHelper.SPALTE_TOE,
            DatenbankHelper.SPALTE_ARM_TYPE,
            DatenbankHelper.SPALTE_TOWER_TYPE,
            DatenbankHelper.SPALTE_CASTER_BLOCK_INSERT,
            DatenbankHelper.SPALTE_BULK_HEAD,
            DatenbankHelper.SPALTE_KICK_UP_ANGLE,
            DatenbankHelper.SPALTE_WHEEL_HEX,
            DatenbankHelper.SPALTE_FS_NOTES
    };



    public DatenbankSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new DatenbankHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public Datenbank createSheet(String datei, String chassis, String datum) {
        ContentValues values = new ContentValues();
        values.put(DatenbankHelper.SPALTE_SPEICHERNAME, datei);
        values.put(DatenbankHelper.SPALTE_CHASSIS, chassis);
        values.put(DatenbankHelper.SPALTE_DATE, datum);
        Log.d(LOG_TAG, "DATEI!!!! "+datei);

        long insertId = database.insert(DatenbankHelper.TABELLE_SETUP_SHEET, null, values);

        Cursor cursor = database.query(DatenbankHelper.TABELLE_SETUP_SHEET, spalten, DatenbankHelper.SPALTE_ID + "=" + insertId,null, null, null, null);

        cursor.moveToFirst();
        Datenbank Datenbank = cursorToDatenbank(cursor);
        cursor.close();

        return Datenbank;
    }

    private Datenbank cursorToDatenbank(Cursor cursor) {
        /**Kopf**/
        int idIndex = cursor.getColumnIndex(DatenbankHelper.SPALTE_ID);
        int idDateiname = cursor.getColumnIndex(DatenbankHelper.SPALTE_SPEICHERNAME);
        int idchassis = cursor.getColumnIndex(DatenbankHelper.SPALTE_CHASSIS);
        int idFahrer = cursor.getColumnIndex(DatenbankHelper.SPALTE_DRIVER);
        int idVeranstaltung = cursor.getColumnIndex(DatenbankHelper.SPALTE_EVENT);
        int idDatum = cursor.getColumnIndex(DatenbankHelper.SPALTE_DATE);
        int idStrecke = cursor.getColumnIndex(DatenbankHelper.SPALTE_TRACK);
        int idTq = cursor.getColumnIndex(DatenbankHelper.SPALTE_TQ);
        int idQualifizierung = cursor.getColumnIndex(DatenbankHelper.SPALTE_QUALIFY);
        int idHaupt = cursor.getColumnIndex(DatenbankHelper.SPALTE_MAIN);
        int idZiel = cursor.getColumnIndex(DatenbankHelper.SPALTE_FINISH);
        int idBesteRunde = cursor.getColumnIndex(DatenbankHelper.SPALTE_BESTLAP);
        /**Front Suspension**/
        int idFahrhoehe_vorne = cursor.getColumnIndex(DatenbankHelper.SPALTE_RIDE_HEIGHT_V);
        int idSturz_vorne = cursor.getColumnIndex(DatenbankHelper.SPALTE_CAMBER_V);
        int idSpitze = cursor.getColumnIndex(DatenbankHelper.SPALTE_TOE);
        int idArmTyp = cursor.getColumnIndex(DatenbankHelper.SPALTE_ARM_TYPE);
        int idTurmTyp = cursor.getColumnIndex(DatenbankHelper.SPALTE_TOWER_TYPE);
        int idRolleneinsatz = cursor.getColumnIndex(DatenbankHelper.SPALTE_CASTER_BLOCK_INSERT);
        int idSchottTyp = cursor.getColumnIndex(DatenbankHelper.SPALTE_BULK_HEAD);
        int idStosswinkel = cursor.getColumnIndex(DatenbankHelper.SPALTE_KICK_UP_ANGLE);
        int idReifenMitnehmer = cursor.getColumnIndex(DatenbankHelper.SPALTE_WHEEL_HEX);
        int idFsNotizen = cursor.getColumnIndex(DatenbankHelper.SPALTE_FS_NOTES);

        long id = cursor.getLong(idIndex);
        /**Kopf**/
        String dateiname = cursor.getString(idDateiname);
        String chassis = cursor.getString(idchassis);
        String fahrer = cursor.getString(idFahrer);
        String veranstaltung = cursor.getString(idVeranstaltung);
        String datum = cursor.getString(idDatum);
        String strecke = cursor.getString(idStrecke);
        String tq = cursor.getString(idTq);
        String qualifizierung = cursor.getString(idQualifizierung);
        String haupt = cursor.getString(idHaupt);
        String ziel = cursor.getString(idZiel);
        String besterunde = cursor.getString(idBesteRunde);
        /**Front Suspension**/
        String Fahrhoehe_vorne = cursor.getString(idFahrhoehe_vorne);
        String Sturz_vorne = cursor.getString(idSturz_vorne);
        String Spitze = cursor.getString(idSpitze);
        String ArmTyp = cursor.getString(idArmTyp);
        String TurmTyp = cursor.getString(idTurmTyp);
        String Rolleneinsatz = cursor.getString(idRolleneinsatz);
        String SchottTyp = cursor.getString(idSchottTyp);
        String Stosswinkel = cursor.getString(idStosswinkel);
        String ReifenMitnehmer = cursor.getString(idReifenMitnehmer);
        String FsNotizen = cursor.getString(idFsNotizen);


        Datenbank SetupSheet = new Datenbank(id, dateiname, chassis, fahrer, veranstaltung, datum,
                                             strecke, tq, qualifizierung, haupt, ziel, besterunde,
                                             Fahrhoehe_vorne, Sturz_vorne, Spitze, ArmTyp, TurmTyp,
                                             Rolleneinsatz, SchottTyp, Stosswinkel, ReifenMitnehmer,
                                             FsNotizen);

        return SetupSheet;
    }

    public List<Datenbank> getAllSavedSheets() {
        List<Datenbank> SetupSheetList = new ArrayList<>();

        Cursor cursor = database.query(DatenbankHelper.TABELLE_SETUP_SHEET, spalten, null, null, null, null, null);

        cursor.moveToFirst();
        Datenbank setupTabelle;

        while(!cursor.isAfterLast()) {
            setupTabelle = cursorToDatenbank(cursor);
            SetupSheetList.add(setupTabelle);
            Log.d(LOG_TAG, "Alle Daten: ");
            Log.d(LOG_TAG, "ID: " + setupTabelle.getId() + ", Inhalt: " + setupTabelle.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return SetupSheetList;
    }

    public List<Datenbank> filterDatenbank() {
        List<Datenbank> gefilterteDaten = new ArrayList<>();

        String[] Suchbegriffe = {"test", "B64"};

        Cursor cursor = database.query(DatenbankHelper.TABELLE_SETUP_SHEET, spalten, DatenbankHelper.SPALTE_SPEICHERNAME + "=? AND " + DatenbankHelper.SPALTE_CHASSIS + "=?", Suchbegriffe, null, null, null);

        cursor.moveToFirst();
        Datenbank gefundeneSheets;

        while(!cursor.isAfterLast()) {
            gefundeneSheets = cursorToDatenbank(cursor);
            gefilterteDaten.add(gefundeneSheets);
            Log.d(LOG_TAG, "Gefilterte Daten: ");
            Log.d(LOG_TAG, "ID: " + gefundeneSheets.getId() + ", Inhalt: " + gefundeneSheets.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return gefilterteDaten;
    }


    public Datenbank updateDatenbank(long id, String newFahrer, String newVeranstaltung, String newDatum,
                                     String newStrecke, String newTq, String newQualifizierung,
                                     String newHaupt, String newZiel, String newBesteRunde,
                                     String newFahrhoehe_vorne, String newSturz_vorne, String newSpitze,
                                     String newArmTyp, String newTurmTyp, String newRolleneinsatz,
                                     String newSchottTyp, String newStosswinkel, String newReifenMitnehmer_vorne,
                                     String newFsNotizen) {
        ContentValues Werte = new ContentValues();
        Werte.put(DatenbankHelper.SPALTE_DRIVER, newFahrer);
        Werte.put(DatenbankHelper.SPALTE_EVENT, newVeranstaltung);
        Werte.put(DatenbankHelper.SPALTE_DATE, newDatum);
        Werte.put(DatenbankHelper.SPALTE_TRACK, newStrecke);
        Werte.put(DatenbankHelper.SPALTE_TQ, newTq);
        Werte.put(DatenbankHelper.SPALTE_QUALIFY, newQualifizierung);
        Werte.put(DatenbankHelper.SPALTE_MAIN, newHaupt);
        Werte.put(DatenbankHelper.SPALTE_FINISH, newZiel);
        Werte.put(DatenbankHelper.SPALTE_BESTLAP, newBesteRunde);
        Werte.put(DatenbankHelper.SPALTE_RIDE_HEIGHT_V, newFahrhoehe_vorne);
        Werte.put(DatenbankHelper.SPALTE_CAMBER_V, newSturz_vorne);
        Werte.put(DatenbankHelper.SPALTE_TOE, newSpitze);
        Werte.put(DatenbankHelper.SPALTE_ARM_TYPE, newArmTyp);
        Werte.put(DatenbankHelper.SPALTE_TOWER_TYPE, newTurmTyp);
        Werte.put(DatenbankHelper.SPALTE_CASTER_BLOCK_INSERT, newRolleneinsatz);
        Werte.put(DatenbankHelper.SPALTE_BULK_HEAD, newSchottTyp);
        Werte.put(DatenbankHelper.SPALTE_KICK_UP_ANGLE, newStosswinkel);
        Werte.put(DatenbankHelper.SPALTE_WHEEL_HEX, newReifenMitnehmer_vorne);
        Werte.put(DatenbankHelper.SPALTE_FS_NOTES, newFsNotizen);

        database.update(DatenbankHelper.TABELLE_SETUP_SHEET, Werte,DatenbankHelper.SPALTE_ID + "=" + id,null);

        Cursor cursor = database.query(DatenbankHelper.TABELLE_SETUP_SHEET,
                spalten, DatenbankHelper.SPALTE_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Datenbank setupTabelle = cursorToDatenbank(cursor);
        cursor.close();

        return setupTabelle;
    }

    public void deleteSheet(Datenbank setupSheets) {
        long id = setupSheets.getId();

        database.delete(DatenbankHelper.TABELLE_SETUP_SHEET,DatenbankHelper.SPALTE_ID + "=" + id,null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + setupSheets.toString());
    }

    public Datenbank getDatenobject(long ID){
        Cursor cursor = database.query(DatenbankHelper.TABELLE_SETUP_SHEET,spalten, DatenbankHelper.SPALTE_ID + "=" + ID,null, null, null, null);

        cursor.moveToFirst();
        Datenbank Datenbank = cursorToDatenbank(cursor);
        cursor.close();

        return Datenbank;
    }

}
