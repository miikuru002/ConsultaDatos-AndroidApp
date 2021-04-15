package com.example.finalbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class PresentacionActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);

        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences=getSharedPreferences("preferencias_login", Context.MODE_PRIVATE); //se recuepran las preferencias con la variable sesion creada anteriormente, se validara si ya hay una sesion  guardada o no
                boolean sesion=preferences.getBoolean("sesion",false);

                if(sesion){ //si es true, muestra la actividad principal
                    Intent intent=new Intent(getApplicationContext(),BusquedaActivity.class); //muestra el home
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class); //muestra el login
                    startActivity(intent);
                    finish();
                }
            }
        },2000);

    }
}