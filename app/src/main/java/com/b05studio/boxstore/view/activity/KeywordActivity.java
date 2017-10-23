package com.b05studio.boxstore.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @BindView(R.id.keyword_icon)
    ImageView keword_icon;

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

    @OnClick(R.id.keyword_add_btn)
    public void OnAddClick(){

        String InputText = "";
        int lastPosition = mAdapter.getItemCount()-1;
        mAdapter.addItem(lastPosition,InputText);
        //mAdapter.notifyDataSetChanged();
    }
}
