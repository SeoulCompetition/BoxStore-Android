package com.b05studio.boxstore.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.util.BaseUtil;
import com.b05studio.boxstore.view.fragment.CategoryFragment;
import com.b05studio.boxstore.view.fragment.HomeFragment;
import com.b05studio.boxstore.view.fragment.MypageFragment;
import com.b05studio.boxstore.view.fragment.NotificationFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by joyeongje on 2017. 9. 28..
 */

public class BoxstoreMenuActivity extends AppCompatActivity {

//    @BindView(R.id.app_bar_layout)
//    AppBarLayout appBarLayout;

    @BindView(R.id.toolbarLayout)
    ConstraintLayout toolbarLayout;


    @OnClick(R.id.moveToKeywordActivityBtn)
    public void moveToKeyword() {
        // 따로 받을건 없는걸로
        Intent intent = new Intent(BoxstoreMenuActivity.this, KeywordActivity.class);
        startActivity(intent);
    }

    @BindView(R.id.searchEditText)
    EditText searchKeywordEditText;

//    @OnClick(R.id.moveToSearchViewButton)
//    public void moveToSearch() {
//
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        searchKeywordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        Intent intent = new Intent(BoxstoreMenuActivity.this,SearchActivity.class);
                        String searchQuery = searchKeywordEditText.getText().toString();
                        intent.putExtra("searchQuery",searchQuery);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }

            }
        });


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment selectedFragment = null;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (tabId) {
                    case R.id.navigation_home:
                        toolbarLayout.setVisibility(View.VISIBLE);
                        selectedFragment = HomeFragment.newInstance();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        break;
                    case R.id.navigation_category:
                        toolbarLayout.setVisibility(View.GONE);
                        selectedFragment = CategoryFragment.newInstance();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        break;
                    case R.id.navigation_sell:
                        BaseUtil.moveActivity(BoxstoreMenuActivity.this, SellActivity.class);
                        break;
                    case R.id.navigation_notifications:
                        toolbarLayout.setVisibility(View.GONE);
                        selectedFragment = NotificationFragment.newInstance();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        break;
                    case R.id.navigation_mypage:
                        toolbarLayout.setVisibility(View.GONE);
                        selectedFragment = MypageFragment.newInstance();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        break;
                }
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
