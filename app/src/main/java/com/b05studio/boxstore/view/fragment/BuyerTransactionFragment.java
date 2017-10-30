package com.b05studio.boxstore.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b05studio.boxstore.R;

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
    @OnClick(R.id.modifyTransactionBtn)
    public void onClickModify(){

    }
    @OnClick(R.id.compleTransactionBtn)
    public void onClickComplete(){



    }
}
