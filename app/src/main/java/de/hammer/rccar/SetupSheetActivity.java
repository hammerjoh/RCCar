package de.hammer.rccar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static de.hammer.rccar.StartActivity.LOG_TAG;

public class SetupSheetActivity extends AppCompatActivity implements View.OnClickListener {

    /**   Deklarationen   **/

    public DatenbankSource dataSource;

    public String dateiname;

    private Button btn_neu;
    private Button btn_oeffnen;
    private Button btn_loeschen;
    private Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_sheet_layout);

        dataSource = new DatenbankSource(this);

        btn_neu = (Button) findViewById(R.id.btn_neu);
        btn_neu.setOnClickListener(this);
        btn_oeffnen = (Button) findViewById(R.id.btn_oeffnen);
        btn_oeffnen.setOnClickListener(this);
        btn_loeschen = (Button) findViewById(R.id.btn_loeschen);
        btn_loeschen.setOnClickListener(this);
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int btn =  v.getId();

        if (btn == R.id.btn_neu){
            neuesBlattDialog();
        }

        if (btn == R.id.btn_oeffnen){

        }

        if (btn == R.id.btn_loeschen){

        }

        if (btn == R.id.btn_test){
            Intent intent = new Intent(SetupSheetActivity.this,B6_SheetActivity.class);
            startActivity(intent);
        }

    }


    public void neuesBlattDialog (){
        final AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
        View myView = getLayoutInflater().inflate(R.layout.dialog_neu,null);
        myBuilder.setView(myView).setCancelable(true).setTitle(R.string.textView_neuesBlatt);

        final EditText et_dateiname = myView.findViewById(R.id.editText_Dateiname);

        Spinner chassis = myView.findViewById(R.id.dp_Chassis);
        ArrayAdapter<String> chassisadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.chassis));
        chassisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chassis.setAdapter(chassisadapter);

        myBuilder.setPositiveButton("Erstelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (et_dateiname.getText().toString().isEmpty())et_dateiname.setError(getString(R.string.Dateinamen_eingeben));
                else {
                    dateiname = et_dateiname.getText().toString();

                    Datenbank setupTabelle = dataSource.createSheet(dateiname);
                    Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
                    Log.d(LOG_TAG, "ID: " + setupTabelle.getId() + ", Inhalt: " + setupTabelle.toString());
                }
                    dialogInterface.dismiss();
            }
        });

        myBuilder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog neuesBlatt = myBuilder.create();
        neuesBlatt.show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle3 wird geöffnet.");
        dataSource.open();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle3 wird geschlossen.");
        dataSource.close();
    }
}
