package de.hammer.rccar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import static de.hammer.rccar.StartActivity.LOG_TAG;
import static java.lang.String.valueOf;

public class B6_SheetActivity extends AppCompatActivity implements View.OnClickListener {

    /**   Variablen Deklarationen   **/

    private static String clear = "";
    private long   ID;
    /**Kopfzeile**/
    private String Fahrer;
    private String Veranstaltung;
    private String Datum;
    private String Strecke;
    private String Tq;
    private String Qualifizierung;
    private String Haupt;
    private String Ziel;
    private String BesteRunde;
    /**Front Suspension**/
    private String Fahrhoehe_vorne;
    private String Sturz_vorne;
    private String Spitze;
    private String ArmTyp;
    private String TurmTyp;
    private String Rolleneinsatz;
    private String SchottTyp;
    private String Stosswinkel;
    private String ReifenMitnehmer_vorne;
    private String FsNotizen;

    /** Textfelder Deklarationen   **/

    private Button btn_Zuruecksetzen;
    private EditText eT_Fahrer;
    private EditText eT_Veranstaltung;
    private EditText eT_Datum;
    private EditText eT_Strecke;
    private CheckBox cB_Tq;
    private EditText eT_Qualifizierung;
    private EditText eT_Haupt;
    private EditText eT_Ziel;
    private EditText eT_BesteRunde;
    private EditText eT_Fahrhoehe_vorne;
    private EditText eT_Sturz_vorne;
    private EditText eT_Spitze;
    private EditText eT_ArmTyp;
    private EditText eT_TurmTyp;
    private EditText eT_Rolleneinsatz;
    private EditText eT_SchottTyp;
    private EditText eT_Stosswinkel;
    private EditText eT_ReifenMitnehmer_vorne;
    private EditText eT_FsNotizen;


    public DatenbankSource dataSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_b6_setup);
        setContentView(R.layout.b6_setup_layout);


        ID = getIntent().getExtras().getLong("ID");

        final ZoomActivity zoomLayout = findViewById(R.id.zoom_layout);
        zoomLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                zoomLayout.init(B6_SheetActivity.this);
                return false;
            }
        });

        dataSource = new DatenbankSource(this);
        /**Kopf**/
        btn_Zuruecksetzen =findViewById(R.id.button_Zuruecksetzen);
        btn_Zuruecksetzen.setOnClickListener(this);
        eT_Fahrer = findViewById(R.id.editText_Fahrer);
        eT_Veranstaltung = findViewById(R.id.editText_Veranstaltung);
        eT_Datum = findViewById(R.id.editText_Datum);
        eT_Strecke = findViewById(R.id.editText_Strecke);
        cB_Tq = findViewById(R.id.checkBox_Tq);
        eT_Qualifizierung = findViewById(R.id.editText_Qualifizierung);
        eT_Haupt = findViewById(R.id.editText_Haupt);
        eT_Ziel = findViewById(R.id.editText_Ziel);
        eT_BesteRunde = findViewById(R.id.editText_BesteRundenZeit);
        /**Front Suspension**/
        eT_Fahrhoehe_vorne = findViewById(R.id.editText_FahrhoeheV);
        eT_Sturz_vorne = findViewById(R.id.editText_SturzV);
        eT_Spitze = findViewById(R.id.editText_Spitze);
        eT_ArmTyp = findViewById(R.id.editText_ArmTyp);
        eT_TurmTyp = findViewById(R.id.editText_TurmTyp);
        eT_Rolleneinsatz = findViewById(R.id.editText_Rolleneinsatz);
        eT_SchottTyp = findViewById(R.id.editText_SchottTyp);
        eT_Stosswinkel = findViewById(R.id.editText_StossWinkel);
        eT_ReifenMitnehmer_vorne = findViewById(R.id.editText_ReifenMitnehmerV);
        eT_FsNotizen = findViewById(R.id.editText_FSNotizen);
    }

    private void holeDatenvonDatenbank(){
        Datenbank Blattdaten = dataSource.getDatenobject(ID);
        /**Kopf**/
        Fahrer = Blattdaten.getFahrer();
        Veranstaltung = Blattdaten.getVeranstaltung();
        Datum = Blattdaten.getDatum();
        Strecke = Blattdaten.getStrecke();
        Tq = Blattdaten.getTq();
        Qualifizierung = Blattdaten.getQualifizierung();
        Haupt = Blattdaten.getHaupt();
        Ziel = Blattdaten.getZiel();
        BesteRunde = Blattdaten.getBesterunde();
        /**Front Suspension**/
        Fahrhoehe_vorne = Blattdaten.getFahrhoehe_vorne();
        Sturz_vorne = Blattdaten.getSturz_vorne();
        Spitze = Blattdaten.getSpitze();
        ArmTyp = Blattdaten.getArmtyp();
        TurmTyp = Blattdaten.getTurmtyp();
        Rolleneinsatz = Blattdaten.getRolleneinsatz();
        SchottTyp = Blattdaten.getSchotttyp();
        Stosswinkel = Blattdaten.getStosswinkel();
        ReifenMitnehmer_vorne = Blattdaten.getReifenmitnehmer();
        FsNotizen = Blattdaten.getFsnotizen();
    }

    private void setzeVariablen(){
        /**Kopf**/
        eT_Fahrer.setText(Fahrer);
        eT_Veranstaltung.setText(Veranstaltung);
        eT_Datum.setText(Datum);
        eT_Strecke.setText(Strecke);
        cB_Tq.setChecked(Boolean.parseBoolean(Tq));
        eT_Qualifizierung.setText(Qualifizierung);
        eT_Haupt.setText(Haupt);
        eT_Ziel.setText(Ziel);
        eT_BesteRunde.setText(BesteRunde);
        /**Front Suspension**/
        eT_Fahrhoehe_vorne.setText(Fahrhoehe_vorne);
        eT_Sturz_vorne.setText(Sturz_vorne);
        eT_Spitze.setText(Spitze);
        eT_ArmTyp.setText(ArmTyp);
        eT_TurmTyp.setText(TurmTyp);
        eT_Rolleneinsatz.setText(Rolleneinsatz);
        eT_SchottTyp.setText(SchottTyp);
        eT_Stosswinkel.setText(Stosswinkel);
        eT_ReifenMitnehmer_vorne.setText(ReifenMitnehmer_vorne);
        eT_FsNotizen.setText(FsNotizen);
    }

    private void speicherDateninDatenbank(){
        /**Kopf**/
        Fahrer = eT_Fahrer.getText().toString();
        Veranstaltung = eT_Veranstaltung.getText().toString();
        Datum = eT_Datum.getText().toString();
        Strecke = eT_Strecke.getText().toString();
        Tq = valueOf(cB_Tq.isChecked());
        Qualifizierung = eT_Qualifizierung.getText().toString();
        Haupt = eT_Haupt.getText().toString();
        Ziel = eT_Ziel.getText().toString();
        BesteRunde = eT_BesteRunde.getText().toString();
        /**Front Suspension**/
        Fahrhoehe_vorne = eT_Fahrhoehe_vorne.getText().toString();
        Sturz_vorne = eT_Sturz_vorne.getText().toString();
        Spitze = eT_Spitze.getText().toString();
        ArmTyp = eT_ArmTyp.getText().toString();
        TurmTyp = eT_TurmTyp.getText().toString();
        Rolleneinsatz = eT_Rolleneinsatz.getText().toString();
        SchottTyp = eT_SchottTyp.getText().toString();
        Stosswinkel = eT_Stosswinkel.getText().toString();
        ReifenMitnehmer_vorne = eT_ReifenMitnehmer_vorne.getText().toString();
        FsNotizen = eT_FsNotizen.getText().toString();

        dataSource.updateDatenbank(ID, Fahrer, Veranstaltung, Datum, Strecke, Tq, Qualifizierung,
                                   Haupt, Ziel, BesteRunde, Fahrhoehe_vorne, Sturz_vorne, Spitze,
                                   ArmTyp, TurmTyp, Rolleneinsatz, SchottTyp, Stosswinkel,
                                   ReifenMitnehmer_vorne, FsNotizen);
    }

    private void alleFelderZuruecksetzen(){
        /**Kopf**/
        eT_Fahrer.setText(clear);
        eT_Veranstaltung.setText(clear);
        eT_Datum.setText(clear);
        eT_Strecke.setText(clear);
        cB_Tq.setChecked(false);
        eT_Qualifizierung.setText(clear);
        eT_Haupt.setText(clear);
        eT_Ziel.setText(clear);
        eT_BesteRunde.setText(clear);
        /**Front Suspension**/
        eT_Fahrhoehe_vorne.setText(clear);
        eT_Sturz_vorne.setText(clear);
        eT_Spitze.setText(clear);
        eT_ArmTyp.setText(clear);
        eT_TurmTyp.setText(clear);
        eT_Rolleneinsatz.setText(clear);
        eT_SchottTyp.setText(clear);
        eT_Stosswinkel.setText(clear);
        eT_ReifenMitnehmer_vorne.setText(clear);
        eT_FsNotizen.setText(clear);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Die Datenquelle wird von B6 Sheet geöffnet.");
        dataSource.open();
        holeDatenvonDatenbank();
        setzeVariablen();
    }

    @Override
    protected void  onPause(){
        super.onPause();
        speicherDateninDatenbank();
        Log.d(LOG_TAG, "Die Datenquelle2 wird von B6 Sheet geschlossen.");
        dataSource.close();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_Zuruecksetzen){
            alleFelderZuruecksetzen();
        }
    }
}
