package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    DataBaseHelper db;
    EditText e1,e2,e3;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseHelper(this);

        e1 = (EditText)findViewById(R.id.email);
        e2 = (EditText)findViewById(R.id.pass);
        e3 = (EditText)findViewById(R.id.cpass);
        b1 = (Button)findViewById(R.id.register);
        b2 = (Button)findViewById(R.id.goLogin);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();

                if(s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(s2.equals(s3)){
                    Boolean chkemail = db.chkemail(s1);
                       if(chkemail == true)
                       {
                           Boolean insert = db.insert(s1,s2);

                       if(insert == true){
                           Toast.makeText(getApplicationContext(),"Register successfully", Toast.LENGTH_SHORT).show();
                       }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email alredy exists", Toast.LENGTH_SHORT).show();
                    }
                }
                    Toast.makeText(getApplicationContext(),"password does not match.", Toast.LENGTH_SHORT).show();

            }



        }});

    }
}
