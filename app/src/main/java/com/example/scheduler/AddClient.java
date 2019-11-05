package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddClient extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        db = new DatabaseHelper(this);
    }

    public void saveClient (View v) {
        String inputName = (((EditText)findViewById(R.id.text_name)).getText()).toString();
        String inputAddress = (((EditText)findViewById(R.id.text_address)).getText()).toString();
        String inputCompany = (((EditText)findViewById(R.id.text_company)).getText()).toString();
        String inputDesignation = (((EditText)findViewById(R.id.text_designation)).getText()).toString();
        boolean success = db.insertData(inputName, inputAddress, inputCompany, inputDesignation);

        Toast t;

        if (success) {
            viewMainIntent(v);
            t = Toast.makeText(getApplicationContext(), "New client added", Toast.LENGTH_SHORT);
            t.show();

        } else {
            t = Toast.makeText(getApplicationContext(), "New client not added", Toast.LENGTH_SHORT);
            t.show();

        }
    }

    public void viewMainIntent(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 0);

    }
}
