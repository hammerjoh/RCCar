package de.hammer.rccar;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import javax.sql.DataSource;

import static de.hammer.rccar.StartActivity.LOG_TAG;

public class SetupSheetActivity extends AppCompatActivity{

    /**   Variablen Deklarationen   **/

    public String Fahrer;
    public String Veranstaltung;
    public int Datum;
    public String Strecke;

    /** Textfelder Deklarationen   **/

    private EditText eT_Fahrer;
    private EditText eT_Veranstaltung;
    private EditText eT_Datum;
    private EditText eT_Strecke;


    public DatenbankSource dataSource2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b6_setup_layout);


        final ZoomActivity zoomLayout = (ZoomActivity) findViewById(R.id.zoom_layout);
         zoomLayout.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
        zoomLayout.init(SetupSheetActivity.this);
        return false;
        }
        });

        dataSource2 = new DatenbankSource(this);

        eT_Fahrer = (EditText) findViewById(R.id.editText_Fahrer);
        eT_Veranstaltung = (EditText) findViewById(R.id.editText_Veranstaltung);
        eT_Datum = (EditText) findViewById(R.id.editText_Datum);
        eT_Strecke = (EditText) findViewById(R.id.editText_Strecke);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Die Datenquelle2 wird geöffnet.");
        dataSource2.open();
    }

    @Override
    protected void  onPause(){
        super.onPause();
        Log.d(LOG_TAG, " FEHLERSUCHE!!! ");

        Fahrer = eT_Fahrer.getText().toString();
        Veranstaltung = eT_Veranstaltung.getText().toString();
        Datum = Integer.parseInt(eT_Datum.getText().toString());
        Strecke = eT_Strecke.getText().toString();

        Datenbank setupTabelle = dataSource2.updateDatenbank(Datum, Fahrer, Veranstaltung, Strecke);


        Log.d(LOG_TAG, "Die Datenquelle2 wird geschlossen.");
        dataSource2.close();

    }

}
