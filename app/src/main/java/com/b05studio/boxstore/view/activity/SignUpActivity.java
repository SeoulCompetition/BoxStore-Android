package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.util.BaseUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignUpActivity extends AppCompatActivity {
    
    @BindView(R.id.signupIdTextView)
    EditText idEditText;
    
    @BindView(R.id.signupPasswordTextView)
    EditText passwordEditText;
    
    @BindView(R.id.signupPasswordRepeatTextView)
    EditText passwordRepeatEditText;
    
    @OnClick(R.id.signupCheckIdButton)
    public void checkIDButton() {
        if(alreadyRegisterID()) {
            Toast.makeText(getApplicationContext(), "이미 가입된 아이디가 있습니다.",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "사용가능한 아이디입니다.",Toast.LENGTH_SHORT).show();
        }

        // TODO: 2017-09-25 나중에 데이터베이스가 생기면 고려해야되는 부분이라 생각됨. 
    }
    
    @OnClick(R.id.signupNextButton)
    public void moveIdenficationAcitivity() {
        // TODO: 2017-09-25 유효값 검증 기능을 구현해야됨.
        if(alreadyRegisterID() && isEqualPassword()) {
            BaseUtil.moveActivity(SignUpActivity.this, IdentificationActivity.class);
        } else {
            Toast.makeText(getApplicationContext(), "회원정보가 올바르지 않습니다.",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private boolean alreadyRegisterID () {
        // TODO: 2017-09-27 작성해야됨.
        return true;
    }

    private boolean isEqualPassword() {
        return passwordEditText.getText().toString().equals(passwordRepeatEditText.getText().toString());
    }


}
