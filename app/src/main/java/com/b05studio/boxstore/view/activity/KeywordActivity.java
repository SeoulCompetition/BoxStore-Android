package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.Keyword;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.BoxtorePostResponse;
import com.b05studio.boxstore.service.response.KeywordGetResponse;
import com.b05studio.boxstore.util.SimpleDividerItemDecoration;
import com.b05studio.boxstore.view.adapter.KeywordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KeywordActivity extends AppCompatActivity {

    List<String> keywordList = new ArrayList<>();

    @BindView(R.id.keywordRecyclerview)
    RecyclerView keywordRecyclerview;

    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private KeywordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);
        ButterKnife.bind(this);

        initRecyclerView();
        initSavedKeyword();
    }



    private void initRecyclerView() {

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        keywordRecyclerview.setLayoutManager(recyclerViewLayoutManager);

        mAdapter = new KeywordAdapter(this,keywordList);
        keywordRecyclerview.setAdapter(mAdapter);
        keywordRecyclerview.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
    }

    private void initSavedKeyword() {
        Retrofit retrofit = BoxStoreApplication.getRetrofit();
        Call<KeywordGetResponse> keywordGetResponseCall = retrofit.create(BoxStoreHttpService.class).getKeywordList(BoxStoreApplication.getCurrentUser().getuId());
        keywordGetResponseCall.enqueue(new Callback<KeywordGetResponse>() {
            @Override
            public void onResponse(Call<KeywordGetResponse> call, Response<KeywordGetResponse> response) {
                if(response.body().getKeywordList()!= null)
                keywordList.addAll(response.body().getKeywordList());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<KeywordGetResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            final String keyword = data.getStringExtra("keyword");
            final String category = data.getStringExtra("category");

            Retrofit retrofit = BoxStoreApplication.getRetrofit();

            if(keyword != null) {
                Log.d("keywordcategory", keyword);
                keywordList.add(keyword);

                Keyword kewordInstance = new Keyword(BoxStoreApplication.getCurrentUser().getuId(),BoxStoreApplication.getCurrentUser().getUserToken(),keyword);
                Call<BoxtorePostResponse> uploadKeywordCall = retrofit.create(BoxStoreHttpService.class).postKeyword(kewordInstance);
                uploadKeywordCall.enqueue(new Callback<BoxtorePostResponse>() {
                    @Override
                    public void onResponse(Call<BoxtorePostResponse> call, Response<BoxtorePostResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<BoxtorePostResponse> call, Throwable t) {

                    }
                });

            }
            if(category != null) {

                keywordList.add(category);
                Log.d("categorycategory", category);
                Keyword kewordInstance = new Keyword(BoxStoreApplication.getCurrentUser().getuId(),BoxStoreApplication.getCurrentUser().getUserToken(),category);
                Call<BoxtorePostResponse> uploadCategoryCall = retrofit.create(BoxStoreHttpService.class).postKeyword(kewordInstance);
                uploadCategoryCall.enqueue(new Callback<BoxtorePostResponse>() {
                    @Override
                    public void onResponse(Call<BoxtorePostResponse> call, Response<BoxtorePostResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<BoxtorePostResponse> call, Throwable t) {

                    }
                });
            };

            mAdapter.notifyDataSetChanged();
        }

        //// TODO: 2017-10-24 여기서 받은걸로 keyword Recyclerview Add 
         /*
        String InputText = "";
        int lastPosition = mAdapter.getItemCount()-1;
        mAdapter.addItem(lastPosition,InputText);
        //mAdapter.notifyDataSetChanged();
        */
    }

    @OnClick(R.id.keywordBackButton)
    public void onCllickBackPress(){
        onBackPressed();
    }

    @OnClick(R.id.keyword_plus_btn)
    public void onClickPlusBtn(){
        Intent intent = new Intent(KeywordActivity.this,KeywordAddActivity.class);
        //// TODO: 2017-10-24 일단 0으로 둠
        startActivityForResult(intent,0);
    }
}
