package com.khezu.listatareas;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Splash extends AppCompatActivity  implements Animation.AnimationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Ocultamos el ActionBar en el Splash
        getSupportActionBar().hide();

        //Creamos fuente
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "Mignone.ttf");

        TextView titulo = (TextView)findViewById(R.id.tituloSplash);
        TextView cargando = (TextView)findViewById(R.id.cargando);

        //Creamos animacion
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_splash);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.anim_splash_2);

        //Establecemos fuente y animacion
        titulo.setTypeface(miFuente);
        cargando.setTypeface(miFuente);

        titulo.setAnimation(anim);
        cargando.setAnimation(anim2);

        anim2.setAnimationListener(this);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
