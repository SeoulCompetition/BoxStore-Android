package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.util.BaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    @BindView(R.id.signupIdTextView)
    EditText idEditText;

    @BindView(R.id.signupPasswordTextView)
    EditText passwordEditText;

    @BindView(R.id.signupPasswordRepeatTextView)
    EditText passwordRepeatEditText;

    private FirebaseAuth mAuth;

    @OnClick(R.id.signupCheckIdButton)
    public void checkIDButton() {
        if (alreadyRegisterID()) {
            Toast.makeText(getApplicationContext(), "이미 가입된 아이디가 있습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
        }

        // TODO: 2017-09-25 나중에 데이터베이스가 생기면 고려해야되는 부분이라 생각됨. 
    }

    @OnClick(R.id.signupNextButton)
    public void moveIdenficationAcitivity() {
        // TODO: 2017-10-01  서버에서 검증하는거 구현해야됨.
        // TODO: 2017-09-25 유효값 검증 기능을 구현해야됨.
        if (!alreadyRegisterID() && isEqualPassword()) {
            createEmailAndPassWordUser();

        } else {

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private boolean alreadyRegisterID() {

        boolean isAleadyRegistedId = false;
        // 회원정보가 제공되면 true;
        // 회원정보가 제공되지 않으면 false;
        // TODO: 2017-09-27 서버로부터 작성해야됨.
        if (isAleadyRegistedId) {
            Toast.makeText(getApplicationContext(), "이미 가입된 ID의 정보가 있습니다..", Toast.LENGTH_SHORT).show();
        }

        return isAleadyRegistedId;
    }

    private boolean isEqualPassword() {
        boolean isEqual = passwordEditText.getText().toString().equals(passwordRepeatEditText.getText().toString());
        if (!isEqual) {
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
        return isEqual;
    }

    private void createEmailAndPassWordUser() {
        final String email = idEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            BoxStoreApplication.getCurrentUser().setEmail(email);
                            BaseUtil.moveActivity(SignUpActivity.this, IdentificationActivity.class);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }


}
