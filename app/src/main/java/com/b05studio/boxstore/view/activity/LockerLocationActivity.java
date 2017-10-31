package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.MapGetResponse;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        Intent intent = getIntent();
        stationText.setText(intent.getStringExtra("Station"));
        stationMapDesText.setText(intent.getStringExtra("StationDes"));

        Retrofit retrofit = BoxStoreApplication.getRetrofit();
        Call<MapGetResponse> mapGetResponseCall = retrofit.create(BoxStoreHttpService.class).getMapUrl(intent.getStringExtra("Station"));
        mapGetResponseCall.enqueue(new Callback<MapGetResponse>() {
            @Override
            public void onResponse(Call<MapGetResponse> call, Response<MapGetResponse> response) {
                if(response.isSuccessful()) {
                    String imageURl = response.body().getMapURL();
                    if(imageURl != null)
                        Picasso.with(getApplicationContext()).load(imageURl).into(mapImageView);
                }
            }

            @Override
            public void onFailure(Call<MapGetResponse> call, Throwable t) {

            }
        });
    }


    @OnClick(R.id.popupMap)
    public void onClickPopup(){
        
    }
}
