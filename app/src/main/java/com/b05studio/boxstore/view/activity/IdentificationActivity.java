package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.BoxstoreUser;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.util.BaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IdentificationActivity extends AppCompatActivity {


    @BindView(R.id.identifyNameEditText)
    EditText userNameEditText;

    @BindView(R.id.identifyPhoneNumEditText)
    EditText phoneEditText;

    @BindView(R.id.identifyAuthCodeEditText)
    EditText authCodeEditText;

    private boolean isFirstTimeGetToken = true;

    // 전화번호 인증
    @OnClick(R.id.identifyRequestAuthCodeButton)
    public void onClickRequestAuthCodeButton() {

        Toast.makeText(getApplicationContext(),"문자메시지로 전송된 인증번호를 입력해주세요", Toast.LENGTH_SHORT).show();

        if (!validatePhoneNumber())
            return;

        if(isFirstTimeGetToken) {
            startPhoneNumberVerification(phoneEditText.getText().toString());
            isFirstTimeGetToken = false;

        } else {
            resendVerificationCode(phoneEditText.getText().toString(), mResendToken);
        }
    }

    @OnClick(R.id.identifyNextButton)
    public void registerUser() {

        String code = authCodeEditText.getText().toString();
        if (TextUtils.isEmpty(code)) {
            authCodeEditText.setError("인증번호를 입력해주세요.");
            return;
        }

        verifyPhoneNumberWithCode(mVerificationId, code);
    }

    private static final String TAG = "IdentificationActivity";
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

        // TODO: 2017-10-07 ResendCode 처리
//        resendVerificationCode("01043019700",mResendToken);

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                // login nono

                // TODO: 2017. 10. 11. 여기서 로그인하지말고 사용해야됨
                signInWithPhoneAuthCredential(credential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    phoneEditText.setError("잘못된 전화번호 양식입니다.");

                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // 이 메소드는 제공된 전화번호로 인증 코드가 SMS를 통해 전송된 후에 호출됩니다.
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }

    // 해당 핸드폰 번호로 인증번호 발급.
    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                120,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true;
    }

    // 번호 누르고 인증번호 눌렀을때 하는 이벤트
    private void verifyPhoneNumberWithCode(String verificationId, String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    // 인증번호 다시보내기
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                120,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    // 여기서 인증 정보를 마저 담아야제.
    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser user = task.getResult().getUser();
                            // TODO: 2017-10-04 서버에 폰인증되었다는 정보와 함께 회원가입.'
                            // TODO: 2017-10-06 회원가입

                            final String uid = currentUser.getUid();
                            final String name = userNameEditText.getText().toString();
                            final String email = currentUser.getEmail();
                            final String photoUrl = currentUser.getPhotoUrl().toString();
                            final String phoneNum = phoneEditText.getText().toString();
                            currentUser.getIdToken(true)
                                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                                            if (task.isSuccessful()) {
                                                String idToken = task.getResult().getToken();
                                                BoxstoreUser boxstoreUser = new BoxstoreUser(uid,email,photoUrl,name,idToken,phoneNum);
                                                BoxStoreApplication.setCurrentUser(boxstoreUser);
                                                Retrofit retrofit = BoxStoreApplication.getRetrofit();
                                                BoxStoreHttpService httpService = retrofit.create(BoxStoreHttpService.class);
                                                httpService.registerUser(boxstoreUser).enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Call<String> call, Response<String> response) {
                                                        if(response.isSuccessful()) {
                                                            Log.d("회원정보 업로드 : ", "SUCCESS");
                                                        } else {
                                                            Log.d("회원정보 업로드 : ", "Fail");
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<String> call, Throwable t) {
                                                        Log.d("회원정보 업로드 : ", "Fail(서버상태확인)");
                                                    }
                                                });
                                            }
                                        }
                                    });



                            BaseUtil.moveActivity(IdentificationActivity.this,BoxstoreMenuActivity.class);
                            finish();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                authCodeEditText.setError("잘못된 인증번호입니다.");
                            }

                        }
                    }
                });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = phoneEditText.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            phoneEditText.setError("올바른 번호가 아닙니다.");
            return false;
        }

        return true;
    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.button_start_verification:
//                if (!validatePhoneNumber()) {
//                    return;
//                }
//                startPhoneNumberVerification(mPhoneNumberField.getText().toString());
//                break;
//            case R.id.button_verify_phone:
//                String code = mVerificationField.getText().toString();
//                if (TextUtils.isEmpty(code)) {
//                    mVerificationField.setError("Cannot be empty.");
//                    return;
//                }
//
//                verifyPhoneNumberWithCode(mVerificationId, code);
//                break;
//            case R.id.button_resend:
//                resendVerificationCode(mPhoneNumberField.getText().toString(), mResendToken);
//                break;
//            case R.id.sign_out_button:
//                signOut();
//                break;
//        }
//    }


}
