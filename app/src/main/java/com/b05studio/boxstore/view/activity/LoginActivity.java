package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.util.BaseUtil;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.loginIdTextView)
    EditText idEditText;

    @BindView(R.id.loginPasswordTextView)
    EditText passwordEditText;

    @BindView(R.id.loginFacebookButton)
    ImageButton facebookLoginButton;

    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;

    @OnClick(R.id.loginRegisterButton)
    public void onClickRegisterButton() {
        BaseUtil.moveActivity(LoginActivity.this, SignUpActivity.class);
    }

    @OnClick(R.id.loginRetrivePasswordButton)
    public void onClickRetrivePasswordButton() {
        // 비밀번호 찾기
    }

    @OnClick(R.id.loginIdPasswrodSubmitButton)
    public void onClickIdAndPasswordLoginButton() {
        signInWithEmailAndPassword(idEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(R.id.loginFacebookButton)
    public void onClickFacebookLoginButton() {
        registerFacebookLoginButtonEvent();
    }

    @OnClick(R.id.loginGoogleButton)
    public void onClickGoogleLoginButton() {
        // 구글 로그인
        signInWithGoogle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void registerFacebookLoginButtonEvent() {
        //페이스북으로 로그인버튼하기 누르면 동작되는 함수구현
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager,new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // 로그인 성공.
                signInWithFacebook(loginResult.getAccessToken());
                Log.d("Facebook Login", "SUCCESS");
            }

            @Override
            public void onCancel() {
                // 로그인 실패
                Log.d("Facebook Login", "Fail");
            }

            @Override
            public void onError(FacebookException error) {
                // 로그인 에러
                Log.d("Facebook Login", "Error");
            }
        });
    }

    private void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // TODO: 2017-10-01 일단은 바로 연결되게, 하지만 나중에 서버에서 재검증 필요.
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Log.d(TAG, "signInWithEmail:success");
                                    startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                                    finish();
                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "회원정보를 다시 확인해주세요.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                );
    }

    private void signInWithFacebook(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        // TODO: 2017-09-19 여기에 사용자 정보저장
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void signInWithGoogle() {
        // TODO: 2017-10-01 작성해야댐
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(mCallbackManager != null)
            mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
