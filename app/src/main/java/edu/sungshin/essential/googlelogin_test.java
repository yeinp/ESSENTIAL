package edu.sungshin.essential;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class googlelogin_test extends AppCompatActivity {
    private SignInButton btn_google; // 구글 로그인 버튼 private FirebaseAuth auth; /// / 파이어베이스 인증 객체//
    private GoogleSignInClient googleSignInClient; // 구글 API 클라이언트 객체
    private static final int REQ_SIGN_GOOGLE = 200; // 구글 로그인 결과 코드
    private FirebaseAuth mAuth;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlelogin_test);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) .requestIdToken(getString(R.string.default_web_client_id)) .requestEmail() .build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
        mAuth = FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화
        btn_google = findViewById(R.id.btn_google);
        btn_google.setOnClickListener(new View.OnClickListener() { // 구글 로그인 버튼 클릭했을 때 이곳을 수행.
            @Override public void onClick(View view) {
                Intent intent = googleSignInClient.getSignInIntent(); // 구글이 만들어놓은 인증페이지로 이동.
                startActivityForResult(intent, REQ_SIGN_GOOGLE);
            }
        });
    }
    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // 구글 인증을 요청했을 떄 결과값을 되돌려 받는 곳.
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_SIGN_GOOGLE){ Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if(task.isSuccessful()){
                GoogleSignInAccount account = task.getResult(); // account 라는 데이터는 구글 로그인 정보를 담고있다. (닉네임 프로필 사진url, 이메일주소등등..)
                resultLogin(account); // 로그인 결과 값 출력 수행하라는 메소드
            }
        }
    } // 로그인 결과값 출력을 수행하는 메소드
    private void resultLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential) .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override public void onComplete(@NonNull Task<AuthResult> task) { // 로그인이 성공하면 닉네임, 프로필사진, 이메일 정보등을 task에 저장한 후 ResultActivity로 넘김.
                if(task.isSuccessful()) { Toast.makeText(googlelogin_test.this, "로그인 성공~!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserFragment.class);
//                    Bundle bundle=new Bundle();
//                    bundle.putString("nickname",account.getDisplayName());
//                    bundle.putString("photoUrl",String.valueOf(account.getPhotoUrl()));
//                    bundle.putString("e-mail",account.getEmail());

                    intent.putExtra("nickname", account.getDisplayName()); //Uri 객체는 String으로 담아줘야한다. String.ValueOf 는 특정 자료형을 String 형태로 변환시켜주는 역할을 함.
                    intent.putExtra("photoUrl", String.valueOf(account.getPhotoUrl()));
                    intent.putExtra("e-mail",account.getEmail());
                    startActivity(intent); }
                else {
                    Toast.makeText(googlelogin_test.this, "로그인 실패~!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}