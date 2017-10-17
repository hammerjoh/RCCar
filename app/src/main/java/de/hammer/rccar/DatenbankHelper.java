package de.hammer.rccar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatenbankHelper extends SQLiteOpenHelper{


    private static final String LOG_TAG = DatenbankHelper.class.getSimpleName();
    public static final String DB_NAME = "sheets_data.db";
    public static final int DB_VERSION = 1;

    public static final String TABELLE_SETUP_SHEET = "setup_sheet";

    public static final String SPALTE_ID = "_id";
    public static final String SPALTE_PRODUCT = "product";
    public static final String SPALTE_QUANTITY = "quantity";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABELLE_SETUP_SHEET +
                    "(" + SPALTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SPALTE_PRODUCT + " TEXT NOT NULL, " +
                    SPALTE_QUANTITY + " INTEGER NOT NULL);";


    public DatenbankHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
