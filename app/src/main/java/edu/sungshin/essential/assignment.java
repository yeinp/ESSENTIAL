package edu.sungshin.essential;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class assignment extends AppCompatActivity {

    DatePicker dp;
    EditText edit1, edit2, edit3;
    Button Wrtbtn1;
    String fileName, fileName2, fileName3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit3 = (EditText) findViewById(R.id.edit3);
        Wrtbtn1 = (Button) findViewById(R.id.Wrtbtn1);

        View view = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if( view != null){
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#ECCEF5"));
            }
        }


        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1)
                + "_" + Integer.toString(cDay) + "_1.txt";
        String str = readDiary(fileName);

        fileName2 = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1)
                + "_" + Integer.toString(cDay) + "_2.txt";
        String str2 = readDiary(fileName2);

        fileName3 = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1)
                + "_" + Integer.toString(cDay) + "_3.txt";
        String str3 = readDiary(fileName3);



        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_" +
                        Integer.toString(monthOfYear + 1) + "_" +
                        Integer.toString(dayOfMonth) + "_1.txt";

                String str = readDiary(fileName);

                fileName2 = Integer.toString(year) + "_" +
                        Integer.toString(monthOfYear + 1) + "_" +
                        Integer.toString(dayOfMonth) + "_2.txt";

                String str2 = readDiary(fileName2);

                fileName3 = Integer.toString(year) + "_" +
                        Integer.toString(monthOfYear + 1) + "_" +
                        Integer.toString(dayOfMonth) + "_3.txt";

                String str3 = readDiary(fileName3);
                edit1.setText(str);
                edit2.setText(str2);
                edit3.setText(str3);

                Wrtbtn1.setEnabled(true);


            }
        });

        Wrtbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName,
                            Context.MODE_PRIVATE);
                    String str = edit1.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();

                    FileOutputStream outFs2 = openFileOutput(fileName2,
                            Context.MODE_PRIVATE);
                    String str2 = edit2.getText().toString();
                    outFs2.write(str2.getBytes());
                    outFs2.close();

                    FileOutputStream outFs3 = openFileOutput(fileName3,
                            Context.MODE_PRIVATE);
                    String str3 = edit3.getText().toString();
                    outFs3.write(str3.getBytes());
                    outFs3.close();

                    Toast.makeText(getApplicationContext(),  "키워드가 저장됐어요!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }

        });


    }


    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            Wrtbtn1.setText("수정하기");

        } catch (IOException e) {
            edit1.setHint("키워드 없음");
            Wrtbtn1.setText("새로 저장");

            edit2.setHint("키워드 없음");


            edit3.setHint("키워드 없음");

        }
        return diaryStr;
    }

    String readDiary2(String fName2) {
        String diaryStr2 = null;
        FileInputStream inFs2;
        try {
            inFs2 = openFileInput(fName2);
            byte[] txt = new byte[500];
            inFs2.read(txt);
            inFs2.close();
            diaryStr2 = (new String(txt)).trim();
            Wrtbtn1.setText("수정하기");

        } catch (IOException e) {
            edit1.setHint("키워드 없음");
            Wrtbtn1.setText("새로 저장");

            edit2.setHint("키워드 없음");


            edit3.setHint("키워드 없음");

        }
        return diaryStr2;
    }

    String readDiary3(String fName3) {
        String diaryStr3 = null;
        FileInputStream inFs3;
        try {
            inFs3 = openFileInput(fName3);
            byte[] txt = new byte[500];
            inFs3.read(txt);
            inFs3.close();
            diaryStr3 = (new String(txt)).trim();
            Wrtbtn1.setText("수정하기");

        } catch (IOException e) {
            edit1.setHint("키워드 없음");
            Wrtbtn1.setText("새로 저장");

            edit2.setHint("키워드 없음");


            edit3.setHint("키워드 없음");

        }
        return diaryStr3;
    }


}