package com.b05studio.boxstore.view.fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.view.adapter.MainProductPagerAdapter;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by young on 2017-10-29.
 */

public class MainStuffFragment extends Fragment {

    @BindView(R.id.firstStuffImage)
    ImageView firstStuffImageview;

    @BindView(R.id.firstStuffTextView)
    TextView firstStuffTile;

    @BindView(R.id.firstStuffMoneyTextView)
    TextView firstStuffMoney;

    @BindView(R.id.secondStuffImage)
    ImageView secondStuffImageView;

    @BindView(R.id.secondStuffTextView)
    TextView secondStuffTextView;

    @BindView(R.id.secondStuffMoneyTextView)
    TextView secondStuffMoneyTextView;

    @BindView(R.id.thirdStuffImage)
    ImageView thirdStuffImageView;

    @BindView(R.id.thirdStuffTextView)
    TextView thirdStuffTextView;

    @BindView(R.id.thirdStuffMoneyTextView)
    TextView thirdStuffMoneyTextView;

    public static MainStuffFragment newInstance(List<Stuff> stuffs, int count) {
        MainStuffFragment fragment = new MainStuffFragment();
        return fragment;
    }

    public MainStuffFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_stuff, container, false);
        ButterKnife.bind(this, rootView);

        if (MainProductPagerAdapter.stuffs.size() == 1) {
            Picasso.with(getContext()).load(MainProductPagerAdapter.stuffs.get(0).getImageUrl().get(0)).into(firstStuffImageview);
        } else if ( MainProductPagerAdapter.stuffs.size() == 2) {
            Picasso.with(getContext()).load(MainProductPagerAdapter.stuffs.get(0).getImageUrl().get(0)).into(firstStuffImageview);
            Picasso.with(getContext()).load(MainProductPagerAdapter.stuffs.get(0).getImageUrl().get(0)).into(secondStuffImageView);
        } else if ( MainProductPagerAdapter.stuffs.size() == 3) {
            Picasso.with(getContext()).load(MainProductPagerAdapter.stuffs.get(0).getImageUrl().get(0)).into(firstStuffImageview);
            Picasso.with(getContext()).load(MainProductPagerAdapter.stuffs.get(1).getImageUrl().get(0)).into(secondStuffImageView);
            Picasso.with(getContext()).load(MainProductPagerAdapter.stuffs.get(2).getImageUrl().get(0)).into(thirdStuffImageView);
        }
        return rootView;
    }


}
