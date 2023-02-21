package edu.sungshin.essential;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class english extends AppCompatActivity {
    private static final String TAG = "english";

    Fragment mainFragment;
    EditText inputToDo, inputToDo2;
    Context context, context2;

    public static NoteDatabase noteDatabase = null;
    public static NoteDatabase noteDatabase2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);

        mainFragment = new MainFragment();

        //getSupportFragmentManager 을 이용하여 이전에 만들었던 **FrameLayout**에 `fragment_main.xml`이 추가
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        View view = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if( view != null){
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#F5DA81"));
            }
        }

        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveToDo();
                saveToDo2();

                Toast.makeText(getApplicationContext(), "추가되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
        openDatabase();
        openDatabase2();
    }

    private void saveToDo() {
        inputToDo = findViewById(R.id.inputToDo);


        //EditText에 적힌 글을 가져오기
        String todo = inputToDo.getText().toString();


        //테이블에 값을 추가하는 sql구문 insert...
        String sqlSave = "insert into " + NoteDatabase.TABLE_NOTE + " (TODO) values (" +
                "'" + todo + "')";


        //sql문 실행
        NoteDatabase database = NoteDatabase.getInstance(context);

        database.execSQL(sqlSave);


        //저장과 동시에 EditText 안의 글 초기화
        inputToDo.setText("");

    }


    public void openDatabase() {
        // open database
        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }

        noteDatabase = NoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }


    }

    private void saveToDo2() {
        inputToDo2 = findViewById(R.id.inputToDo2);


        //EditText에 적힌 글을 가져오기
        String todo2 = inputToDo2.getText().toString();


        //테이블에 값을 추가하는 sql구문 insert...
        String sqlSave2 = "insert into " + NoteDatabase.TABLE_NOTE + " (TODO) values (" +
                "'" + todo2 + "')";


        //sql문 실행
        NoteDatabase database2 = NoteDatabase.getInstance(context2);

        database2.execSQL(sqlSave2);


        //저장과 동시에 EditText 안의 글 초기화
        inputToDo2.setText("");

    }


    public void openDatabase2() {
        // open database
        if (noteDatabase2 != null) {
            noteDatabase2.close();
            noteDatabase2 = null;
        }

        noteDatabase2 = NoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase2.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }


    }

}