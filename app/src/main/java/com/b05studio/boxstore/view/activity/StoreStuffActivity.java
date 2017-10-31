package com.b05studio.boxstore.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.b05studio.boxstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-31.
 */

public class StoreStuffActivity extends AppCompatActivity {

    @BindView()

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store_stuff);
        ButterKnife.bind(this);
    }
}
