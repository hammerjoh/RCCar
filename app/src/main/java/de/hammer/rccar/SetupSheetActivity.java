package de.hammer.rccar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import static de.hammer.rccar.StartActivity.LOG_TAG;


public class SetupSheetActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /**   Deklarationen   **/

    public DatenbankSource dataSource;

    public String dateiname;
    public String[] chassis1;
    public String chassis;
    public int pos;

    private Button btn_neu;
    private Button btn_test;
    private Button btn_filter;
    private ListView lv_gespeicherteSheets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_sheet);

        dataSource = new DatenbankSource(this);

        btn_neu = findViewById(R.id.btn_neu);
        btn_neu.setOnClickListener(this);
        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);
        btn_filter = findViewById(R.id.btn_filter);
        btn_filter.setOnClickListener(this);

        initCAB();

    }

    public void neuesBlattDialog (){
        final AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
        View myView = getLayoutInflater().inflate(R.layout.dialog_neu,null);
        myBuilder.setView(myView).setCancelable(true).setTitle(R.string.textView_neuesBlatt);

        final EditText et_dateiname = myView.findViewById(R.id.editText_Dateiname);

        final Spinner dp_chassis = myView.findViewById(R.id.dp_Chassis);
        ArrayAdapter<String> chassisadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.chassis));
        chassisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dp_chassis.setAdapter(chassisadapter);

        myBuilder.setPositiveButton(R.string.textView_neuesBlatt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (et_dateiname.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),R.string.Dateinamen_eingeben,Toast.LENGTH_SHORT).show();
                }
                else {
                    dateiname = et_dateiname.getText().toString();
                    pos = dp_chassis.getSelectedItemPosition();
                    chassis1 = getResources().getStringArray(R.array.chassis);
                    chassis = chassis1[pos];

                    Datenbank setupTabelle = dataSource.createSheet(dateiname,chassis);
                    Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
                    Log.d(LOG_TAG, "ID: " + setupTabelle.getId() + ", Inhalt: " + setupTabelle.getSpeichername());
                    showAllListEntries();
                    dialogInterface.dismiss();
                }
            }
        });

        myBuilder.setNegativeButton(R.string.btn_abbruch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog neuesBlatt = myBuilder.create();
        neuesBlatt.show();
    }


    private void initCAB (){

        lv_gespeicherteSheets = findViewById(R.id.listview_saved_sheets);
        lv_gespeicherteSheets.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv_gespeicherteSheets.setOnItemClickListener(this);

        lv_gespeicherteSheets.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.contextual_action_bar, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.cab_loeschen:
                        SparseBooleanArray markierteEintraege = lv_gespeicherteSheets.getCheckedItemPositions();
                        for (int i=0; i < markierteEintraege.size(); i++) {
                            boolean isChecked = markierteEintraege.valueAt(i);
                            if(isChecked) {
                                int postitionInListView = markierteEintraege.keyAt(i);
                                Datenbank setupTabelle = (Datenbank) lv_gespeicherteSheets.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + setupTabelle.toString());
                                dataSource.deleteSheet(setupTabelle);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        return true;

                    case R.id.cab_kopieren:
                        Toast.makeText(getApplicationContext(),"Hier sollte die Kopierfunktion sein",Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        int btn =  v.getId();

        if (btn == R.id.btn_neu){
            neuesBlattDialog();
        }

        if (btn == R.id.btn_filter) {

        }

        if (btn == R.id.btn_test){
            Intent intent = new Intent(SetupSheetActivity.this,B6_SheetActivity.class);
            startActivity(intent);
        }

    }


    private void showAllListEntries () {
        List<Datenbank> savedSheetsList = dataSource.getAllSavedSheets();
        ArrayAdapter<Datenbank> setupSheetsArrayAdapter = new ArrayAdapter<> (this, R.layout.test_listview, savedSheetsList);

        lv_gespeicherteSheets = findViewById(R.id.listview_saved_sheets);
        lv_gespeicherteSheets.setAdapter(setupSheetsArrayAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird von SetupSheetActivity geöffnet.");
        dataSource.open();
        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird von SetupSheetActivity geschlossen.");
        dataSource.close();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Datenbank setupTabelle = (Datenbank) lv_gespeicherteSheets.getItemAtPosition(i);
        Log.d(LOG_TAG, "ID: " + setupTabelle.getId() + ", Inhalt: " + setupTabelle.getSpeichername() + ", Chassis: " + setupTabelle.getChassis());
        //Toast.makeText(getApplicationContext(),"Sie haben auf die Position " + l + " mit dem Namen " + setupTabelle.getSpeichername() + " und dem Chassis " + setupTabelle.getChassis() + " geklickt.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SetupSheetActivity.this,B6_SheetActivity.class);
        intent.putExtra("ID",setupTabelle.getId());
        startActivity(intent);
    }
}
