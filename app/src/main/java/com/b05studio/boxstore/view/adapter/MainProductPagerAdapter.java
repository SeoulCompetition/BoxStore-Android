package com.b05studio.boxstore.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.view.fragment.MainStuffFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungwoo on 2017-09-27.
 */

public class MainProductPagerAdapter extends FragmentStatePagerAdapter  {

    private static final String TAG = "FramentStatePagerAdapter";
    public List<Stuff> stuffs = new ArrayList<>();

    int pageNum;
    int productCount = 0;
    int count = 1;


    public MainProductPagerAdapter(FragmentManager fm, List<Stuff> stuffs) {
        super(fm);
        this.stuffs = stuffs;
        pageNum = stuffs.size() / 3 + 1;
    }

    @Override
    public Fragment getItem(int position) {
        productCount = count != pageNum ? 3 : stuffs.size() % 3;
        count++;

        return new MainStuffFragment(stuffs.subList(position * 3,position * 3 + productCount));
    }

    @Override
    public int getCount() {
        return stuffs.size() / 3 + 1;
    }
}
