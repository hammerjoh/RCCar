package de.hammer.rccar;

import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final EditText va_laenge = (EditText) findViewById(R.id.va_laenge);
        final EditText va_breite = (EditText) findViewById(R.id.va_breite);
        final TextView va_flaeche = (TextView) findViewById(R.id.tv_result);
        Button btn_berechne = (Button)findViewById(R.id.btn_berechne);
        btn_berechne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (va_breite.getText().toString().isEmpty()||va_laenge.getText().toString().isEmpty())
                {
                    va_flaeche.setText("");
                    if(va_breite.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),"Bitte Breite eingeben!",Toast.LENGTH_SHORT).show();
                    if(va_laenge.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),"Bitte Länge eingeben!",Toast.LENGTH_SHORT).show();
                }
                else {
                    int laenge = Integer.parseInt(va_laenge.getText().toString());
                    int breite = Integer.parseInt(va_breite.getText().toString());
                    int flaeche = laenge * breite;
                    va_flaeche.setText("Der Flächeninhalt beträgt: " + flaeche);
                }
            }
        });
    }
}
