package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
 * Created by seungwoo on 2017-10-31.
 */

public class SellerTransactionActivity extends AppCompatActivity {

    @BindView(R.id.sellProductPriceEditText)
    EditText sellPriceText;
    @BindView(R.id.stationText)
    EditText stationNameText;

    @BindView(R.id.completeSellerView)
    View completeView;
    @BindView(R.id.storeItemBtn)
    Button storeItemBtn;
    @BindView(R.id.completeItemStoreBtn)
    Button completeBtn;

    private DatabaseReference mRootRef;

    private String mbuyerUID;
    private String msellerUID;
    private String mstuffID;

    private static final String ARG_PIRCE = "PRICE";
    private static final String ARG_STATION = "STATION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_seller_transaction);
        ButterKnife.bind(this);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        mbuyerUID = intent.getStringExtra("BuyerUID");
        msellerUID = intent.getStringExtra("SellerUID");
        mstuffID = intent.getStringExtra("stuff_id");

        sellPriceText.setText(intent.getStringExtra("Price"));
        stationNameText.setText(intent.getStringExtra("Station"));
        String step = intent.getStringExtra("step");

        if(step != null){
            if(step.equals("2")){
                completeView.setVisibility(View.VISIBLE);
                storeItemBtn.setVisibility(View.VISIBLE);
                completeBtn.setVisibility(View.GONE);
                stationNameText.setClickable(false);
                stationNameText.setFocusable(false);
                sellPriceText.setClickable(false);
                sellPriceText.setFocusable(false);
            }
        }

    }

    @OnClick(R.id.completeItemStoreBtn)
    public void onClickComplete() {

        //EditText의 값들을 받아서 bundle로 전송

        String itemPrice = sellPriceText.getText().toString().trim();
        String stationName = stationNameText.getText().toString().trim();

        if (!TextUtils.isEmpty(itemPrice) && !TextUtils.isEmpty(stationName)) {
            String current_user_ref = "messages/" + msellerUID + "/" + mbuyerUID + "/" + mstuffID;
            String chat_user_ref = "messages/" + mbuyerUID + "/" + msellerUID + "/" + mstuffID;

            DatabaseReference user_message_push = mRootRef.child("messages")
                    .child(msellerUID).child(mbuyerUID).child(mstuffID).child("chat").push();

            String push_id = user_message_push.getKey();

            Map<String, Object> messageMap = new HashMap<String, Object>();
            messageMap.put("message", BoxStoreApplication.getCurrentUser().getName());
            messageMap.put("seen", false);
            messageMap.put("type", "box");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("sender", msellerUID);
            messageMap.put("price", itemPrice);
            messageMap.put("station", stationName);
            messageMap.put("BuyerUID", mbuyerUID);
            messageMap.put("SellerUID", msellerUID);
            messageMap.put("stuff_id", mstuffID);
            messageMap.put("step", "1");

            Map<String, Object> messageUserMap = new HashMap<>();
            messageUserMap.put(current_user_ref + "/chat/" + push_id, messageMap);
            messageUserMap.put(chat_user_ref + "/chat/" + push_id, messageMap);

            mRootRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Log.d("CHAT_LOG", databaseError.getMessage().toString());
                    }
                }
            });
        }




        Intent chatIntent = new Intent(this,ChatActivity.class);
        chatIntent.putExtra("BuyerUID",mbuyerUID);
        chatIntent.putExtra("SellerUID",msellerUID);
        chatIntent.putExtra("stuff_id",mstuffID);
        chatIntent.putExtra("Type","Notification");
        startActivity(chatIntent);
    }

    @OnClick(R.id.storeItemBtn)
    public void onClickGotoLocker(){
        //TODO : 영수증 찍고 보관함 비밀 번호 입력하고 하는거 함 영제

        Intent chatIntent = new Intent(this,ChatActivity.class);
        chatIntent.putExtra("BuyerUID",mbuyerUID);
        chatIntent.putExtra("SellerUID",msellerUID);
        chatIntent.putExtra("stuff_id",mstuffID);
        chatIntent.putExtra("price",sellPriceText.getText().toString().trim());
        chatIntent.putExtra("station",stationNameText.getText().toString().trim());
        chatIntent.putExtra("Type","Bill");
        startActivity(chatIntent);

    }
}
