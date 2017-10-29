package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.view.adapter.DetailProductPagerAdapter;
import com.b05studio.boxstore.view.fragment.HomeFragment;
import com.b05studio.boxstore.view.fragment.MainStuffFragment;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailProductActivity extends AppCompatActivity {


    @OnClick(R.id.detailProductScrapButton)
    public void onClickDetailProductScrap() {
        // TODO: 2017-10-30 담아두기버튼눌렀을때
    }

    @OnClick(R.id.detailProductSellButton)
    public void onClickDetailPrdouctSell() {
        // TODO: 2017-10-30 구매하기버튼눌렀을때
    }

    @OnClick(R.id.detailProductBackButton)
    public void onClickBackButton() {
        finish();
    }

    @BindView(R.id.detailProductNameTitleTextview)
    TextView detailProductNameTitleTextview;

    @BindView(R.id.detailProductViewPager)
    ViewPager detailProductViewPager;

    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;

    @BindView(R.id.detailProductNameTextview)
    TextView detailProductNameTextview;

    @BindView(R.id.detailProductPriceTextview)
    TextView detailProductPriceTextview;

    @BindView(R.id.detailProductStateImageView)
    ImageView detailProductStateImageView;

    @BindView(R.id.detailProductStateTextView)
    TextView detailProductStateTextView;

    @BindView(R.id.detailPrductDescriptionTextView)
    TextView detailPrductDescriptionTextView;

    private Stuff selectedStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);

        selectedStuff = MainStuffFragment.selectedStuff;
        initProductImageViewPager();
        initProductInfomationView();
    }

    private void initProductImageViewPager() {
        DetailProductPagerAdapter detailProductPagerAdapter = new DetailProductPagerAdapter(getSupportFragmentManager(),selectedStuff.getImageUrl());
        detailProductViewPager.setAdapter(detailProductPagerAdapter);
        detailProductViewPager.setCurrentItem(0);
        pageIndicatorView.setViewPager(detailProductViewPager);
        pageIndicatorView.setSelectedColor(Color.parseColor("#4B65A7"));
        pageIndicatorView.setUnselectedColor(Color.parseColor("#F1F1F1"));
        pageIndicatorView.setAnimationType(AnimationType.DROP);
    }

    private void initProductInfomationView() {
        detailProductNameTitleTextview.setText(selectedStuff.getStuffName());
        detailProductNameTextview.setText(selectedStuff.getStuffName());
        detailProductPriceTextview.setText(String.valueOf(selectedStuff.getPrice()));
//        detailProductStateImageView.setImageResource(selectedStuff.get);
//        detailProductStateTextView.setText(selectedStuff.get);
        detailPrductDescriptionTextView.setText(selectedStuff.getStuffInfo());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
