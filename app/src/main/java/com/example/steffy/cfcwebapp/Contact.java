package com.example.steffy.cfcwebapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Contact extends AppCompatActivity {

    Button subbt, vbt;
    DatabaseHelper mydb;
    EditText e1, e2, e3, e4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        Button homebt = (Button) findViewById(R.id.home);
        Button aboutbt = (Button) findViewById(R.id.about);
        Button contactbt = (Button) findViewById(R.id.contact);
       
        e1 = (EditText) findViewById(R.id.editText3);
        e2 = (EditText) findViewById(R.id.editText5);
        e3 = (EditText) findViewById(R.id.editText4);
        e4 = (EditText) findViewById(R.id.editText6);

        vbt = (Button) findViewById(R.id.viewbtn);
        subbt = (Button) findViewById(R.id.submit);

        Button mapbt = (Button) findViewById(R.id.map);
        final String mess=e4.getText().toString();


        homebt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, MainActivity.class);
                startActivity(gotoIntent);
            }
        });
        aboutbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, About.class);
                startActivity(gotoIntent);
            }
        });
        contactbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, Contact.class);
                startActivity(gotoIntent);
            }
        });
        mapbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, MapActivity.class);
                startActivity(gotoIntent);
            }
        });
        subbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               boolean isInserted = mydb.insertdata(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), mess);

                if (isInserted == true)
                    Toast.makeText(Contact.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Contact.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                //sendEmail();

            }
        });




    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"anithavikkiraman@gmail.com"};
        String[] CC = {"francy.keerthi@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Contact.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewres() {

        vbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()) {
                    buffer.append("Name:" + res.getString(0) + "\n");
                    buffer.append("e-Mail:" + res.getString(1) + "\n");
                    buffer.append("Phone no.:" + res.getString(2) + "\n");
                    buffer.append("Message:" + res.getString(3) + "\n");
                }

                showMessage("Data", buffer.toString());
            }
        });

    }

    private void showMessage(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }





}
