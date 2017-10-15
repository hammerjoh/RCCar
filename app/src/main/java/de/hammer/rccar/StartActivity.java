package de.hammer.rccar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class StartActivity extends AppCompatActivity {

    public float laenge;
    public float breite;
    public float hoehe;
    public String flaeche;
    public String volumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final EditText va_laenge = (EditText) findViewById(R.id.va_laenge);
        final EditText va_breite = (EditText) findViewById(R.id.va_breite);
        final EditText va_hoehe = (EditText) findViewById(R.id.va_hoehe);
        final TextView va_flaeche = (TextView) findViewById(R.id.tv_result);
        Button btn_berechne = (Button)findViewById(R.id.btn_berechne);
        btn_berechne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (va_breite.getText().toString().isEmpty()||va_laenge.getText().toString().isEmpty())
                {
                    va_flaeche.setText("");
                    if(va_breite.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),R.string.breite_eingeben,Toast.LENGTH_SHORT).show();
                    if(va_laenge.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),R.string.laenge_eingeben,Toast.LENGTH_SHORT).show();
                }
                else if (va_hoehe.getText().toString().isEmpty()){
                    laenge = Float.parseFloat(va_laenge.getText().toString());
                    breite = Float.parseFloat(va_breite.getText().toString());
                    flaeche =String.format("%.2f",laenge * breite);
                    va_flaeche.setText(getString(R.string.Ergebnis_flaeche) + flaeche + getString(R.string.cm2));
                }
                else {
                    laenge = Float.parseFloat(va_laenge.getText().toString());
                    breite = Float.parseFloat(va_breite.getText().toString());
                    hoehe = Float.parseFloat(va_hoehe.getText().toString());
                    volumen = String.format("%.1f",laenge * breite * hoehe);
                    va_flaeche.setText(getString(R.string.Ergebnis_volmen) + volumen + getString(R.string.cm3));
                }
            }
        });
    }
}
