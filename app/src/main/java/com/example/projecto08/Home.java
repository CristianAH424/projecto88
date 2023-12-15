package com.example.projecto08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView exit, estadistica, recomen, regism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        exit = findViewById(R.id.imagSalir);
        estadistica= findViewById(R.id.imagEsta);
        recomen= findViewById(R.id.imagReco);
        regism= findViewById(R.id.imagRM);

        //Inten
        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        Intent salir=new Intent(getApplicationContext(), MainActivity.class);
        Intent estadisticas=new Intent(getApplicationContext(), StatisticActivity2.class);
        estadisticas.putExtra("idUser",idUser);
        Intent recomendacion= new Intent(getApplicationContext(), Recomendaciones.class);
        Intent material=new Intent(getApplicationContext(), Materiales.class);
        material.putExtra("idUser",idUser);







        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(salir);
            }
        });

        estadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(estadisticas);
            }
        });

        recomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(recomendacion);
            }
        });

        regism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(material);
            }
        });

    }
}