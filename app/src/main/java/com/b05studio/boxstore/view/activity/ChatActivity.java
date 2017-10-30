package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private String sellerUId = "";
    private String buyerUId= "";
    private String sellerName = "";
    private String buyerName = "";


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

        String itemPrice = intent.getStringExtra("ProductPrice");
        String itemName = intent.getStringExtra("ProductName");
        String itemImageURL = intent.getStringExtra("ProductImage");

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
        transaction.replace(R.id.chat_frame_layout, ChatFragment.newInstance(buyerUId,sellerUId));
        transaction.commit();
    }
    //판매자만 보인다.
    @OnClick(R.id.makeDealBtn)
    public void onClick(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //bundlel로 구매자 UID 판매자 UID 전송, 파이어베이스 데이터베이스 생성에 쓰인다.
        transaction.replace(R.id.chat_frame_layout, SellerTransactionFragment.newInstance(buyerUId,sellerUId));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
