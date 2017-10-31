package com.b05studio.boxstore.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seungwoo on 2017-10-29.
 */

public class SellerTransactionFragment extends Fragment{

    @BindView(R.id.sellProductPriceEditText)
    EditText sellPriceText;
    @BindView(R.id.stationText)
    EditText stationNameText;



    private DatabaseReference mRootRef;

    private String mbuyerUID;
    private String msellerUID;
    private String mstuffID;

    private static final String ARG_PIRCE = "PRICE";
    private static final String ARG_STATION = "STATION";
    // Empty Constructor
    public SellerTransactionFragment(){

    }

    public static SellerTransactionFragment newInstance(){

        SellerTransactionFragment sellerTransactionFragment = new SellerTransactionFragment();
        return sellerTransactionFragment;
    }

    public static SellerTransactionFragment newInstance(String paramPrice, String paramStation,String paramStuffName){
        SellerTransactionFragment sellerTransactionFragment = new SellerTransactionFragment();
        Bundle args = new Bundle();
        args.putString("BuyerUID",paramPrice);
        args.putString("SellerUID",paramStation);
        args.putString("stuff_id",paramStuffName);
        sellerTransactionFragment.setArguments(args);
        return sellerTransactionFragment;
    }

    private void setUsersId() {
        if (getArguments() != null) {
            mbuyerUID = getArguments().getString("BuyerUID").toString();
            msellerUID = getArguments().getString("SellerUID").toString();
            mstuffID = getArguments().getString("stuff_id").toString();

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setUsersId();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootRef = FirebaseDatabase.getInstance().getReference();
        View view = inflater.inflate(R.layout.fragment_seller_transaction, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.completeItemStoreBtn)
    public void onClickComplete(){

        //EditText의 값들을 받아서 bundle로 전송

        String itemPrice = sellPriceText.getText().toString().trim();
        String stationName = stationNameText.getText().toString().trim();

        if(!TextUtils.isEmpty(itemPrice) && !TextUtils.isEmpty(stationName))
        {
            String current_user_ref ="messages/" + msellerUID + "/" +mbuyerUID + "/"+ mstuffID;
            String chat_user_ref = "messages/" + mbuyerUID + "/" + msellerUID + "/" + mstuffID;

            DatabaseReference user_message_push = mRootRef.child("messages")
                    .child(msellerUID).child(mbuyerUID).child(mstuffID).push();

            String push_id = user_message_push.getKey();

            Map messageMap = new HashMap();
            messageMap.put("message", BoxStoreApplication.getCurrentUser().getName());
            messageMap.put("seen",false);
            messageMap.put("type","box");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("sender",msellerUID);
            messageMap.put("price",itemPrice);
            messageMap.put("station",stationName);

            Map messageUserMap = new HashMap();
            messageUserMap.put(current_user_ref + "/" + push_id,messageMap);
            messageUserMap.put(chat_user_ref + "/" + push_id,messageMap);

            mRootRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if(databaseError != null) {
                        Log.d("CHAT_LOG", databaseError.getMessage().toString());
                    }
                }
            });
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.chat_frame_layout,ChatFragment.newInstance(mbuyerUID,msellerUID,mstuffID));
        transaction.commit();
    }

}
