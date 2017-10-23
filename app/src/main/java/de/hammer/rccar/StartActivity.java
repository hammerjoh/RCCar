package de.hammer.rccar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    /**   Variablen   **/

    public int MZ;
    public int HZ;
    public String Dateiname = "";
    public String uebersetzung;
    public int pos;

    /**   Deklarationen   **/

    private Spinner auswahl;
    private EditText va_MZ;
    private EditText va_HZ;
    private EditText va_Dateiname;
    private TextView va_flaeche;
    private Button btn_berechne;
    private Button btn_b6;
    private Button btn_erzeuge;
    private RelativeLayout layout;

    /** Datenbank Deklarationen   **/

    public static final String LOG_TAG = StartActivity.class.getSimpleName();
    public DatenbankSource dataSource;

    /**   Ende Datenbank Deklarationen   **/

    /**    Programm   **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /**   Datenbank Test   **/

        dataSource = new DatenbankSource(this);


        /**   Ende Datenbank Test**/

        va_MZ = (EditText) findViewById(R.id.va_MZ);
        va_HZ = (EditText) findViewById(R.id.va_HZ);
        va_HZ.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                onClick(btn_berechne);
                return false;
            }
        });
        va_Dateiname = (EditText) findViewById(R.id.va_dateiname);
        va_Dateiname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                onClick(btn_erzeuge);
                return false;
            }
        });
        va_flaeche = (TextView) findViewById(R.id.tv_result);

        auswahl = (Spinner) findViewById(R.id.dp_uebersetztung);
        final ArrayAdapter uebersettzungsadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.uebersetungen));
        uebersettzungsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        auswahl.setAdapter(uebersettzungsadapter);

        btn_berechne = (Button) findViewById(R.id.btn_berechne);
        btn_berechne.setOnClickListener(this);
        btn_b6 = (Button) findViewById(R.id.btn_b6);
        btn_b6.setOnClickListener(this);
        btn_erzeuge = (Button) findViewById(R.id.btn_erzeuge);
        btn_erzeuge.setOnClickListener(this);
        layout = (RelativeLayout) findViewById(R.id.layout);
        layout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }}
        });

        /**   Tastatur ausblenden und Fokus von Textview entfernen, wenn auf Layout geklickt wird   **/
        findViewById(R.id.layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                layout.requestFocus();
                return false;
            }
        });
    }

    private void showAllListEntries () {
        List<Datenbank> savedSheetsList = dataSource.getAllSavedSheets();

        ArrayAdapter<Datenbank> setupSheetsArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_1,
                savedSheetsList);

        ListView shoppingMemosListView = (ListView) findViewById(R.id.listview_saved_sheets);
        shoppingMemosListView.setAdapter(setupSheetsArrayAdapter);
    }


    /**    OnClickListener   **/

    @Override
    public void onClick(View v) {
        int btn =  v.getId();
        if (btn == R.id.btn_berechne) {
            layout.requestFocus();
            if (va_HZ.getText().toString().isEmpty()||va_MZ.getText().toString().isEmpty())
            {
                va_flaeche.setText("");
                if(va_HZ.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),R.string.HZ_eingeben,Toast.LENGTH_SHORT).show();
                if(va_MZ.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),R.string.MZ_eingeben,Toast.LENGTH_SHORT).show();
            }
            else{
                pos = auswahl.getSelectedItemPosition();
                MZ = Integer.parseInt(va_MZ.getText().toString());
                HZ = Integer.parseInt(va_HZ.getText().toString());
                uebersetzung = String.format("%.2f",(HZ/MZ)*Float.parseFloat(getResources().getStringArray(R.array.uebersetungen)[pos]));
                va_flaeche.setText(getString(R.string.Ergebnis) + uebersetzung);
            }
        }

        if (btn == R.id.btn_b6){
            Intent intent = new Intent(StartActivity.this,SetupSheetActivity.class);
            startActivity(intent);
        }

        if (btn == R.id.btn_erzeuge){
            layout.requestFocus();
            if (va_Dateiname.getText().toString().isEmpty())Toast.makeText(getApplicationContext(),R.string.Dateinamen_eingeben,Toast.LENGTH_SHORT).show();
            else {
                Dateiname = va_Dateiname.getText().toString();

                Datenbank setupTabelle = dataSource.createSheet(Dateiname);
                Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
                Log.d(LOG_TAG, "ID: " + setupTabelle.getId() + ", Inhalt: " + setupTabelle.toString());

                Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
                showAllListEntries();

                va_Dateiname.setText("");
            }
        }


    }
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }
}
