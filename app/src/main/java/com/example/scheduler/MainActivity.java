package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addClientIntent(View v) {
        Intent intent = new Intent(this, AddClient.class);
        startActivityForResult(intent, 0);

    }

    public void viewClientIntent(View v) {
        Intent intent = new Intent(this, View_Client.class);
        startActivityForResult(intent, 0);

    }
}
