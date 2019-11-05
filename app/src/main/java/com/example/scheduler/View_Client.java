package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class View_Client extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList <Client> clientArrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__client);


        db = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listClient);
        clientArrayList = new ArrayList<>();
        viewClient();

        //ArrayAdapter a = new ArrayAdapter(this, clientArrayList);
        ClientListAdapter a = new ClientListAdapter(this, R.layout.activity_adapter_view, clientArrayList);
        listView.setAdapter(a);

    }

    public void viewClient() {
        Cursor c = db.getAllData();

        if (c.getCount() != 0) {
            Client cl = new Client();
            while (c.moveToNext()) {
                cl.setId( Integer.parseInt(c.getString(0)));
                cl.setName(c.getString(1));
                cl.setAddress(c.getString(2));
                cl.setCompany(c.getString(3));
                cl.setDesignation(c.getString(4));
                clientArrayList.add(cl);
            }

        } else {

        }

    }
}
