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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    /**   Variablen   **/

    public int MZ;
    public int HZ;
    public String uebersetzung;
    public int pos;

    /**   Deklarationen   **/

    private Spinner auswahl;
    private EditText va_MZ;
    private EditText va_HZ;
    private TextView va_flaeche;
    private Button btn_berechne;
    private Button btn_setupSheets;
    private RelativeLayout layout;


    /** Datenbank Deklarationen   **/

    public static final String LOG_TAG = StartActivity.class.getSimpleName();
    public DatenbankSource dataSource;



    /**    Programm   **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        dataSource = new DatenbankSource(this);

        va_MZ = findViewById(R.id.va_MZ);
        va_HZ = findViewById(R.id.va_HZ);
        va_HZ.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                onClick(btn_berechne);
                return false;
            }
        });
        va_flaeche = findViewById(R.id.tv_result);

        auswahl = findViewById(R.id.dp_uebersetztung);
        final ArrayAdapter<String> uebersettzungsadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.uebersetungen));
        uebersettzungsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        auswahl.setAdapter(uebersettzungsadapter);

        btn_berechne = findViewById(R.id.btn_berechne);
        btn_berechne.setOnClickListener(this);
        btn_setupSheets = findViewById(R.id.btn_setupSheets);
        btn_setupSheets.setOnClickListener(this);


        /*   Tastatur ausblenden und Fokus von Textview entfernen, wenn auf Layout geklickt wird   */

        layout = findViewById(R.id.layout);
        layout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }}
        });

        findViewById(R.id.layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.requestFocus();
                return false;
            }
        });
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
                if(va_HZ.getText().toString().isEmpty()) va_HZ.setError(getString(R.string.HZ_eingeben));
                if(va_MZ.getText().toString().isEmpty()) va_MZ.setError(getString(R.string.MZ_eingeben));
            }
            else{
                pos = auswahl.getSelectedItemPosition();
                MZ = Integer.parseInt(va_MZ.getText().toString());
                HZ = Integer.parseInt(va_HZ.getText().toString());
                uebersetzung = String.format("%.2f",(HZ/MZ)*Float.parseFloat(getResources().getStringArray(R.array.uebersetungen)[pos]));
                va_flaeche.setText(getString(R.string.Ergebnis) + uebersetzung);
            }
        }

        if (btn == R.id.btn_setupSheets){
            Intent intent = new Intent(StartActivity.this,SetupSheetActivity.class);
            startActivity(intent);
        }


    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird von StartActivity geöffnet.");
        dataSource.open();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird von StartActivity geschlossen.");
        dataSource.close();
    }

}
