package com.example.projecto08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecto08.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextView Register;

    EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login= findViewById(R.id.btnlogin);
        Register= findViewById(R.id.TvRegister);
        user= findViewById(R.id.user_login);
        password= findViewById(R.id.Password_login);

        File fileReader= new File(getFilesDir(),"user.txt");

        ArrayList<User> users= lisUser(fileReader);

        Intent home= new Intent(getApplicationContext(),
                Home.class);
        Intent regis= new Intent(getApplicationContext(),
                com.example.projecto08.Register.class);




        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(regis);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean state= false;
                if (user.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ambos campos deben estar diligenciados",Toast.LENGTH_LONG).show();
                }else {
                    String userLogin= user.getText().toString();
                    for (User i: users){
                        if (i.getName().equals(userLogin)||
                        i.getEmail().equals(userLogin) ||
                                i.getPhone().equals(userLogin)){
                            state= true;
                            if (i.getPassword().equals(password.getText().toString())){
                                home.putExtra("idUser",i.getID());
                                startActivity(home);
                                break;
                            }else {
                                Toast.makeText(getApplicationContext(),"la contraseña es incorrecta",Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                    if (!state){
                        Toast.makeText(getApplicationContext(),
                                "El usuario no esta registrado",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    private ArrayList<User> lisUser(File data) {
        ArrayList<User> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(data);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String user;
            while ((user = bufferedReader.readLine()) != null) {
                String[] userData = user.split(",");
                String id = userData[0];
                String name = userData[1];
                String email = userData[2];
                String phone = userData[3];
                String password = userData[4];

                User userObject = new User(id, name, email, phone, password);
                list.add(userObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}