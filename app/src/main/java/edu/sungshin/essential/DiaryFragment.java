package edu.sungshin.essential;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class DiaryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View view;
    private Button ss;





    public DiaryFragment() {
        // Required empty public constructor
    }

    public static DiaryFragment newInstance(String param1, String param2) {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;
    ImageView happy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_diary, container, false);

        dp = (DatePicker) view.findViewById(R.id.datePicker1);
        edtDiary = (EditText) view.findViewById(R.id.edtDiary);
        btnWrite = (Button) view.findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);


        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth+1)
                + "_" + Integer.toString(cDay) + ".txt";

        String str = readDiary(fileName);
        edtDiary.setText(str);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_"
                        + Integer.toString(monthOfYear + 1) + "_"
                        + Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(fileName);
                edtDiary.setText(str);
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad  = new AlertDialog.Builder(getActivity());
                ad.setMessage("일기를 저장할까요?");
                ad.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            FileOutputStream outFs = getContext().openFileOutput(fileName,
                                    Context.MODE_PRIVATE);
                            String str = edtDiary.getText().toString();
                            outFs.write(str.getBytes());
                            outFs.close();
                            Toast.makeText(getActivity().getApplicationContext(), "일기가 저장됐어요!", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                        }
                    }
                });
                ad.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ad.show();

            }
        });





        return view;
    }

    private String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = getContext().openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정 하기");
        }catch (IOException e) {
            edtDiary.setHint("일기 쓰기");
            btnWrite.setText("저장하기");
        }
        return diaryStr;
    }
    };
