package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.view.fragment.ChatFragment;
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
 * Created by seungwoo on 2017-10-31.
 */

public class BuyerTransactionActivity extends AppCompatActivity {

    @BindView(R.id.buyProductPriceEditText)
    EditText buyProductPrice;
    @BindView(R.id.stationText)
    EditText stationText;

    private String msellerUID;
    private String mstuffID;

    DatabaseReference mRootRef;


    @OnClick(R.id.compleTransactionBtn)
    public void onClickComplte(){

        String itemPrice = buyProductPrice.getText().toString().trim();
        String stationName = stationText.getText().toString().trim();

        String mbuyerUID = BoxStoreApplication.getCurrentUser().getuId();

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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.chat_frame_layout, ChatFragment.newInstance(mbuyerUID,msellerUID,mstuffID));
        transaction.commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.fragment_buyer_transaction);

        mRootRef = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        stationText.setText(intent.getStringExtra("Station"));
        buyProductPrice.setText(intent.getStringExtra("Price"));
        msellerUID  = intent.getStringExtra("SellerUID");
        mstuffID = intent.getStringExtra("stuff_id");
    }
}
