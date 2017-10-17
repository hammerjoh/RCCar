package de.hammer.rccar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DatenbankSource {
    private static final String LOG_TAG = DatenbankSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatenbankHelper dbHelper;


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
}
