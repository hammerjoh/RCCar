package de.hammer.rccar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class StartActivity extends AppCompatActivity {

    public float MZ;
    public float HZ;
    public String flaeche;
    public String volumen;
    public int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final Spinner uebersetzung = (Spinner) findViewById(R.id.dp_uebersetztung);
        final ArrayAdapter uebersettzungsadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.uebersetungen));
        uebersettzungsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uebersetzung.setAdapter(uebersettzungsadapter);
        final EditText va_MZ = (EditText) findViewById(R.id.va_MZ);
        final EditText va_HZ = (EditText) findViewById(R.id.va_HZ);
        final TextView va_flaeche = (TextView) findViewById(R.id.tv_result);
        Button btn_berechne = (Button)findViewById(R.id.btn_berechne);
        btn_berechne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (va_HZ.getText().toString().isEmpty()||va_MZ.getText().toString().isEmpty())
                {
                    va_flaeche.setText("");
                    if(va_HZ.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),R.string.HZ_eingeben,Toast.LENGTH_SHORT).show();
                    if(va_MZ.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(),R.string.MZ_eingeben,Toast.LENGTH_SHORT).show();
                }
                else{
                    pos = uebersetzung.getSelectedItemPosition();
                    MZ = Float.parseFloat(va_MZ.getText().toString());
                    HZ = Float.parseFloat(va_HZ.getText().toString());
                    //flaeche =String.format("%.2f",laenge * breite);
                    flaeche = String.format("%.2f",Float.parseFloat(getResources().getStringArray(R.array.uebersetungen)[pos])*HZ*MZ);
                    va_flaeche.setText(getString(R.string.Ergebnis) + flaeche);
                }
            }
        });
    }
}
