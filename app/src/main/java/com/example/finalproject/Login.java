package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Login extends AppCompatActivity {
    EditText s1,s2;
    Button b1;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DataBaseHelper(this);
        s1 = (EditText)findViewById(R.id.loginEmail);
        s2 = (EditText)findViewById(R.id.loginPass);
        b1 = (Button)findViewById(R.id.login);

        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                String email = s1.getText().toString();
                String password = s2.getText().toString();
                Boolean Chkemailpass = db.emailpassword(email,password);
                if(Chkemailpass == true){
                    Intent i = new Intent(Login.this,DailyTaskActivity.class);
                startActivity(i);}
                    //Toast.makeText(getApplicationContext(),"successfully login", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"wrong email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
