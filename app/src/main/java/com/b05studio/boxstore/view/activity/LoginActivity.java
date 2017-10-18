package com.b05studio.boxstore.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.BoxstoreUser;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.UserGetResponse;
import com.b05studio.boxstore.util.BaseUtil;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.stetho.server.http.HttpStatus;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    @BindView(R.id.loginIdTextView)
    EditText idEditText;

    @BindView(R.id.loginPasswordTextView)
    EditText passwordEditText;

    @BindView(R.id.loginFacebookButton)
    ImageButton facebookLoginButton;

    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
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
        firebaseAuthWithEmailAndPassword(idEditText.getText().toString(), passwordEditText.getText().toString());
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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

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
                firebaseAuthWithFacebook(loginResult.getAccessToken());
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

    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithEmailAndPassword(String email, String password) {
        showProgressDialog();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "signInWithCredential:success");
                                    checkRegisterEmailLoginUser();

                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "회원정보를 다시 확인해주세요.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                hideProgressDialog();

                            }
                        }
                );
    }

    private void firebaseAuthWithFacebook(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        showProgressDialog();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            checkRegisterSNSLoginUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        hideProgressDialog();
                    }
                });
    }

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            checkRegisterSNSLoginUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void checkRegisterEmailLoginUser() {
        final FirebaseUser user = mAuth.getCurrentUser();
        final String uid = user.getUid();
        BoxStoreHttpService boxStoreHttpService = BoxStoreApplication.getRetrofit().create(BoxStoreHttpService.class);
        Call<UserGetResponse> boxstoreUserCall = boxStoreHttpService.getUserData(uid);

        boxstoreUserCall.enqueue(new Callback<UserGetResponse>() {
            @Override
            public void onResponse(Call<UserGetResponse> call, Response<UserGetResponse> response) {
                if(response.code() == HttpStatus.HTTP_OK) {
                    BoxstoreUser boxstoreUser = response.body().getUserInfo();
                    BoxStoreApplication.setCurrentUser(boxstoreUser);
                    BaseUtil.moveActivity(LoginActivity.this, BoxstoreMenuActivity.class);
                    finish();

                } else {
                    BoxStoreApplication.getCurrentUser().setuId(uid);
                    BaseUtil.moveActivity(LoginActivity.this, SignUpActivity.class);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserGetResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"서버 상태를 확인해주세요",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    private void checkRegisterSNSLoginUser() {
        final FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        BoxStoreHttpService boxStoreHttpService = BoxStoreApplication.getRetrofit().create(BoxStoreHttpService.class);
        Call<UserGetResponse> boxstoreUserCall = boxStoreHttpService.getUserData(uid);

        boxstoreUserCall.enqueue(new Callback<UserGetResponse>() {
            @Override
            public void onResponse(Call<UserGetResponse> call, Response<UserGetResponse> response) {
                if(response.code() == HttpStatus.HTTP_OK) {
                    BoxstoreUser boxstoreUser = response.body().getUserInfo();
                    BoxStoreApplication.setCurrentUser(boxstoreUser);
                    BaseUtil.moveActivity(LoginActivity.this, BoxstoreMenuActivity.class);
                    finish();

                } else {
                    getUserInfoByFirebaseAuth(user);
                    BaseUtil.moveActivity(LoginActivity.this, IdentificationActivity.class);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserGetResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"서버 상태를 확인해주세요",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    private void getUserInfoByFirebaseAuth(FirebaseUser user) {

        final BoxstoreUser currentUser = BoxStoreApplication.getCurrentUser();
        currentUser.setName(user.getDisplayName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhotoURL(user.getPhotoUrl().toString());
        currentUser.setuId(user.getUid());
        BoxStoreApplication.setCurrentUser(currentUser);
//        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//            @Override
//            public void onComplete(@NonNull Task<GetTokenResult> task) {
//                currentUser.setUserToken(task.getResult().getToken());
//
//
//            }
//        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]

                // [END_EXCLUDE]
            }
        }
        else if(mCallbackManager != null) {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
