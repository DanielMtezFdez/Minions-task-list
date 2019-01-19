package com.khezu.listatareas;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public void crearCuenta(View view){
        Toast toast = Toast.makeText(this, "Funcionalidad no disponible", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void accederApp(View view){
        //User: itt
        //pw: 123
        TextInputEditText user = (TextInputEditText) findViewById(R.id.user);
        TextInputEditText pw = (TextInputEditText) findViewById(R.id.pw);

        if(user.getText().toString().toLowerCase().equals("itt") && pw.getText().toString().equals("123")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Usuario o contraseña no válidos", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
