package com.khezu.listatareas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.khezu.listatareas.db.ControladorDB;

public class DesafiosActivity extends AppCompatActivity {

    ControladorDB controladorDB;

    static ImageView star1;
    static ImageView star2;
    static ImageView star3;
    static ImageView star4;
    static ImageView star5;
    static ImageView star6;

    static TextView desafio1;
    static TextView desafio2;
    static TextView desafio3;
    static TextView desafio4;
    static TextView desafio5;
    static TextView desafio6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafios);
        getSupportActionBar().setTitle("Desafíos");

        controladorDB = new ControladorDB(this);

        star1 = (ImageView)findViewById(R.id.starDesafio1);
        star2 = (ImageView)findViewById(R.id.starDesafio2);
        star3 = (ImageView)findViewById(R.id.starDesafio3);
        star4 = (ImageView)findViewById(R.id.starDesafio4);
        star5 = (ImageView)findViewById(R.id.starDesafio5);
        star6 = (ImageView)findViewById(R.id.starDesafio6);

        desafio1 = (TextView)findViewById(R.id.textoDesafio1);
        desafio2 = (TextView)findViewById(R.id.textoDesafio2);
        desafio3 = (TextView)findViewById(R.id.textoDesafio3);
        desafio4 = (TextView)findViewById(R.id.textoDesafio4);
        desafio5 = (TextView)findViewById(R.id.textoDesafio5);
        desafio6 = (TextView)findViewById(R.id.textoDesafio6);

        comprobarDesafios();

    }

    public void comprobarDesafios(){
        int tareasCompletas = controladorDB.comprobarTareasCompletas();
        if(tareasCompletas!=0){
            DesafiosActivity.cambiarDesafíos(tareasCompletas);
        }
    }

    public static void cambiarDesafíos(int tareasCompletas) {

        if (tareasCompletas >= 5) {
            star1.setImageResource(R.mipmap.star_amarilla);
            desafio1.setText(R.string.desafio1);
        }

        if (tareasCompletas >= 20) {
            star2.setImageResource(R.mipmap.star_amarilla);
            desafio2.setText(R.string.desafio2);
        }

        if (tareasCompletas >= 50) {
            star3.setImageResource(R.mipmap.star_amarilla);
            desafio3.setText(R.string.desafio3);
        }

        if (tareasCompletas >= 100) {
            star4.setImageResource(R.mipmap.star_amarilla);
            desafio4.setText(R.string.desafio4);
        }

        if (tareasCompletas >= 500) {
            star5.setImageResource(R.mipmap.star_amarilla);
            desafio5.setText(R.string.desafio5);
        }

        if (tareasCompletas >= 1000) {
            star6.setImageResource(R.mipmap.star_amarilla);
            desafio6.setText(R.string.desafio6);
        }
    }
}
