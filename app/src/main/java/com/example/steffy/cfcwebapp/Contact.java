package com.example.steffy.cfcwebapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editname,editmail,editmob,editmsg ;
    Button btnAddData;
    Button btnviewAll;
    Button hbt;
    Button abt;
    Button cbt;
    Button mbt;

    /*Button homebt = (Button) findViewById(R.id.home);
    Button aboutbt = (Button) findViewById(R.id.about);
    Button contactbt = (Button) findViewById(R.id.contact);
    Button mapbt = (Button) findViewById(R.id.map);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        myDb = new DatabaseHelper(this);

        editname = (EditText)findViewById(R.id.etname);
        editmail = (EditText)findViewById(R.id.etmail);
        editmob = (EditText)findViewById(R.id.etphone);
        editmsg = (EditText)findViewById(R.id.etmsg);


        btnAddData = (Button)findViewById(R.id.submit);
        btnviewAll = (Button)findViewById(R.id.viewbtn);
        mbt=(Button)findViewById(R.id.map);
        hbt=(Button)findViewById(R.id.home);
        abt=(Button)findViewById(R.id.about);
        cbt=(Button)findViewById(R.id.contact);



      /*  homebt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, MainActivity.class);
                startActivity(gotoIntent);
            }
        });
        abt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, About.class);
                startActivity(gotoIntent);
            }
        });
        cbt.setOnClickListener(new View.OnClickListener() {
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
        });*/
        AddData();
        viewAll();
        Map();

        Home();
        Abt();
        Cont();

    }

    private void Cont() {
        cbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, Contact.class);
                startActivity(gotoIntent);
            }
        });
    }

    private void Abt() {
        abt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, About.class);
                startActivity(gotoIntent);
            }
        });
    }

    private void Home() {
        hbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, MainActivity.class);
                startActivity(gotoIntent);
            }
        });
    }

    private void Map() {
        mbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gotoIntent = new Intent(Contact.this, MapActivity.class);
                startActivity(gotoIntent);
            }
        });
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editname.getText().toString(),
                                editmail.getText().toString(),
                                editmob.getText().toString(),
                                editmsg.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(Contact.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Contact.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Mail ID :"+ res.getString(2)+"\n");
                            buffer.append("Mobile No. :"+ res.getString(3)+"\n");
                            buffer.append("Message :"+ res.getString(4)+"\n\n");

                        }

                        // Show all data
                        showMessage("Messages",buffer.toString());
                    }


                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
