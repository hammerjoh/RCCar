package de.hammer.rccar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatenbankHelper extends SQLiteOpenHelper{

    /**   Deklarationen   **/

    private static final String LOG_TAG = DatenbankHelper.class.getSimpleName();
    public static final String DB_NAME = "sheets_data.db";
    public static final int DB_VERSION = 1;
    //Blaetter
    public static final String TABELLE_SETUP_SHEET = "setup_sheet";

    //Kopf
    public static final String SPALTE_ID                    = "_id";
    public static final String SPALTE_SPEICHERNAME          = "name";
    public static final String SPALTE_DRIVER                = "driver";
    public static final String SPALTE_EVENT                 = "event";
    public static final String SPALTE_DATE                  = "date";
    public static final String SPALTE_TRACK                 = "track";
    public static final String SPALTE_QUALIFY               = "qualify";
    public static final String SPALTE_TQ                    = "tq";
    public static final String SPALTE_MAIN                  = "main";
    public static final String SPALTE_FINISH                = "finish";
    public static final String SPALTE_BESTLAP               = "bestlap";
    //Front Suspension
    public static final String SPALTE_RIDE_HEIGHT           = "ride_height";
    public static final String SPALTE_CAMBER                = "camber";
    public static final String SPALTE_TOE                   = "toe";
    public static final String SPALTE_ARM_TYPE              = "arm_type";
    public static final String SPALTE_TOWER_TYPE            = "tower_type";
    public static final String SPALTE_CASTER_BLOCK_INSERT   = "caster_block_insert";
    public static final String SPALTE_BULK_HEAD             = "bulk_head";
    public static final String SPALTE_KICK_UP_ANGLE         = "kick_up_angle";
    public static final String SPALTE_WHEEL_HEX             = "wheel_hex";
    public static final String SPALTE_FS_NOTES              = "fs_notes";
    public static final String SPALTE_ARB_NONE              = "arb_none";
    public static final String SPALTE_ARB_WHITE             = "arb_white";
    public static final String SPALTE_ARB_GRAY              = "arb_gray";
    public static final String SPALTE_ARB_BLUE              = "arb_blue";
    public static final String SPALTE_ARB_OTHER             = "arb_other";
    public static final String SPALTE_BUMPER_STEER_SPACING  = "bumper_steer_spacing";
    public static final String SPALTE_AXLE_HEIGHT_0         = "axle_height_0";
    public static final String SPALTE_AXLE_HEIGHT_1         = "axle_height_1";
    public static final String SPALTE_AXLE_HEIGHT_2         = "axle_height_2";
    public static final String SPALTE_AXLE_HEIGHT_3         = "axle_height_3";
    public static final String SPALTE_STEERING_PLATE        = "steering_plate";
    public static final String SPALTE_STEERING_STOP_SPACING = "steering_stop_spacing";
    public static final String SPALTE_CASTER_BLOCK_SPACING  = "caster_block_spacing";
    public static final String SPALTE_BALL_STUD_SPACING_1O  = "ball_stud_spacing_1o";
    public static final String SPALTE_BALL_STUD_SPACING_2O  = "ball_stud_spacing_2o";
    public static final String SPALTE_BALL_STUD_SPACING_3O  = "ball_stud_spacing_3o";
    public static final String SPALTE_BALL_STUD_SPACING_AO  = "ball_stud_spacing_ao";
    public static final String SPALTE_BALL_STUD_SPACING_BO  = "ball_stud_spacing_bo";
    public static final String SPALTE_BALL_STUD_SPACING_1U  = "ball_stud_spacing_1u";
    public static final String SPALTE_BALL_STUD_SPACING_2U  = "ball_stud_spacing_2u";
    public static final String SPALTE_BALL_STUD_SPACING_3U  = "ball_stud_spacing_3u";
    public static final String SPALTE_BALL_STUD_SPACING_AU  = "ball_stud_spacing_au";
    public static final String SPALTE_BALL_STUD_SPACING_BU  = "ball_stud_spacing_bu";
    public static final String SPALTE_BALL_STUD_SPACING_CU  = "ball_stud_spacing_cu";

    /**   SQL String   **/

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABELLE_SETUP_SHEET +
                    "(" + SPALTE_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SPALTE_SPEICHERNAME          + " TEXT, " +
                    SPALTE_DRIVER                + " TEXT, " +
                    SPALTE_EVENT                 + " TEXT, " +
                    SPALTE_DATE                  + " INTEGER, " +
                    SPALTE_TRACK                 + " TEXT, " +
                    SPALTE_QUALIFY               + " TEXT, " +
                    SPALTE_TQ                    + " INTEGER, " +
                    SPALTE_MAIN                  + " TEXT, " +
                    SPALTE_FINISH                + " TEXT, " +
                    SPALTE_BESTLAP               + " TEXT, " +
                    SPALTE_RIDE_HEIGHT           + " TEXT, " +
                    SPALTE_CAMBER                + " TEXT, " +
                    SPALTE_TOE                   + " TEXT, " +
                    SPALTE_ARM_TYPE              + " TEXT, " +
                    SPALTE_TOWER_TYPE            + " TEXT, " +
                    SPALTE_CASTER_BLOCK_INSERT   + " TEXT, " +
                    SPALTE_BULK_HEAD             + " TEXT, " +
                    SPALTE_KICK_UP_ANGLE         + " TEXT, " +
                    SPALTE_WHEEL_HEX             + " TEXT, " +
                    SPALTE_FS_NOTES              + " TEXT, " +
                    SPALTE_ARB_NONE              + " INTEGER, " +
                    SPALTE_ARB_WHITE             + " INTEGER, " +
                    SPALTE_ARB_GRAY              + " INTEGER, " +
                    SPALTE_ARB_BLUE              + " INTEGER, " +
                    SPALTE_ARB_OTHER             + " INTEGER, " +
                    SPALTE_BUMPER_STEER_SPACING  + " TEXT, " +
                    SPALTE_AXLE_HEIGHT_0         + " INTEGER, " +
                    SPALTE_AXLE_HEIGHT_1         + " INTEGER, " +
                    SPALTE_AXLE_HEIGHT_2         + " INTEGER, " +
                    SPALTE_AXLE_HEIGHT_3         + " INTEGER, " +
                    SPALTE_STEERING_PLATE        + " TEXT, " +
                    SPALTE_STEERING_STOP_SPACING + " TEXT, " +
                    SPALTE_CASTER_BLOCK_SPACING  + " TEXT, " +
                    SPALTE_BALL_STUD_SPACING_1O  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_2O  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_3O  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_AO  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_BO  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_1U  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_2U  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_3U  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_AU  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_BU  + " INTEGER, " +
                    SPALTE_BALL_STUD_SPACING_CU  + " INTEGER );";


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
