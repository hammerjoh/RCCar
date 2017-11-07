package de.hammer.rccar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DatenbankSource {
    private static final String LOG_TAG = DatenbankSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatenbankHelper dbHelper;

    private String[] spalten = {
            DatenbankHelper.SPALTE_ID,
            DatenbankHelper.SPALTE_SPEICHERNAME,
            DatenbankHelper.SPALTE_DRIVER,
            DatenbankHelper.SPALTE_EVENT,
            DatenbankHelper.SPALTE_TRACK
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

    public Datenbank createSheet(String datei) {
        ContentValues values = new ContentValues();
        values.put(DatenbankHelper.SPALTE_SPEICHERNAME, datei);

        long insertId = database.insert(DatenbankHelper.TABELLE_SETUP_SHEET, null, values);

        Cursor cursor = database.query(DatenbankHelper.TABELLE_SETUP_SHEET,spalten, DatenbankHelper.SPALTE_ID + "=" + insertId,null, null, null, null);

        cursor.moveToFirst();
        Datenbank Datenbank = cursorToDatenbank(cursor);
        cursor.close();

        return Datenbank;
    }

    private Datenbank cursorToDatenbank(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatenbankHelper.SPALTE_ID);
        int idDateiname = cursor.getColumnIndex(DatenbankHelper.SPALTE_SPEICHERNAME);
        int idFahrer = cursor.getColumnIndex(DatenbankHelper.SPALTE_DRIVER);
        int idVeranstaltung = cursor.getColumnIndex(DatenbankHelper.SPALTE_EVENT);
        int idStrecke = cursor.getColumnIndex(DatenbankHelper.SPALTE_TRACK);

        long id = cursor.getLong(idIndex);
        String dateiname = cursor.getString(idDateiname);
        String fahrer = cursor.getString(idFahrer);
        String veranstaltung = cursor.getString(idVeranstaltung);
        String strecke = cursor.getString(idStrecke);

        Datenbank SetupSheet = new Datenbank(id, dateiname, fahrer,veranstaltung,strecke);

        return SetupSheet;
    }

    public List<Datenbank> getAllSavedSheets() {
        List<Datenbank> SetupSheetList = new ArrayList<>();

        Cursor cursor = database.query(DatenbankHelper.TABELLE_SETUP_SHEET,
                spalten, null, null, null, null, null);

        cursor.moveToFirst();
        Datenbank setupTabelle;

        while(!cursor.isAfterLast()) {
            setupTabelle = cursorToDatenbank(cursor);
            SetupSheetList.add(setupTabelle);
            Log.d(LOG_TAG, "ID: " + setupTabelle.getId() + ", Inhalt: " + setupTabelle.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return SetupSheetList;
    }

    public Datenbank updateDatenbank(long id, String newFahrer, String newVeranstaltung, String newStrecke) {
        ContentValues Werte = new ContentValues();
        Werte.put(DatenbankHelper.SPALTE_DRIVER, newFahrer);
        Werte.put(DatenbankHelper.SPALTE_EVENT, newVeranstaltung);
        Werte.put(DatenbankHelper.SPALTE_TRACK, newStrecke);

        database.update(DatenbankHelper.TABELLE_SETUP_SHEET,
                Werte,
                DatenbankHelper.SPALTE_ID + "=" + id,
                null);

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

}
