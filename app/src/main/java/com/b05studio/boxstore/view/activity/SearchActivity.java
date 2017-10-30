package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.Item;
import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.StuffGetResponse;
import com.b05studio.boxstore.view.adapter.SpinnerAdapter;
import com.b05studio.boxstore.view.adapter.StationAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Path;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.item_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.searchToolbarTitle)
    TextView searchToolbarTitle;

    @BindView(R.id.searchItemList)
    TextView searchItemListNum;

    android.widget.SpinnerAdapter adapterSpinner;

    ArrayList<Stuff> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String[] str= getResources().getStringArray(R.array.order);
        List<String> mNewList = new ArrayList<String>(Arrays.asList(str));

        adapterSpinner = new SpinnerAdapter(this,mNewList);
        Spinner spinner = (Spinner) findViewById(R.id.order_spinner);
        spinner.setAdapter(adapterSpinner);

        ButterKnife.bind(this);

        final GridLayoutManager mGrid = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mGrid);
        recyclerView.setHasFixedSize(true);
        final StationAdapter mAdapter = new StationAdapter(this,items);
        recyclerView.setAdapter(mAdapter);

        Intent intent = getIntent();
        String searchQuery = intent.getStringExtra("searchQuery");
        searchToolbarTitle.setText(searchQuery);

        Retrofit retrofit = BoxStoreApplication.getRetrofit();
        Call<StuffGetResponse> getStuffListByStationNameCall = retrofit.create(BoxStoreHttpService.class).getStuffListByStationName(searchQuery);
        getStuffListByStationNameCall.enqueue(new Callback<StuffGetResponse>() {
            @Override
            public void onResponse(Call<StuffGetResponse> call, Response<StuffGetResponse> response) {
                if(response.isSuccessful()) {
                    items.addAll(response.body().getStuffs());
                }
                searchItemListNum.setText("상품 " + String.valueOf(items.size()) + "건");
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<StuffGetResponse> call, Throwable t) {

            }
        });


//        Call<StuffGetResponse> getStuffListByKeywordCall = retrofit.create(BoxStoreHttpService.class).getStuffListByStationName(searchQuery);
//        Call<StuffGetResponse> getStuffListByCategoryCall = retrofit.create(BoxStoreHttpService.class).getStuffListByCategoryName(searchQuery);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.category_toolbar);
//        setSupportActionBar(toolbar);
    }
}
