package com.b05studio.boxstore.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.b05studio.boxstore.model.Item;
import com.b05studio.boxstore.view.fragment.NewItem_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungwoo on 2017-09-27.
 */

public class homeveiw1Adapter extends FragmentStatePagerAdapter {


    private static final String TAG = "FramentStatePagerAdapter";
    private ArrayList<Item> mImageItemList;
    private FragmentManager mFragmentManager;

    public homeveiw1Adapter(FragmentManager fm, ArrayList<Item> imageItemList) {
        super(fm);
        this.mImageItemList = imageItemList;
        this.mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {

        //We are doing this only for checking the total number of fragments in the fragment manager.
        List<Fragment> fragmentsList= mFragmentManager.getFragments();
        int size = 0;
        if(fragmentsList != null) {
            size = fragmentsList.size();
        }
        List<Item> dummyItemList  = new ArrayList<Item>();
             if(3*position < getCount()) {
                 Item dummyItem = mImageItemList.get(3 * position);
            dummyItemList.add(dummyItem);
             }
            else{
                 Item dummyItem = new Item("No","title","price");
            dummyItemList.add(dummyItem);
             }
            if((3*position)+1 < getCount()){
                Item dummyItem1 = mImageItemList.get((3*position)+1);
                dummyItemList.add(dummyItem1);
            }
            else{
                Item dummyItem1 = new Item("No","title","price");
                dummyItemList.add(dummyItem1);
            }
            if((3*position) +2 < getCount()) {
                Item dummyItem2 = mImageItemList.get((3*position) + 2);
                dummyItemList.add(dummyItem2);
            }
            else{
                Item dummyItem2 = new Item("No","title","price");
                dummyItemList.add(dummyItem2);
            }
            position++;
            position++;
        //Create a new instance of the fragment and return it.
        NewItem_Fragment sampleFragment = (NewItem_Fragment) NewItem_Fragment.getInstance(/*dummyItem.getImageUrl(), dummyItem.getImageTitle()*/);
        //We will not pass the data through bundle because it will not gets updated by calling notifyDataSetChanged()  method. We will do it through getter and setter.
        sampleFragment.setDummyItem(dummyItemList);
        return sampleFragment;
    }

    @Override
    public int getItemPosition(Object object) {

        List<Fragment> fragmentsList= mFragmentManager.getFragments();
        NewItem_Fragment fragment = (NewItem_Fragment)object;
        List<Item> dummyItem = fragment.getDummyItem();
        int position = mImageItemList.indexOf(dummyItem.get(0));

        /*_____________________________________________*/
        /*Only for logging purpose*/
        int size = fragmentsList.size();
        Item dummyItemNew = null;
        if(position>=0){
            dummyItemNew = mImageItemList.get(position);
        }
        /*_____________________________________________*/


        if (position >= 0) {
            // The current data matches the data in this active fragment, so let it be as it is.
            return position;
        } else {
            // Returning POSITION_NONE means the current data does not matches the data this fragment is showing right now.  Returning POSITION_NONE constant will force the fragment to redraw its view layout all over again and show new data.
            return POSITION_NONE;
        }
    }

    @Override
    public int getCount() {
        return mImageItemList.size();
    }
}
