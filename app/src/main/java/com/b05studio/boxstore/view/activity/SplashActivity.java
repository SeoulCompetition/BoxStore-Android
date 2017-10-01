package com.b05studio.boxstore.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.util.BaseUtil;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


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

        if (currentUser != null) {
            getUserData(currentUser.getUid());
            finish();

        } else {
            BaseUtil.moveActivity(this,LoginActivity.class);
            finish();
        }
    }

    private void getUserData(String uid) {
        // 서버로부터 정보 가져오는거 구현
        // TODO: 2017-10-01 레트로핏 들어가야되는 부분.
        // 서버에 회원정보없어서 못가져와도 바로 회원가입 액티비티.
        BaseUtil.moveActivity(this,BoxstoreMenuActivity.class);
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