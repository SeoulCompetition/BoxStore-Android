package com.b05studio.boxstore.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.b05studio.boxstore.model.Item;
import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.view.fragment.MainStuffFragment;
import com.b05studio.boxstore.view.fragment.NewItem_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungwoo on 2017-09-27.
 */

public class MainProductPagerAdapter extends FragmentStatePagerAdapter  {

    private static final String TAG = "FramentStatePagerAdapter";
    public static ArrayList<Stuff> stuffs = new ArrayList<>();

    public MainProductPagerAdapter(FragmentManager fm, List<Stuff> stuffs) {
        super(fm);
        this.stuffs = (ArrayList)stuffs;
    }

    @Override
    public Fragment getItem(int position) {
        return MainStuffFragment.newInstance(stuffs.subList(position * 3,position * 3 + stuffs.size() % 3), position);
    }

    @Override
    public int getCount() {
        return stuffs.size() / 3 + 1;
    }
}
