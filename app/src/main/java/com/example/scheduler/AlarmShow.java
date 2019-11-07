package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AlarmShow extends AppCompatActivity {
    int id;
    List<Scheduler> array;
    List<Client> clientArrayList;
    String name, location, time;
    TextView tvName, tvLocation, tvTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_show);

        tvLocation = findViewById(R.id.tvLocation);
        tvName = findViewById(R.id.tvName);
        tvTime = findViewById(R.id.tvTime);

        if (getIntent().getExtras() != null){
            id = getIntent().getExtras().getInt("id");
            getData();
        }
    }

    private void getData(){
        Cursor c = new DatabaseHelper(this).getAllData();
        clientArrayList = new ArrayList<>();
            array = new ArrayList<>();
            if (c.getCount() != 0) {
                while (c.moveToNext()) {
                    Client cl = new Client();
                    cl.setId( Integer.parseInt(c.getString(0)));
                    cl.setName(c.getString(1));
                    cl.setAddress(c.getString(2));
                    cl.setCompany(c.getString(3));
                    cl.setDesignation(c.getString(4));
                    clientArrayList.add(cl);
                }

                c = new DbSchedulersHelper(this).getAllData();

                if (c.getCount() != 0) {
                    array = new ArrayList<>();
                    while (c.moveToNext()) {
                        Scheduler cl = new Scheduler(Integer.parseInt(c.getString(0)),
                                Integer.parseInt(c.getString(1)),
                                (long)Long.parseLong(c.getString(2)),
                                (long)Integer.parseInt(c.getString(3)),
                                c.getString(1));
                        array.add(cl);
                    }
                }
            }

            populateData();
    }

    private void populateData(){
        int clientId = -1;
        for (int i = 0 ; i < array.size() ; i++){
            if (array.get(i).id == id){
                time = new SimpleDateFormat("yyyy.MM.dd hh:mm a").
                        format(array.get(i).time);
                location = array.get(i).location;
                clientId = array.get(i).clientId;
            }
        }
        for (int i = 0 ; i < clientArrayList.size() ; i++){
            if (clientArrayList.get(i).id == clientId){
                name = clientArrayList.get(i).name;
            }
        }

        initData();
    }

    private void initData(){
        tvName.setText(name);
        tvLocation.setText(location);
        tvTime.setText(time);
    }
}
