package com.khezu.listatareas;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Eliminamos el Action Bar
        getSupportActionBar().hide();

        TextView titulo = (TextView) findViewById(R.id.tituloSplash);

        Typeface miFuente = Typeface.createFromAsset(getAssets(), "Mignone.ttf");

        titulo.setTypeface(miFuente);
    }
}
