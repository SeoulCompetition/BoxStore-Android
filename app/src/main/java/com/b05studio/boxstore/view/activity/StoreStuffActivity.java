package com.b05studio.boxstore.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialcamera.MaterialCamera;
import com.b05studio.boxstore.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seungwoo on 2017-10-31.
 */

public class StoreStuffActivity extends AppCompatActivity {

    private static final int RC_CAMERA = 3000;
    private final static int CAMERA_RQ = 6969;

    private ArrayList<Uri> imagePath = new ArrayList<>();

    @BindView(R.id.sellProductNameEditText)
    EditText sellProductNameEditText;
    @BindView(R.id.locker_number)
    EditText lock_number;
    @BindView(R.id.locker_password)
    EditText lock_password;
    @BindView(R.id.etcEditText)
    EditText etcEditText;



    @OnClick(R.id.seeStoreLocationText)
    public void onClickStore(){

    }

    @OnClick(R.id.bill_photo_btn)
    public void onClickBilBtn(){
        final Activity activity = StoreStuffActivity.this;
        final String[] permissions = new String[]{Manifest.permission.CAMERA};
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, permissions, RC_CAMERA);
            Log.d("Permission!!!!!!", "PERMIDOJFOSIJFIOSDJOI");
        } else {
            new MaterialCamera(StoreStuffActivity.this)
                    .stillShot() // launches the Camera in stillshot mode
                    .start(CAMERA_RQ);
        }

    }
    @OnClick(R.id.completeItemStoreBtn)
    public void onClickCompleteBtn(){


        final String tradeStationName = sellProductNameEditText.getText().toString();
        final String lockerNumber = lock_number.getText().toString();
        final String lockerPassword = lock_password.getText().toString();
        final String etcEditTextString = etcEditText.getText().toString();
        final String BillURL = imagePath.get(0).toString();

        Intent intent = new Intent();
        intent.putExtra("trade_station",tradeStationName);
        intent.putExtra("locker_number",lockerNumber);
        intent.putExtra("locker_password",lockerPassword);
        intent.putExtra("etc",etcEditTextString);
        intent.putExtra("bill_URL",BillURL);



    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store_stuff);
        ButterKnife.bind(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Received recording or error from MaterialCamera
        if (requestCode == CAMERA_RQ) {
            if (resultCode == RESULT_OK) {
                //Toast.makeText(this, "Saved to: " + data.getDataString(), Toast.LENGTH_LONG).show();
                imagePath.add(data.getData());

            } else if (data != null) {
                Exception e = (Exception) data.getSerializableExtra(MaterialCamera.ERROR_EXTRA);
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                imagePath.add(data.getData());
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
