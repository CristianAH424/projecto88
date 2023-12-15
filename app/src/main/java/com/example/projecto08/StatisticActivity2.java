package com.example.projecto08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projecto08.models.cardboard;
import com.example.projecto08.models.copper;
import com.example.projecto08.models.glass;
import com.example.projecto08.models.plastic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class StatisticActivity2 extends AppCompatActivity {
    TextView totalCarton,totalcopper,totalglass,totalplastic,total_pay,max_carton_month,
            max_copper_month,max_glass_month,
            max_plastic_month,max_carton_quantity,
            max_copper_quantity,max_glass_quantity,max_plastic_quantity;

    Button more;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic2);

        totalCarton = findViewById(R.id.textViewTotalCarton);
        totalcopper = findViewById(R.id.textViewTotalCobre);
        totalglass = findViewById(R.id.textViewTotalvidrio);
        totalplastic = findViewById(R.id.textViewTotalPlastic);
        total_pay=findViewById(R.id.textViewTotalPay);
        max_carton_month=findViewById(R.id.textViewMonthMaxCarton);
        max_carton_quantity=findViewById(R.id.textViewMaxCartonQuantity);
        max_copper_month= findViewById(R.id.textViewMonthMaxCobre);
        max_copper_quantity=findViewById(R.id.textViewMaxCobreQuantity);
        max_glass_month=findViewById(R.id.textViewMonthMaxVidrio);
        max_glass_quantity=findViewById(R.id.textViewMaxVidrioQuantity);
        max_plastic_month=findViewById(R.id.textViewMonthMaxPlastico);
        max_plastic_quantity=findViewById(R.id.textViewMaxPlasticQuantity);



        Intent receive = getIntent();
        String idUser = receive.getStringExtra("idUser");

        File CartonFile = new File(getFilesDir(), "cardboard.txt");
        File plasticFile = new File(getFilesDir(), "plastic.txt");
        File copperFile = new File(getFilesDir(),"copper.txt");
        File glassFile = new File(getFilesDir(), "glass.txt");

        ArrayList<cardboard> list_cardboard = listCarton(CartonFile, idUser);
        ArrayList<copper> list_copper = listcopper(copperFile, idUser);
        ArrayList<glass> list_glass = listglass(glassFile, idUser);
        ArrayList<plastic> list_plastic = listplastic(plasticFile, idUser);


        totalconsumecarton(list_cardboard);
        totalconsumecopper(list_copper);
        totalconsumeglass(list_glass);
        totalconsumeplastic(list_plastic);

        int total= totalPaycarton(list_cardboard)+totalPayplastic(list_plastic)+totalPaycopper(list_copper)+
                totalPayglass(list_glass);
        total_pay.setText("$ "+total);




    }
    public int totalPaycarton(ArrayList<cardboard>list){
        int pay=0;
        for (cardboard i: list){
            pay+=i.getPrice();
        }
        return pay;
    }
    public int totalPayplastic(ArrayList<plastic>list){
        int pay=0;
        for (plastic i: list){
            pay+=i.getPrice();
        }
        return pay;
    }
    public int totalPaycopper(ArrayList<copper>list){
        int pay=0;
        for (copper i: list){
            pay+=i.getPrice();
        }
        return pay;
    }
    public int totalPayglass(ArrayList<glass>list){
        int pay=0;
        for (glass i: list){
            pay+=i.getPrice();
        }
        return pay;
    }
    public void totalconsumecopper(ArrayList<copper>list){
        int total = 0;
        String month="";
        int max=0;
        for (copper i:list){
            total+=i.getQuantity();
            if (max<i.getQuantity()){
                max=i.getQuantity();
                month=i.getMonth();
            }
        }
        totalcopper.setText(total+" kl");
        max_copper_quantity.setText(max+" kl");
        max_copper_month.setText(month);
    }


        public void totalconsumecarton(ArrayList<cardboard>list){
            int total = 0;
            String month="";
            int max=0;
            for (cardboard i:list){
                total+=i.getQuantity();
                if (max<i.getQuantity()){
                    max=i.getQuantity();
                    month=i.getMonth();
                }
            }
            totalCarton.setText(total+" kl");
            max_carton_quantity.setText(max+" kl");
            max_carton_month.setText(month);
        }
                public void totalconsumeglass(ArrayList<glass>list){
                    int total = 0;
                    String month="";
                    int max=0;
                    for (glass i:list){
                        total+=i.getQuantity();
                        if (max<i.getQuantity()){
                            max=i.getQuantity();
                            month=i.getMonth();
                        }
                    }
                    totalglass.setText(total+" kl");
                    max_glass_quantity.setText(max+" kl");
                    max_glass_month.setText(month);
                }

    public void totalconsumeplastic(ArrayList<plastic>list){
        int total=0;
        String month="";
        int max=0;
        for (plastic i:list){
            total+=i.getQuantity();
            if (max<i.getQuantity()){
                max=i.getQuantity();
                month=i.getMonth();
            }
        }
        totalplastic.setText(total+" Kl");
        max_plastic_quantity.setText(max+" Kl");
        max_plastic_month.setText(month);
    }


    public ArrayList<cardboard> listCarton(File carton,String user) {
        ArrayList<cardboard> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(carton);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String cadena;

            while ((cadena = bufferedReader.readLine())!= null) {
                String[] data = cadena.split(",");

                String serial = data[0];
                int quantity = Integer.parseInt(data[1]);
                int price = Integer.parseInt(data[2]);
                String month = data[3];
                String idUser = data[4];
                if (data[4].equals(user)) {
                    cardboard obj = new cardboard(serial, quantity, price, month, idUser);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<plastic> listplastic(File plastic, String user){
        ArrayList<plastic> list= new ArrayList<>();
        try {
            FileReader reader= new FileReader(plastic);
            BufferedReader bufferedReader=new BufferedReader(reader);
            String cadena;
            while ((cadena=bufferedReader.readLine())!=null){
                String [] data= cadena.split(",");
                if(data[4].equals(user)){
                    String serial= data[0];
                    int quantity= Integer.parseInt(data[1]);
                    int price= Integer.parseInt(data[2]);
                    String month= data[3];
                    String idUser= data[4];

                    plastic obj= new plastic(serial,quantity,price,month,idUser);
                    list.add(obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<copper> listcopper(File copper,String user) {
        ArrayList<copper> list = new ArrayList<>();
        try {
            FileReader Reader = new FileReader(copper);
            BufferedReader bufferedReader = new BufferedReader(Reader);
            String cadena;

            while ((cadena = bufferedReader.readLine())!= null) {
                String[] data = cadena.split(",");
                if (data[4].equals(user)) {
                String serial = data[0];
                int quantity = Integer.parseInt(data[1]);
                int price = Integer.parseInt(data[2]);
                String month = data[3];
                String idUser = data[4];

                    copper obj = new copper(serial, quantity, price, month, idUser);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<glass> listglass(File glass,String user) {
        ArrayList<glass> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(glass);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String cadena;

            while ((cadena = bufferedReader.readLine())!= null) {
                String[] data = cadena.split(",");
                String serial = data[0];
                if (data[4].equals(user)) {
                int quantity = Integer.parseInt(data[1]);
                int price = Integer.parseInt(data[2]);
                String month = data[3];
                String idUser = data[4];

                    glass obj = new glass(serial, quantity, price, month, idUser);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



}



