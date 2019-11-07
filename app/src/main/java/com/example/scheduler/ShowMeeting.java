package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowMeeting extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<Client> clientArrayList;
    List<Scheduler> array = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoow_meeting);

        db = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listMeeting);
        clientArrayList = new ArrayList<>();
        viewMeeting();
    }

    private void viewMeeting() {
        Cursor c = db.getAllData();

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
                            (long)Integer.parseInt(c.getString(2)),
                            (long)Integer.parseInt(c.getString(3)),
                            c.getString(1));
                    array.add(cl);
                }

                //ArrayAdapter a = new ArrayAdapter(this, clientArrayList);
                ScheduleListAdapter a = new ScheduleListAdapter(this,
                        R.layout.adapter_meeting, clientArrayList, array);
                listView.setAdapter(a);
            }
        } else {

        }

    }
}
