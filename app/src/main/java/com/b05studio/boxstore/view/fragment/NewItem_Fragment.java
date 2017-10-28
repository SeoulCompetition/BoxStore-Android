package com.b05studio.boxstore.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Item;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by seungwoo on 2017-09-27.
 */

public class NewItem_Fragment extends Fragment {

    /*--지하철역 새로운 아이템 ->*/
    List<Item> dummyItem;
    private String TAG = "PagerFragment";
    private String title;

    public NewItem_Fragment() {

    }

    public static Fragment getInstance() {
        Fragment fragment = new NewItem_Fragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_viewpager_item, container, false);
        TextView titleTextView = (TextView) view.findViewById(R.id.tv_new_goods_1);
        //If we have the requirement of adding, removing or changing the fragment data once it has been instantiated, We will set the data explicitly in the fragment through a getter and setter.
        //Bundle bundle = getArguments();
        //title = bundle.getString(Utils.EXTRA_TITLE);
        //titleTextView.setText(bundle.getString(Utils.EXTRA_TITLE));
        //title = dummyItem.getImageTitle();
        //titleTextView.setText(title);
        ImageView item_view1 = (ImageView) view.findViewById(R.id.new_goods_1);
        ImageView item_view2 = (ImageView) view.findViewById(R.id.new_goods_2);
        ImageView item_view3 = (ImageView) view.findViewById(R.id.new_goods_3);

        //ImageLoaderUtil.downloadImage(bundle.getString(Utils.EXTRA_IMAGE_URL), imageView);
        //Glide 사용 출력
        Glide.with(getActivity())
                .load(dummyItem.get(0).getItemURL())
                .into(item_view1);

        Glide.with(getActivity())
                .load(dummyItem.get(1).getItemURL())
                .into(item_view2);

        Glide.with(getActivity())
                .load(dummyItem.get(2).getItemURL())
                .into(item_view3);
        //ImageLoaderUtil.downloadImage(dummyItem.getImageUrl(), imageView);
        Log.i(TAG, "****PagerFragment onCreateView()#" + title);
        return view;
    }

    public List<Item> getDummyItem() {
        return dummyItem;
    }
    public void setDummyItem(List<Item> dummyItem) {
        this.dummyItem = dummyItem;
    }
}
