package com.b05studio.boxstore.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.util.BaseUtil;
import com.b05studio.boxstore.view.fragment.CategoryFragment;
import com.b05studio.boxstore.view.fragment.HomeFragment;
import com.b05studio.boxstore.view.fragment.MypageFragment;
import com.b05studio.boxstore.view.fragment.NotificationFragment;

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
    public void moveToKeyword(){
        // 따로 받을건 없는걸로
        Intent intent = new Intent(BoxstoreMenuActivity.this,KeywordActivity.class);
        startActivity(intent);
    }

//    @OnClick(R.id.moveToSearchViewButton)
//    public void moveToSearch() {
//        Intent intent = new Intent(BoxstoreMenuActivity.this,SearchActivity.class);
//        startActivity(intent);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ButterKnife.bind(this);
//        searchView.setQueryHint("상품명, 키워드, 역 이름으로 검색하세요.");

//        final SearchViewLayout searchViewLayout = (SearchViewLayout) findViewById(R.id.search_view_container);
//        searchViewLayout.setExpandedContentSupportFragment(this, new NotificationFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.notification_badge, bottomNavigationMenuView, false);

        itemView.addView(badge);

        BottomNavigationViewHelper bottomnavigationViewHelper = new BottomNavigationViewHelper();
        bottomnavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        switch (item.getItemId()) {

                            case R.id.navigation_home:
                                toolbarLayout.setVisibility(View.VISIBLE);
                                selectedFragment = HomeFragment.newInstance();
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
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();

    }


    class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        public void removeShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
            } catch (IllegalAccessException e) {
                Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
            }
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
