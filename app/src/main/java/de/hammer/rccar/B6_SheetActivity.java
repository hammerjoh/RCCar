package de.hammer.rccar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import static de.hammer.rccar.StartActivity.LOG_TAG;

public class B6_SheetActivity extends AppCompatActivity {

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


    public DatenbankSource dataSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b6_setup_layout);

        final ZoomActivity zoomLayout = findViewById(R.id.zoom_layout);
        zoomLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                zoomLayout.init(B6_SheetActivity.this);
                return false;
            }
        });

        dataSource = new DatenbankSource(this);

        eT_Fahrer = findViewById(R.id.editText_Fahrer);
        eT_Veranstaltung = findViewById(R.id.editText_Veranstaltung);
        eT_Datum = findViewById(R.id.editText_Datum);
        eT_Strecke = findViewById(R.id.editText_Strecke);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Die Datenquelle2 wird geöffnet.");
        dataSource.open();
    }

    @Override
    protected void  onPause(){
        super.onPause();

        Fahrer = eT_Fahrer.getText().toString();
        Veranstaltung = eT_Veranstaltung.getText().toString();
        Datum = Integer.parseInt(eT_Datum.getText().toString());
        Strecke = eT_Strecke.getText().toString();

        dataSource.updateDatenbank(Datum, Fahrer, Veranstaltung, Strecke);


        Log.d(LOG_TAG, "Die Datenquelle2 wird geschlossen.");
        dataSource.close();

    }
}
