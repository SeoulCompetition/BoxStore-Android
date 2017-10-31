package com.b05studio.boxstore.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seungwoo on 2017-10-29.
 */

public class BuyerTransactionFragment extends Fragment {


    // Empty Constructor
    public BuyerTransactionFragment(){

    }
    public static BuyerTransactionFragment newInstance(){
        BuyerTransactionFragment buyerTransactionFragment = new BuyerTransactionFragment();
        return buyerTransactionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buyer_transaction, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @OnClick(R.id.compleTransactionBtn)
    public void onClickComplete(){

       }
}
