package edu.sungshin.essential;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class HomeFragment extends Fragment {

    public Button btnWorkout,btnEnglish,btnAssignment;
    TextView name;
    String result;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);
        btnWorkout = rootView.findViewById(R.id.btnWorkout);
        btnEnglish = rootView.findViewById(R.id.btnEnglish);
        btnAssignment = rootView.findViewById(R.id.btnAssignment);
        name=rootView.findViewById(R.id.name);

        Intent intentReceived = getActivity().getIntent();
        Bundle data=intentReceived.getExtras();

        if(data !=null){

            result=data.getString("nickname"); //googlelogin_test에서 nickname 받아옴
            name.setText(result);

        }
        else{
            result="";

        }


        btnWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChallengeActivity1.class); //fragment라서 activity intent와는 다른 방식
                startActivity(intent);
            }
        });

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChallengeActivity2.class); //fragment라서 activity intent와는 다른 방식
                startActivity(intent);
            }
        });

        btnAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChallengeActivity3.class); //fragment라서 activity intent와는 다른 방식
                startActivity(intent);
            }
        });



        return rootView;
    }


}