package com.b05studio.boxstore.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.view.fragment.BuyerTransactionFragment;
import com.b05studio.boxstore.view.fragment.ChatFragment;
import com.b05studio.boxstore.view.fragment.HomeFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {


    @BindView(R.id.chatTitleBar)
    TextView chattalkerName;
    @BindView(R.id.itemInfoImage)
    ImageView itemInfoImage;
    @BindView(R.id.itemInfoName)
    TextView itemInfoName;
    @BindView(R.id.itemInfoPrice)
    TextView itemInfoPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String othersideName = intent.getStringExtra("SELECTED_PEOPLE_NAME");
        String itemPrice = intent.getStringExtra("ITEM_PRICE");
        String itemImageURL = "";

        Picasso.with(ChatActivity.this).load(itemImageURL)
                .into(itemInfoImage);
        itemInfoName.setText(othersideName);
        itemInfoPrice.setText(itemPrice);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.chat_frame_layout, ChatFragment.newInstance());
        transaction.commit();
    }
    @OnClick(R.id.makeDealBtn)
    public void onClick(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.chat_frame_layout, BuyerTransactionFragment.newInstance());
        transaction.addToBackStack(null);
    }
}
