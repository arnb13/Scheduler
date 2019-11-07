package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddSchedulers extends AppCompatActivity {
    Calendar calendar;
    Button date;
    Button time;
    EditText editText;
    Spinner spinner;
    Spinner addTime;
    List<Client> clientList;
    Client selectedClient;
    int timeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedulers);

        date = findViewById(R.id.buttonDate);
        time = findViewById(R.id.buttonTime);
        editText = findViewById(R.id.editTextLocation);
        spinner = findViewById(R.id.spinnerClient);
        addTime = findViewById(R.id.spinnerTime);

        calendar = Calendar.getInstance();

        getClient();
    }

    private void getClient() {
        clientList = new ArrayList<>();
        Cursor c = new DatabaseHelper(this).getAllData();

        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                Client cl = new Client();
                cl.setId( Integer.parseInt(c.getString(0)));
                cl.setName(c.getString(1));
                cl.setAddress(c.getString(2));
                cl.setCompany(c.getString(3));
                cl.setDesignation(c.getString(4));
                clientList.add(cl);
            }

            List<String> ada = new ArrayList<>();
            for (int i = 0 ; i < clientList.size() ; i++) {
                ada.add(clientList.get(i).name);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ada);
            spinner.setAdapter(adapter);
            selectedClient = clientList.get(0);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedClient = clientList.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    selectedClient = null;
                }
            });

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    timeSpinner = position+1;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    timeSpinner = 1;
                }
            });
        }
    }

    public void pickDate(View v){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date.setText(
                        new SimpleDateFormat("yyyy.MM.dd").
                                format(calendar.getTimeInMillis()));
            }
        });
    }

    public void pickTime(View v){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                time.setText(
                        new SimpleDateFormat("hh:mm a").
                                format(calendar.getTimeInMillis()));
            }
        }, 10, 10, false);
        timePickerDialog.show();
    }

    public void save(View v){
        if (editText.getText() != null && editText.getText().toString().trim().length() > 1){
            Scheduler scheduler = new Scheduler();
            scheduler.clientId = selectedClient.id;
            scheduler.location = editText.getText().toString();
            scheduler.time = calendar.getTimeInMillis();
            scheduler.timeDifference = timeSpinner * 60 * 1000;

            Log.d("xyz", scheduler.toString());

            boolean success = new DbSchedulersHelper(this).insertData(scheduler.clientId,
                    scheduler.time, scheduler.location, scheduler.timeDifference);

            if (success){
                new AlarmAdapter(this).setAlarm(scheduler.id,
                        scheduler.time - scheduler.timeDifference);
                Toast.makeText(getApplicationContext(),
                        "New schedule has been added", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "New schedule not added", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
