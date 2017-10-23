package com.b05studio.boxstore.view.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.b05studio.boxstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seungwoo on 2017-10-23.
 */

public class KeywordAddActivity extends AppCompatActivity{


    @BindView(R.id.edit_keyword)
    EditText editKeyword;
    @BindView(R.id.keyword_icon)
    ImageView keywordIcon;

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
        String getKeyword = editKeyword.getText().toString();
        //
        finish();
    }

    @Override
    protected void onDestroy(){      //액티비티가 종료될 때의 메서드
        super.onDestroy();
        setResult( 0 ); // 여기에 넣는 int형 정수는 MainActivity의 onActivityResult안에서 requestCode로 들어간다.
    }
}
