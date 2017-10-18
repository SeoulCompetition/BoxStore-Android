package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Item;
import com.b05studio.boxstore.view.adapter.SpinnerAdapter;
import com.b05studio.boxstore.view.adapter.StationAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-02.
 */

public class StationActivity extends AppCompatActivity {

    @BindView(R.id.item_recycler)
    RecyclerView recyclerView;
    android.widget.SpinnerAdapter adapterSpinner;


    private final String android_image_urls = "http://api.learn2crack.com/android/images/donut.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);


        String[] str= getResources().getStringArray(R.array.order);

        List<String> mNewList = new ArrayList<String>(Arrays.asList(str));

        adapterSpinner = new SpinnerAdapter(this,mNewList);
        Spinner spinner = (Spinner) findViewById(R.id.order_spinner);
        Spinner category_spinner = (Spinner) findViewById(R.id.category_spinner);

        category_spinner.setAdapter(adapterSpinner);
        spinner.setAdapter(adapterSpinner);
        ButterKnife.bind(this);

        ArrayList items = new ArrayList<>();
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));
        items.add(new Item(android_image_urls,"Android",android_image_urls,"Sell Box","10000"));

        GridLayoutManager mGrid = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mGrid);
        recyclerView.setHasFixedSize(true);
        StationAdapter mAdapter = new StationAdapter(this,items);
        recyclerView.setAdapter(mAdapter);

        // TODO: 카테고리 클릭시 putExtra로 클릭한 카테고리 이름 전송
        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("CATEGORY_NAME");


        Toolbar toolbar = (Toolbar) findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);
        // TODO: 백버튼 추가
//        toolbar.setTitle(categoryName);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 //       getSupportActionBar().setDisplayShowHomeEnabled(true);




    }
}
