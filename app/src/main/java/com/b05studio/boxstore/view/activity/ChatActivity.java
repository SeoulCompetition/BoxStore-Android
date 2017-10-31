package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.util.ExtraIntent;
import com.b05studio.boxstore.view.fragment.BuyerTransactionFragment;
import com.b05studio.boxstore.view.fragment.ChatFragment;
import com.b05studio.boxstore.view.fragment.HomeFragment;
import com.b05studio.boxstore.view.fragment.SellerTransactionFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {


    @BindView(R.id.opponentName)
    TextView chattalkerName;
    @BindView(R.id.itemInfoImage)
    ImageView itemInfoImage;
    @BindView(R.id.itemInfoName)
    TextView itemInfoName;
    @BindView(R.id.itemInfoPrice)
    TextView itemInfoPrice;

    @BindView(R.id.makeDealBtn)
    TextView makeDealBtn;
    @BindView(R.id.searchCheaterBtn)
    TextView searchCheaterBtn;

    @BindView(R.id.itemInfoView)
    View itemInfo;

    private String sellerUId = "";
    private String buyerUId= "";
    private String sellerName = "";
    private String buyerName = "";
    private String stuffId;
    private String itemName;

    private String userState = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        //구매자 판매자에 따라 name 표시 바뀌게

        buyerName = intent.getStringExtra("BuyerName");
        buyerUId = intent.getStringExtra("BuyerUID");
        sellerUId = intent.getStringExtra("SellerUID");
        sellerName = intent.getStringExtra("SellerName");
        stuffId = intent.getStringExtra("stuff_id");


        if(buyerUId.equals(BoxStoreApplication.getCurrentUser().getuId())){
            chattalkerName.setText(sellerName);
            //구매자와 현재 chatAcitivyt에 들어온 사람의 UID가 같을때
            userState = "BUYER";
            searchCheaterBtn.setVisibility(View.VISIBLE);
            makeDealBtn.setVisibility(View.GONE);

        }else {
            //구매자와 지금 들어온 사람의 UID가 다를때
            chattalkerName.setText(buyerName);
            userState = "SELLER";
            makeDealBtn.setVisibility(View.VISIBLE);
            searchCheaterBtn.setVisibility(View.GONE);
        }

        String itemPrice = intent.getStringExtra("stuff_price");
        itemName = intent.getStringExtra("stuff_name");
        String itemImageURL = intent.getStringExtra("stuff_image");

        if(itemPrice == null && itemName == null && itemImageURL == null){
            itemInfo.setVisibility(View.GONE);
        }

        Picasso.with(ChatActivity.this).load(itemImageURL)
                .into(itemInfoImage);
        if(BoxStoreApplication.getCurrentUser().getName().equals(sellerName))
        {
            //지금 접속한 사람이랑 물건을 팔고자하는 사람이랑 같으니까 구매자 아이디가 툴바창이름

            itemInfoName.setText(buyerName);
        }
        else {
            //지금 접속한 사람이 판매자가 아니므로 판매자의 이름을 툴바창에
            itemInfoName.setText(sellerName);

        }
        itemInfoName.setText("'"+itemName+"'");
        itemInfoPrice.setText(itemPrice+"원");


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.chat_frame_layout, ChatFragment.newInstance(buyerUId,sellerUId,stuffId));
        transaction.commit();
    }
    //판매자만 보인다.
    @OnClick(R.id.makeDealBtn)
    public void onClickDeal(){

        Intent intent = new Intent(this,SellerTransactionActivity.class);
        intent.putExtra("BuyerUID",buyerUId);
        intent.putExtra("SellerUID",sellerUId);
        intent.putExtra("stuff_id",stuffId);
        startActivity(intent);
        /*
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //bundlel로 구매자 UID 판매자 UID 전송, 파이어베이스 데이터베이스 생성에 쓰인다.
        transaction.replace(R.id.chat_frame_layout, SellerTransactionFragment.newInstance(buyerUId,sellerUId,stuffId));
        transaction.addToBackStack(null);
        transaction.commit();
        */
    }
    @OnClick(R.id.searchCheaterBtn)
    public void onClickSearch(){
        //todo : 구매자가 사기꾼을 조회한다.
    }
}
