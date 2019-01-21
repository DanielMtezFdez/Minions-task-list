package com.khezu.listatareas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
        //Comprobamos el número de tareas completas para rellenar los desafíos
        comprobarDesafios();
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "Mignone.ttf");
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
        comprobarDesafios();
    }

    public void comprobarDesafios(){
        int tareasCompletas = controladorDB.comprobarTareasCompletas();
        if(tareasCompletas!=0){
            cambiarDesafíos(tareasCompletas);
        }
    }

    public void cambiarDesafíos(int tareasCompletas) {

        ImageView star1 = (ImageView)findViewById(R.id.starDesafio1);
        ImageView star2 = (ImageView)findViewById(R.id.starDesafio2);
        ImageView star3 = (ImageView)findViewById(R.id.starDesafio3);
        ImageView star4 = (ImageView)findViewById(R.id.starDesafio4);
        ImageView star5 = (ImageView)findViewById(R.id.starDesafio5);
        ImageView star6 = (ImageView)findViewById(R.id.starDesafio6);

        TextView desafio1 = (TextView)findViewById(R.id.textoDesafio1);
        TextView desafio2 = (TextView)findViewById(R.id.textoDesafio2);
        TextView desafio3 = (TextView)findViewById(R.id.textoDesafio3);
        TextView desafio4 = (TextView)findViewById(R.id.textoDesafio4);
        TextView desafio5 = (TextView)findViewById(R.id.textoDesafio5);
        TextView desafio6 = (TextView)findViewById(R.id.textoDesafio6);

        DialogFragment notificacion = new DialogPersonalizado();

        if (tareasCompletas >= 5) {
            notificacion.show(getSupportFragmentManager(), "notificacion");
            star1.setImageResource(R.mipmap.star_amarilla);
            desafio1.setText(R.string.desafio1);
        }

        if (tareasCompletas >= 20) {
            notificacion.show(getSupportFragmentManager(), "notificacion");
            star2.setImageResource(R.mipmap.star_amarilla);
            desafio2.setText(R.string.desafio2);
        }

        if (tareasCompletas >= 50) {
            notificacion.show(getSupportFragmentManager(), "notificacion");
            star3.setImageResource(R.mipmap.star_amarilla);
            desafio3.setText(R.string.desafio3);
        }

        if (tareasCompletas >= 100) {
            notificacion.show(getSupportFragmentManager(), "notificacion");
            star4.setImageResource(R.mipmap.star_amarilla);
            desafio4.setText(R.string.desafio4);
        }

        if (tareasCompletas >= 500) {
            notificacion.show(getSupportFragmentManager(), "notificacion");
            star5.setImageResource(R.mipmap.star_amarilla);
            desafio5.setText(R.string.desafio5);
        }
        
        if (tareasCompletas >= 1000) {
            notificacion.show(getSupportFragmentManager(), "notificacion");
            star6.setImageResource(R.mipmap.star_amarilla);
            desafio6.setText(R.string.desafio6);
        }
    }
}
