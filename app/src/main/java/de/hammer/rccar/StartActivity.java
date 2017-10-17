package de.hammer.rccar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    /**   Variablen   **/

    public float MZ;
    public float HZ;
    public String uebersetzung;
    public int pos;

    /**   Deklarationen   **/

    private Spinner auswahl;
    private EditText va_MZ;
    private EditText va_HZ;
    private TextView va_flaeche;
    private Button btn_berechne;
    private Button btn_b6;

    /** Datenbank Deklarationen   **/

    public static final String LOG_TAG = StartActivity.class.getSimpleName();
    private DatenbankSource dataSource;

    /**   Ende Datenbank Deklarationen   **/

    /**    Programm   **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /**   Datenbank Test   **/

        Datenbank test = new Datenbank("Birnen", 5, 102);
        Log.d(LOG_TAG, "Inhalt der Testmemo: " + test.toString());

        dataSource = new DatenbankSource(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();


        /**   Ende Datenbank Test**/

        va_MZ = (EditText) findViewById(R.id.va_MZ);
        va_HZ = (EditText) findViewById(R.id.va_HZ);
        va_flaeche = (TextView) findViewById(R.id.tv_result);

        auswahl = (Spinner) findViewById(R.id.dp_uebersetztung);
        final ArrayAdapter uebersettzungsadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.uebersetungen));
        uebersettzungsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        auswahl.setAdapter(uebersettzungsadapter);

        btn_berechne = (Button) findViewById(R.id.btn_berechne);
        btn_berechne.setOnClickListener(this);
        btn_b6 = (Button) findViewById(R.id.btn_b6);
        btn_b6.setOnClickListener(this);
    }

    /**    OnClickListener   **/

    @Override
    public void onClick(View v) {
        int btn =  v.getId();
        if (btn == R.id.btn_berechne) {
            if (va_HZ.getText().toString().isEmpty()||va_MZ.getText().toString().isEmpty())
            {
                va_flaeche.setText("");
                if(va_HZ.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),R.string.HZ_eingeben,Toast.LENGTH_SHORT).show();
                if(va_MZ.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),R.string.MZ_eingeben,Toast.LENGTH_SHORT).show();
            }
            else{
                pos = auswahl.getSelectedItemPosition();
                MZ = Float.parseFloat(va_MZ.getText().toString());
                HZ = Float.parseFloat(va_HZ.getText().toString());
                uebersetzung = String.format("%.2f",Float.parseFloat(getResources().getStringArray(R.array.uebersetungen)[pos])*HZ*MZ);
                va_flaeche.setText(getString(R.string.Ergebnis) + uebersetzung);
            }
        }

        if (btn == R.id.btn_b6){
            Intent intent = new Intent(StartActivity.this,SetupSheetActivity.class);
            startActivity(intent);
        }
    }


}
