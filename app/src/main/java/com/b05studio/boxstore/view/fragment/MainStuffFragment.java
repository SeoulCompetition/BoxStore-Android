package com.b05studio.boxstore.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.view.activity.DetailProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by young on 2017-10-29.
 */

@SuppressLint("ValidFragment")
public class MainStuffFragment extends Fragment {

    public static  Stuff selectedStuff;

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

    @OnClick({R.id.firstStuffImage, R.id.secondStuffImage, R.id.thirdStuffImage})
    public void onClickProductList(View view) {

        Intent intent = new Intent(getActivity(), DetailProductActivity.class);
        switch (view.getId()) {
            case R.id.firstStuffImage:
                if (stuffList.size() >= 1) {
                    selectedStuff =  stuffList.get(0);
                }
                break;
            case R.id.secondStuffImage:
                if (stuffList.size() >= 2) {
                    selectedStuff =  stuffList.get(1);
                }

                break;
            case R.id.thirdStuffImage:
                if (stuffList.size() == 3) {
                    selectedStuff = stuffList.get(2);
                }
                break;
        }
        startActivity(intent);
    }

    @BindView(R.id.thirdStuffTextView)
    TextView thirdStuffTextView;

    @BindView(R.id.thirdStuffMoneyTextView)
    TextView thirdStuffMoneyTextView;

    List<Stuff> stuffList = new ArrayList<>();

//    public MainStuffFragment newInstance(List<Stuff> stuffs) {
////        MainStuffFragment fragment = new MainStuffFragment();
////        return fragment;
//    }

    public MainStuffFragment() {

    }

    @SuppressLint("ValidFragment")
    public MainStuffFragment(List<Stuff> stuffs) {
        stuffList = stuffs;
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

        if (stuffList.size() == 1) {
            Picasso.with(getContext()).load(stuffList.get(0).getImageUrl().get(0)).into(firstStuffImageview);
            firstStuffTile.setText(stuffList.get(0).getStuffName());
            firstStuffMoney.setText(String.valueOf(stuffList.get(0).getPrice()) + "원");
        } else if (stuffList.size() == 2) {
            Picasso.with(getContext()).load(stuffList.get(0).getImageUrl().get(0)).into(firstStuffImageview);
            firstStuffTile.setText(stuffList.get(0).getStuffName());
            firstStuffMoney.setText(String.valueOf(stuffList.get(0).getPrice()) + "원");

            Picasso.with(getContext()).load(stuffList.get(1).getImageUrl().get(0)).into(secondStuffImageView);
            secondStuffTextView.setText(stuffList.get(1).getStuffName());
            secondStuffMoneyTextView.setText(String.valueOf(stuffList.get(1).getPrice()) + "원");
        } else if (stuffList.size() == 3) {

            Picasso.with(getContext()).load(stuffList.get(0).getImageUrl().get(0)).into(firstStuffImageview);
            firstStuffTile.setText(stuffList.get(0).getStuffName());
            firstStuffMoney.setText(String.valueOf(stuffList.get(0).getPrice()) + "원");

            Picasso.with(getContext()).load(stuffList.get(1).getImageUrl().get(0)).into(secondStuffImageView);
            secondStuffTextView.setText(stuffList.get(1).getStuffName());
            secondStuffMoneyTextView.setText(String.valueOf(stuffList.get(1).getPrice()) + "원");

            Picasso.with(getContext()).load(stuffList.get(2).getImageUrl().get(0)).into(thirdStuffImageView);
            thirdStuffTextView.setText(stuffList.get(2).getStuffName());
            thirdStuffMoneyTextView.setText(String.valueOf(stuffList.get(2).getPrice()) + "원");
        }
        return rootView;
    }


}
