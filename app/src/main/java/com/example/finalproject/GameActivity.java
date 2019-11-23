package com.example.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Button w1, w2, w3, w4, w5, m1, m2, m3, m4, m5;
    public static int[] myNumbers = null;
    Map compare = null;
    int term = 0;
    String str1 = null, str2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Open database
        DataBaseHelper myDatabaseHelper;
        myDatabaseHelper = new DataBaseHelper(this);
        final String[][] str = myDatabaseHelper.getAppCategoryDetail();

        // Set word and meaning button
        w1 = (Button) findViewById(R.id.w1);
        w2 = (Button) findViewById(R.id.w2);
        w3 = (Button) findViewById(R.id.w3);
        w4 = (Button) findViewById(R.id.w4);
        w5 = (Button) findViewById(R.id.w5);

        m1 = (Button) findViewById(R.id.m1);
        m2 = (Button) findViewById(R.id.m2);
        m3 = (Button) findViewById(R.id.m3);
        m4 = (Button) findViewById(R.id.m4);
        m5 = (Button) findViewById(R.id.m5);

        // Get ready for the data string
        String data[][] = generate_data(str);
        compare = new HashMap();
        compare.put(data[0][0], data[1][0]);
        compare.put(data[0][1], data[1][1]);
        compare.put(data[0][2], data[1][2]);
        compare.put(data[0][3], data[1][3]);
        compare.put(data[0][4], data[1][4]);

        // Set text to every button
        w1.setText(data[0][myNumbers[0]]);
        w2.setText(data[0][myNumbers[1]]);
        w3.setText(data[0][myNumbers[2]]);
        w4.setText(data[0][myNumbers[3]]);
        w5.setText(data[0][myNumbers[4]]);

        m1.setText(data[1][0]);
        m2.setText(data[1][1]);
        m3.setText(data[1][2]);
        m4.setText(data[1][3]);
        m5.setText(data[1][4]);

        listener();
    }

    public void listener(){

        w1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                term++;
                w1.setBackgroundColor(Color.parseColor("#bafffa"));
                set_word(w1);

            }

        });

        w2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                term++;
                w2.setBackgroundColor(Color.parseColor("#bafffa"));
                set_word(w2);
            }
        });

        w3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                term++;
                w3.setBackgroundColor(Color.parseColor("#bafffa"));
                set_word(w3);
            }
        });

        w4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                term++;
                w4.setBackgroundColor(Color.parseColor("#bafffa"));
                set_word(w4);
            }
        });

        w5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                term++;
                w5.setBackgroundColor(Color.parseColor("#bafffa"));
                set_word(w5);
            }
        });

        m1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                term++;
                m1.setBackgroundColor(Color.parseColor("#bafffa"));
                set_meaning(m1);
            }
        });

        m2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                term++;
                m2.setBackgroundColor(Color.parseColor("#bafffa"));
                set_meaning(m2);
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                term++;
                m3.setBackgroundColor(Color.parseColor("#bafffa"));
                set_meaning(m3);
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                term++;
                m4.setBackgroundColor(Color.parseColor("#bafffa"));
                set_meaning(m4);
            }
        });

        m5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                term++;
                m5.setBackgroundColor(Color.parseColor("#bafffa"));
                set_meaning(m5);
            }
        });
    }

    public void set_word(Button tmp) {
        if (term == 1) {
            str1 = tmp.getText().toString();
        } else if (term == 2) {
            term = 0;
            str2 = tmp.getText().toString();

            if(compare.get(str2) == null){
                tmp.setBackgroundColor(Color.parseColor("#dee0de"));
                // Status 1 means not match
                search(str1, 1);
                str2 = null;
                str1 = null;
                return;
            }

            if (stringCompare(compare.get(str2).toString(), str1) == 0) {
                tmp.setVisibility(View.GONE);
                // Status 0 means matched
                search(str1, 0);
            } else {
                tmp.setBackgroundColor(Color.parseColor("#dee0de"));
                // Status 1 means not match
                search(str1, 1);
            }

            str2 = null;
            str1 = null;
        }
    }

    public void set_meaning(Button tmp) {
        if (term == 1) {
            str1 = tmp.getText().toString();
        } else if (term == 2) {
            term = 0;
            str2 = tmp.getText().toString();

            if(compare.get(str1) == null){
                tmp.setBackgroundColor(Color.parseColor("#dee0de"));
                // Status 1 means not match
                search(str1, 1);
                str2 = null;
                str1 = null;
                return;
            }

            if (stringCompare(compare.get(str1).toString(), str2) == 0) {
                tmp.setVisibility(View.GONE);
                // Status 0 means matched
                search(str1, 0);
            } else {
                tmp.setBackgroundColor(Color.parseColor("#dee0de"));
                // Status 1 means not match
                search(str1, 1);
            }

            str2 = null;
            str1 = null;
        }
    }

    public void search(String str, int status) {
        if((stringCompare(str, w1.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            w1.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, w1.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            w1.setVisibility(View.GONE);
        }

        if((stringCompare(str, w2.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            w2.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, w2.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            w2.setVisibility(View.GONE);
        }

        if((stringCompare(str, w3.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            w3.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, w3.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            w3.setVisibility(View.GONE);
        }

        if((stringCompare(str, w4.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            w4.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, w4.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            w4.setVisibility(View.GONE);
        }

        if((stringCompare(str, w5.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            w5.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, w5.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            w5.setVisibility(View.GONE);
        }

        if((stringCompare(str, m1.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            m1.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, m1.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            m1.setVisibility(View.GONE);
        }

        if((stringCompare(str, m2.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            m2.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, m2.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            m2.setVisibility(View.GONE);
        }

        if((stringCompare(str, m3.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            m3.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, m3.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            m3.setVisibility(View.GONE);
        }

        if((stringCompare(str, m4.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            m4.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, m4.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            m4.setVisibility(View.GONE);
        }

        if((stringCompare(str, m5.getText().toString()) == 0) && (status == 1)){
            // status 1 means wrong
            m5.setBackgroundColor(Color.parseColor("#dee0de"));
        } else if ((stringCompare(str, m5.getText().toString()) == 0) && (status == 0)) {
            // status 0 means matched
            m5.setVisibility(View.GONE);
        }
    }

    public int random(int max, int min) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    public String[][] generate_data(String[][] arg){

        myNumbers = new int[5];
        int total_elements_cnt = 0, num = 0;
        boolean loop_status = true;

        String data[][] = new String[2][5];
        int index = 0;

        while (loop_status) {
            num = random(52, 1);
            if (!isCompleted()) {
                if (!isDuplicate(num)) {
                    myNumbers[total_elements_cnt] = num;
                    total_elements_cnt++;
                }
            } else {
                loop_status = false;
            }
        }

        myNumbers[0] = myNumbers[0] - 1;
        myNumbers[1] = myNumbers[1] - 1;
        myNumbers[2] = myNumbers[2] - 1;
        myNumbers[3] = myNumbers[3] - 1;
        myNumbers[4] = myNumbers[4] - 1;

        for(int i = 0; i < 5; i++){
            data[0][i] = arg[0][myNumbers[i]];
            data[1][i] = arg[2][myNumbers[i]];
        }

        myNumbers = null;
        myNumbers = new int[5];
        total_elements_cnt = 0;
        num = 0;
        loop_status = true;

        while (loop_status) {
            num = random(5, 1);
            if (!isCompleted()) {
                if (!isDuplicate(num)) {
                    myNumbers[total_elements_cnt] = num;
                    total_elements_cnt++;
                }
            } else {
                loop_status = false;
            }
        }

        myNumbers[0] = myNumbers[0] - 1;
        myNumbers[1] = myNumbers[1] - 1;
        myNumbers[2] = myNumbers[2] - 1;
        myNumbers[3] = myNumbers[3] - 1;
        myNumbers[4] = myNumbers[4] - 1;

        return data;
    }

    public static boolean isCompleted(){
        boolean status = true;
        for (int i = 0; i < myNumbers.length; i++){
            if(myNumbers[i]==0){
                status = false;
                break;
            }

        }
        return  status;
    }

    public static boolean isDuplicate(int num){
        boolean status = false;
        for (int i = 0; i < myNumbers.length; i++){
            if(myNumbers[i]== num){
                status = true;
                break;
            }
        }
        return  status;
    }

    public static int stringCompare(String str01, String str02) {

        if (str01 == null){
            return 1;
        } else if (str02 == null) {
            return 1;
        }

        int l1 = str01.length();
        int l2 = str02.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str01.charAt(i);
            int str2_ch = (int)str02.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }

}
