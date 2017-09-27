package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.util.BaseUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginIdTextView)
    EditText idEditText;

    @BindView(R.id.loginPasswordTextView)
    EditText passwordEditText;

    @OnClick(R.id.loginIdPasswrodSubmitButton)
    public void onClickIdAndPasswordLoginButton() {

    }

    @OnClick(R.id.loginRegisterButton)
    public void onClickRegisterButton() {
        BaseUtil.moveActivity(LoginActivity.this,SignUpActivity.class);
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
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
