package com.b05studio.boxstore.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.b05studio.boxstore.view.fragment.DetailStuffPagerFragment;
import com.b05studio.boxstore.view.fragment.MainStuffFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by young on 2017-10-30.
 */

public class DetailProductPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "DetailProductPagerAdapter";
    public List<String> detailProductImageURLlist = new ArrayList<>();

    public DetailProductPagerAdapter(FragmentManager fm,List<String> detailProductImageURLlist ) {
        super(fm);
        this.detailProductImageURLlist =detailProductImageURLlist;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailStuffPagerFragment.newInstance(detailProductImageURLlist.get(position));
    }

    @Override
    public int getCount() {
        return detailProductImageURLlist.size();
    }
}
