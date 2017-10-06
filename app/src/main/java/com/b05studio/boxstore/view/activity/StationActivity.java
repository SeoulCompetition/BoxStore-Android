package com.b05studio.boxstore.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Item;
import com.b05studio.boxstore.view.Adapter.Spinner_Adapter;
import com.b05studio.boxstore.view.Adapter.Station_Adapter;

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
    SpinnerAdapter adapterSpinner;


    private final String android_image_urls = "http://api.learn2crack.com/android/images/donut.png";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);


        String[] str= getResources().getStringArray(R.array.order);

        List<String> mNewList = new ArrayList<String>(Arrays.asList(str));

        adapterSpinner = new Spinner_Adapter(this,mNewList);
        Spinner spinner = (Spinner) findViewById(R.id.order_spinner);
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
        Station_Adapter mAdapter = new Station_Adapter(this,items);
        recyclerView.setAdapter(mAdapter);

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.category_toolbar);
        .setSupportActionBar(toolbar);
        toolbar.setTitle("카테고리");
//
        ButterKnife.bind(this,view);
*/
    }
}
