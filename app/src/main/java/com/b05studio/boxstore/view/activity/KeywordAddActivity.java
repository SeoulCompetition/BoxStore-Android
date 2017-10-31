package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seungwoo on 2017-10-23.
 */

public class  KeywordAddActivity extends AppCompatActivity{

    private static final int SELECT_CATEGORY_BUTTON = 3001;
    @BindView(R.id.edit_keyword)
    EditText editKeyword;

    @BindView(R.id.keyword_icon)
    ImageView keywordIcon;

    private String keyword = "";
    private String category = "";

    @OnClick(R.id.category_view)
    public void onClickCategorySelected() {
        Intent intent = new Intent(KeywordAddActivity.this, CategorySelectActivity.class);
        startActivityForResult(intent,SELECT_CATEGORY_BUTTON);
    }

    @BindView(R.id.category_explain)
    TextView categoryTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_add);
        //#키 서울 색깔로
        ButterKnife.bind(this);
        keywordIcon.setColorFilter(getApplicationContext().getResources().getColor(R.color.seoul));
    }

    @OnClick(R.id.keywordAddBackButton)
    public void onBackBtnClick(){
        onBackPressed();
    }

    @OnClick(R.id.addKeywordBtn)
    public void onClickAddBtn(){
        keyword = editKeyword.getText().toString();
        Intent intent = new Intent();
        if(!keyword.equals("")) intent.putExtra("keyword", keyword);
        if(!category.equals("")) intent.putExtra("category",category);
        setResult(0,intent);
        finish();
    }

    @Override
    protected void onDestroy(){      //액티비티가 종료될 때의 메서드
        super.onDestroy();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Received recording or error from MaterialCamera
        if( requestCode == SELECT_CATEGORY_BUTTON) {
            String category = data.getStringExtra("category");
            categoryTextView.setText(category);
            this.category = category;
        }
    }
}
