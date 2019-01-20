package com.khezu.listatareas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.khezu.listatareas.db.ControladorDB;

public class MainActivity extends AppCompatActivity {

    private ControladorDB controladorDB;
    private ArrayAdapter<String> adapter;
    ListView listViewTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controladorDB = new ControladorDB(this);
        listViewTareas = (ListView) findViewById(R.id.listaTareas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final EditText cajaTextoDialog = new EditText(this);
        //TO-DO
        switch (item.getItemId()) {
            case R.id.añadirtarea:
                AlertDialog dialog;

                dialog = new AlertDialog.Builder(this)
                        .setTitle("Nueva tarea")
                        .setMessage("¿Qué quieres apuntar?")
                        .setView(cajaTextoDialog)
                        .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tarea = cajaTextoDialog.getText().toString();
                                controladorDB.insertarTarea(tarea);
                                actualizarUI();
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create();


                dialog.show();
                return true;

            case R.id.desafios:
                Intent intent = new Intent(this, DesafiosActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void actualizarUI() {
        if(controladorDB.contabilizarRegistros() == 0){
            listViewTareas.setAdapter(null);
        } else {
            adapter = new ArrayAdapter<String>(this, R.layout.item_tarea, R.id.tarea, controladorDB.obtenerTareas());
            listViewTareas.setAdapter(adapter);
        }
    }

    public void borrarTarea(View view){
        View parent = (View) view.getParent();
        TextView tarea = (TextView) parent.findViewById(R.id.tarea);
        controladorDB.borrarTarea(tarea.getText().toString());
        Toast toast = Toast.makeText(this, "Tarea finalizada", Toast.LENGTH_SHORT);
        actualizarUI();
    }
}
