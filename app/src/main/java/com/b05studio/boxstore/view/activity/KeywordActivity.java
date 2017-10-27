package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.view.adapter.KeywordAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KeywordActivity extends AppCompatActivity {

    List<String> keywordList;

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
    }

    private void initRecyclerView() {

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        keywordRecyclerview.setLayoutManager(recyclerViewLayoutManager);

        mAdapter = new KeywordAdapter(this,keywordList);
        keywordRecyclerview.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){

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
