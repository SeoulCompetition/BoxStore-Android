package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seungwoo on 2017-10-31.
 */

public class LockerLocationActivity extends AppCompatActivity {

    @BindView(R.id.stationText)
    TextView stationText;
    @BindView(R.id.stationMapDesText)
    TextView stationMapDesText;
    @BindView(R.id.stationMapImage)
    ImageView mapImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_locker_location);
        ButterKnife.bind(this);
        

        setStationNameAndDescription();
    }

    private void setStationNameAndDescription() {
        Intent intent = getIntent();
        stationText.setText(intent.getStringExtra("Station"));
        stationMapDesText.setText(intent.getStringExtra("StationDes"));
        String imageURl = intent.getStringExtra("ImageURL");
        Picasso.with(this).load(imageURl).into(mapImageView);
    }


    @OnClick(R.id.popupMap)
    public void onClickPopup(){
        
    }
}
