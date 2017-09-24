package com.b05studio.boxstore.auth;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.b05studio.boxstore.R;

import butterknife.BindView;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginIdTextView)
    EditText idTextView;

    @BindView(R.id.loginPasswordTextView)
    EditText passwordTextView;

    @OnClick(R.id.loginIdPasswrodSubmitButton)
    public void onClickIdAndPasswordLoginButton() {

    }

    @OnClick(R.id.loginRegisterButton)
    public void onClickRegisterButton() {

    }

    @OnClick(R.id.loginRetrivePasswordButton)
    public void onClickRetrivePasswordButton() {

    }

    @OnClick(R.id.loginFacebookButton)
    public void onClickFacebookLoginButton() {

    }

    @OnClick(R.id.loginGoogleButton)
    public void onClickGoogleLoginButton() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }




}
