package edu.sungshin.essential;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UserFragment extends Fragment {

    TextView logout;
    FirebaseAuth mAuth;
    TextView tv_result;
    TextView tv_email;
    ImageView iv_profile;
    GoogleSignInClient mGoogleSignInClient;
    TextView emailtext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user, container, false);
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_user, container, false);

        logout = v.findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        tv_result= v.findViewById(R.id.tv_result);
        tv_email= v.findViewById(R.id.tv_email);
        iv_profile=v.findViewById(R.id.iv_profile);
        emailtext = v.findViewById(R.id.emailtext);
        String result,email,photo, Email;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        Intent intentReceived = getActivity().getIntent();
        Bundle data=intentReceived.getExtras();


        if(data !=null){
            result=data.getString("nickname"); //googlelogin_test에서 nickname 받아옴
            tv_result.setText(result);



            photo=data.getString("photoUrl"); //googlelogin_test에서 photoUrl 받아옴
            Glide.with(this).load(photo).into(iv_profile); //프로필 url을 이미지 뷰에 세팅

            Email = data.getString("Email");
            emailtext.setText(Email);

            email=data.getString("e-mail"); //googlelogin_test에서 e-mail 받아옴
            tv_email.setText(email);

        }
        else{
            result="";
            email="";
            photo="";

        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getActivity(),LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getActivity(),"로그아웃 성공",Toast.LENGTH_LONG).show();
                        }
                        else{

                        }
                    }
                });
            }
        });




        return v;
    }

}