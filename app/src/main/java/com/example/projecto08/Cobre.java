package com.example.projecto08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projecto08.models.copper;
import com.example.projecto08.models.plastic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Cobre extends AppCompatActivity {
    ImageView salir;
    EditText quantity, price;
    Spinner months;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobre);

        salir = findViewById(R.id.imagSalirMCobre);
        quantity = findViewById(R.id.editTextKl3);
        price = findViewById(R.id.editTexPe3);
        months = findViewById(R.id.spinnerMonthCobre);
        register = findViewById(R.id.btnRe3);

        Intent receive = getIntent();
        String idUser = receive.getStringExtra("idUser");


        Intent exit = new Intent(getApplicationContext(), Materiales.class);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(exit);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity.getText().toString().isEmpty() ||
                        price.getText().toString().isEmpty() ||
                        months.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Todos los Campos deben diligenciarse", Toast.LENGTH_LONG).show();
                } else {
                    int pesoCobre = Integer.parseInt(quantity.getText().toString());
                    int costoCobre = Integer.parseInt(price.getText().toString());
                    String monthCobre = months.getSelectedItem().toString();
                    String serial = idUser + monthCobre;
                    copper materialcobre = new copper(serial, pesoCobre, costoCobre, monthCobre, idUser);
                    registerCobre(materialcobre);
                    Toast.makeText(getApplicationContext(), "Registro exitoso",
                            Toast.LENGTH_LONG).show();
                    cleanView();

                }

            }
        });

    }

    public void registerCobre(copper material) {
        File copperFile = new File(getFilesDir(), "copper.txt");

        try {
            FileWriter writer = new FileWriter(copperFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(
                    material.getSERIAL() + "," +
                            material.getQuantity() + "," +
                            material.getPrice() + "," +
                            material.getMonth() + "," +
                            material.getIdUser()
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cleanView() {
        quantity.setText("");
        price.setText("");
        months.setSelection(0);
    }
}
