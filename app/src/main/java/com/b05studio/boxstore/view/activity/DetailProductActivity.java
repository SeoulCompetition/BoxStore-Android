package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.view.adapter.DetailProductPagerAdapter;
import com.b05studio.boxstore.view.fragment.HomeFragment;
import com.b05studio.boxstore.view.fragment.MainStuffFragment;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailProductActivity extends AppCompatActivity {


    private DatabaseReference mNotificationDatabase;
    private DatabaseReference mRootRef;

    @OnClick(R.id.detailProductScrapButton)
    public void onClickDetailProductScrap() {
        // TODO: 2017-10-30 담아두기버튼눌렀을때
    }

    @OnClick(R.id.detailProductSellButton)
    public void onClickDetailPrdouctSell() {

        String user_id = selectedStuff.getSellerId().getUid();

        DatabaseReference newNotificationref = mRootRef.child("notifications").child(user_id).push();
        String newNotificationId = newNotificationref.getKey();

        HashMap<String,String> notificationData = new HashMap<>();
        notificationData.put("stuff_id",selectedStuff.getId());
        notificationData.put("stuff_image",selectedStuff.getImageUrl().get(0).toString());
        notificationData.put("stuff_price",selectedStuff.getPrice().toString());
        notificationData.put("stuff_name",selectedStuff.getStuffName());
        notificationData.put("name",BoxStoreApplication.getCurrentUser().getName());
        notificationData.put("from",BoxStoreApplication.getCurrentUser().getuId());
        notificationData.put("type","request");
        notificationData.put("device_token",selectedStuff.getSellerId().getUserToken());
        notificationData.put("SellerUID",selectedStuff.getSellerId().getUid());
        notificationData.put("SellerName",selectedStuff.getSellerId().getName());

        Map requestMap = new HashMap();
        requestMap.put("Question_req/"+ BoxStoreApplication.getCurrentUser().getuId() + "/" + user_id + "/request_type","sent");
        requestMap.put("Question_req/"+ user_id + "/" + BoxStoreApplication.getCurrentUser().getuId() + "/request_type","received");
        requestMap.put("notifications/" + user_id + "/" + newNotificationId,notificationData);

        mRootRef.updateChildren(requestMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null) {
                    Toast.makeText(DetailProductActivity.this,"There was some error in seding request",Toast.LENGTH_SHORT).show();
                }
            }
        });



        //<-- firebase database 에 채팅창에서 거래하고 있는 물품에 대한 정보를 삽입 ->> //

        final String current_user_ref = "messages/"+ BoxStoreApplication.getCurrentUser().getuId()
                + "/"+ selectedStuff.getSellerId().getUid()  +"/"+ selectedStuff.getId();
        final String chat_user_ref = "messages/"+ selectedStuff.getSellerId().getUid()
                + "/" + BoxStoreApplication.getCurrentUser().getuId()  + "/" + selectedStuff.getId();

        /*
        DatabaseReference user_message_stuffinfo = mRootRef.child("messages")
                .child(BoxStoreApplication.getCurrentUser().getuId()).child(selectedStuff.getSellerId().getUid()).child(selectedStuff.getId()).push();

        String push_id = user_message_stuffinfo.getKey();
        */

        Map stuffMap = new HashMap();
        stuffMap.put("sellerName",selectedStuff.getSellerId().getName());
        stuffMap.put("sellerID",false);
        stuffMap.put("stuffImage",selectedStuff.getImageUrl().get(0));
        stuffMap.put("time", ServerValue.TIMESTAMP);
        stuffMap.put("stuffPrice",selectedStuff.getPrice());
        stuffMap.put("stuffInfo",selectedStuff.getStuffInfo());
        stuffMap.put("buyerName",BoxStoreApplication.getCurrentUser().getName());
        stuffMap.put("buyerUID",BoxStoreApplication.getCurrentUser().getuId());
        //판매자이름
        //판매자ID
        //판매물품 이미지
        //시간
        //판매물품 가격
        //판매물품 설명
        //구매자이름
        //구매자 UID

        Map messageUserMap = new HashMap();
        messageUserMap.put(current_user_ref + "/" + "selectedStuff",stuffMap);
        messageUserMap.put(chat_user_ref + "/" + "selectedStuff",stuffMap);


        mRootRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null) {
                    Log.d("CHAT_LOG", databaseError.getMessage().toString());
                }
            }
        });


        Intent intent = new Intent(this,ChatActivity.class);

        //상품 이미지배열 첫번째
        //상품 이름
        //상품 가격
        intent.putExtra("BuyerUID",BoxStoreApplication.getCurrentUser().getuId());
        intent.putExtra("BuyerName",BoxStoreApplication.getCurrentUser().getName());
        intent.putExtra("stuff_id",selectedStuff.getId());
        intent.putExtra("SellerName",selectedStuff.getSellerId().getName());
        intent.putExtra("SellerUID",selectedStuff.getSellerId().getUid());
        intent.putExtra("stuff_image",selectedStuff.getImageUrl().get(0).toString());
        intent.putExtra("stuff_name",selectedStuff.getStuffName().toString());
        intent.putExtra("stuff_price",selectedStuff.getPrice().toString());
        intent.putExtra("Type","Detail");

        startActivity(intent);
        // TODO: 2017-10-30 구매하기버튼눌렀을때 일단 들어간느 사람이 물건을 올린 당사자가 아니라고 가정함
        // TODO : 이후 만들어지는 ChatAcitity는 NotificationFragment에서 들어가므로 Sender와 Recipedent를 확정할 수 있다.

    }

    @OnClick(R.id.detailProductBackButton)
    public void onClickBackButton() {
        finish();
    }

    @BindView(R.id.detailProductNameTitleTextview)
    TextView detailProductNameTitleTextview;

    @BindView(R.id.detailProductViewPager)
    ViewPager detailProductViewPager;

    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;

    @BindView(R.id.detailProductNameTextview)
    TextView detailProductNameTextview;

    @BindView(R.id.detailProductPriceTextview)
    TextView detailProductPriceTextview;

    @BindView(R.id.detailProductStateImageView)
    ImageView detailProductStateImageView;

    @BindView(R.id.detailProductStateTextView)
    TextView detailProductStateTextView;

    @BindView(R.id.detailProductStateImageViewReal)
    ImageView detailProductStateImageViewReal;


    @BindView(R.id.detailPrductDescriptionTextView)
    TextView detailPrductDescriptionTextView;



    private Stuff selectedStuff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mNotificationDatabase = FirebaseDatabase.getInstance().getReference().child("notifications");

        selectedStuff = MainStuffFragment.selectedStuff;
        initProductImageViewPager();
        initProductInfomationView();
    }

    private void initProductImageViewPager() {
        DetailProductPagerAdapter detailProductPagerAdapter = new DetailProductPagerAdapter(getSupportFragmentManager(),selectedStuff.getImageUrl());
        detailProductViewPager.setAdapter(detailProductPagerAdapter);
        detailProductViewPager.setCurrentItem(0);
        pageIndicatorView.setViewPager(detailProductViewPager);
        pageIndicatorView.setSelectedColor(Color.parseColor("#4B65A7"));
        pageIndicatorView.setUnselectedColor(Color.parseColor("#F1F1F1"));
        pageIndicatorView.setAnimationType(AnimationType.DROP);
    }

    private void initProductInfomationView() {
        detailProductNameTitleTextview.setText(selectedStuff.getStuffName());
        detailProductNameTextview.setText(selectedStuff.getStuffName());
        detailProductPriceTextview.setText(String.valueOf(selectedStuff.getPrice()) +" 원");

        String productState = selectedStuff.getProductState();
        if(productState.equals("미개봉")) detailProductStateImageViewReal.setImageResource(R.drawable.ic_sell_no_open_state_check);
        else if(productState.equals("중고상품")) detailProductStateImageViewReal.setImageResource(R.drawable.ic_sell_new_state_check);
        else if(productState.equals("하자있음")) detailProductStateImageViewReal.setImageResource(R.drawable.ic_sell_not_good_state_check);
        detailProductStateTextView.setText(selectedStuff.getPostType());
        detailPrductDescriptionTextView.setText(selectedStuff.getStuffInfo());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
