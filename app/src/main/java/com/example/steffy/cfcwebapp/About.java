package com.example.steffy.cfcwebapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button homebt = (Button) findViewById(R.id.home);
        Button aboutbt = (Button) findViewById(R.id.about);
        Button contactbt= (Button) findViewById(R.id.contact);
        homebt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent gotoIntent = new Intent(About.this, MainActivity.class);
                startActivity(gotoIntent);
            }
        });
        aboutbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent gotoIntent = new Intent(About.this, About.class);
                startActivity(gotoIntent);
            }
        });
        contactbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent gotoIntent = new Intent(About.this, Contact.class);
                startActivity(gotoIntent);
            }
        });
    }
}
