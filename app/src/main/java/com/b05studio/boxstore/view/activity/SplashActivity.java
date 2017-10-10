package com.b05studio.boxstore.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.BoxstoreUser;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.util.BaseUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by seungwoo on 2017-09-17.
 */

public class SplashActivity extends AppCompatActivity {


    private final static String TAG = "SplashActivity";
//
//    private final static int RC_CAMERA_AND_EXTERNAL = 11;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

//        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//          //  startActivity(intent);
//            // 권한을 이미 가지고 있을때이동.
//        }
//
//        methodRequiresTwoPermission();

    } @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) {
            BaseUtil.moveActivity(this,LoginActivity.class);
            finish();
        } else {
            checkRegisterLoginUserInSplash();
        }
    }

    private void checkRegisterLoginUserInSplash() {
        final FirebaseUser user = mAuth.getCurrentUser();
        final String uid = user.getUid();
        BoxStoreHttpService boxStoreHttpService = BoxStoreApplication.getRetrofit().create(BoxStoreHttpService.class);
        Call<BoxstoreUser> boxstoreUserCall = boxStoreHttpService.getUserData(uid);

        boxstoreUserCall.enqueue(new Callback<BoxstoreUser>() {
            @Override
            public void onResponse(Call<BoxstoreUser> call, Response<BoxstoreUser> response) {
                BoxstoreUser boxstoreUser = response.body();
                Log.d("response.code",String.valueOf(response.code()));
                if(response.code() == RESULT_OK) {
                    Log.d("Usercall",call.toString());
                    Log.d("Userresponse", response.toString());
                    BoxStoreApplication.setCurrentUser(boxstoreUser);
                    BaseUtil.moveActivity(SplashActivity.this, BoxstoreMenuActivity.class);
                    finish();

                } else {
                    BaseUtil.moveActivity(SplashActivity.this, LoginActivity.class);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BoxstoreUser> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

//
//    @AfterPermissionGranted(RC_CAMERA_AND_EXTERNAL)
//    private void methodRequiresTwoPermission() {
//        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//
//            // Already have permission, do the thing
//            Log.d(TAG,"You  a aleady have Permissions");
//            finish();
//
//        } else {
//            // Do not have permissions, request them now
//            EasyPermissions.requestPermissions(this, getString(R.string.camera_and_external_storage),
//                    RC_CAMERA_AND_EXTERNAL, perms);
//            Log.d(TAG,"Request Permission");
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        // Forward results to EasyPermissions
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
//        Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
//    }
}