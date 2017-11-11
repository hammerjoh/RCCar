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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static de.hammer.rccar.StartActivity.LOG_TAG;


public class SetupSheetActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /**   Deklarationen   **/

    public DatenbankSource dataSource;

    private int pos;
    private String suchdateiname;
    private String suchchassis;
    private String dateiname;
    private String chassis;
    private String datum;

    private ListView lv_gespeicherteSheets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "FEHLERSUCHE!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_sheet);

        dataSource = new DatenbankSource(this);

        Button btn_neu = findViewById(R.id.btn_neu);
        btn_neu.setOnClickListener(this);
        Button btn_filter = findViewById(R.id.btn_filter);
        btn_filter.setOnClickListener(this);

        initCAB();
        Log.d(LOG_TAG, "FEHLERSUCHE!!!");

    }

    public void neuesBlattDialog (){
        final AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
        View myView = getLayoutInflater().inflate(R.layout.dialog_neu,null);
        myBuilder.setView(myView).setCancelable(true).setTitle(R.string.textView_neuesBlatt);

        final EditText et_dateiname = myView.findViewById(R.id.editText_Dateiname);

        final Spinner dp_chassis = myView.findViewById(R.id.dp_Chassis);
        ArrayList<String> liste = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.chassis)));
        liste.remove(0);
        ArrayAdapter<String> chassisadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,liste);
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
                    pos = dp_chassis.getSelectedItemPosition()+1;
                    String[] chassis1 = getResources().getStringArray(R.array.chassis);
                    chassis = chassis1[pos];
                    Calendar aktuellesDatum = Calendar.getInstance();
                    SimpleDateFormat formatieren = new SimpleDateFormat("dd.MM.yyyy");
                    datum = formatieren.format(aktuellesDatum.getTime());

                    Datenbank setupTabelle = dataSource.createSheet(dateiname, chassis, datum);
                    Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
                    Log.d(LOG_TAG, "ID: " + setupTabelle.getId() + ", Inhalt: " + setupTabelle.getSpeichername());
                    showAllListEntries();
                }
            }
        });

        myBuilder.setNegativeButton(R.string.btn_abbruch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

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
            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cab_loeschen:
                        final AlertDialog.Builder myBuilder = new AlertDialog.Builder(SetupSheetActivity.this);
                        //TODO Hardcoded Texte in Strings umwandeln
                        myBuilder.setTitle("Wirklich löschen?").setMessage("Wollen Sie die Ausgewählten Blätter wirklich löschen?").setCancelable(false);
                        myBuilder.setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                markierteEintraegeLoeschen();
                                mode.finish();
                            }
                        });
                        myBuilder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog wirklich = myBuilder.create();
                        wirklich.show();
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
            final AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            View myView = getLayoutInflater().inflate(R.layout.dialog_filter,null);
            myBuilder.setView(myView).setCancelable(true).setTitle(R.string.textView_Filter);

            final EditText et_suchdateiname = myView.findViewById(R.id.editText_Suchdateiname);

            final Spinner dp_chassis = myView.findViewById(R.id.dp_Suchchassis);
            ArrayAdapter<String> chassisadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.chassis));
            chassisadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dp_chassis.setAdapter(chassisadapter);

            myBuilder.setPositiveButton(R.string.textView_Anwenden, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    suchdateiname = et_suchdateiname.getText().toString();
                    pos = dp_chassis.getSelectedItemPosition();
                    if (pos==0) {
                        suchchassis = "";
                    }
                    else {
                        String[] chassis2 = getResources().getStringArray(R.array.chassis);
                        suchchassis = chassis2[pos];
                    }
                    Filter(suchdateiname, suchchassis);
                }
            });
            AlertDialog neuesBlatt = myBuilder.create();
            neuesBlatt.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Datenbank setupTabelle = (Datenbank) lv_gespeicherteSheets.getItemAtPosition(i);
        Log.d(LOG_TAG, "ID: " + setupTabelle.getId() + ", Inhalt: " + setupTabelle.getSpeichername() + ", Chassis: " + setupTabelle.getChassis());
        Intent intent = new Intent(SetupSheetActivity.this,B6_SheetActivity.class);
        intent.putExtra("ID",setupTabelle.getId());
        startActivity(intent);
    }

    private void markierteEintraegeLoeschen(){
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
    }


    private void Filter(String name, String chassis) {
        List<Datenbank> gefilterteSheetsList = dataSource.filterDatenbank(name,chassis);
        ArrayAdapter<Datenbank> gefiltertAdapter = new ArrayAdapter<>(this, R.layout.listview_deletable, gefilterteSheetsList);

        lv_gespeicherteSheets = findViewById(R.id.listview_saved_sheets);
        lv_gespeicherteSheets.setAdapter(gefiltertAdapter);
    }


    private void showAllListEntries () {
        List<Datenbank> savedSheetsList = dataSource.getAllSavedSheets();
        ArrayAdapter<Datenbank> setupSheetsArrayAdapter = new ArrayAdapter<> (this, R.layout.listview_deletable, savedSheetsList);

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

}
